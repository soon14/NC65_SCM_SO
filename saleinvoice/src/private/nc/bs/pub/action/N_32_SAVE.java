package nc.bs.pub.action;

import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.so.m32.maintain.CommitSaleInvoiceBP;
import nc.bs.so.m32.plugin.BP32PlugInPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m32.action.rule.commit.CheckCommitEnableRule;
import nc.impl.so.m32.action.rule.commit.SetCommitStatusRule;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票提交脚本
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-5-31 上午09:35:21
 */
public class N_32_SAVE extends AbstractPfAction<SaleInvoiceVO> {

  /**
   * N_32_SAVE 的构造子
   */
  public N_32_SAVE() {
    super();
  }

  @Override
  protected CompareAroundProcesser<SaleInvoiceVO> getCompareAroundProcesserWithRules(
      Object userObj) {
    CompareAroundProcesser<SaleInvoiceVO> processor =
        new CompareAroundProcesser<SaleInvoiceVO>(BP32PlugInPoint.SendAction);
    // TODO 在此处添加审核前规则
    this.addBeforeRule(processor);
    // TODO 在此处添加审核后规则
    return processor;
  }

  @Override
  protected SaleInvoiceVO[] processBP(Object userObj,
      SaleInvoiceVO[] clientFullVOs, SaleInvoiceVO[] originBills) {
    CommitSaleInvoiceBP bp = new CommitSaleInvoiceBP();
    return bp.sendApprove(clientFullVOs, originBills);
  }

  private void addBeforeRule(CompareAroundProcesser<SaleInvoiceVO> processer) {
    processer.addBeforeRule(new CheckCommitEnableRule());

    processer.addBeforeRule(new SetCommitStatusRule());
  }

}
