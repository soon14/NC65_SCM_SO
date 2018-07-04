package nc.impl.so.m32.action;

import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.bs.so.m32.maintain.UpdateSaleInvoiceBP;
import nc.bs.so.m32.plugin.Action32PlugInPoint;

import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;

/**
 * 销售发票修改保存操作实现
 * 
 * @since 6.0
 * @version 2010-1-22 下午12:43:19
 * @author 冯加滨
 */
public class UpdateSaleInvoiceAction {

  /**
   * 
   * @param fullbills 前台传过来补全后的vo
   * @param originBills 原始vo
   * @return 更新后的vo
   */
  public SaleInvoiceVO[] update(SaleInvoiceVO[] fullbills,
      SaleInvoiceVO[] originBills) {

    CompareAroundProcesser<SaleInvoiceVO> compareProcesser =
        new CompareAroundProcesser<SaleInvoiceVO>(
            Action32PlugInPoint.UpdateAction);

    TimeLog.logStart();

    compareProcesser.before(fullbills, originBills);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0052")/*@res "调用更新保存BP前执行业务规则"*/);

    UpdateSaleInvoiceBP action = new UpdateSaleInvoiceBP();
    SaleInvoiceVO[] ret = action.update(fullbills, originBills);

    TimeLog.logStart();
    compareProcesser.after(ret, originBills);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0053")/*@res "调用更新保存BP后执行业务规则"*/);

    TimeLog.logStart();
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0042")/*@res "业务日志"*/);

    return ret;

  }

}
