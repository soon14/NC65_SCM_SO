package nc.ui.so.mreturnpolicy.ref;

import javax.swing.JButton;
import javax.swing.JPanel;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.formulaset.FormulaEditorModel;

/**
 * 此处插入类型说明。 创建日期：(2004-3-9 15:46:12)
 *
 * @author：左小军
 */
public class FomulaDialog extends nc.ui.pub.beans.UIDialog {
  class IvjEventHandler implements java.awt.event.ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      if (e.getSource() == FomulaDialog.this.getBtnCancel()) {
        FomulaDialog.this.connEtoC1();
      }
      if (e.getSource() == FomulaDialog.this.getBtnOk()) {
        FomulaDialog.this.connEtoC2();
      }
    }
  }

  private static final long serialVersionUID = 1L;

  private javax.swing.JButton ivjBtnCancel;

  private javax.swing.JButton ivjBtnOk;

  private IvjEventHandler ivjEventHandler = new IvjEventHandler();

  private nc.ui.pub.beans.UIPanel ivjPnlButtons;

  private javax.swing.JPanel ivjUIDialogContentPane;

  private FormulaRefView m_fvFormula;

  private FormulaEditorModel m_model;

  /**
   * FomulaDialog 构造子注解。
   */
  @SuppressWarnings("deprecation")
  public FomulaDialog() {
    super();
    this.initialize();
  }

  public FomulaDialog(java.awt.Container parent) {
    super(parent);
    this.initialize();
  }

  public FomulaDialog(java.awt.Container parent, String title) {
    super(parent, title);
  }

  public FomulaDialog(java.awt.Frame owner) {
    super(owner);
  }

  public FomulaDialog(java.awt.Frame owner, String title) {
    super(owner, title);
  }

  public static void main(java.lang.String[] args) {
    FomulaDialog aFomulaDialog;
    aFomulaDialog = new FomulaDialog();
    aFomulaDialog.setModal(true);
    aFomulaDialog.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        System.exit(0);
      }
    });
    java.awt.Insets insets = aFomulaDialog.getInsets();
    aFomulaDialog.setSize(
        aFomulaDialog.getWidth() + insets.left + insets.right,
        aFomulaDialog.getHeight() + insets.top + insets.bottom);
    aFomulaDialog.setVisible(true);
  }

  public String getFormulaDesc() {
    return this.getFormulaView().getFormulaName();
  }

  public String getFormulaShow() {
    return this.getFormulaView().getFormulaCode();
  }

  public FormulaEditorModel getModel() {
    if (this.m_model == null) {
      this.m_model = new FormulaEditorModel();
    }
    return this.m_model;
  }

  /**
   * Comment
   */
  public void onCancel() {
    this.closeCancel();
    return;
  }

  public void onOk() {
    if ((this.getFormulaView().getFormulaCode().trim() == null)
        || (this.getFormulaView().getFormulaCode().trim().length() == 0)) {
      MessageDialog.showErrorDlg(this, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0016")/*@res "错误"*/, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0017")/*@res "请指定要进行处理的公式"*/);
      return;
    }
    this.getFormulaView().checkFormula();
    this.closeOK();
  }

  public void setCurFormulaShow(String[] strFom) {
    this.getFormulaView().setCurFormula(strFom);
  }

  public void setFormulaDesc(String strFom) {
    this.getFormulaView().setDefFormula(strFom);
  }

  public void setModel(FormulaEditorModel model) {
    this.m_model = model;
    this.getFormulaView().setModel(this.m_model);
  }

  void connEtoC1() {
    this.onCancel();

  }

  void connEtoC2() {
    this.onOk();
  }

  JButton getBtnCancel() {
    if (this.ivjBtnCancel == null) {
      this.ivjBtnCancel = new javax.swing.JButton();
      this.ivjBtnCancel.setName("BtnCancel");
      this.ivjBtnCancel.setPreferredSize(new java.awt.Dimension(70, 20));
      this.ivjBtnCancel.setFont(new java.awt.Font("dialog", 0, 12));
      this.ivjBtnCancel.setText(NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0043")/*取消*/);
      this.ivjBtnCancel.setActionCommand("BtnCancel");
    }
    return this.ivjBtnCancel;
  }

  JButton getBtnOk() {
    if (this.ivjBtnOk == null) {
      this.ivjBtnOk = new javax.swing.JButton();
      this.ivjBtnOk.setName("BtnOk");
      this.ivjBtnOk.setPreferredSize(new java.awt.Dimension(70, 20));
      this.ivjBtnOk.setFont(new java.awt.Font("dialog", 0, 12));
      this.ivjBtnOk.setText(NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0044")/*确认*/);
      this.ivjBtnOk.setActionCommand("BtnOk");

    }
    return this.ivjBtnOk;
  }

  nc.ui.pub.beans.UIPanel getPnlButtons() {
    if (this.ivjPnlButtons == null) {

      this.ivjPnlButtons = new nc.ui.pub.beans.UIPanel();
      this.ivjPnlButtons.setName("PnlButtons");
      this.ivjPnlButtons.setPreferredSize(new java.awt.Dimension(10, 30));
      this.ivjPnlButtons.setBounds(10, 435, 700, 30);
      this.ivjPnlButtons.setLayout(this.getPnlButtonsFlowLayout());
      this.getPnlButtons().add(this.getBtnOk(), this.getBtnOk().getName());
      this.getPnlButtons().add(this.getBtnCancel(),
          this.getBtnCancel().getName());
    }
    return this.ivjPnlButtons;
  }

  JPanel getUIDialogContentPane() {
    if (this.ivjUIDialogContentPane == null) {

      this.ivjUIDialogContentPane = new javax.swing.JPanel();
      this.ivjUIDialogContentPane.setName("UIDialogContentPane");
      this.ivjUIDialogContentPane.setLayout(null);
      this.getUIDialogContentPane().add(this.getFormulaView(), "Center");
      this.getUIDialogContentPane().add(this.getPnlButtons(), "South");
    }
    return this.ivjUIDialogContentPane;
  }

  private FormulaRefView getFormulaView() {
    if (this.m_fvFormula == null) {
      this.m_fvFormula = new FormulaRefView();
      this.m_fvFormula.setName("FormulaEdit");
      this.m_fvFormula.setBounds(10, 10, 693, 425);

    }
    return this.m_fvFormula;
  }

  /**
   * 返回 PnlButtonsFlowLayout 特性值。
   *
   * @return java.awt.FlowLayout
   */
  /* 警告：此方法将重新生成。 */
  private java.awt.FlowLayout getPnlButtonsFlowLayout() {
    java.awt.FlowLayout ivjPnlButtonsFlowLayout = null;
    ivjPnlButtonsFlowLayout = new java.awt.FlowLayout();
    ivjPnlButtonsFlowLayout.setAlignment(java.awt.FlowLayout.RIGHT);
    ivjPnlButtonsFlowLayout.setVgap(5);
    ivjPnlButtonsFlowLayout.setHgap(8);

    return ivjPnlButtonsFlowLayout;
  }

  private void initConnections() {
    this.getBtnCancel().addActionListener(this.ivjEventHandler);
    this.getBtnOk().addActionListener(this.ivjEventHandler);
  }

  private void initialize() {
    this.setName("FomulaDialog");
    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    this.setResizable(false);

    this.setSize(720, 500);

    this.setContentPane(this.getUIDialogContentPane());
    this.initConnections();
    this.setTitle(NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0052")/*公式编辑*/);

  }
}