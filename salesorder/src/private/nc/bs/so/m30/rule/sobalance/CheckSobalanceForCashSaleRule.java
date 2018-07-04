package nc.bs.so.m30.rule.sobalance;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * 收款核销关系数据检查规则
 * 1、不允许删除已进行财务核销的记录
 */
public class CheckSobalanceForCashSaleRule implements IRule<SoBalanceVO> {
  public CheckSobalanceForCashSaleRule() {
    //
  }

  @Override
  public void process(SoBalanceVO[] vos) {
    for (SoBalanceVO bill : vos) {
      this.checkNorigaccbalmny(bill);
    }
  }

  private void checkNorigaccbalmny(SoBalanceVO bill) {
    // SoBalanceHVO headvo = bill.getParentVO();
    SoBalanceBVO[] bodyvos = bill.getChildrenVO();
    for (SoBalanceBVO bodyvo : bodyvos) {
      UFDouble norigaccbalmny = bodyvo.getNorigaccbalmny();
      if (!((norigaccbalmny == null)
          || MathTool.isZero(norigaccbalmny))) {
        ExceptionUtils.wrappBusinessException(
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0125")/*@res "不允许删除已财务核销的订单收款关系。"*/);
      }
    }
  }

}