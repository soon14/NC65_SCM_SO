package nc.ui.so.mreturncondition.ref;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UICheckBox;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;

/**
 * ?user> 功能： 日期：(2004-6-30 11:09:57)
 */
public class LargessFlagPane extends UIDialog implements IRefReturn {
  class IvjEventHandler implements java.awt.event.ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      if (e.getSource() == LargessFlagPane.this.getBtnCancel()) {
        LargessFlagPane.this.connEtoC1();
      }
      if (e.getSource() == LargessFlagPane.this.getBtnOk()) {
        LargessFlagPane.this.connEtoC2();
      }
    }
  }

  private static final long serialVersionUID = 1L;

  private IvjEventHandler ivjEventHandler = new IvjEventHandler();

  private UIButton m_btnCancel;

  private UIButton m_btnOK;

  private UIComboBox m_cbLogic;

  private UICheckBox m_ckbLargess;

  private UILabel m_lblName;

  private UIPanel m_paneAll;

  private UIPanel m_paneButton;

  private UIPanel m_paneContent;

  private String m_sReturnCode;

  private String m_sReturnName;

  /**
   * InvCodePane 构造子注解。
   */
  @SuppressWarnings("deprecation")
  public LargessFlagPane() {
    super();
    this.initialize();
  }

  @Override
  public java.lang.String getNameByCode(java.lang.String code) {
    return null;
  }

  @Override
  public java.lang.String getRefReturnCode() {
    return this.m_sReturnCode;
  }

  @Override
  public java.lang.String getRefReturnName() {
    return this.m_sReturnName;
  }

  void connEtoC1() {

    this.onCancel();

  }

  void connEtoC2() {
    this.onOk();
  }

  UIPanel getAllPane() {
    if (this.m_paneAll == null) {
      this.m_paneAll = new UIPanel();
      this.m_paneAll.setName("All");
      this.m_paneAll.setLayout(new java.awt.BorderLayout());
      UIPanel north = new UIPanel();
      north.setPreferredSize(new java.awt.Dimension(100, 10));
      UIPanel west = new UIPanel();
      west.setPreferredSize(new java.awt.Dimension(10, 100));
      UIPanel east = new UIPanel();
      east.setPreferredSize(new java.awt.Dimension(10, 100));
      UIPanel south = new UIPanel();
      south.setPreferredSize(new java.awt.Dimension(100, 10));
      this.m_paneAll.add(north, "North");
      this.m_paneAll.add(west, "West");
      this.m_paneAll.add(east, "East");
      this.m_paneAll.add(south, "South");
      this.m_paneAll.add(this.getInputContentPane(),
          java.awt.BorderLayout.CENTER);
      this.m_paneAll.add(this.getButtonPane(), java.awt.BorderLayout.SOUTH);
    }
    return this.m_paneAll;
  }

  UIButton getBtnCancel() {
    if (this.m_btnCancel == null) {
      this.m_btnCancel = new UIButton();
      this.m_btnCancel.setName("BtnCancel");
      this.m_btnCancel.setPreferredSize(new java.awt.Dimension(70, 20));
      this.m_btnCancel.setFont(new java.awt.Font("dialog", 0, 12));
      this.m_btnCancel.setText(NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0043")/*取消*/);
      this.m_btnCancel.setActionCommand("BtnCancel");
    }
    return this.m_btnCancel;
  }

  UIButton getBtnOk() {
    if (this.m_btnOK == null) {
      this.m_btnOK = new UIButton();
      this.m_btnOK.setName("BtnOk");
      this.m_btnOK.setPreferredSize(new java.awt.Dimension(70, 20));
      this.m_btnOK.setFont(new java.awt.Font("dialog", 0, 12));
      this.m_btnOK.setText(NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0044")/*确认*/);
      this.m_btnOK.setActionCommand("BtnOk");
    }
    return this.m_btnOK;
  }

  UILabel getUIName() {
    if (this.m_lblName == null) {
      this.m_lblName = new UILabel();
      this.m_lblName.setText(NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0063")/*赠品*/);
      this.m_lblName.setMaximumSize(new java.awt.Dimension(50, 22));
      this.m_lblName.setPreferredSize(new java.awt.Dimension(50, 22));
      this.m_lblName.setBounds(140, 1, 100, 22);
      this.m_lblName.setAlignmentY(150);
      this.m_lblName.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.m_lblName;
  }

  void onCancel() {
    this.closeCancel();
  }

  void onOk() {
    String sLogic = this.getUILogic().getSelectedItem().toString();
    if (this.getUILargess().isSelected()) {
      this.m_sReturnCode = sLogic + "isLargessFlag()";
      this.m_sReturnName = NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0088", null, new String[]{sLogic})/*{0}赠品()*/;
    }
    else {
      this.m_sReturnCode = sLogic + "(not isLargessFlag())";
      this.m_sReturnName = NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0089", null, new String[]{sLogic})/*{0}(not 赠品())*/;
    }
    this.closeOK();
  }

  private UIPanel getButtonPane() {
    if (this.m_paneButton == null) {
      this.m_paneButton = new UIPanel();
      this.m_paneButton.setName("Content");
      this.m_paneButton.setPreferredSize(new java.awt.Dimension(10, 30));
      this.m_paneButton.setLayout(this.getPnlButtonsFlowLayout());
      this.m_paneButton.add(this.getBtnOk(), this.getBtnOk().getName());
      this.m_paneButton.add(this.getBtnCancel(), this.getBtnCancel().getName());
    }
    return this.m_paneButton;
  }

  private UIPanel getInputContentPane() {
    if (this.m_paneContent == null) {
      this.m_paneContent = new UIPanel();
      this.m_paneContent.setName("Content");
      this.m_paneContent.setLayout(null);
      this.m_paneContent.add(this.getUILogic());
      this.m_paneContent.add(this.getUILargess());
      this.m_paneContent.add(this.getUIName());
    }
    return this.m_paneContent;
  }

  private java.awt.FlowLayout getPnlButtonsFlowLayout() {
    java.awt.FlowLayout ivjPnlButtonsFlowLayout = null;
    ivjPnlButtonsFlowLayout = new java.awt.FlowLayout();
    ivjPnlButtonsFlowLayout.setAlignment(java.awt.FlowLayout.RIGHT);
    return ivjPnlButtonsFlowLayout;
  }

  private UICheckBox getUILargess() {
    if (this.m_ckbLargess == null) {
      this.m_ckbLargess = new UICheckBox();
      this.m_ckbLargess.setMaximumSize(new java.awt.Dimension(50, 22));
      this.m_ckbLargess.setPreferredSize(new java.awt.Dimension(50, 22));
      this.m_ckbLargess.setBounds(100, 1, 30, 22);
      this.m_ckbLargess.setMinimumSize(new java.awt.Dimension(50, 22));
      this.m_ckbLargess.setSelected(true);
    }
    return this.m_ckbLargess;
  }

  private UIComboBox getUILogic() {
    if (this.m_cbLogic == null) {
      this.m_cbLogic = new UIComboBox();
      this.m_cbLogic.addItem(" and ");
      this.m_cbLogic.addItem(" or ");
      this.m_cbLogic.setSelectedIndex(0);
      this.m_cbLogic.setTranslate(true);
      this.m_cbLogic.setMaximumSize(new java.awt.Dimension(50, 22));
      this.m_cbLogic.setPreferredSize(new java.awt.Dimension(50, 22));
      this.m_cbLogic.setBounds(0, 1, 50, 22);
      this.m_cbLogic.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.m_cbLogic;
  }

  private void initConnections() {
    this.getBtnCancel().addActionListener(this.ivjEventHandler);
    this.getBtnOk().addActionListener(this.ivjEventHandler);
  }

  private void initialize() {
    this.setName("UIRefPane");
    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    this.setSize(400, 110);
    this.setContentPane(this.getAllPane());
    this.initConnections();
    this.setTitle(NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0046")/*业务函数参照*/);

  }
}
