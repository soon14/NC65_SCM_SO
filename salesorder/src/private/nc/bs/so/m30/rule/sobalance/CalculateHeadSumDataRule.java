package nc.bs.so.m30.rule.sobalance;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * @description
 * 保存收款核销关系、本次收款金额前：1、核销关系表头【订单已收款金额】、【订单已核销金额】的计算和补全规则
 * @scene
 * 销售订单保存收款核销关系、本次收款金额前
 * @param 
 * 无
 */
public class CalculateHeadSumDataRule implements IRule<SoBalanceVO> {
  public CalculateHeadSumDataRule() {
    //
  }

  @Override
  public void process(SoBalanceVO[] vos) {
    for (SoBalanceVO bill : vos) {
      // 这个是补全VO，处理时需要区分行状态
      this.calculateheadsumdata(bill);
    }
  }

  private void calculateheadsumdata(SoBalanceVO bill) {
    SoBalanceBVO[] bodyvos = bill.getChildrenVO();
    UFDouble ntotalpaymny = UFDouble.ZERO_DBL;
    UFDouble ntotalorigbalmny = UFDouble.ZERO_DBL;
    for (SoBalanceBVO bodyvo : bodyvos) {
      int vostatus = bodyvo.getStatus();
      if (vostatus != VOStatus.DELETED) {
        UFDouble norigordbalmny = bodyvo.getNorigordbalmny();
        UFDouble norigaccbalmny = bodyvo.getNorigaccbalmny();
        ntotalpaymny = MathTool.add(ntotalpaymny, norigordbalmny);
        ntotalorigbalmny =
            MathTool.add(ntotalorigbalmny, norigaccbalmny);
      }
    }
    SoBalanceHVO headvo = bill.getParentVO();
    headvo.setNtotalpaymny(ntotalpaymny);
    headvo.setNtotalorigbalmny(ntotalorigbalmny);
    headvo.setStatus(VOStatus.UPDATED);
  }

}
