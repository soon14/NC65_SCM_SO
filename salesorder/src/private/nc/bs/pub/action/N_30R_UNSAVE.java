package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m30.bp.SaleOrderUnSendApproveBP;
import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.rule.send.CheckUnSendEnableRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 *  销售订单修订收回
 *
 * @since 6.36
 * @version 2014-12-26 下午2:49:00
 * @author wangshu6
 */
public class N_30R_UNSAVE extends AbstractPfAction<SaleOrderVO> {
  /**
   * N_30_UNSEND 的构造子
   */
  public N_30R_UNSAVE() {
    super();
  }

  @Override
  protected CompareAroundProcesser<SaleOrderVO> getCompareAroundProcesserWithRules(
      Object userObj) {
    CompareAroundProcesser<SaleOrderVO> processor =
        new CompareAroundProcesser<SaleOrderVO>(BP30PlugInPoint.UnSendBP);
    this.addRule(processor);
    return processor;
  }

  @Override
  protected SaleOrderVO[] processBP(Object userObj,
      SaleOrderVO[] clientFullVOs, SaleOrderVO[] originBills) {
    SaleOrderUnSendApproveBP unsavebp = new SaleOrderUnSendApproveBP();
    SaleOrderVO[] bills = unsavebp.unSend(clientFullVOs, originBills);
    return bills;
  }

  private void addRule(CompareAroundProcesser<SaleOrderVO> processer) {
    // 校验销售订单是否允许收回
    IRule<SaleOrderVO> rule = new CheckUnSendEnableRule();
    processer.addBeforeRule(rule);

  }
}
