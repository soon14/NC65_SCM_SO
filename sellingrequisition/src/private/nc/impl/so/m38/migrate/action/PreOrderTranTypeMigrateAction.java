package nc.impl.so.m38.migrate.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DBTool;
import nc.impl.so.m38.migrate.action.pub.MigrateBillTypeAction;
import nc.impl.so.m38.migrate.action.pub.QueryBillTypeVOAction;
import nc.impl.so.m38.migrate.constant.OPC_PreData;
import nc.impl.so.m38.migrate.vo.PreOrderBilltypeVO;
import nc.itf.opc.mc1trantype.ISaveMc1TranType;
import nc.vo.opc.mc1trantype.entity.Mc1TranTypeVO;
import nc.vo.opc.mc1trantype.enumeration.SourceType;
import nc.vo.opc.mc3trantype.enumeration.SaleMode;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;

public class PreOrderTranTypeMigrateAction {

  
  /**
   * 迁移时需要从bd_billtype中复制交易类型的记录，重新插入到bd_billtype中，所以需要
   * 生成新的pk_billtypeid,这里的oldNewTrantypeIdMap为就的pk_billtypeid与
   * 新生成pk_billtypeid之间的映射关系
   */
  private Map<String, String> oldNewTrantypeIdMap;

  /**
   * 预订单交易类型迁方法入口
   */
  public void migrate(Map<String, String> oldNewTrantypeIdMap)
      throws BusinessException {
    // 获取m38trantype
    M38TranTypeVO[] m38trantypeVOs = readyMigData(oldNewTrantypeIdMap);
    // 获取bd_billtype
    Map<String, BilltypeVO> billtypeVOs = getBillTypeVOById(m38trantypeVOs);
    // 迁移bd_billtype
    migrateBillType(billtypeVOs);
    // 迁移m38trantype
    migrateTrantype(billtypeVOs, m38trantypeVOs);
  }

  private void migrateBillType(Map<String, BilltypeVO> billtypeVOs) {
    // 将bd_billtype表中销售预订单交易类型数据转化为EC预订单时，其中许多属性的值以ECC1的为模板
    String ecc1_id = OPC_PreData.ECC1_ID;
    String tranTypeCodePrefix = OPC_PreData.ECC1_CODE;
    MigrateBillTypeAction action = new MigrateBillTypeAction();
    action.migrateBilltype(billtypeVOs, ecc1_id, tranTypeCodePrefix, this.oldNewTrantypeIdMap);
  }

  private void migrateTrantype(Map<String, BilltypeVO> billtypeVOs,
      M38TranTypeVO[] m38trantypeVOs) {
    List<Mc1TranTypeVO> mc1trantypeVOs = new ArrayList<Mc1TranTypeVO>();
    for (int i = 0; i < m38trantypeVOs.length; i++) {
      Mc1TranTypeVO newVO = new Mc1TranTypeVO();

      // 对两个VO中共同的字段用for循环来赋值(其中vtrantypecode字段后面会单独重新赋值)
      String[] fields = m38trantypeVOs[i].getAttributeNames();
      for (String f : fields) {
        newVO.setAttributeValue(f, m38trantypeVOs[i].getAttributeValue(f));
      }
      
      // 对于EC预订单交易类型比销售预订单交易类型多出来的字段，挨个进行赋值
      newVO.setAutoapprove(UFBoolean.FALSE);
      newVO.setAutoarrange(UFBoolean.FALSE);
      newVO.setBcheckcredit(UFBoolean.FALSE);
      newVO.setBissueportal(UFBoolean.FALSE);
      newVO.setFsalemode(SaleMode.MODE_COMMONRETURNCHANGE.getIntegerValue()); //普通+退换货（5）
      newVO.setFsourcetype(SourceType.SELF.getIntegerValue()); //物料
      newVO.setIdisableday(3); //失效天数
      newVO.setCtrantypeid(this.oldNewTrantypeIdMap.get(newVO.getCtrantypeid()));
      
      //对电子销售的trantypecode字段进行赋值，由于m38trantype表没有交易类型code，所以该code取自bd_billtype表中
      String m38trantypeCode = billtypeVOs.get(m38trantypeVOs[i].getCtrantypeid()).getPk_billtypecode();
      String mc1trantypeCode = new StringBuilder(OPC_PreData.ECC1_CODE).append("-")
              .append(m38trantypeCode).toString();
      newVO.setVtrantypecode(mc1trantypeCode);
      mc1trantypeVOs.add(newVO);
    }

    // 写入数据:调用EC提供的保存接口
    try {
      NCLocator.getInstance().lookup(ISaveMc1TranType.class)
          .saveMc1TranTypeVO(mc1trantypeVOs.toArray(new Mc1TranTypeVO[0]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 获取所有的预订单交易类型, 并且进行初始化全局变量oldNewTrantypeIdMap
   * @author liylr 2015-01-07 13:47
   * @return
   */
  private M38TranTypeVO[] readyMigData(Map<String, String> oldNewTrantypeIdMap) {
    VOQuery<M38TranTypeVO> srv =
        new VOQuery<M38TranTypeVO>(M38TranTypeVO.class);
    M38TranTypeVO[] m38trantypeVOs = srv.query("", null);

    int len = m38trantypeVOs.length;
    DBTool tool = new DBTool();
    String[] ids = tool.getOIDs(len);
    for (int i = 0; i < len; i++) {
      oldNewTrantypeIdMap.put(m38trantypeVOs[i].getCtrantypeid(), ids[i]);
    }
    this.oldNewTrantypeIdMap = oldNewTrantypeIdMap;

    return m38trantypeVOs;
  }

  /**
   * 根据ID获取对应的单据类型
   * 此处不使用VOQuery是因为VOQuery会自动在查询条件中添加dr=0,而数据库中大多为dr=null
   * 使用DataAccessUtil需要自己组装VO对象，因此这里使用dao的方式来直接返回VO
   * 
   * @author liylr 2015-03-12 19:20
   * @param pk_billtypeids 交易类型ids
   * @return 返回交易类型id与其VO的映射关系
   */
  private Map<String, BilltypeVO> getBillTypeVOById(
      M38TranTypeVO[] m38trantypeVOs) {
    Set<String> pk_trantypeids = new HashSet<String>();
    for (int i = 0; i < m38trantypeVOs.length; i++) {
      pk_trantypeids.add(m38trantypeVOs[i].getCtrantypeid());
    }
    pk_trantypeids.add(OPC_PreData.ECC1_ID);
    QueryBillTypeVOAction action = new QueryBillTypeVOAction();
    List<PreOrderBilltypeVO> list = action.queryBilltypeVOByIds(pk_trantypeids);

    Map<String, BilltypeVO> map = new HashMap<String, BilltypeVO>();
    for (BilltypeVO vo : list) {
      map.put(vo.getPk_billtypeid(), vo);
    }
    return map;
  }
}
