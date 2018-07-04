package nc.ui.so.salequotation.view;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.so.salequotation.entity.HisSalequoVO;

public class HisQuotationDlg extends UIDialog implements BillEditListener {
	class AdjpriceBtnActionPerform implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			HisQuotationDlg.this.setNewPrice(new UFDouble[HisQuotationDlg.this
					.getBillCardPanel().getRowCount()]);
			UFDouble[] newPrices = HisQuotationDlg.this.getNewPrice();
			for (int i = 0; i < newPrices.length; i++) {
				UFDouble newprice = (UFDouble) HisQuotationDlg.this
						.getBillCardPanel().getBodyValueAt(i, "newprice");
				newPrices[i] = newprice;
			}
			HisQuotationDlg.this.closeOK();
		}
	}

	class CancelBtnActionPerform implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			HisQuotationDlg.this.closeCancel();
		}
	}

	private static final String HISSALEQUO_TEMPLATE_CODE = "HisSalequo";

	private static final String[] PRICEKEYS = new String[] { "hisprice",
			"newprice" };

	/**
   * 
   */
	private static final long serialVersionUID = 2732018547371039327L;

	private UIButton adjprice_btn;

	private AppUiState appUiState;

	private BillCardPanel billCardPanel;

	private UIButton cancel_btn;

	private HisSalequoVO[] data;

	/**
	 * 拟报价
	 */
	private UFDouble[] newPrice;

	public HisQuotationDlg(Container container, String title,
			HisSalequoVO[] data, AppUiState appUiState) {
		super(container, title);
		this.data = data;
		this.appUiState = appUiState;
		this.initUI();
		this.initData();
	}
	
	public HisQuotationDlg(Container container, String title,
			HisSalequoVO[] data, AppUiState appUiState, boolean reset) {
		super(container, title, reset);
		this.data = data;
		this.appUiState = appUiState;
		this.initUI();
		this.initData();
	}

	@Override
	public void afterEdit(BillEditEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void bodyRowChange(BillEditEvent e) {
		// TODO Auto-generated method stub
	}

	public UFDouble[] getNewPrice() {
		return this.newPrice;
	}

	public void setCardPrecision(String pk_group, BillCardPanel cardpanel) {
		BillScaleProcessor scaleprocess = new CardPaneScaleProcessor(pk_group,
				cardpanel);
		this.setBillPrecision(scaleprocess);
	}

	public void setNewPrice(UFDouble[] newPrice) {
		this.newPrice = newPrice;
	}

	BillCardPanel getBillCardPanel() {
		if (this.billCardPanel == null) {
			this.billCardPanel = new BillCardPanel();
			this.billCardPanel.loadTemplet(
					HisQuotationDlg.HISSALEQUO_TEMPLATE_CODE, null, null,
					"@@@@");
			this.billCardPanel.setEnabled(true);
			this.billCardPanel.addEditListener(this);
		}
		return this.billCardPanel;
	}

	private UIButton getAdjprice_btn() {
		if (this.adjprice_btn == null) {
			this.adjprice_btn = new UIButton();
			this.adjprice_btn.setText(NCLangRes.getInstance().getStrByID(
					"4006009_0", "04006009-0060")/* 调整报价单 */);
			this.adjprice_btn.addActionListener(new AdjpriceBtnActionPerform());
		}
		return this.adjprice_btn;
	}

	private UIButton getCancel_btn() {
		if (this.cancel_btn == null) {
			this.cancel_btn = new UIButton();
			this.cancel_btn.setText(NCLangRes.getInstance().getStrByID(
					"4006009_0", "04006009-0061")/* 取消 */);
			this.cancel_btn.addActionListener(new CancelBtnActionPerform());
		}
		return this.cancel_btn;
	}

	private void initData() {
		this.setCardPrecision(AppUiContext.getInstance().getPkGroup(),
				this.getBillCardPanel());
		if (this.data != null) {
			this.getBillCardPanel().getBillData().setBodyValueVO(this.data);
			this.getBillCardPanel().getBillModel().execLoadFormula();			
		}
	}

	private void initUI() {
		UIPanel btnPanel = new UIPanel();
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		btnPanel.add(this.getAdjprice_btn());
		btnPanel.add(this.getCancel_btn());
		this.setLayout(new BorderLayout());
		if (AppUiState.ADD.equals(this.appUiState)
				|| AppUiState.EDIT.equals(this.appUiState)
				|| AppUiState.COPY_ADD.equals(this.appUiState)) {
			this.getBillCardPanel().getBodyItem("newprice").setEdit(true);
		} else {
			this.getAdjprice_btn().setEnabled(false);
			this.getCancel_btn().setEnabled(false);
			this.getBillCardPanel().getBodyItem("newprice").setEdit(false);
		}
		this.add(this.getBillCardPanel(), BorderLayout.CENTER);
		this.add(btnPanel, BorderLayout.SOUTH);
		this.setDialogSize();
		this.setResizable(true);
	}

	private void setBillPrecision(BillScaleProcessor scaleprocess) {
		 scaleprocess.setPriceCtlInfo(HisQuotationDlg.PRICEKEYS, PosEnum.body,
		 null,HisSalequoVO.PK_CURRTYPE,PosEnum.body,null);
		 scaleprocess.process();
	}

	private void setDialogSize() {
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(screenWidth / 2, screenHeight / 2);
		this.setSize(screenWidth / 2, screenHeight / 3);
	}
}
