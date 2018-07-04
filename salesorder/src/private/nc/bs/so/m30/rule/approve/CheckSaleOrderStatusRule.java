package nc.bs.so.m30.rule.approve;

import java.util.ArrayList;
import java.util.List;

import nc.vo.so.m30.util.Transfer30and30RVOTool;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.pub.util.ArrayUtil;
import nc.vo.so.pub.util.ListUtil;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * 校验销售订未修订前销售订单单据状态，防止修订审批时，销售订单被弃审
 * @scene
 * 审批前
 * @param
 * 无
 *
 * @since 6.3
 * @version 2015-1-5 上午9:40:34
 * @author wangshu6
 */
public class CheckSaleOrderStatusRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    Transfer30and30RVOTool trans = new Transfer30and30RVOTool();
    SaleOrderHistoryVO[] bills = trans.transfer30TOOrderhisVO(vos);
    try {
      String[] fstatusflags = query30BillStatusBeforeRevise(bills);
      if(!ArrayUtil.isEmpty(fstatusflags)){
        for(String fstatusflag : fstatusflags){
          if (!BillStatus.AUDIT.equalsValue(fstatusflag)) {
            ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0498")/*当前操作的销售订单已被弃审，不能审批当前修订版本！*/);
          }
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    
  }
  
  /**
   * 根据销售订单修订单据查询对应销售订单单据
   * 
   * @param bills 销售订单修订bills
   * @return saleorderbills 销售订单bills 
   * @throws BusinessException
   */
  public String[] query30BillStatusBeforeRevise(SaleOrderHistoryVO[] bills)
      throws BusinessException {
    if (ArrayUtil.isEmpty(bills)) {
      return null;
    }
    String[] ids = getOrderHistoryPKs(bills);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select fstatusflag ");
    sql.append("from so_saleorder where ");
    sql.append(SaleOrderHVO.CSALEORDERID, ids);
    sql.append(" and dr = 0");

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet set = utils.query(sql.toString());
    if (set.size() == 0) {
      return null;
    }
    return set.toOneDimensionStringArray();
  }
  /**
   * 获取销售订单修订主键
   * 
   * @param bills 销售订单修订vo
   * @return 销售订单修订主键
   */
  private String[] getOrderHistoryPKs(SaleOrderHistoryVO[] bills) {

    List<String> list = new ArrayList<String>();

    for (SaleOrderHistoryVO bill : bills) {
      String corderhistoryID = bill.getParentVO().getCorderhistoryid();
      list.add(corderhistoryID);
    }
    return ListUtil.toArray(list);
  }
}
