package nc.ui.so.salequotation.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.parameter.SCMParameterUtils;

import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITextField;
import nc.ui.pub.beans.textfield.UITextDocument;
import nc.ui.pub.beans.textfield.UITextType;
import nc.ui.so.salequotation.model.QuickPriceData;

public class QuickEditPriceDialog extends UIDialog {

	class CancelActionPerformed implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			QuickEditPriceDialog.this.doCancel();
		}

	}

	class OKActionPerformed implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			QuickEditPriceDialog.this.doOk();
		}

	}

	private static final int DLG_HEIGHT = 120;

	private static final int DLG_WIDTH = 250;

	/**
   * 
   */
	private static final long serialVersionUID = -5127654571371318195L;

	private static final int TEXT_HEIGHT = 20;

	private static final int TEXT_WITHD = 100;

	private UITextField textField1 = new UITextField();

	private UITextField textField2 = new UITextField();

	private QuickPriceData value;

	public QuickEditPriceDialog(Container parent, String title, boolean reset) {
		super(parent, title, reset);
		this.constuctUI();
		this.value = new QuickPriceData();
	}

	public QuickPriceData getValue() {
		return this.value;
	}

	void doCancel() {
		this.closeCancel();
	}

	void doOk() {
		String value = this.textField2.getText();
		UFDouble price = UFDouble.ZERO_DBL;
		if (!PubAppTool.isNull(value)) {
			// 去掉String中的空格，以进行负数转换
			price = new UFDouble(value.replaceAll(" ", ""));
		}
		this.value.setAddValue(price);
		this.value.setFactorValue(new UFDouble(this.textField1.getText()));
		this.closeOK();
	}

	private void constuctUI() {
		UIPanel contentPanel = new UIPanel();
		contentPanel.setLayout(new BorderLayout());
		UIPanel inputPanel = new UIPanel();
		inputPanel.setLayout(new BorderLayout());
		UIPanel panel1 = new UIPanel();
		panel1.add(
				new UILabel(NCLangRes.getInstance().getStrByID("4006009_0",
						"04006009-0062")/* 价格指数% */), BorderLayout.WEST);
		panel1.add(this.textField1, BorderLayout.EAST);
		this.textField1.setSize(QuickEditPriceDialog.TEXT_WITHD,
				QuickEditPriceDialog.TEXT_HEIGHT);
		this.textField1.setText("100");
		this.textField1.setTextType(UITextType.TextDbl);
		// 设置价格指数精度
		this.setpriceFactorScale();
		UIPanel panel2 = new UIPanel();
		panel2.add(
				new UILabel(NCLangRes.getInstance().getStrByID("4006009_0",
						"04006009-0063")/* 价格加成 */), BorderLayout.WEST);
		panel2.add(this.textField2, BorderLayout.EAST);
		this.textField2.setSize(QuickEditPriceDialog.TEXT_WITHD,
				QuickEditPriceDialog.TEXT_HEIGHT);
		this.textField2.setText("0");
		this.textField2.setTextType(UITextType.TextDbl);
		// 设置价格加成精度
		this.setpriceAddScale();

		UIPanel bottomJpanel = new UIPanel();
		UIButton okButton = new UIButton(NCLangRes.getInstance().getStrByID(
				"4006009_0", "04006009-0064")/* 确定 */);
		bottomJpanel.add(okButton, BorderLayout.WEST);
		UIButton cancelButton = new UIButton(NCLangRes.getInstance()
				.getStrByID("4006009_0", "04006009-0061")/* 取消 */);
		bottomJpanel.add(cancelButton, BorderLayout.EAST);

		inputPanel.add(panel1, BorderLayout.NORTH);
		inputPanel.add(panel2, BorderLayout.SOUTH);
		contentPanel.add(inputPanel, BorderLayout.CENTER);

		contentPanel.add(bottomJpanel, BorderLayout.SOUTH);
		this.setContentPane(contentPanel);
		this.setSize(QuickEditPriceDialog.DLG_WIDTH,
				QuickEditPriceDialog.DLG_HEIGHT);
		this.setResizable(true);
		okButton.addActionListener(new OKActionPerformed());
		cancelButton.addActionListener(new CancelActionPerformed());
	}

	private void setpriceAddScale() {
		// 公共选择 NC004 采购/销售单价小数位
		String pk_group = AppContext.getInstance().getPkGroup();
		//Integer priceScale = SysParaInitQuery.getParaInt(pk_group, "NC004");
		((UITextDocument) this.textField2.getDocument()).setNumPoint(2);
	}

	private void setpriceFactorScale() {
		// 供应链基础设置 SCM08 销售折扣精度
		String pk_group = AppContext.getInstance().getPkGroup();
		int priceScale = SCMParameterUtils.getSCM08(pk_group);
		((UITextDocument) this.textField1.getDocument())
				.setNumPoint(priceScale);
	}
}
