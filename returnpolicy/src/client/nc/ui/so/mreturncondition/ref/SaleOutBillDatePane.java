package nc.ui.so.mreturncondition.ref;

import java.awt.FlowLayout;

import javax.swing.WindowConstants;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;

/**
 * ?user> 功能：源出库单日期 日期：(2004-6-30 11:09:57)
 */
public class SaleOutBillDatePane extends UIDialog implements IRefReturn {
  class IvjEventHandler implements java.awt.event.ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      if (e.getSource() == SaleOutBillDatePane.this.getBtnCancel()) {
        SaleOutBillDatePane.this.connEtoC1();
      }
      if (e.getSource() == SaleOutBillDatePane.this.getBtnOk()) {
        SaleOutBillDatePane.this.connEtoC2();
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

  // private UIComboBox m_cbValue;
  private UIRefPane m_refDate;

  private String m_sReturnCode;

  private String m_sReturnName;

  /**
   * InvCodePane 构造子注解。
   */
  @SuppressWarnings("deprecation")
  public SaleOutBillDatePane() {
    super();
    this.initialize();
  }

  /**
   * ?user> 功能： 参数： 返回： 例外： 日期：(2004-7-1 13:04:39) 修改日期，修改人，修改原因，注释标志：
   * 
   * @return java.lang.String
   * @param code
   *          java.lang.String
   */
  @Override
  public java.lang.String getNameByCode(java.lang.String code) {
    return null;
  }

  /**
   * ?user> 功能： 参数： 返回： 例外： 日期：(2004-7-1 8:34:59) 修改日期，修改人，修改原因，注释标志：
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getRefReturnCode() {
    return this.m_sReturnCode;
  }

  /**
   * ?user> 功能： 参数： 返回： 例外： 日期：(2004-7-1 8:34:59) 修改日期，修改人，修改原因，注释标志：
   * 
   * @return java.lang.String
   */
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
      this.m_btnCancel.setText(NCLangRes.getInstance().getStrByID("4006006_0",
          "04006006-0043")/* 取消 */);
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
      this.m_btnOK.setText(NCLangRes.getInstance().getStrByID("4006006_0",
          "04006006-0044")/* 确认 */);
      this.m_btnOK.setActionCommand("BtnOk");
    }
    return this.m_btnOK;
  }

  UIPanel getButtonPane() {
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

  UIPanel getInputContentPane() {

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

  FlowLayout getPnlButtonsFlowLayout() {
    java.awt.FlowLayout ivjPnlButtonsFlowLayout = null;
    /* 创建部件 */
    ivjPnlButtonsFlowLayout = new java.awt.FlowLayout();
    ivjPnlButtonsFlowLayout.setAlignment(java.awt.FlowLayout.RIGHT);

    return ivjPnlButtonsFlowLayout;
  }

  void onCancel() {
    this.closeCancel();
  }

  void onOk() {
    String sLogic = this.getUILogic().getSelectedItem().toString();
    String sName = "getSaleOutBillDate()";
    String sOperate = this.getUIOperate().getSelectedItem().toString();
    String sValue = this.getUIValue().getText().trim();
    String sValueCode = "";

    if (sValue == null || sValue.length() == 0) {
      MessageDialog
          .showErrorDlg(
              this,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006006_0", "04006006-0014")/* @res "警告" */,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006006_0", "04006006-0022")/* @res "请输入函数值!" */);
      return;
    }
    if (sValue.length() == 10) {
      sValueCode =
          sValue.substring(0, 4) + sValue.substring(5, 7)
              + sValue.substring(8, 10);
    }
    else {
      MessageDialog
          .showErrorDlg(
              this,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006006_0", "04006006-0014")/* @res "警告" */,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006006_0", "04006006-0023")/* @res "输入日期的格式不正确!" */);
      return;
    }
    this.m_sReturnCode = sLogic + sName + sOperate + "\"" + sValueCode + "\"";
    this.m_sReturnName =
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0107", null,
            new String[] {
              sLogic, sOperate, sValue
            })/* {0}源出库单日期(){1}"{2}" */;
    this.closeOK();
  }

  /**
   * ?user> 功能： 参数： 返回： 例外： 日期：(2004-6-30 11:30:06) 修改日期，修改人，修改原因，注释标志：
   */
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

  /**
   * ?user> 功能： 参数： 返回： 例外： 日期：(2004-6-30 11:27:40) 修改日期，修改人，修改原因，注释标志：
   */
  private UILabel getUIName() {
    if (this.m_lblName == null) {
      this.m_lblName = new UILabel();
      this.m_lblName.setText(NCLangRes.getInstance().getStrByID("4006006_0",
          "04006006-0108")/* 源出库单日期: */);
      this.m_lblName.setMaximumSize(new java.awt.Dimension(50, 22));
      this.m_lblName.setPreferredSize(new java.awt.Dimension(50, 22));
      this.m_lblName.setBounds(60, 1, 100, 22);
      this.m_lblName.setAlignmentY(150);
      this.m_lblName.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.m_lblName;
  }

  /**
   * ?user> 功能： 参数： 返回： 例外： 日期：(2004-6-30 11:30:06) 修改日期，修改人，修改原因，注释标志：
   */
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
      this.m_cbOperate.setMaximumSize(new java.awt.Dimension(50, 22));
      this.m_cbOperate.setPreferredSize(new java.awt.Dimension(50, 22));
      this.m_cbOperate.setBounds(170, 1, 50, 22);
      this.m_cbOperate.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.m_cbOperate;
  }

  /**
   * ?user> 功能： 参数： 返回： 例外： 日期：(2004-6-30 11:27:40) 修改日期，修改人，修改原因，注释标志：
   */
  private UIRefPane getUIValue() {
    if (this.m_refDate == null) {
      this.m_refDate = new UIRefPane();
      this.m_refDate.setRefType(5);
      this.m_refDate.setRefNodeName("日历");
      this.m_refDate.setMaximumSize(new java.awt.Dimension(50, 22));
      this.m_refDate.setPreferredSize(new java.awt.Dimension(50, 22));
      this.m_refDate.setBounds(230, 1, 100, 22);
      this.m_refDate.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.m_refDate;
  }

  private void initConnections() {
    this.getBtnCancel().addActionListener(this.ivjEventHandler);
    this.getBtnOk().addActionListener(this.ivjEventHandler);
  }

  private void initialize() {
    this.setName("UIRefPane");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setSize(400, 110);
    this.setContentPane(this.getAllPane());
    this.initConnections();
    // this.setFuncName(new String[] {
    // "getInvCode()", "存货编码"
    // });
    this.setTitle(nc.ui.ml.NCLangRes.getInstance().getStrByID("4006006_0",
        "04006006-0140")/* @res "业务函数参照" */);
  }
}
