/**
 * 
 */
package nc.impl.so.m30.sobalance.maintain;

//import nc.bs.so.m30.rule.sobalance.AutoDeleteDataRule;
import nc.bs.so.m30.rule.sobalance.AdjustDataRule;
import nc.bs.so.m30.rule.sobalance.CalculateHeadSumDataRule;
import nc.bs.so.m30.rule.sobalance.arengross.RewriteD2WhenAddNewRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

public class InsertBPForCashSale extends AbstractInsertBP {

  @Override
  protected void addBeforeRule(AroundProcesser<SoBalanceVO> processer) {
    IRule<SoBalanceVO> rule = null;

    // rule = new FillupRedundanceDataRule();
    // processer.addBeforeRule(rule);

    // 根据本次收款金额更新订单收款核销金额
    rule = new AdjustDataRule();
    processer.addBeforeRule(rule);

    // rule = new CheckSaleOrderRule();
    // processer.addBeforeRule(rule);

    // rule = new CheckBalmnyUpdatableRule();
    // processer.addBeforeRule(rule);

    // rule = new CheckSobalanceRule();
    // processer.addBeforeRule(rule);

    // rule = new CheckOrderbalmnyRule();
    // processer.addBeforeRule(rule);

    rule = new CalculateHeadSumDataRule();
    processer.addBeforeRule(rule);

    // rule = new AutoDeleteDataRule();
    // processer.addBeforeRule(rule);

  }

  @Override
  protected void addAfterRule(AroundProcesser<SoBalanceVO> processer) {
    IRule<SoBalanceVO> rule = null;

    // rule = new SynSaleorderRule();
    // processer.addAfterRule(rule);

    rule = new RewriteD2WhenAddNewRule();
    processer.addAfterRule(rule);

  }

}
