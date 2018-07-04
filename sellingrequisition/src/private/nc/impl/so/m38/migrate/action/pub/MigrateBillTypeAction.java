package nc.impl.so.m38.migrate.action.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uif.pub.IUifService;
import nc.uif.pub.exception.UifException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.pub.util.ListUtil;

/**
 * 将bd_billtype表中销售预订单交易类型数据转化为EC预订单
 * billtypeVOs中有一条为来自EC预订单的数据，其余为销售预订单的数据，
 * 销售预订单迁移时要以来自EC预订单的那条数据作为输入模板
 * @since 6.36
 * @version 2015-05-23
 * @author liylr
 *
 */
public class MigrateBillTypeAction {
  public void migrateBilltype(Map<String, BilltypeVO> billtypeVOs,
      String ecc_pk, String prefix, Map<String, String> oldNewTrantypeIdMap) {
    BilltypeVO ec_billTypeVO = billtypeVOs.get(ecc_pk);
    List<BilltypeVO> newBillTypeVOs = new ArrayList<BilltypeVO>();
    for (String pk : billtypeVOs.keySet()) {
      if (pk.equals(ecc_pk))
        continue;
      BilltypeVO newVO = (BilltypeVO) billtypeVOs.get(pk).clone();
      // 将m38trantype与opc_trantype之间有差异的billtypeVO字段进行重新调整，ecc_pk对应的billtypeVO作为调整的模板
      for (String field : this.MIGFIELDS) {
        newVO.setAttributeValue(field,
            ec_billTypeVO.getAttributeValue(field));
      }
      newVO.setParentbilltype(prefix);
      newVO.setPk_billtypeid(oldNewTrantypeIdMap.get(newVO.getPk_billtypeid()));
      newVO.setPk_billtypecode(new StringBuffer(prefix).append("-")
          .append(newVO.getPk_billtypecode()).toString());
      newVO.setIsLock(UFBoolean.FALSE);
      newBillTypeVOs.add(newVO);
    }

    // 保存转化后的交易类型（bd_billtype表）
    if (newBillTypeVOs.size() > 0) {
      try {
        NCLocator.getInstance().lookup(IUifService.class)
            .insertAry(ListUtil.toArray(newBillTypeVOs));
      }
      catch (UifException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  public final String[] MIGFIELDS = new String[] {
    "checkclassname", "classname", "component", "forwardbilltype",
    "isapprovebill", "isEditableProperty", "isEnableButton", "isroot",
    "ncbrcode", "nodecode", "referclassname", "systemcode",
    "transtype_class", "wherestring"
  };
}
