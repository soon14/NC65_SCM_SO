package nc.bs.so.m4331.maintain.rule.insert;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 销售发货单保存前单据非空校验
 * @scene
 * 销售发货单保存前
 * @param
 * 无
 */
public class CheckNewNullRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    if (VOChecker.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0040")/*@res "新增保存单据不能为空。"*/);
    }
    for (DeliveryVO vo : vos) {
      if (VOChecker.isEmpty(vo) || VOChecker.isEmpty(vo.getChildrenVO())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0117")/*新增保存单据体不能为空*/);
      }
    }
  }
}