package nc.bs.so.m4331.maintain.rule.send;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * 销售发货单收回操作前检查单据是否可收回
 * @scene
 * 销售发货单收回操作前
 * @param
 * 无
 * @since 6.0
 * @version 2011-2-22 上午10:58:10
 * @author 么贵敬
 */
public class CheckUnSendEnableRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    for (DeliveryVO vo : vos) {
      DeliveryHVO header = vo.getParentVO();
      // 审批中状态并且审批人为空的单据允许收回
      if (!BillStatus.AUDITING.equalsValue(header.getFstatusflag())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0042")/*@res "当前单据的单据状态，不可进行收回。"*/);
      }
      else {
        if (null != header.getApprover()) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0043")/*@res "当前单据已经有人审批通过，不可进行收回。"*/);
        }
      }

    }
  }
}