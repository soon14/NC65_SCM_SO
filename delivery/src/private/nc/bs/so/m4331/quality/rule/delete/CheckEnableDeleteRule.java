package nc.bs.so.m4331.quality.rule.delete;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryViewVO;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ic.m4c.I4CQueryPubService;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;

/**
 * @description
 * 销售发货单质检信息删除前校验能否删除
 * @scene
 * 销售发货单质检信息删除前
 * @param
 * 无
 */
public class CheckEnableDeleteRule {

  public void checkDelete(DeliveryViewVO[] views, boolean isCheck) {
    if(!SysInitGroupQuery.isICEnabled()) {
  	  return;
  	}
    // 检查是否存在下游单据
    this.existBill(views, isCheck);
  }

  /*
   * 检查发货单是否存在下游单据，如果存在不能弃审
   */
  private void existBill(DeliveryViewVO[] views, boolean isCheck) {
    String[] cdeliveryids = new String[views.length];
    for (int i = 0; i < views.length; i++) {
      cdeliveryids[i] = views[i].getItem().getCdeliverybid();// .getHead().getCdeliveryid();
    }
    // 检查是否存在下游单据
    I4CQueryPubService icquerysrv =
        NCLocator.getInstance().lookup(I4CQueryPubService.class);
    try {
      Map<String, UFBoolean> hmExit =
          icquerysrv.existBill(cdeliveryids, false,
              SOBillType.Delivery.getCode());
      for (String key : cdeliveryids) {
        if (hmExit.get(key).booleanValue()) {
          if (!isCheck) {
            ExceptionUtils
                .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0129")/*当前质检报告对应的发货单行已经生成下游单据，不可报检。*/);
          }
          else {
            ExceptionUtils
                .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                    .getNCLangRes().getStrByID("4006002_0", "04006002-0054")/*@res
                                                                            "当前发货单已经生成下游单据，不可进行报检。"*/);
          }
          // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0054")/*@res
          // "当前发货单已经生成下游单据，不可进行报检。"*/);
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
