package nc.bs.so.m4331.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 销售发货单删除前校验能否删除
 * @scene
 * 销售发货单删除前
 * @param
 * 无
 */
public class CheckEnableDeleteRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {

    for (DeliveryVO vo : vos) {

      /*String cauditorid = vo.getParentVO().getApprover();
      if (!VOChecker.isEmpty(cauditorid)) {
        // 审批中状态，审批人为空时可以删除。所以审批人非空一定不可删除。
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0038")@res "已审核发货单据不可删除。");
      }*/
      Integer fstatusflag = vo.getParentVO().getFstatusflag();
      if (!BillStatus.FREE.equalsValue(fstatusflag)
          && !BillStatus.AUDITING.equalsValue(fstatusflag)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0039")/*@res "当前发货单据状态不可删除。"*/);
      }
    }

  }

}