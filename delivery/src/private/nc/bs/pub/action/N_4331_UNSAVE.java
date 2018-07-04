package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m4331.maintain.DeliveryUnSendApproveBP;
import nc.bs.so.m4331.maintain.rule.send.CheckUnSendEnableRule;
import nc.bs.so.m4331.maintain.rule.send.SetUnSendValueRule;
import nc.bs.so.m4331.plugin.BP4331PlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * 收回脚本类
 * 
 * @since 6.0
 * @version 2011-2-22 上午10:41:12
 * @author 么贵敬
 */
public class N_4331_UNSAVE extends AbstractPfAction<DeliveryVO> {
  /**
   * N_4331_UNSEND 的构造子
   */
  public N_4331_UNSAVE() {
    super();
  }

  @Override
  protected CompareAroundProcesser<DeliveryVO> getCompareAroundProcesserWithRules(
      Object userObj) {
    CompareAroundProcesser<DeliveryVO> processor =
        new CompareAroundProcesser<DeliveryVO>(BP4331PlugInPoint.UnSendBP);
    // TODO 在此处添加审核前规则
    this.addRule(processor);
    // TODO 在此处添加审核后规则
    return processor;
  }

  @Override
  protected DeliveryVO[] processBP(Object userObj, DeliveryVO[] clientFullVOs,
      DeliveryVO[] originBills) {
    DeliveryVO[] bills =
        new DeliveryUnSendApproveBP().unSend(clientFullVOs, originBills);
    return bills;
  }

  private void addRule(CompareAroundProcesser<DeliveryVO> processer) {
    // 校验是否允许收回
    IRule<DeliveryVO> rule = new CheckUnSendEnableRule();
    processer.addBeforeRule(rule);
    // 设置收回后相关字段值
    rule = new SetUnSendValueRule();
    processer.addBeforeRule(rule);

  }
}
