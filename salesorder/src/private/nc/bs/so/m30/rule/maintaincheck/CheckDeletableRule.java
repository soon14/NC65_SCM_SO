package nc.bs.so.m30.rule.maintaincheck;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 销售订单删除前数据合法性校验
 * @scene 
 * 销售订单删除保存前
 * @param 
 * 无
 * @author zhangcheng
 *
 */
public class CheckDeletableRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {

    for (SaleOrderVO saleordervo : vos) {

      // 检查销售订单状态是否可被删除
      this.checkEnableDeleteByStatus(saleordervo);
    }

  }

  /**
   * 检查销售订单状态是否可被删除
   *
   * @param svo
   */
  private void checkEnableDeleteByStatus(SaleOrderVO svo) {

   /* // 已经审批，不能删除
    String cauditorid = svo.getParentVO().getApprover();
    if (!VOChecker.isEmpty(cauditorid)) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0086")@res "已审核销售订单不能删除。");
    }*/

    // 只有自由状态或者审批中状态，审批人为空时的销售订单可以删除
    Integer fstatusflag = svo.getParentVO().getFstatusflag();
    if (!BillStatus.FREE.equalsValue(fstatusflag)
        && !BillStatus.AUDITING.equalsValue(fstatusflag)) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0087")/*@res "当前销售订单不可删除。"*/);
    }

  }
}