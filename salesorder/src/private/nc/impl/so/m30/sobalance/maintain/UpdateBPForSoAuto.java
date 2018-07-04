package nc.impl.so.m30.sobalance.maintain;

import nc.bs.so.m30.rule.sobalance.AutoDeleteDataRule;
import nc.bs.so.m30.rule.sobalance.CalculateHeadSumDataRule;
import nc.bs.so.m30.rule.sobalance.CheckBalmnyUpdatableRule;
import nc.bs.so.m30.rule.sobalance.CheckOrderbalmnyRule;
import nc.bs.so.m30.rule.sobalance.CheckSaleOrderRule;
import nc.bs.so.m30.rule.sobalance.CheckSobalanceRule;
import nc.bs.so.m30.rule.sobalance.FillupRedundanceDataRule;
import nc.bs.so.m30.rule.sobalance.SynSaleorderRule;
import nc.bs.so.m30.rule.sobalance.arengross.RewriteD2WhenUpdateRule;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * 修改保存动作
 * 
 * @author gdsjw
 */
public class UpdateBPForSoAuto extends AbstractUpdateBP {
  @Override
  protected void addBeforeRule(CompareAroundProcesser<SoBalanceVO> processer) {
    IRule<SoBalanceVO> rule = null;

    rule = new FillupRedundanceDataRule();
    processer.addBeforeRule(rule);

    rule = new CheckSaleOrderRule();
    processer.addBeforeRule(rule);

    rule = new CheckBalmnyUpdatableRule();
    processer.addBeforeRule(rule);

    rule = new CheckSobalanceRule();
    processer.addBeforeRule(rule);

    rule = new CheckOrderbalmnyRule();
    processer.addBeforeRule(rule);

    rule = new CalculateHeadSumDataRule();
    processer.addBeforeRule(rule);

    rule = new AutoDeleteDataRule();
    processer.addBeforeRule(rule);

  }

  @Override
  protected void addAfterRule(CompareAroundProcesser<SoBalanceVO> processer) {
    IRule<SoBalanceVO> rule = null;

    rule = new SynSaleorderRule();
    processer.addAfterRule(rule);

    ICompareRule<SoBalanceVO> comparerule = new RewriteD2WhenUpdateRule();
    processer.addAfterRule(comparerule);

  }

}
