package nc.impl.so.m4331.action.maintain.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * 销售发货单审批前检查单据是否可以审批
 * @scene
 * 销售发货单审批前
 * @param
 * 无
 */
public class CheckEnableApproveRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    for (DeliveryVO vo : vos) {
      DeliveryHVO hvo = vo.getParentVO();
      Integer status = hvo.getFstatusflag();
      boolean expr1 = BillStatus.FREE.equalsValue(status);
      boolean expr2 = BillStatus.AUDITING.equalsValue(status);
      // 自由、审批中状态单据允许审核
      if (!(expr1 || expr2)) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006002_0", "04006002-0063")/*@res "当前发货单据状态，不可进行审批。"*/);
      }
    }
  }
}
