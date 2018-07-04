package nc.impl.so.m32.action;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.bs.pub.action.N_32_APPROVE;
import nc.bs.so.m32.plugin.Action32PlugInPoint;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.so.m32.action.rule.approve.BusiLog;
import nc.impl.so.m32.action.rule.approve.CheckAppEnableRule;
import nc.impl.so.m32.action.rule.approve.PushSquareRule;
import nc.impl.so.m32.action.rule.approve.ReWriteArsubAppRule;

/**
 * 销售发票审批action
 * 
 * @since 6.3
 * @version 2012-12-10 下午01:31:15
 * @author yaogj
 */
public class ApproveSaleInvoiceAction {

  /**
   * 方法功能描述：销售发票审批操作实现。
   * <p>
   * <b>参数说明</b>
   * 
   * @param script
   * @return <p>
   * @author 冯加滨
   * @time 2010-1-22 上午11:02:45
   */
  public Object approve(N_32_APPROVE script) {
    Object ret = null;
    try {
      // Object inCurObject = script.getPfParameterVO().m_preValueVo;
      //
      // SaleInvoiceVO[] inCurVOs = new SaleInvoiceVO[] {
      // (SaleInvoiceVO) inCurObject
      // };
      // m_preValueVo 在通过某些调用的时候没有数据
      SaleInvoiceVO[] inCurVOs =
          (SaleInvoiceVO[]) script.getPfParameterVO().m_preValueVos;

      AroundProcesser<SaleInvoiceVO> processer =
          new AroundProcesser<SaleInvoiceVO>(Action32PlugInPoint.ApproveAction);

      this.addBeforeRule(processer);
      TimeLog.logStart();
      processer.before(inCurVOs);
      TimeLog.info("调用审批流前执行业务规则"); /*-=notranslate=-*/

      TimeLog.logStart();
      ret = script.procActionFlow(script.getPfParameterVO());
      TimeLog.info("走审批工作流处理，此处不允许进行修改"); /*-=notranslate=-*/
      if (null == ret) {
        ret = this.queryNewVO(inCurVOs);

        this.addAfterRule(processer);
        TimeLog.logStart();
        processer.after((SaleInvoiceVO[]) ret);
        TimeLog.info("调用审批流后执行业务规则"); /*-=notranslate=-*/
      }

    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return ret;
  }

  /**
   * 方法功能描述：添加销售发票调用审批流后业务规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @author 冯加滨
   * @time 2010-4-20 下午04:35:15
   */
  private void addAfterRule(AroundProcesser<SaleInvoiceVO> processer) {
    // 回写销售费用单
    IRule<SaleInvoiceVO> rule = new ReWriteArsubAppRule();
    processer.addAfterRule(rule);
    // 推式结算
    rule = new PushSquareRule();
    processer.addAfterRule(rule);

  }

  /**
   * 方法功能描述：添加销售发票调用审批流前业务规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @author 冯加滨
   * @time 2010-1-22 上午08:57:00
   */
  private void addBeforeRule(AroundProcesser<SaleInvoiceVO> processer) {

    // 检查销售发票是否可审核
    IRule<SaleInvoiceVO> rule = new CheckAppEnableRule();
    processer.addBeforeRule(rule);
    // 业务日志
    rule = new BusiLog();
    processer.addBeforeRule(rule);

  }

  /**
   * 方法功能描述：查询审核后发票VO。
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
