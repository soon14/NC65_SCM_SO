/**
 * 
 */
package nc.impl.so.m30.sobalance.maintain;

import nc.bs.so.m30.rule.sobalance.AutoDeleteDataRule;
import nc.bs.so.m30.rule.sobalance.CalculateHeadSumDataRule;
import nc.bs.so.m30.rule.sobalance.CheckSaleOrderRule;
import nc.bs.so.m30.rule.sobalance.FillupRedundanceDataRule;
import nc.bs.so.m30.rule.sobalance.SynSaleorderRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * ÐÂÔö±£´æBP
 * 
 * @author gdsjw
 */
public class InsertBPForAccBalance extends AbstractInsertBP {

  @Override
  protected void addBeforeRule(AroundProcesser<SoBalanceVO> processer) {
    IRule<SoBalanceVO> rule = null;

    rule = new FillupRedundanceDataRule();
    processer.addBeforeRule(rule);

    rule = new CheckSaleOrderRule();
    processer.addBeforeRule(rule);

    rule = new CalculateHeadSumDataRule();
    processer.addBeforeRule(rule);

    rule = new AutoDeleteDataRule();
    processer.addBeforeRule(rule);

  }

  @Override
  protected void addAfterRule(AroundProcesser<SoBalanceVO> processer) {
    IRule<SoBalanceVO> rule = null;
    rule = new SynSaleorderRule();
    processer.addAfterRule(rule);
  }

}
