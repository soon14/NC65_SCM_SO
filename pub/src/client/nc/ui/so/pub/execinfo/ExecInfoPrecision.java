package nc.ui.so.pub.execinfo;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.so.pub.execinfo.ExecInfoReportVO;

/**
 * 订单执行情况精度处理
 * 
 * @since 6.0
 * @version 2011-7-26 下午07:55:44
 * @author 么贵敬
 */
public class ExecInfoPrecision {

  /** 表体 */

  // 本币金额
  private static final String[] MNYKEYS = new String[] {
    // 未开票金额\未收款金额
    ExecInfoReportVO.NEEDINVOICEMONEY, ExecInfoReportVO.NEEDPAYMONEY,
    // 已开票金额\已收款金额
    ExecInfoReportVO.TOTALINVOICEMONEY, ExecInfoReportVO.TOTALPAYMONEY
  };

  // 主数量
  private static final String[] NUMKEYS = new String[] {
    // 数量、未开票数量
    ExecInfoReportVO.NNUM, ExecInfoReportVO.NEEDINVOICENUM,
    // 未出库数量\未发货数量
    ExecInfoReportVO.NEEDOUTNUM, ExecInfoReportVO.NEEDSENDNUM,
    // 已开票数量\已出库数量
    ExecInfoReportVO.NTOTALINVOICENUM, ExecInfoReportVO.NTOTALOUTNUM,
    // 已发货数量\应发数量
    ExecInfoReportVO.NTOTALSENDNUM, ExecInfoReportVO.SHOULDSENDNUM
  };

  private static ExecInfoPrecision precision = new ExecInfoPrecision();

  /**
   * 
   * PreOrderPrecision 的构造子
   */
  private ExecInfoPrecision() {
    //
  }

  public static ExecInfoPrecision getInstance() {
    return ExecInfoPrecision.precision;
  }

  /**
   * 设置集中安排界面精度
   * 
   * @param pk_group
   * @param model
   */
  public void setModelPrecision(String pk_group, BillModel model) {
    BillModelScaleProcessor scaleprocess =
        new BillModelScaleProcessor(pk_group, model);
    this.setTablePrecision(scaleprocess);
  }

  /**
   * 单表精度处理
   * 
   * @param panel
   */
  public void setSingleTableScale(String pk_group, BillListPanel panel) {
    this.setModelPrecision(pk_group, panel.getHeadBillModel());
  }

  /**
   * 设置表格精度
   * 
   * @param scaleprocess
   */
  private void setTablePrecision(TableScaleProcessor scaleprocess) {

    // 主数量
    scaleprocess.setNumCtlInfo(ExecInfoPrecision.NUMKEYS,
        ExecInfoReportVO.CUNITID);
    // 本币金额
    scaleprocess.setMnyCtlInfo(ExecInfoPrecision.MNYKEYS,
        ExecInfoReportVO.CCURRENCYID);

    scaleprocess.process();
  }

}
