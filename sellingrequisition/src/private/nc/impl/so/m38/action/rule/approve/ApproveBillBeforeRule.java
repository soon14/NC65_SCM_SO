package nc.impl.so.m38.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * 检查预订单当前状态是否可审核
 * @scene
 * 销售预订单审批前
 * @param
 * 无
 */
public class ApproveBillBeforeRule implements IRule<PreOrderVO> {

  @Override
  public void process(PreOrderVO[] vos) {
    for (PreOrderVO prevo : vos) {
      if (!(BillStatus.FREE.equalsValue(prevo.getParentVO().getFstatusflag())
          || BillStatus.AUDITING
            .equalsValue(prevo.getParentVO().getFstatusflag()))) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0024")/*@res "当前预订单单据状态，不可进行审批。"*/);
      }
    }
  }

}