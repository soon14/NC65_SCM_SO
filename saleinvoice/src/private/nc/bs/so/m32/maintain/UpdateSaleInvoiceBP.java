package nc.bs.so.m32.maintain;

import nc.bs.so.m32.maintain.rule.insert.CheckBillCodeRule;
import nc.bs.so.m32.maintain.rule.insert.CheckBillValityRule;
import nc.bs.so.m32.maintain.rule.update.CheckBillState;
import nc.bs.so.m32.maintain.rule.update.CheckCanUpdateWhenAuditing;
import nc.bs.so.m32.maintain.rule.update.FillUpdateDefaultRule;
import nc.bs.so.m32.maintain.rule.update.RewriteBillUpdateRule;
import nc.bs.so.m32.plugin.BP32PlugInPoint;
import nc.bs.so.pub.rule.CheckApproverRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.util.SetUpdateAuditInfoRule;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * 修改保存bp
 * 
 * @since 6.3
 * @version 2012-12-21 上午09:12:51
 * @author yaogj
 */
public class UpdateSaleInvoiceBP {

  /**
   * 
   * @param bills 客户端传过来的数据
   * @param originBills 原始vo
   * @return 发票vo
   */
  public SaleInvoiceVO[] update(SaleInvoiceVO[] bills,
      SaleInvoiceVO[] originBills) {
    CompareAroundProcesser<SaleInvoiceVO> processer =
        new CompareAroundProcesser<SaleInvoiceVO>(BP32PlugInPoint.UpdateAction);
    for (SaleInvoiceVO bill : bills) {
      bill.getParentVO().setStatus(VOStatus.UPDATED);
    }
    // 增加执行前业务规则
    this.addBeforeRule(processer);

    // 增加执行后业务规则
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills, originBills);
    /* -=notranslate=- */
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0026")/* @res "修改保存前执行业务规则" */);

    TimeLog.logStart();
    BillUpdate<SaleInvoiceVO> bo = new BillUpdate<SaleInvoiceVO>();
    SaleInvoiceVO[] vos = bo.update(bills, originBills);
    /* -=notranslate=- */
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0027")/* @res "修改保存单据到数据库" */);

    TimeLog.logStart();
    processer.after(bills, originBills);
    /* -=notranslate=- */
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0028")/* @res "修改保存后执行业务规则" */);

    return vos;
  }

  /**
   * 方法功能描述：添加保存后执行规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @author 冯加滨
   * @time 2010-1-21 下午05:08:24
   */
  private void addAfterRule(CompareAroundProcesser<SaleInvoiceVO> processer) {
    // 单据号重复性校验
    IRule<SaleInvoiceVO> rule = new CheckBillCodeRule();
    processer.addAfterRule(rule);

    // 回写发票来源单据
    ICompareRule<SaleInvoiceVO> rewriteRule = new RewriteBillUpdateRule();
    processer.addAfterRule(rewriteRule);
  }

  /**
   * 方法功能描述：添加保存前执行规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @author 冯加滨
   * @time 2010-1-21 下午05:07:25
   */
  private void addBeforeRule(CompareAroundProcesser<SaleInvoiceVO> processer) {
    IRule<SaleInvoiceVO> rule = null;
    // 检查参数是否支持审批中修改
    rule = new CheckCanUpdateWhenAuditing();
    processer.addBeforeRule(rule);
    
    // add by wangshu6 for 636 2014-01-20 销售发票审批中支持修订
    // 检查当前操作人是不是审批人， 如果是审批中状态并且当前操作人不是审批人，不允许修改
    rule =  new CheckApproverRule();
    processer.addBeforeRule(rule);
    // end 
    
    // 填充默认值
    ICompareRule<SaleInvoiceVO> fillRule = new FillUpdateDefaultRule();
    processer.addBeforeRule(fillRule);

    // 数据合法性校验
    rule = new CheckBillValityRule();
    processer.addBeforeRule(rule);
 
    
    // 校验单据状态
    rule = new CheckBillState();
    processer.addBeforeRule(rule);

    // 补充审计信息:最后修改人、最后修改时间
    rule = new SetUpdateAuditInfoRule<SaleInvoiceVO>();
    processer.addBeforeRule(rule);

  }

}
