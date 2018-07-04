package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m32.plugin.Action32PlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m32.action.UnCommitSaleInvoiceAction;
import nc.impl.so.m32.action.rule.uncommit.CheckUnCommitEnableRule;
import nc.impl.so.m32.action.rule.uncommit.SetUnCommitValueRule;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * 收回脚本类
 * 
 * @since 6.0
 * @version 2011-2-22 上午10:41:12
 * @author 么贵敬
 */
public class N_32_UNSAVE extends AbstractPfAction<SaleInvoiceVO> {
  /**
   * N_32_UNCOMMIT 的构造子
   */
  public N_32_UNSAVE() {
    super();
  }

  @Override
  protected CompareAroundProcesser<SaleInvoiceVO> getCompareAroundProcesserWithRules(
      Object userObj) {
    CompareAroundProcesser<SaleInvoiceVO> processor =
        new CompareAroundProcesser<SaleInvoiceVO>(
            Action32PlugInPoint.UnSendAppAction);
    // TODO 在此处添加审核前规则
    this.addRule(processor);
    // TODO 在此处添加审核后规则
    return processor;
  }

  @Override
  protected SaleInvoiceVO[] processBP(Object userObj,
      SaleInvoiceVO[] clientFullVOs, SaleInvoiceVO[] originBills) {
    SaleInvoiceVO[] bills =
        new UnCommitSaleInvoiceAction().unSend(clientFullVOs, originBills);
    return bills;
  }

  private void addRule(CompareAroundProcesser<SaleInvoiceVO> processer) {
    // 校验销售发票是否允许收回
    IRule<SaleInvoiceVO> rule = new CheckUnCommitEnableRule();
    processer.addBeforeRule(rule);
    // 设置收回后相关字段值
    rule = new SetUnCommitValueRule();
    processer.addBeforeRule(rule);

  }
}
