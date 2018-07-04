package nc.ui.so.pub.dlg;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

import nc.vo.pubapp.AppContext;
import nc.vo.so.entry.ProfitVO;
import nc.vo.so.pub.enumeration.ListTemplateType;

import nc.desktop.ui.WorkbenchEnvironment;

import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillListData;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.so.pub.keyvalue.ListPanelValueUtils;
import nc.ui.so.pub.listener.SOListPanelTotalListener;
import nc.ui.so.pub.precision.ProfitPrecision;

/**
 * 查询结果对话框
 * 
 * @since 6.0
 * @version 2011-03-01 下午12:29:04
 * @author 么贵敬
 */
public class ProfitDlg extends UIDialog implements BillEditListener {

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
      if (e.getSource() == ProfitDlg.this.getBtnCancel()) {
        // 取消按钮执行事件
        ProfitDlg.this.doCancelAction();
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
  private BillListPanel profitPanel;

  /** UI JPanel */
  private JPanel uiContentPane;

  /** 主键ID */
  private ProfitVO[] viewvo;

  /**
   * 构造方法
   * 
   * @param parent 对话框
   * @param viewvo 毛利预估vo
   */
  public ProfitDlg(BillCardPanel parent, ProfitVO[] viewvo) {
    super(parent);
    this.viewvo = viewvo;
    this.initialize();
  }
  
  public ProfitDlg(BillCardPanel parent, ProfitVO[] viewvo, boolean reset) {
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
          .getStrByID("4006004_0", "04006004-0000")/*@res "关闭"*/);
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
      // yixl 2013-03-18 去掉关闭按钮
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
   * @param btnCancel 取消按钮
   */
  public void setBtnCancel(UIButton btnCancel) {
    this.btnCancel = btnCancel;
  }

  /**
   * 按钮panel setter
   * 
   * @param btnUIPanel 按钮panel
   */
  public void setBtnUIPanel(UIPanel btnUIPanel) {
    this.btnUIPanel = btnUIPanel;
  }

  /**
   * BillListPanel setter
   * 
   * @param profitPanel 列表模板
   */
  public void setProfitPanel(BillListPanel profitPanel) {
    this.profitPanel = profitPanel;
  }

  /**
   * UI content
   * 
   * @param uiContentPane JPanel
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
    if (null == this.profitPanel) {
      this.profitPanel = new BillListPanel();
      this.profitPanel.setName("profitPanel");
      this.profitPanel
          .setToolTipText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006004_0", "04006004-0001")/*@res "销售毛利分析"*/);
      String operator =
          WorkbenchEnvironment.getInstance().getLoginUser().getPrimaryKey();
      String pk_group =
          WorkbenchEnvironment.getInstance().getGroupVO().getPk_group();
      this.profitPanel.loadTemplet("40060301", null, operator, pk_group,
          "profit");

      this.profitPanel.addBodyEditListener(this);
      this.profitPanel.getHeadTable().setSelectionMode(
          javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      this.profitPanel.getHeadTable().setEnabled(false);
      BillScrollPane panle = this.profitPanel.getChildListPanel();

      ListPanelValueUtils listutils =
          new ListPanelValueUtils(this.profitPanel, ListTemplateType.SUB);
      SOListPanelTotalListener totallis =
          new SOListPanelTotalListener(listutils);
      this.profitPanel.getBodyBillModel().addTotalListener(totallis);
      panle.setTotalRowShow(true);
      this.profitPanel.setMultiSelect(false);
      this.profitPanel.setEnabled(true);

    }
    return this.profitPanel;
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
    this.setName("ProfitDlg");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    Dimension dd = Toolkit.getDefaultToolkit().getScreenSize();
    int w = ProfitDlg.DEFAULTWITH;
    int h = ProfitDlg.DEFAUTHIGH;
    if (dd != null) {
      w = (int) dd.getWidth() * ProfitDlg.NOLECULE / ProfitDlg.DENOMINATOR;
      h = (int) dd.getHeight() * ProfitDlg.NOLECULE / ProfitDlg.DENOMINATOR;
    }
    w = w > ProfitDlg.DEFAULTWITH || w <= 0 ? ProfitDlg.DEFAULTWITH : w;
    h = h > ProfitDlg.DEFAUTHIGH || h <= 0 ? ProfitDlg.DEFAUTHIGH : h;
    this.setSize(w, h);
    this.setResizable(true);
    this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006004_0", "04006004-0001")/*@res "销售毛利分析"*/);
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
    String pk_group = AppContext.getInstance().getPkGroup();

    // 单据模板数据控制
    BillListData billlistdata = this.getProfitPanel().getBillListData();
    ProfitPrecision.getInstance().setModelPrecision(pk_group,
        billlistdata.getBodyBillModel());
    billlistdata.setBodyValueVO(this.viewvo);
    billlistdata.getBodyBillModel().loadLoadRelationItemValue();
    billlistdata.getBodyBillModel().execLoadFormula();
  }
}
