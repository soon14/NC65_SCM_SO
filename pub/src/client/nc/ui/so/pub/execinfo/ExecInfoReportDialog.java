package nc.ui.so.pub.execinfo;

import java.awt.Container;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.bill.BillCardPanel;
import nc.vo.pubapp.AppContext;
import nc.vo.so.pub.execinfo.ExecInfoReportVO;

/**
 * 单据执行情况
 * 
 * @since 6.0
 * @version 2010-12-14 上午10:36:04
 * @author 祝会征
 */
public class ExecInfoReportDialog extends UIDialog {
	private static final int HEIGTH = 530;

	private static final int LENGTH = 800;

	private static final long serialVersionUID = 1L;

	public ExecInfoReportDialog(Container container) {
		super(container);
	}

	public ExecInfoReportDialog(Container container, boolean reset) {
		super(container, reset);
	}

	/**
	 * 销售发票执行情况
	 * 
	 * @param ordervo
	 */
	public void invoiceExecReport(ExecInfoReportVO[] vos) {
		BillCardPanel cardPanel = new BillCardPanel();
		cardPanel.loadTemplet("1001Z81000000000291H");
		ExecInfoPrecision.getInstance()
				.setModelPrecision(AppContext.getInstance().getPkGroup(),
						cardPanel.getBillModel());
		cardPanel.getBillModel().setBodyDataVO(vos);
		cardPanel.getBillModel().execLoadFormula();
		cardPanel.getBillModel().loadLoadRelationItemValue();
		cardPanel.setEnabled(false);
		this.add(cardPanel);
		this.setSize(ExecInfoReportDialog.LENGTH, ExecInfoReportDialog.HEIGTH);
		this.setResizable(true);
		this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
				"4006004_0", "04006004-0003")/*
											 * @res "销售发票执行情况"
											 */);
	}

	/**
	 * 销售订单执行情况
	 * 
	 * @param ordervo
	 */
	public void orderExecReport(ExecInfoReportVO[] reportVOs) {
		BillCardPanel cardPanel = new BillCardPanel();
		cardPanel.loadTemplet("1001Z81000000000290R");
		ExecInfoPrecision.getInstance()
				.setModelPrecision(AppContext.getInstance().getPkGroup(),
						cardPanel.getBillModel());
		cardPanel.getBillModel().setBodyDataVO(reportVOs);
		cardPanel.getBillModel().execLoadFormula();
		cardPanel.getBillModel().loadLoadRelationItemValue();

		cardPanel.setEnabled(false);
		this.add(cardPanel);

		this.setSize(ExecInfoReportDialog.LENGTH, ExecInfoReportDialog.HEIGTH);
		this.setResizable(true);
		this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
				"4006004_0", "04006004-0004")/*
											 * @res "销售订单执行情况"
											 */);

	}
}
