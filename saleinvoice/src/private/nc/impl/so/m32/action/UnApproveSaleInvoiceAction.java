package nc.impl.so.m32.action;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.bs.pub.action.N_32_UNAPPROVE;
import nc.bs.so.m32.plugin.Action32PlugInPoint;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.so.m32.action.rule.unapprove.BusiLog;
import nc.impl.so.m32.action.rule.unapprove.CancleSquareRule;
import nc.impl.so.m32.action.rule.unapprove.CheckConsumeRule;
import nc.impl.so.m32.action.rule.unapprove.CheckUnAppEnableRule;
import nc.impl.so.m32.action.rule.unapprove.DeleteVoucherRule;
import nc.impl.so.m32.action.rule.unapprove.ReWriteArsubUnAppRule;

/**
 * 发票弃审action
 * 
 * @since 6.0
 * @version 2011-10-27 下午12:41:35
 * @author 么贵敬
 */
public class UnApproveSaleInvoiceAction {

  /**
   * 方法功能描述：销售发票弃审操作实现。
   * <p>
   * <b>参数说明</b>
   * 
   * @param script
   * @return <p>
   * @author 冯加滨
   * @time 2010-1-22 上午11:02:45
   */
  public SaleInvoiceVO[] unapprove(N_32_UNAPPROVE script) {
    SaleInvoiceVO[] retvos = null;
    try {
      SaleInvoiceVO[] inCurVOs =
          (SaleInvoiceVO[]) script.getPfParameterVO().m_preValueVos;

      AroundProcesser<SaleInvoiceVO> processer =
          new AroundProcesser<SaleInvoiceVO>(
              Action32PlugInPoint.UnApproveAction);

      this.addBeforeRule(processer);
      TimeLog.logStart();
      processer.before(inCurVOs);
      TimeLog.info("调用审批流前执行业务规则"); /*-=notranslate=-*/

      TimeLog.logStart();
      script.procUnApproveFlow(script.getPfParameterVO());
      TimeLog.info("走审批工作流处理，此处不允许进行修改"); /*-=notranslate=-*/

      this.addAfterRule(processer);
      TimeLog.logStart();
      processer.after(inCurVOs);
      TimeLog.info("调用审批流后执行业务规则"); /*-=notranslate=-*/

      TimeLog.logStart();
      retvos = this.queryNewVO(inCurVOs);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return retvos;
  }

  /**
   * 方法功能描述：添加取消审核后业务规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @author fengjb
   * @time 2010-6-23 上午11:33:08
   */
  private void addAfterRule(AroundProcesser<SaleInvoiceVO> processer) {
    // 回写冲应收单
    IRule<SaleInvoiceVO> rule = new ReWriteArsubUnAppRule();
    processer.addAfterRule(rule);
    // 删除凭证
    rule = new DeleteVoucherRule();
    processer.addAfterRule(rule);

    // 发票审核后调用自动弃审流程
    rule = new CancleSquareRule();
    processer.addAfterRule(rule);

  }

  /**
   * 方法功能描述：添加取消审核前业务规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @author 冯加滨
   * @time 2010-1-22 上午08:57:00
   */
  private void addBeforeRule(AroundProcesser<SaleInvoiceVO> processer) {
    // 检查销售发票当前状态是否可弃审
    IRule<SaleInvoiceVO> rule = new CheckUnAppEnableRule();
    processer.addBeforeRule(rule);
    rule = new CheckConsumeRule();
    processer.addBeforeRule(rule);
    // 业务日志
    rule = new BusiLog();
    processer.addBeforeRule(rule);
  }

  /**
   * 方法功能描述：查询弃审后最新发票VO。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   * @return <p>
   * @author 冯加滨
   * @time 2010-1-22 下午02:20:47
   */
  private SaleInvoiceVO[] queryNewVO(SaleInvoiceVO[] bills) {
    int ilength = bills.length;
    String[] ids = new String[ilength];
    for (int i = 0; i < ilength; i++) {
      ids[i] = bills[i].getPrimaryKey();
    }
    BillQuery<SaleInvoiceVO> query =
        new BillQuery<SaleInvoiceVO>(SaleInvoiceVO.class);
    return query.query(ids);

  }

}
