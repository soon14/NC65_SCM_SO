package nc.bs.so.m30.rule.send;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * 销售订单收回前检查单据是否可收回
 * @scene
 * 销售订单收回前
 * @param
 * 无
 * @since 6.0
 * @version 2011-2-22 上午10:58:10
 * @author 么贵敬
 */
public class CheckUnSendEnableRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO vo : vos) {
      SaleOrderHVO header = vo.getParentVO();
      // 审批中状态并且审批人为空的单据允许收回
      if (!BillStatus.AUDITING.equalsValue(header.getFstatusflag())
          || null != header.getApprover()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0116")/*@res "当前单据单据状态，不可进行收回。"*/);
      }

    }
  }

}