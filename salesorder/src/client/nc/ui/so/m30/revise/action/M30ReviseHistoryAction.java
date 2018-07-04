package nc.ui.so.m30.revise.action;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITextField;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pub.component.ButtonPanel;
import nc.ui.pub.query.SetOrderDlg;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.NCAction;

public class M30ReviseHistoryAction extends NCAction {
	public static final String HISTORY_FUNCODE = "40060302H";
	private static final long serialVersionUID = -1040049186179282394L;
	private BillForm editor;

	private UITextField textFiled = null;

	private BillManageModel model;

	public M30ReviseHistoryAction() {
		super();
		this.setBtnName(NCLangRes.getInstance().getStrByID("4006011_0",
				"04006011-0298")/* 修订历史 */);
		this.setCode("ReviseHistory");
		this.putValue(Action.SHORT_DESCRIPTION, NCLangRes.getInstance()
				.getStrByID("4006011_0", "04006011-0298")/* 修订历史 */);
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		Object showInputDlg = MessageDialog.showInputDlg(this.getEditor().getParent(),"请用扫描枪进行扫描", "请用扫描枪进行扫描", "");
	}

	public BillForm getEditor() {
		return this.editor;
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
	}

	public BillManageModel getModel() {
		return this.model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}

	public class InPutDialog extends UIDialog implements ActionListener {

		private ButtonPanel buttonPanel;

		private UIPanel contentPanel = null;

		private BillManageModel model;

		private BillCardPanel billCardPanel;

		private String reason;

		public InPutDialog(Container parent, BillManageModel model) {
			super(parent);
			this.model = model;
			initUI();
		}

		protected void initUI() {

			setTitle("请用扫描枪进行扫描");

			setLayout(new BorderLayout());

			add(getContentPanel(), BorderLayout.CENTER);
			add(getButtonPanel(), BorderLayout.SOUTH);
			setSize(500, 80);
			initListener();
		}

		private void initListener() {
			getButtonPanel().getBtnOK().addActionListener(this);
			getButtonPanel().getBtnCancel().addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent en) {
			if (en.getSource() == getButtonPanel().getBtnOK()) {
				if (StringUtils.isEmpty((String) billCardPanel.getHeadItem(
						"reason").getValueObject())) {
					MessageDialog.showErrorDlg(this, "错误", "请输入正确的非空单据号！");
					return;
				} else {
					setReason((String) billCardPanel.getHeadItem("reason")
							.getValueObject());
				}
				closeOK();
			} else if (en.getSource() == getButtonPanel().getBtnCancel()) {
				closeCancel();
			}

		}

		private ButtonPanel getButtonPanel() {
			if (buttonPanel == null) {
				buttonPanel = new ButtonPanel();
			}
			return buttonPanel;
		}

		private UIPanel getContentPanel() {
			if (contentPanel == null) {
				contentPanel = new UIPanel(new BorderLayout());
				contentPanel.add(getBillCardPanel(), BorderLayout.CENTER);
			}
			return contentPanel;
		}

		private BillCardPanel getBillCardPanel() {
			if (billCardPanel == null) {
				billCardPanel = new BillCardPanel();
				billCardPanel.setBillData(getBillData());
			}
			return billCardPanel;
		}

		private BillData getBillData() {
			ArrayList<BillItem> itemList = new ArrayList<BillItem>();

			BillItem item = new BillItem();
			item.setName("");
			item.setKey("reason");
			item.setDataType(IBillItem.STRING);
			//对长度不做限制
			item.setLength(10000);
			item.setWidth(10000);
			item.setEdit(true);
			item.setNull(false);
			itemList.add(item);
			BillData billdata = new BillData();
			billdata.setHeadItems(itemList.toArray(new BillItem[itemList.size()]));

			return billdata;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}
	}

}
