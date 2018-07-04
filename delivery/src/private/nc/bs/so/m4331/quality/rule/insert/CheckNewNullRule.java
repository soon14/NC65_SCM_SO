package nc.bs.so.m4331.quality.rule.insert;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 销售发货单质检信息保存前校验非空数据
 * @scene
 * 销售发货单质检信息保存前
 * @param
 * 无
 */
public class CheckNewNullRule {
  public void process(DeliveryCheckVO[] vos) {
    if (VOChecker.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0055")/*@res "质检审核回写发货单不能为空。"*/);
    }
    for (DeliveryCheckVO vo : vos) {
      if (VOChecker.isEmpty(vo.getCdeliverybid())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0056")/*@res "质检单审核回写发货单，发货单表体id不能为空。"*/);
      }
      if (VOChecker.isEmpty(vo.getVcheckcode())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0057")/*@res "质检单审核回写发货单，质检单号不能为空。"*/);
      }
    }
  }
}