package nc.impl.so.m30.sobalance.maintain;

import nc.bs.so.m30.rule.sobalance.arengross.RewriteD2WhenDeleteRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * 收款核销关系删除——for销售订单触发的自动删除
 * 
 * @since 6.0
 * @version 2011-8-19 下午03:23:12
 * @author 刘志伟
 */
public class DeleteBPForSoAuto extends AbstractDeleteBP {

  @Override
  protected void addBeforeRule(AroundProcesser<SoBalanceVO> processer) {
    //
  }

  @Override
  protected void addAfterRule(AroundProcesser<SoBalanceVO> processer) {
    IRule<SoBalanceVO> rule = null;
    rule = new RewriteD2WhenDeleteRule();
    processer.addAfterRule(rule);
  }
}
