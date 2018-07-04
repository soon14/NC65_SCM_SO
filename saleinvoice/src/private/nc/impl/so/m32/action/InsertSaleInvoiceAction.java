package nc.impl.so.m32.action;

import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.bs.so.m32.maintain.InsertSaleInvoiceBP;
import nc.bs.so.m32.plugin.Action32PlugInPoint;

import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * 发票插入action
 * 
 * @since 6.0
 * @version 2011-10-27 下午12:40:51
 * @author 么贵敬
 */
public class InsertSaleInvoiceAction {

  /**
   * 销售发票新增保存
   * 
   * @param newvos 发票vo
   * @return 保存后的发票vo
   */
  public SaleInvoiceVO[] insert(SaleInvoiceVO[] newvos) {
    AroundProcesser<SaleInvoiceVO> processer =
        new AroundProcesser<SaleInvoiceVO>(Action32PlugInPoint.InsertAction);

    TimeLog.logStart();
    processer.before(newvos);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0047")/*@res "调用新增保存BP前执行业务规则"*/);

    TimeLog.logStart();
    InsertSaleInvoiceBP action = new InsertSaleInvoiceBP();
    SaleInvoiceVO[] vos = action.insert(newvos);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0048")/*@res "调用新增保存BP，进行保存"*/);

    TimeLog.logStart();
    processer.after(vos);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0049")/*@res "调用新增保存BP后执行业务规则"*/);
    return vos;
  }
}
