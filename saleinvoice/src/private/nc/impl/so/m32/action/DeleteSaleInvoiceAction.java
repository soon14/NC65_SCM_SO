package nc.impl.so.m32.action;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.bs.so.m32.maintain.DeleteSaleInvoiceBP;
import nc.bs.so.m32.plugin.Action32PlugInPoint;

import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * 销售发票删除
 * 
 * @since 6.3
 * @version 2012-12-21 上午10:02:40
 * @author yaogj
 */
public class DeleteSaleInvoiceAction {

  /**
   * 
   * @param bills 要删除的销售发票vo
   */
  public void delete(SaleInvoiceVO[] bills) {

    AroundProcesser<SaleInvoiceVO> processer =
        new AroundProcesser<SaleInvoiceVO>(Action32PlugInPoint.DeleteAction);

    TimeLog.logStart();
    processer.before(bills);
    /*-=notranslate=-*/
    TimeLog.info(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
        "04006008-0044")/*@res "调用删除BP前执行业务规则"*/);

    TimeLog.logStart();
    DeleteSaleInvoiceBP action = new DeleteSaleInvoiceBP();
    action.delete(bills);
    /*-=notranslate=-*/
    TimeLog.info(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
        "04006008-0045")/*@res "调用删除BP，进行删除"*/);

    TimeLog.logStart();
    processer.after(bills);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0046")/*@res "调用删除BP后执行业务规则"*/);

  }
}
