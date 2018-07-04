package nc.ui.so.pub.dlg;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillListData;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.so.pub.precision.RecPlanPrecision;
import nc.vo.so.entry.RecPlanVO;

/**
 * 收款计划对话框
 * 
 * @since 6.0
 * @version 2011-7-1 下午03:34:12
 * @author 么贵敬
 */
public class RecPlanDlg extends UIDialog implements BillEditListener {

	/**
	 * 
	 * 事件监听
	 * 
	 * @since 6.0
	 * @version 2010-12-10 下午12:35:14
	 * @author 么贵敬
	 */
	class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == RecPlanDlg.this.getBtnCancel()) {
				// 取消按钮执行事件
				RecPlanDlg.this.doCancelAction();
			}
		}
	}

	/** 默认宽度 */
	private static final int DEFAULTWITH = 900;

	/** 默认高度 */
	private static final int DEFAUTHIGH = 700;

	/** 变化比例 */
	private static final int DENOMINATOR = 8;

	/** 变化比例 */
	private static final int NOLECULE = 7;

	private static final long serialVersionUID = 2421981094802478580L;

	/** 按钮响应事件 */
	private ActionHandler actionHandler = new ActionHandler();

	/** 关闭按钮 */
	private UIButton btnCancel;

	/** 按钮panel */
	private UIPanel btnUIPanel;

	/** 列表模板 */
	private BillListPanel recplanPanel;

	/** UI JPanel */
	private JPanel uiContentPane;

	/** 主键ID */
	private RecPlanVO[] viewvo;

	/**
	 * 构造方法
	 * 
	 * @param parent
	 *            界面
	 * @param viewvo
	 *            收款计划vo
	 */
	public RecPlanDlg(BillCardPanel parent, RecPlanVO[] viewvo) {
		super(parent);
		this.viewvo = viewvo;
		this.initialize();
	}

	public RecPlanDlg(BillCardPanel parent, RecPlanVO[] viewvo, boolean reset) {
		super(parent, reset);
		this.viewvo = viewvo;
		this.initialize();
	}

	@Override
	public void afterEdit(BillEditEvent e) {
		return;
	}

	@Override
	public void bodyRowChange(BillEditEvent e) {
		return;
	}

	/**
	 * 取消按钮动作
	 */
	public void doCancelAction() {
		this.closeCancel();
	}

	/**
	 * 取消按钮getter
	 * 
	 * @return 取消按钮
	 */
	public UIButton getBtnCancel() {
		if (null == this.btnCancel) {
			this.btnCancel = new UIButton();
			this.btnCancel.setName("btnCancel");
			this.btnCancel.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("4006004_0", "04006004-0000")/* @res "关闭" */);
		}
		return this.btnCancel;
	}

	/**
	 * 按钮pannelgetter
	 * 
	 * @return 按钮pannel
	 */
	public UIPanel getBtnUIPanel() {
		if (this.btnUIPanel == null) {
			this.btnUIPanel = new nc.ui.pub.beans.UIPanel();
			this.btnUIPanel.setName("BtnUIPanel");
			// this.btnUIPanel.add(this.getBtnCancel(),
			// this.getBtnCancel().getName());
		}
		return this.btnUIPanel;
	}

	/**
	 * 返回UI模板
	 * 
	 * @return JPanel
	 */
	public JPanel getUiContentPane() {
		if (null == this.uiContentPane) {
			this.uiContentPane = new JPanel();
			this.uiContentPane.setName("UIDialogContentPane");
			this.uiContentPane.setLayout(new BorderLayout());

			this.getUIContentPane().add(this.getProfitPanel(), "Center");
			this.getUIContentPane().add(this.getBtnUIPanel(), "South");

		}
		return this.uiContentPane;
	}

	/**
	 * 取消按钮setter
	 * 
	 * @param btnCancel
	 *            取消按钮
	 */
	public void setBtnCancel(UIButton btnCancel) {
		this.btnCancel = btnCancel;
	}

	/**
	 * 按钮panel setter
	 * 
	 * @param btnUIPanel
	 *            按钮panel
	 */
	public void setBtnUIPanel(UIPanel btnUIPanel) {
		this.btnUIPanel = btnUIPanel;
	}

	/**
	 * BillListPanel setter
	 * 
	 * @param recplanPanel
	 *            列表模板
	 */
	public void setProfitPanel(BillListPanel recplanPanel) {
		this.recplanPanel = recplanPanel;
	}

	/**
	 * UI content
	 * 
	 * @param uiContentPane
	 *            JPanel
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
	 * 返回毛利分析列表Panel
	 * 
	 * @return 列表模板
	 */
	private BillListPanel getProfitPanel() {
		if (null == this.recplanPanel) {
			this.recplanPanel = new BillListPanel();
			this.recplanPanel.setName("profitPanel");
			this.recplanPanel
					.setToolTipText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
							.getStrByID("4006004_0", "04006004-0002")/*
																	 * @res
																	 * "收款计划"
																	 */);
			String operator = WorkbenchEnvironment.getInstance().getLoginUser()
					.getPrimaryKey();
			String pk_group = WorkbenchEnvironment.getInstance().getGroupVO()
					.getPk_group();
			this.recplanPanel.loadTemplet("40060301", null, operator, pk_group,
					"recplan");

			this.recplanPanel.addBodyEditListener(this);
			this.recplanPanel.getHeadTable().setSelectionMode(
					javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			this.recplanPanel.getHeadTable().setEnabled(true);
			this.recplanPanel.setMultiSelect(false);
			this.recplanPanel.setEnabled(true);
			recplanPanel.getBodyScrollPane("recvplan").setBBodyMenuShow(false);
		}
		return this.recplanPanel;
	}

	private javax.swing.JPanel getUIContentPane() {
		if (null == this.uiContentPane) {
			this.uiContentPane = new JPanel();
			this.uiContentPane.setName("UIDialogContentPane");
			this.uiContentPane.setLayout(new BorderLayout());

			this.getUIContentPane().add(this.getProfitPanel(), "Center");
			this.getUIContentPane().add(this.getBtnUIPanel(), "South");
		}
		return this.uiContentPane;
	}

	/**
	 * 初始化方法
	 */
	private void initialize() {
		this.setName("RecPlanDlg");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Dimension dd = Toolkit.getDefaultToolkit().getScreenSize();
		int w = RecPlanDlg.DEFAULTWITH;
		int h = RecPlanDlg.DEFAUTHIGH;
		if (dd != null) {
			w = (int) dd.getWidth() * RecPlanDlg.NOLECULE
					/ RecPlanDlg.DENOMINATOR;
			h = (int) dd.getHeight() * RecPlanDlg.NOLECULE
					/ RecPlanDlg.DENOMINATOR;
		}
		w = w > RecPlanDlg.DEFAULTWITH || w <= 0 ? RecPlanDlg.DEFAULTWITH : w;
		h = h > RecPlanDlg.DEFAUTHIGH || h <= 0 ? RecPlanDlg.DEFAUTHIGH : h;
		this.setSize(w, h);
		this.setResizable(true);
		this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
				"4006004_0", "04006004-0002")/* @res "收款计划" */);
		this.setContentPane(this.getUIContentPane());
		// 添加监听
		this.addActionListener();
		// 初始化数据
		this.queryAndLoadProfit();
	}

	/**
	 * 查询加载毛利分析
	 */
	private void queryAndLoadProfit() {
		// 设置界面精度
		String pk_group = WorkbenchEnvironment.getInstance().getGroupVO()
				.getPk_group();

		// 单据模板数据控制
		BillListData billlistdata = this.getProfitPanel().getBillListData();
		RecPlanPrecision.getInstance().setModelPrecision(pk_group,
				billlistdata.getBodyBillModel());
		billlistdata.setBodyValueVO(this.viewvo);
		billlistdata.getBodyBillModel().loadLoadRelationItemValue();
		billlistdata.getBodyBillModel().execLoadFormula();
	}
}
