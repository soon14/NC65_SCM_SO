package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.rule.approve.CheckExistWorkflowRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m30.action.main.CommitSaleOrderAction;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 
 * 送审动作脚本
 * 
 * @author 平台脚本生成
 * @since 6.0
 */
public class N_30_SAVE extends AbstractPfAction<SaleOrderVO> {
  /**
   * 构造方法
   */
  public N_30_SAVE() {
    super();
  }

  @Override
  protected CompareAroundProcesser<SaleOrderVO> getCompareAroundProcesserWithRules(
      Object userObj) {
    CompareAroundProcesser<SaleOrderVO> processor =
        new CompareAroundProcesser<SaleOrderVO>(BP30PlugInPoint.SendBP);
    this.addBeforeRule(processor);
    return processor;
  }

  @Override
  protected SaleOrderVO[] processBP(Object userObj,
      SaleOrderVO[] clientFullVOs, SaleOrderVO[] originBills) {
    CommitSaleOrderAction action = new CommitSaleOrderAction();
    return action.sendApprove(clientFullVOs, originBills);
  }

  private void addBeforeRule(CompareAroundProcesser<SaleOrderVO> processor) {
    // 校验销售订单是否（有审批流）允许提交
    IRule<SaleOrderVO> rule = new CheckExistWorkflowRule();
    processor.addBeforeRule(rule);
  }
}
