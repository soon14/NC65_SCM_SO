package nc.bs.so.m32.maintain;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.rule.SaveHyfjsdAfterRule;
import nc.bs.so.m32.maintain.rule.delete.CheckEnableDeleteRule;
import nc.bs.so.m32.maintain.rule.delete.ReturnBillCodeRule;
import nc.bs.so.m32.maintain.rule.delete.RewriteARSubDeleteRule;
import nc.bs.so.m32.maintain.rule.delete.RewriteBillDeleteRule;
import nc.bs.so.m32.maintain.rule.delete.UpdateOppFlagDeleteRule;
import nc.bs.so.m32.plugin.BP32PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * 销售发票删除bp
 * 
 * @since 6.3
 * @version 2012-12-21 上午09:11:26
 * @author yaogj
 */
public class DeleteSaleInvoiceBP {

  /**
   * 
   * @param bills 销售发票vo
   */
  public void delete(SaleInvoiceVO[] bills) {

    AroundProcesser<SaleInvoiceVO> processer =
        new AroundProcesser<SaleInvoiceVO>(BP32PlugInPoint.DeleteAction);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    // 增加执行后业务规则
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);

    /*-=notranslate=-*/
    TimeLog.info(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
        "04006008-0020")/*@res "删除前执行业务规则"*/);

    TimeLog.logStart();
    BillDelete<SaleInvoiceVO> bo = new BillDelete<SaleInvoiceVO>();
    bo.delete(bills);
    /*-=notranslate=-*/
    TimeLog.info(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
        "04006008-0021")/*@res "写数据库，删除单据"*/);

    TimeLog.logStart();
    processer.after(bills);
    /*-=notranslate=-*/
    TimeLog.info(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
        "04006008-0022")/*@res "删除后执行业务规则"*/);

  }

  private void addAfterRule(AroundProcesser<SaleInvoiceVO> processer) {
    // 回退单据号
    IRule<SaleInvoiceVO> rule = new ReturnBillCodeRule();
    processer.addAfterRule(rule);
    // 对冲生成发票删除时更新来源发票对冲标志
    rule = new UpdateOppFlagDeleteRule();
    processer.addAfterRule(rule);
    // 回写销售费用单冲抵关系
    rule = new RewriteARSubDeleteRule();
    processer.addAfterRule(rule);
    // 回写来源单据
    rule = new RewriteBillDeleteRule();
    processer.addAfterRule(rule);
    // 自动结算关闭
    // rule = new AutoSaleBalEndRule();
    // processer.addAfterRule(rule);
    //销售发票拉海运费结算单回写
    rule = new SaveHyfjsdAfterRule();
    processer.addAfterRule(rule);
  }

  /**
   * 方法功能描述：添加删除前规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @author 冯加滨
   * @time 2010-1-22 上午08:57:00
   */
  private void addBeforeRule(AroundProcesser<SaleInvoiceVO> processer) {

    // 检查销售发票当前状态是否可被删除
    IRule<SaleInvoiceVO> rule = new CheckEnableDeleteRule();
    processer.addBeforeRule(rule);
  }

}
