package nc.impl.so.m32.action;

import nc.bs.so.m32.plugin.Action32PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.so.m32.action.rule.commit.CheckCommitEnableRule;
import nc.impl.so.m32.action.rule.commit.SetCommitStatusRule;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票提交功能后台实现
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-5-21 下午03:00:56
 */
public class CommitSaleInvoiceAction {
  /**
   * 方法功能描述：销售发票提交审批功能实现。
   * <p>
   * <b>参数说明</b>
   * 
   * @param voInvoices
   * @return
   *         <p>
   * @author fengjb
   * @time 2010-5-21 下午03:01:36
   */
  public SaleInvoiceVO[] commit(SaleInvoiceVO[] voInvoices) {

    TimeLog.logStart();
    BillTransferTool<SaleInvoiceVO> transferTool =
        new BillTransferTool<SaleInvoiceVO>(voInvoices);

    TimeLog.info("保存前台VO，组织返回值时使用"); /*-=notranslate=-*/

    AroundProcesser<SaleInvoiceVO> processer =
        new AroundProcesser<SaleInvoiceVO>(Action32PlugInPoint.SendAppAction);

    this.addRule(processer);
    TimeLog.logStart();
    processer.before(voInvoices);
    TimeLog.info("调用送审前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    this.updateBillStatus(voInvoices);

    TimeLog.info("更新数据库记录"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(voInvoices);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0040")/*@res "调用送审后执行业务规则"*/); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleInvoiceVO[] vos = transferTool.getBillForToClient(voInvoices);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0041")/*@res "组织返回值,返回轻量级VO"*/); /*-=notranslate=-*/

    TimeLog.logStart();
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0042")/*@res "业务日志"*/); /*-=notranslate=-*/
    return vos;

  }

  /**
   * 方法功能描述：添加业务规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @author fengjb
   * @time 2010-5-21 下午03:50:56
   */
  private void addRule(AroundProcesser<SaleInvoiceVO> processer) {
    // 校验销售发票是否允许送审
    IRule<SaleInvoiceVO> rule = new CheckCommitEnableRule();
    processer.addBeforeRule(rule);
    // 设置送审后发票状态
    rule = new SetCommitStatusRule();
    processer.addBeforeRule(rule);

  }

  /**
   * 方法功能描述：更新数据库中记录的单据状态。
   * <p>
   * <b>参数说明</b>
   * 
   * @param voInvoices
   *          <p>
   * @author fengjb
   * @time 2010-5-21 下午03:50:37
   */
  private void updateBillStatus(SaleInvoiceVO[] voInvoices) {
    int ilength = voInvoices.length;
    SaleInvoiceHVO[] voHeads = new SaleInvoiceHVO[ilength];
    for (int i = 0; i < ilength; i++) {
      voHeads[i] = voInvoices[i].getParentVO();
    }
    VOUpdate<SaleInvoiceHVO> updatesrv = new VOUpdate<SaleInvoiceHVO>();
    String[] updateKeys = new String[] {
      SaleInvoiceHVO.FSTATUSFLAG
    };
    updatesrv.update(voHeads, updateKeys);

  }
}
