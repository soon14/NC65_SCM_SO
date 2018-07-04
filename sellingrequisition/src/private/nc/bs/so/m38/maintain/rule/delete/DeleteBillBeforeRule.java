package nc.bs.so.m38.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 预订单删除前检查能否删除（预订单已经安排不允许删除、单据状态）
 * @scene
 * 销售预订单删除前
 * @param
 * 无
 * @author 刘志伟
 * @version v6.0
 */
public class DeleteBillBeforeRule implements IRule<PreOrderVO> {

  @Override
  public void process(PreOrderVO[] vos) {

    for (PreOrderVO vo : vos) {
      this.checkstatus(vo);
      this.checkArrangeNum(vo);
    }

  }

  /**
   * 预订单已经安排不允许删除
   *
   * @author 刘志伟
   * @version v6.0
   */
  private void checkArrangeNum(PreOrderVO vo) {
    PreOrderBVO[] bvos = vo.getChildrenVO();

    for (PreOrderBVO bvo : bvos) {
      if (MathTool.compareTo(bvo.getNarrnum(), UFDouble.ZERO_DBL) > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0018")/*@res "当前预订单已安排销售订单，不可删除！"*/);
      }
    }

  }

  private void checkstatus(PreOrderVO vo) {
    Integer fstatusflag = vo.getParentVO().getFstatusflag();
    String cauditorid = vo.getParentVO().getApprover();
    if (!BillStatus.FREE.equalsValue(fstatusflag)
        && !(BillStatus.AUDITING.equalsValue(fstatusflag) && VOChecker
            .isEmpty(cauditorid))) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0019")/*@res "当前预订单状态不可删除！"*/);
    }
  }

}