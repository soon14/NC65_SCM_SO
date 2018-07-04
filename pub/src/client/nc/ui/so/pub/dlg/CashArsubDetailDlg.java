package nc.ui.so.pub.dlg;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m35.so.m30.IArsubToSaleorder;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListData;
import nc.ui.pub.bill.BillListPanel;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m35.entity.ArsubDetailVO;

/**
 * 
 * 费用兑付明细信息对话框
 * 
 * @since 6.35
 * @version 2013-12-11 14:04:09
 * @author 宫体雷
 */
public class CashArsubDetailDlg extends UIDialog implements BillEditListener {

	private static final long serialVersionUID = -283873732522189276L;

	/**
	 * 事件监听类
	 * 
	 * @since 6.35
	 * @version 2013-12-11 14:04:47
	 * @author 宫体雷
	 */
	class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == CashArsubDetailDlg.this.getBtnCancel()) {
				// 关闭按钮执行事件
				CashArsubDetailDlg.this.doCancelAction();
			}
		}
	}

	/** 默认宽度 */
	private static final int DEFAULTWITH = 600;

	/** 默认高度 */
	private static final int DEFAUTHIGH = 450;

	/** 变化比例 */
	private static final int DENOMINATOR = 8;

	/** 变化比例 */
	private static final int NOLECULE = 5;

	/** 按钮响应事件 */
	private ActionHandler actionHandler = new ActionHandler();

	/** 销售订单表ID集 */
	private String[] saleBillIDs;

	/** 列表模板 */
	private BillListPanel arsubPanel;

	/** 关闭按钮 */
	private UIButton btnCancel;

	/** 按钮panel */
	private UIPanel btnUIPanel;

	private int decimaldigit;

	/** UI JPanel */
	private JPanel uiContentPane;

	/**
	 * 构造方法
	 * 
	 * @param parent
	 *            卡片
	 * @param saleBillIDs
	 *            销售订单ID集
	 */
	public CashArsubDetailDlg(BillCardPanel parent, String[] saleBillIDs) {
		super(parent);
		this.decimaldigit = parent.getBodyItem(SaleOrderBVO.NORIGSUBMNY)
				.getDecimalDigits();
		this.saleBillIDs = saleBillIDs;
		this.initialize();
	}

	public CashArsubDetailDlg(BillCardPanel parent, String[] saleBillIDs, boolean reset) {
		super(parent, reset);
		this.decimaldigit = parent.getBodyItem(SaleOrderBVO.NORIGSUBMNY)
				.getDecimalDigits();
		this.saleBillIDs = saleBillIDs;
		this.initialize();
	}

	@Override
	public void afterEdit(BillEditEvent e) {
		//
	}

	@Override
	public void bodyRowChange(BillEditEvent e) {
		//
	}

	/**
	 * 取消按钮动作
	 */
	public void doCancelAction() {
		this.closeCancel();
	}

	/**
	 * 关闭按钮
	 * 
	 * @return 按钮
	 */
	public UIButton getBtnCancel() {
		if (null == this.btnCancel) {
			this.btnCancel = new UIButton();
			this.btnCancel.setName("btnCancel");

			this.btnCancel.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("4006001_0", "04006001-0011")/* @res "关闭" */);
		}
		return this.btnCancel;
	}

	/**
	 * 按钮容器
	 * 
	 * @return panel
	 */
	public UIPanel getBtnUIPanel() {
		if (this.btnUIPanel == null) {
			this.btnUIPanel = new nc.ui.pub.beans.UIPanel();
			this.btnUIPanel.setName("BtnUIPanel");
			this.btnUIPanel.add(this.getBtnCancel(), this.getBtnCancel()
					.getName());
		}
		return this.btnUIPanel;
	}

	/**
	 * JPanel
	 * 
	 * @return JPanel
	 */
	public JPanel getUiContentPane() {
		return this.uiContentPane;
	}

	/**
	 * 关闭按钮setter
	 * 
	 * @param btnCancel
	 *            关闭按钮
	 */
	public void setBtnCancel(UIButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	/**
	 * 按钮容器
	 * 
	 * @param btnUIPanel
	 *            容器
	 */
	public void setBtnUIPanel(UIPanel btnUIPanel) {
		this.btnUIPanel = btnUIPanel;
	}

	/**
	 * UiContent
	 * 
	 * @param uiContentPane
	 *            UiContent
	 */
	public void setUiContentPane(JPanel uiContentPane) {
		this.uiContentPane = uiContentPane;
	}

	/**
	 * 添加事件监听
	 */
	private void addActionListener() {
		this.getBtnCancel().addActionListener(this.actionHandler);
	}

	/**
	 * 返回销售费用单列表Panel
	 * 
	 * @return 列表模板
	 */
	private BillListPanel getArSubPanel() {
		if (null == this.arsubPanel) {
			this.arsubPanel = new BillListPanel();
			this.arsubPanel.setName("ArSubPanel");
			this.arsubPanel.setToolTipText(NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0239")/*费用兑付明细*/);
			String operator = AppContext.getInstance().getPkUser();
			String pk_group = AppContext.getInstance().getPkGroup();
			this.arsubPanel.loadTemplet("40060301", null, operator, pk_group,
					"casharsub");

			this.arsubPanel.getHeadTable().setSelectionMode(
					javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			this.arsubPanel.getHeadTable().setEnabled(true);
			this.arsubPanel.getChildListPanel().setTotalRowShow(true);
			this.arsubPanel.setEnabled(false);

		}
		return this.arsubPanel;
	}

	private Container getUIContentPane() {
		if (null == this.uiContentPane) {
			this.uiContentPane = new JPanel();
			this.uiContentPane.setName("UIDialogContentPane");
			this.uiContentPane.setLayout(new BorderLayout());

			this.getUIContentPane().add(this.getArSubPanel(), "Center");
			this.getUIContentPane().add(this.getBtnUIPanel(), "South");

		}
		return this.uiContentPane;
	}

	/**
	 * 初始化方法
	 */
	private void initialize() {
		this.setName("ArsubDlg");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Dimension dd = Toolkit.getDefaultToolkit().getScreenSize();
		int w = CashArsubDetailDlg.DEFAULTWITH;
		int h = CashArsubDetailDlg.DEFAUTHIGH;
		if (dd != null) {
			w = (int) dd.getWidth() * CashArsubDetailDlg.NOLECULE
					/ CashArsubDetailDlg.DENOMINATOR;
			h = (int) dd.getHeight() * CashArsubDetailDlg.NOLECULE
					/ CashArsubDetailDlg.DENOMINATOR;
		}
		w = w > CashArsubDetailDlg.DEFAULTWITH || w <= 0 ? CashArsubDetailDlg.DEFAULTWITH
				: w;
		h = h > CashArsubDetailDlg.DEFAUTHIGH || h <= 0 ? CashArsubDetailDlg.DEFAUTHIGH
				: h;
		this.setSize(w, h);
		this.setResizable(true);
		this.setTitle(NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0239")/*费用兑付明细*/);
		this.setContentPane(this.getUIContentPane());
		// 添加监听
		this.addActionListener();
		// 初始化数据
		this.queryAndLoadArsub();
	}

	private void queryAndLoadArsub() {

		// 查询销售费用单
		ArsubDetailVO[] bills = null;
		IArsubToSaleorder service = NCLocator.getInstance().lookup(
				IArsubToSaleorder.class);
		try {
			bills = service.queryCashArsubDetailVO(this.saleBillIDs);
		} catch (Exception e) {
			ExceptionUtils.wrappException(e);
		}
		// 单据模板数据控制
		BillListData billlistdata = this.getArSubPanel().getBillListData();
		BillItem item = billlistdata.getBodyBillModel().getItemByKey(
				ArsubDetailVO.NDETAILSUBMNY);
		item.setDecimalDigits(this.decimaldigit);
		billlistdata.setBodyValueVO(bills);
		billlistdata.getBodyBillModel().loadLoadRelationItemValue();
		billlistdata.getBodyBillModel().execLoadFormula();
	}

}
