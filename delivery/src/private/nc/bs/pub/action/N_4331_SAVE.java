package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m4331.plugin.Action4331PlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m4331.action.maintain.CommitDeliveryAction;
import nc.impl.so.m4331.action.maintain.rule.send.CheckExistWorkflowRule;
import nc.impl.so.m4331.action.maintain.rule.send.SetCommitStatusRule;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * 送审脚本动作
 * 
 * @since 6.0
 * @version 2011-7-13 下午07:27:33
 * @author 么贵敬
 */
public class N_4331_SAVE extends AbstractPfAction<DeliveryVO> {
  /**
   * 构造方法
   */
  public N_4331_SAVE() {
    super();
  }

  @Override
  protected CompareAroundProcesser<DeliveryVO> getCompareAroundProcesserWithRules(
      Object userObj) {
    CompareAroundProcesser<DeliveryVO> processor =
        new CompareAroundProcesser<DeliveryVO>(
            Action4331PlugInPoint.SendApproveAction);
    // TODO 在此处添加审核前规则
    this.addBeforeRule(processor);
    // TODO 在此处添加审核后规则
    return processor;
  }

  @Override
  protected DeliveryVO[] processBP(Object userObj, DeliveryVO[] clientFullVOs,
      DeliveryVO[] originBills) {
    CommitDeliveryAction action = new CommitDeliveryAction();
    return action.sendApprove(clientFullVOs, originBills);
  }

  private void addBeforeRule(CompareAroundProcesser<DeliveryVO> processor) {
    // 校验销售订单是否（有审批流）允许提交
    IRule<DeliveryVO> rule = new CheckExistWorkflowRule();
    processor.addBeforeRule(rule);

    rule = new SetCommitStatusRule();
    processor.addBeforeRule(rule);

  }
}
