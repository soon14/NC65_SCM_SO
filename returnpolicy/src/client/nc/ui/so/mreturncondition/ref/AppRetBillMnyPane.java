package nc.ui.so.mreturncondition.ref;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITextField;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 申请退货单据金额 日期：(2004-6-30 11:09:57)
 */
public class AppRetBillMnyPane extends UIDialog implements IRefReturn {
  class IvjEventHandler implements ActionListener {
    @Override
    public void actionPerformed(final java.awt.event.ActionEvent e) {
      if (e.getSource() == AppRetBillMnyPane.this.getBtnCancel()) {
        AppRetBillMnyPane.this.connEtoC1();
      }
      if (e.getSource() == AppRetBillMnyPane.this.getBtnOk()) {
        AppRetBillMnyPane.this.connEtoC2();
      }
    }
  }

  private static final long serialVersionUID = 1L;

  private IvjEventHandler ivjEventHandler = new IvjEventHandler();

  private UIButton m_btnCancel;

  private UIButton m_btnOK;

  private UIComboBox m_cbLogic;

  private UIComboBox m_cbOperate;

  private UILabel m_lblName;

  private UIPanel m_paneAll;

  private UIPanel m_paneButton;

  private UIPanel m_paneContent;

  private String m_sReturnCode;

  private String m_sReturnName;

  private UITextField m_txtValue;

  @SuppressWarnings("deprecation")
  public AppRetBillMnyPane() {
    super();
    this.initialize();
  }

  @Override
  public java.lang.String getNameByCode(final java.lang.String code) {
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

  public void onCancel() {
    this.closeCancel();
  }

  public void onOk() {
    String sLogic = this.getUILogic().getSelectedItem().toString();
    String sName = "getAppRetBillMny()";
    String sOperate = this.getUIOperate().getSelectedItem().toString();
    String sValue = this.getUIValue().getText().trim();
    if (VOChecker.isEmpty(sValue)) {
      MessageDialog
          .showErrorDlg(
              this,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006006_0", "04006006-0014")/*@res "警告"*/,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006006_0", "04006006-0015")/*@res "请输入值!"*/);
      return;
    }
    sValue = sValue.replaceAll(",", "");
    this.m_sReturnCode = sLogic + sName + sOperate + sValue;
    this.m_sReturnName =
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0042", null,
            new String[] {
              sLogic, sOperate, sValue
            })/*{0}申请退货单据金额(){1}{2}*/;
    this.closeOK();
  }

  void connEtoC1() {
    this.onCancel();
  }

  void connEtoC2() {
    this.onOk();
  }

  UIButton getBtnCancel() {
    if (this.m_btnCancel == null) {
      this.m_btnCancel = new UIButton();
      this.m_btnCancel.setName("BtnCancel");
      this.m_btnCancel.setPreferredSize(new Dimension(70, 20));
      this.m_btnCancel.setFont(new Font("dialog", 0, 12));
      this.m_btnCancel.setText(NCLangRes.getInstance().getStrByID("4006006_0",
          "04006006-0043")/*取消*/);
      this.m_btnCancel.setActionCommand("BtnCancel");
    }
    return this.m_btnCancel;
  }

  UIButton getBtnOk() {
    if (this.m_btnOK == null) {
      this.m_btnOK = new UIButton();
      this.m_btnOK.setName("BtnOk");
      this.m_btnOK.setPreferredSize(new Dimension(70, 20));
      this.m_btnOK.setFont(new Font("dialog", 0, 12));
      this.m_btnOK.setText(NCLangRes.getInstance().getStrByID("4006006_0",
          "04006006-0044")/*确认*/);
      this.m_btnOK.setActionCommand("BtnOk");
    }
    return this.m_btnOK;
  }

  private UIPanel getAllPane() {

    if (this.m_paneAll == null) {
      this.m_paneAll = new UIPanel();
      this.m_paneAll.setName("All");
      this.m_paneAll.setLayout(new BorderLayout());
      final UIPanel north = new UIPanel();
      north.setPreferredSize(new Dimension(100, 10));
      final UIPanel west = new UIPanel();
      west.setPreferredSize(new Dimension(10, 100));
      final UIPanel east = new UIPanel();
      east.setPreferredSize(new Dimension(10, 100));
      final UIPanel south = new UIPanel();
      south.setPreferredSize(new Dimension(100, 10));
      this.m_paneAll.add(north, "North");
      this.m_paneAll.add(west, "West");
      this.m_paneAll.add(east, "East");
      this.m_paneAll.add(south, "South");
      this.m_paneAll.add(this.getInputContentPane(), BorderLayout.CENTER);
      this.m_paneAll.add(this.getButtonPane(), BorderLayout.SOUTH);
    }
    return this.m_paneAll;
  }

  private UIPanel getButtonPane() {
    if (this.m_paneButton == null) {
      this.m_paneButton = new UIPanel();
      this.m_paneButton.setName("Content");
      this.m_paneButton.setPreferredSize(new Dimension(10, 30));
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
      this.m_paneContent.add(this.getUIName());
      this.m_paneContent.add(this.getUIOperate());
      this.m_paneContent.add(this.getUIValue());
      this.m_paneContent.setSize(380, 30);
    }
    return this.m_paneContent;
  }

  private FlowLayout getPnlButtonsFlowLayout() {
    FlowLayout ivjPnlButtonsFlowLayout = null;
    /* 创建部件 */
    ivjPnlButtonsFlowLayout = new FlowLayout();
    ivjPnlButtonsFlowLayout.setAlignment(FlowLayout.RIGHT);

    return ivjPnlButtonsFlowLayout;
  }

  private UIComboBox getUILogic() {
    if (this.m_cbLogic == null) {
      this.m_cbLogic = new UIComboBox();
      this.m_cbLogic.addItem(" and ");
      this.m_cbLogic.addItem(" or ");
      this.m_cbLogic.setSelectedIndex(0);
      this.m_cbLogic.setTranslate(true);
      this.m_cbLogic.setMaximumSize(new Dimension(50, 22));
      this.m_cbLogic.setPreferredSize(new Dimension(50, 22));
      this.m_cbLogic.setBounds(0, 1, 50, 22);
      this.m_cbLogic.setMinimumSize(new Dimension(50, 22));
    }
    return this.m_cbLogic;
  }

  private UILabel getUIName() {
    if (this.m_lblName == null) {
      this.m_lblName = new UILabel();
      this.m_lblName.setText(NCLangRes.getInstance().getStrByID("4006006_0",
          "04006006-0045", null, new String[] {})/*申请退货单据金额:*/);
      this.m_lblName.setMaximumSize(new Dimension(50, 22));
      this.m_lblName.setPreferredSize(new Dimension(50, 22));
      this.m_lblName.setBounds(60, 1, 100, 22);
      this.m_lblName.setAlignmentY(150);
      this.m_lblName.setMinimumSize(new Dimension(50, 22));
    }
    return this.m_lblName;
  }

  private UIComboBox getUIOperate() {
    if (this.m_cbOperate == null) {
      this.m_cbOperate = new UIComboBox();
      this.m_cbOperate.addItem("=");
      this.m_cbOperate.addItem("!=");
      this.m_cbOperate.addItem(">");
      this.m_cbOperate.addItem(">=");
      this.m_cbOperate.addItem("<");
      this.m_cbOperate.addItem("<=");
      this.m_cbOperate.setSelectedIndex(0);
      this.m_cbOperate.setTranslate(true);
      this.m_cbOperate.setMaximumSize(new Dimension(50, 22));
      this.m_cbOperate.setPreferredSize(new Dimension(50, 22));
      this.m_cbOperate.setBounds(170, 1, 50, 22);
      this.m_cbOperate.setMinimumSize(new Dimension(50, 22));
    }
    return this.m_cbOperate;
  }

  private UITextField getUIValue() {
    if (this.m_txtValue == null) {
      this.m_txtValue = new UITextField();
      this.m_txtValue.setMaximumSize(new Dimension(50, 22));
      this.m_txtValue.setPreferredSize(new Dimension(50, 22));
      this.m_txtValue.setBounds(230, 1, 100, 22);
      this.m_txtValue.setMinimumSize(new Dimension(50, 22));
      this.m_txtValue.setTextType("TextDbl");
    }
    return this.m_txtValue;
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
    this.setTitle(NCLangRes.getInstance().getStrByID("4006006_0",
        "04006006-0046")/*业务函数参照*/);
  }
}
