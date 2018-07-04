package nc.impl.so.m30.sobalance.maintain;

import nc.bs.so.m30.rule.maintainprocess.NullRule;
//import nc.bs.so.m30.rule.sobalance.CheckSobalanceForCashSaleRule;
//import nc.bs.so.m30.rule.sobalance.SynSaleorderRule;
//import nc.bs.so.m30.rule.sobalance.arengross.RewriteD2WhenDeleteRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

public class DeleteBPForCashSale extends AbstractDeleteBP {

  @Override
  protected void addBeforeRule(AroundProcesser<SoBalanceVO> processer) {
    IRule<SoBalanceVO> rule = null;

    rule = new NullRule<SoBalanceVO>();
    processer.addBeforeRule(rule);
    
//    rule = new CheckSobalanceForCashSaleRule();
//    processer.addBeforeRule(rule);

  }

  @Override
  protected void addAfterRule(AroundProcesser<SoBalanceVO> processer) {
//    IRule<SoBalanceVO> rule = null;

//    rule = new SynSaleorderRule();
//    processer.addAfterRule(rule);

//    rule = new RewriteD2WhenDeleteRule();
//    processer.addAfterRule(rule);
  }

}
