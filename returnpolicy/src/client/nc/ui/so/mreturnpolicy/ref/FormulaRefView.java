package nc.ui.so.mreturnpolicy.ref;

/**
 * 此处插入类型说明。 创建日期：(2002-3-25 14:27:51)
 * 
 * @作者：张春明
 */
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.EtchedBorder;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.pub.beans.UITextArea;
import nc.ui.pub.formulaset.FormulaEditorModel;
import nc.ui.pub.formulaset.TextAreaEdit;

public class FormulaRefView extends UIPanel {

  class IvjEventHandler implements java.awt.event.ActionListener {

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      if (e.getSource() == FormulaRefView.this.getUIBnLeft()) {
        FormulaRefView.this.connEtoC5();
      }
      if (e.getSource() == FormulaRefView.this.getUIBnRight()) {
        FormulaRefView.this.connEtoC6();
      }
      if (e.getSource() == FormulaRefView.this.getUICmbYwFunc()) {
        FormulaRefView.this.connEtoC11();
      }
      if (e.getSource() == FormulaRefView.this.getUIBnFormulaCheck()) {
        FormulaRefView.this.connEtoC15();
      }
      if (e.getSource() == FormulaRefView.this.getUIBnAnd()) {
        FormulaRefView.this.connEtoC16();
      }
      if (e.getSource() == FormulaRefView.this.getUIBnOr()) {
        FormulaRefView.this.connEtoC17();
      }
      if (e.getSource() == FormulaRefView.this.getUIBnNot()) {
        FormulaRefView.this.connEtoC18();
      }
      if (e.getSource() == FormulaRefView.this.getUIBnBusiAdd()) {
        FormulaRefView.this.connEtoC19();
      }
      if (e.getSource() == FormulaRefView.this.getUIBnBusiDel()) {
        FormulaRefView.this.connEtoC20();
      }
      if (e.getSource() == FormulaRefView.this.getUIBnBusiUndo()) {
        FormulaRefView.this.connEtoC21();
      }
      if (e.getSource() == FormulaRefView.this.getUIBnBusiClear()) {
        FormulaRefView.this.connEtoC22();
      }
    }
  }

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private IvjEventHandler ivjEventHandler = new IvjEventHandler();

  private UIPanel ivjPaneBnShow;

  private UIPanel ivjPaneEdit;

  private UIPanel ivjPaneExplain;

  private UIPanel ivjPaneOper;

  private UIPanel ivjPaneTitle;

  private UIButton ivjUIBnAnd;

  private UIButton ivjUIBnBusiAdd;

  private UIButton ivjUIBnBusiClear;

  // 程序内部产生的变量

  private UIButton ivjUIBnBusiDel;

  private UIButton ivjUIBnBusiUndo;

  private UIButton ivjUIBnFormulaCheck;

  private UIButton ivjUIBnLeft;

  private UIButton ivjUIBnNot;

  private UIButton ivjUIBnOr;

  private UIButton ivjUIBnRight;

  private UIComboBox ivjUICmbYwFunc;

  private UILabel ivjUILabFuncSm;

  private UILabel ivjUILabGsEdit;

  private UILabel ivjUILabYwFunc;

  private UIScrollPane ivjUIScrollPane1;

  private UIScrollPane ivjUIScrollPane2;

  private TextAreaEdit ivjUITxtFormulaEdit;

  private UITextArea ivjUITxtFuncExplain;

  private FormulaEditorModel m_model;

  private StringBuffer m_sFormulaCode = new StringBuffer("");

  private String m_sLastValue;

  private List<String[]> m_vFormula = new ArrayList<String[]>();

  /**
   * FormulaEditPan 构造子注解。
   */
  public FormulaRefView() {
    super();
    this.initialize();
  }

  /**
   * FormulaEditPan 构造子注解。
   * 
   * @param p0
   *          boolean
   */
  public FormulaRefView(boolean p0) {
    super(p0);
  }

  /**
   * FormulaEditPan 构造子注解。
   * 
   * @param p0
   *          java.awt.LayoutManager
   */
  public FormulaRefView(java.awt.LayoutManager p0) {
    super(p0);
  }

  /**
   * FormulaEditPan 构造子注解。
   * 
   * @param p0
   *          java.awt.LayoutManager
   * @param p1
   *          boolean
   */
  public FormulaRefView(java.awt.LayoutManager p0, boolean p1) {
    super(p0, p1);
  }

  /**
   * 主入口点 - 当部件作为应用程序运行时，启动这个部件。
   * 
   * @param args
   *          java.lang.String[]
   */
  public static void main(java.lang.String[] args) {
    javax.swing.JFrame frame = new javax.swing.JFrame();
    nc.ui.pub.formulaset.FormulaEditorView aFormulaEditPan;
    aFormulaEditPan = new nc.ui.pub.formulaset.FormulaEditorView();
    frame.setContentPane(aFormulaEditPan);
    frame.setSize(aFormulaEditPan.getSize());
    frame.addWindowListener(new java.awt.event.WindowAdapter() {

      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        System.exit(0);
      }
    });
    frame.setVisible(true);
    java.awt.Insets insets = frame.getInsets();
    frame.setSize(frame.getWidth() + insets.left + insets.right,
        frame.getHeight() + insets.top + insets.bottom);
    frame.setVisible(true);
  }

  /**
   * 对公式进行合法性检查。 创建日期：(2002-4-19 15:21:41)
   * 
   * @return java.lang.String
   */
  public void checkFormula() {
    String sFormula = this.m_sFormulaCode.toString();
    try {
      nc.ui.bank_cvp.formulainterface.RefCompilerClient.check(sFormula);
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
  }

  /**
   * 获得将要用来存储或进行公式运算的公式 创建日期：(2002-4-19 9:23:16)
   * 
   * @return java.lang.String
   */
  public String getFormula() {
    if (this.m_model.isFormulaEditFunName()) {
      // 界面编辑框可以见到的共识
      String sFormulaEdit = this.getUITxtFormulaEdit().getText();
      // 转换成用字母业务函数表达的公式
      String sFormula = this.changeFormula(sFormulaEdit, false);
      return sFormula;
    }
    return this.getUITxtFormulaEdit().getText();
  }

  /**
   * 获得将要用来存储或进行公式运算的公式 创建日期：(2002-4-19 9:23:16)
   * 
   * @return java.lang.String
   */
  public String getFormulaCode() {
    return this.m_sFormulaCode.toString();
  }

  /**
   * 获得将要用来存储或进行公式运算的公式 创建日期：(2002-4-19 9:23:16)
   * 
   * @return java.lang.String
   */
  public String getFormulaName() {
    return this.getUITxtFormulaEdit().getText();
  }

  /**
   * 返回 UITxtFormulaEdit 特性值。
   * 
   * @return nc.ui.bank_cvp.viewer.TextAreaEdit
   */
  public TextAreaEdit getUITxtFormulaEdit() {
    if (this.ivjUITxtFormulaEdit == null) {
      this.ivjUITxtFormulaEdit = new TextAreaEdit();
      this.ivjUITxtFormulaEdit.setName("UITxtFormulaEdit");
      this.ivjUITxtFormulaEdit.setBorder(new EtchedBorder());
      this.ivjUITxtFormulaEdit.setBounds(0, 0, 4, 20);
      this.ivjUITxtFormulaEdit.setLineWrap(true);
      this.ivjUITxtFormulaEdit.setAutoscrolls(true);
      this.ivjUITxtFormulaEdit.setEditable(false);
    }
    return this.ivjUITxtFormulaEdit;
  }

  /**
   * 此处插入方法说明。 创建日期：(2002-3-28 14:09:36)
   * 
   * @param sOld
   *          java.lang.String
   * @param iStart
   *          int
   * @param iEnd
   *          int
   */
  public String replaceString(String sOld, int iStart, int iEnd) {
    String f = sOld.substring(0, iStart);
    String b = sOld.substring(iEnd + 1, sOld.length());
    StringBuffer re = new StringBuffer();
    for (int i = 0; i < iEnd - iStart + 1; i++) {
      re.append("$");
    }
    if (re.length() > 0) {
      return f + re.toString() + b;
    }
    return f + b;
  }

  /**
   * 设置当前的公式 创建日期：(2002-4-24 16:15:34)
   * 
   * @param sFormula
   *          java.lang.String
   */
  public void setCurFormula(String[] sFormula) {
    if (sFormula == null || sFormula.length == 0) {
      return;
    }
    String sCode = sFormula[0];
    String sName = sFormula[1];

    int iandcode = 0;
    int iorcode = 0;
    int iandname = 0;
    int iorname = 0;
    String sc = null;
    String sn = null;
    String sOper = null;
    this.m_vFormula.clear();
    int i = 0;
    int j = 0;
    for (; i < sCode.length() && j < sName.length();) {
      iandcode = sCode.indexOf(" and ", i);
      iorcode = sCode.indexOf(" or ", i);
      iandname = sName.indexOf(" and ", j);
      iorname = sName.indexOf(" or ", j);
      sOper = null;

      if (iandcode == -1 && iorcode == -1) {
        sc = sCode.substring(i, sCode.length());
        i = sCode.length();
      }
      else if (iandcode == -1 && iorcode != -1) {
        sc = sCode.substring(i, iorcode);
        i = iorcode + 4;
        sOper = " or ";
      }
      else if (iandcode != -1 && iorcode == -1) {
        sc = sCode.substring(i, iandcode);
        i = iandcode + 5;
        sOper = " and ";
      }
      else if (iandcode != -1 && iorcode != -1 && iandcode < iorcode) {
        sc = sCode.substring(i, iandcode);
        i = iandcode + 5;
        sOper = " and ";
      }
      else if (iandcode != -1 && iorcode != -1 && iandcode > iorcode) {
        sc = sCode.substring(i, iorcode);
        i = iorcode + 4;
        sOper = " or ";
      }

      if (iandname == -1 && iorname == -1) {
        sn = sName.substring(j, sName.length());
        j = sName.length();
      }
      else if (iandname == -1 && iorname != -1) {
        sn = sName.substring(j, iorname);
        j = iorname + 4;
      }
      else if (iandname != -1 && iorname == -1) {
        sn = sName.substring(j, iandname);
        j = iandname + 5;
      }
      else if (iandname != -1 && iorname != -1 && iandname < iorname) {
        sn = sName.substring(j, iandname);
        j = iandname + 5;
      }
      else if (iandname != -1 && iorname != -1 && iandname > iorname) {
        sn = sName.substring(j, iorname);
        j = iorname + 4;
      }
      this.m_vFormula.add(new String[] {
        sc, sn
      });
      if (sOper != null && sOper.length() > 0) {
        this.m_vFormula.add(new String[] {
          sOper, sOper
        });
      }
    }
    this.setFormula();
  }

  /**
   * 此处插入方法说明。 创建日期：(2002-4-24 16:15:34)
   * 
   * @param sFormula
   *          java.lang.String
   */
  public void setDefFormula(String sFormula) {
    String sFormulaEdit = null;
    if (this.m_model.isFormulaEditFunName()) {
      sFormulaEdit = this.changeFormula(sFormula, true);
    }
    else {
      sFormulaEdit = sFormula;
    }
    this.getUITxtFormulaEdit().setText(sFormulaEdit);
  }

  /**
   * 此处插入方法说明。 创建日期：(2003-11-18 9:11:55)
   * 
   * @param enable
   *          boolean
   */
  @Override
  public void setEnabled(boolean enable) {
    this.getUIBnLeft().setEnabled(enable);
    this.getUIBnRight().setEnabled(enable);
    this.getUIBnAnd().setEnabled(enable);
    this.getUIBnOr().setEnabled(enable);
    this.getUIBnNot().setEnabled(enable);
    this.getUIBnBusiAdd().setEnabled(enable);
    this.getUIBnBusiDel().setEnabled(enable);
    this.getUIBnBusiUndo().setEnabled(enable);
    this.getUIBnBusiClear().setEnabled(enable);
    this.getUITxtFormulaEdit().setEditable(enable);
  }

  /**
   * 此处插入方法说明。 创建日期：(2002-3-25 16:21:18)
   * 
   * @param model
   *          nc.ui.bank_cvp.viewer.FormulaEditorModel
   */
  public void setModel(FormulaEditorModel model) {
    this.removeCmbListener();
    this.getUICmbYwFunc().removeAllItems();
    this.m_model = model;

    Object[][] oYwFunc = this.m_model.getBusiRefinfo();
    if (oYwFunc != null && oYwFunc.length != 0) {
      this.getUICmbYwFunc().setEnabled(true);
      this.getUIBnBusiAdd().setEnabled(true);
      for (int i = 0; i < oYwFunc.length; i++) {
        this.getUICmbYwFunc().addItem(oYwFunc[i][3].toString());
      }
    }
    else {
      this.getUICmbYwFunc().setEnabled(false);
      this.getUIBnBusiAdd().setEnabled(false);
    }
    this.getUITxtFormulaEdit().setInsertType(this.m_model.getInputType());
    this.getUITxtFormulaEdit().setControl(this.m_model.getInputControl());
    this.getUITxtFormulaEdit().setBusiBlock(this.m_model.getBusiFuncBlock());
    this.getUITxtFormulaEdit().setSysBlock(this.m_model.getSysFuncBlock());
    this.initListener();
    this.onChangeBusiFunc();
  }

  /**
   * 此处插入方法说明。 创建日期：(2002-4-25 15:18:31)
   * 
   * @return java.lang.String
   * @param sWhole
   *          java.lang.String
   * @param sSub
   *          java.lang.String
   */
  public String trimSubString(String sWhole, String sSub) {
    String str = sWhole;
    while (str.indexOf(sSub) >= 0) {
      str =
          str.substring(0, str.indexOf(sSub))
              + str.substring(str.indexOf(sSub) + sSub.length(), str.length());
    }
    return str;
  }

  void connEtoC11() {
    this.onChangeBusiFunc();
  }

  void connEtoC15() {
    this.onFormulaCheck();
  }

  void connEtoC16() {
    this.onOperAnd();
  }

  void connEtoC17() {
    this.onOperOr();
  }

  void connEtoC18() {
    this.onOperNot();
  }

  void connEtoC19() {
    this.onShowAddUI();
  }

  void connEtoC20() {
    this.onDel();
  }

  void connEtoC21() {
    this.onUndo();
  }

  void connEtoC22() {
    this.onClear();
  }

  void connEtoC5() {
    this.onOperLeft();
  }

  void connEtoC6() {
    this.onOperRight();
  }

  UIPanel getPaneOper() {
    if (this.ivjPaneOper == null) {
      this.ivjPaneOper = new nc.ui.pub.beans.UIPanel();
      this.ivjPaneOper.setName("PaneOper");
      this.ivjPaneOper.setLayout(null);
      this.getPaneOper().add(this.getUIBnAnd(), this.getUIBnAnd().getName());
      this.getPaneOper().add(this.getUIBnOr(), this.getUIBnOr().getName());
      this.getPaneOper().add(this.getUIBnBusiDel(),
          this.getUIBnBusiDel().getName());
      this.getPaneOper().add(this.getUIBnBusiClear(),
          this.getUIBnBusiClear().getName());
      this.getPaneOper().add(this.getUILabYwFunc(),
          this.getUILabYwFunc().getName());
      this.getPaneOper().add(this.getUICmbYwFunc(),
          this.getUICmbYwFunc().getName());
      this.getPaneOper().add(this.getUIBnBusiAdd(),
          this.getUIBnBusiAdd().getName());
      this.getPaneOper().add(this.getUILabFuncSm(),
          this.getUILabFuncSm().getName());
    }
    return this.ivjPaneOper;
  }

  UIButton getUIBnAnd() {
    if (this.ivjUIBnAnd == null) {
      this.ivjUIBnAnd = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnAnd.setName("UIBnAnd");
      this.ivjUIBnAnd.setText("and");
      this.ivjUIBnAnd.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnAnd.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnAnd.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnAnd.setBounds(110, 1, 50, 22);
      this.ivjUIBnAnd.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnAnd;
  }

  UIButton getUIBnBusiAdd() {
    if (this.ivjUIBnBusiAdd == null) {
      this.ivjUIBnBusiAdd = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnBusiAdd.setName("UIBnBusiAdd");
      this.ivjUIBnBusiAdd.setText(NCLangRes.getInstance().getStrByID(
          "4006006_0", "04006006-0079")/*增加*/);
      this.ivjUIBnBusiAdd.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnBusiAdd.setBounds(575, 1, 70, 22);
    }
    return this.ivjUIBnBusiAdd;
  }

  UIButton getUIBnBusiClear() {
    if (this.ivjUIBnBusiClear == null) {
      this.ivjUIBnBusiClear = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnBusiClear.setName("UIBnBusiClear");
      this.ivjUIBnBusiClear.setText(NCLangRes.getInstance().getStrByID(
          "4006006_0", "04006006-0070")/*清空*/);
      this.ivjUIBnBusiClear.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnBusiClear.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnBusiClear.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnBusiClear.setBounds(55, 1, 50, 22);
      this.ivjUIBnBusiClear.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnBusiClear;
  }

  UIButton getUIBnBusiDel() {
    if (this.ivjUIBnBusiDel == null) {
      this.ivjUIBnBusiDel = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnBusiDel.setName("UIBnBusiDel");
      this.ivjUIBnBusiDel.setText(NCLangRes.getInstance().getStrByID(
          "4006006_0", "04006006-0071")/*删除*/);
      this.ivjUIBnBusiDel.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnBusiDel.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnBusiDel.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnBusiDel.setBounds(0, 1, 50, 22);
      this.ivjUIBnBusiDel.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnBusiDel;
  }

  UIButton getUIBnBusiUndo() {
    if (this.ivjUIBnBusiUndo == null) {
      this.ivjUIBnBusiUndo = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnBusiUndo.setName("UIBnBusiUndo");
      this.ivjUIBnBusiUndo.setText(NCLangRes.getInstance().getStrByID(
          "4006006_0", "04006006-0072")/*撤销*/);

      this.ivjUIBnBusiUndo.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnBusiUndo.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnBusiUndo.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnBusiUndo.setBounds(165, 1, 50, 22);
      this.ivjUIBnBusiUndo.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnBusiUndo;
  }

  UIButton getUIBnFormulaCheck() {
    if (this.ivjUIBnFormulaCheck == null) {
      this.ivjUIBnFormulaCheck = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnFormulaCheck.setName("UIBnFormulaCheck");
      this.ivjUIBnFormulaCheck.setText(NCLangRes.getInstance().getStrByID(
          "4006006_0", "04006006-0073")/*公式检查*/);
      this.ivjUIBnFormulaCheck.setBounds(0, 1, 110, 22);
    }
    return this.ivjUIBnFormulaCheck;
  }

  UIButton getUIBnLeft() {
    if (this.ivjUIBnLeft == null) {
      this.ivjUIBnLeft = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnLeft.setName("UIBnLeft");
      this.ivjUIBnLeft.setText("(");
      this.ivjUIBnLeft.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnLeft.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnLeft.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnLeft.setBounds(110, 1, 50, 22);
      this.ivjUIBnLeft.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnLeft;
  }

  UIButton getUIBnNot() {
    if (this.ivjUIBnNot == null) {
      this.ivjUIBnNot = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnNot.setName("UIBnNot");
      this.ivjUIBnNot.setText("not");
      this.ivjUIBnNot.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnNot.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnNot.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnNot.setBounds(330, 1, 50, 22);
      this.ivjUIBnNot.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnNot;
  }

  UIButton getUIBnOr() {
    if (this.ivjUIBnOr == null) {
      this.ivjUIBnOr = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnOr.setName("UIBnOr");
      this.ivjUIBnOr.setText("or");
      this.ivjUIBnOr.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnOr.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnOr.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnOr.setBounds(165, 1, 50, 22);
      this.ivjUIBnOr.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnOr;
  }

  UIButton getUIBnRight() {
    if (this.ivjUIBnRight == null) {
      this.ivjUIBnRight = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnRight.setName("UIBnRight");
      this.ivjUIBnRight.setText(")");
      this.ivjUIBnRight.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnRight.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnRight.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnRight.setBounds(165, 1, 50, 22);
      this.ivjUIBnRight.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnRight;
  }

  UIComboBox getUICmbYwFunc() {
    if (this.ivjUICmbYwFunc == null) {
      this.ivjUICmbYwFunc = new nc.ui.pub.beans.UIComboBox();
      this.ivjUICmbYwFunc.setName("UICmbYwFunc");
      this.ivjUICmbYwFunc.setBounds(450, 1, 120, 22);
    }
    return this.ivjUICmbYwFunc;
  }

  UILabel getUILabFuncSm() {
    if (this.ivjUILabFuncSm == null) {
      this.ivjUILabFuncSm = new nc.ui.pub.beans.UILabel();
      this.ivjUILabFuncSm.setName("UILabFuncSm");
      this.ivjUILabFuncSm.setText(NCLangRes.getInstance().getStrByID(
          "4006006_0", "04006006-0074")/*函数说明*/);
      this.ivjUILabFuncSm.setBounds(0, 53, 52, 22);
    }
    return this.ivjUILabFuncSm;
  }

  UILabel getUILabGsEdit() {
    if (this.ivjUILabGsEdit == null) {
      this.ivjUILabGsEdit = new nc.ui.pub.beans.UILabel();
      this.ivjUILabGsEdit.setName("UILabGsEdit");
      this.ivjUILabGsEdit.setPreferredSize(new java.awt.Dimension(60, 22));
      this.ivjUILabGsEdit.setText(NCLangRes.getInstance().getStrByID(
          "4006006_0", "04006006-0075", null, new String[] {})/*公式编辑:*/);
      this.ivjUILabGsEdit.setBounds(0, 1, 60, 22);
    }
    return this.ivjUILabGsEdit;
  }

  UILabel getUILabYwFunc() {
    if (this.ivjUILabYwFunc == null) {
      this.ivjUILabYwFunc = new nc.ui.pub.beans.UILabel();
      this.ivjUILabYwFunc.setName("UILabYwFunc");
      this.ivjUILabYwFunc.setPreferredSize(new java.awt.Dimension(60, 22));
      this.ivjUILabYwFunc.setText(NCLangRes.getInstance().getStrByID(
          "4006006_0", "04006006-0076", null, new String[] {})/*业务函数:*/);
      this.ivjUILabYwFunc.setBounds(385, 1, 60, 22);
    }
    return this.ivjUILabYwFunc;
  }

  UIScrollPane getUIScrollPane1() {
    if (this.ivjUIScrollPane1 == null) {
      this.ivjUIScrollPane1 = new nc.ui.pub.beans.UIScrollPane();
      this.ivjUIScrollPane1.setName("UIScrollPane1");
      this.getUIScrollPane1().setViewportView(this.getUITxtFormulaEdit());
    }
    return this.ivjUIScrollPane1;
  }

  UIScrollPane getUIScrollPane2() {
    if (this.ivjUIScrollPane2 == null) {
      this.ivjUIScrollPane2 = new nc.ui.pub.beans.UIScrollPane();
      this.ivjUIScrollPane2.setName("UIScrollPane2");
      this.getUIScrollPane2().setViewportView(this.getUITxtFuncExplain());
    }
    return this.ivjUIScrollPane2;
  }

  UITextArea getUITxtFuncExplain() {
    if (this.ivjUITxtFuncExplain == null) {
      this.ivjUITxtFuncExplain = new nc.ui.pub.beans.UITextArea();
      this.ivjUITxtFuncExplain.setName("UITxtFuncExplain");
      this.ivjUITxtFuncExplain.setBorder(new javax.swing.border.EtchedBorder());
      this.ivjUITxtFuncExplain.setEditable(false);
      this.ivjUITxtFuncExplain.setBounds(0, 0, 4, 20);
      this.ivjUITxtFuncExplain.setLineWrap(true);
      this.ivjUITxtFuncExplain.setAutoscrolls(true);
    }
    return this.ivjUITxtFuncExplain;
  }

  /**
   * 存储和用于公式运算的公式：是用字母业务函数表达的 界面编辑的业务函数：是用汉字业务函数名表达的
   * 本方法用于在两种公式之间进行转换（仅仅转换业务函数名，而不负责进行业务函数的参数编码、名称之间的转换） 创建日期：(2004-6-25
   * 12:57:22)
   * 
   * @return java.lang.String
   * @param sInitFormula
   *          java.lang.String
   * @param bToDis
   *          boolean
   */
  private String changeFormula(String sInitFormula, boolean bToEdit) {
    if (this.m_model.getBusiRefinfo() == null
        || this.m_model.getBusiRefinfo().length == 0) {
      return sInitFormula;
    }
    List<String> vSource = new ArrayList<String>();
    List<String> vTarget = new ArrayList<String>();
    Object[][] oBusiFunc = this.m_model.getBusiRefinfo();
    String sFun = null;
    for (int i = 0; i < oBusiFunc.length; i++) {
      sFun =
          oBusiFunc[i][0].toString().substring(0,
              oBusiFunc[i][0].toString().indexOf("("));
      if (oBusiFunc[i][3] != null) {
        if (bToEdit) {
          vSource.add(sFun);
          vTarget.add(oBusiFunc[i][3].toString());
        }
        else {
          vSource.add(oBusiFunc[i][3].toString());
          vTarget.add(sFun);
        }
      }
    }
    StringBuffer sb = new StringBuffer(sInitFormula);
    int idxStart;
    int idxEnd;
    int idxNode1;
    int idxNode2;
    int idx;
    idxStart = 0;
    boolean bReplaced = false;
    while (idxStart >= 0) {
      idxNode1 = sb.substring(idxStart, sb.length()).indexOf("\"");
      if (idxNode1 < 0) {
        idxEnd = sb.length();
      }
      else {
        idxEnd = idxStart + idxNode1 - 1;
      }
      bReplaced = false;
      for (int i = 0; i < vSource.size(); i++) {
        idx = sb.substring(idxStart, idxEnd).indexOf(vSource.get(i).toString());
        if (idx >= 0) {
          sb.replace(idxStart + idx, idxStart + idx
              + vSource.get(i).toString().length(), vTarget.get(i).toString());
          idxStart += idx + vTarget.get(i).toString().length();
          bReplaced = true;
          break;
        }
      }
      if (bReplaced) {
        continue;
      }
      if (idxNode1 < 0) {
        idxStart = -1;
      }
      else {
        idxNode2 =
            sb.substring(idxStart + idxNode1 + 1, sb.length()).indexOf("\"");
        idxStart += idxNode1 + 1 + idxNode2 + 1;
      }
    }
    return sb.toString();
  }

  /**
   * 检查当前公式后是否有逻辑符 创建日期：(2002-4-19 9:23:16)
   * 
   * @return java.lang.String
   */
  private boolean checkInputOper() {
    String sFormula = this.getUITxtFormulaEdit().getText();
    String sRight = null;
    if (sFormula != null && sFormula.length() > 5) {
      sRight = sFormula.substring(sFormula.length() - 5, sFormula.length());
      if (sRight.indexOf(" and ") == -1 && sRight.indexOf(" or ") == -1) {
        return false;
      }
    }
    return true;
  }

  /**
   * Comment
   */
  private int getAndOrNum(String s, String ss) {
    int iindex = 0;
    int iReturn = 0;
    for (int i = 0; i < s.length();) {
      iindex = s.indexOf(ss, i);
      if (iindex == -1) {
        break;
      }

      i = iindex + ss.length();
      iReturn = iReturn + 2;
    }
    return iReturn;
  }

  private nc.ui.pub.beans.UIPanel getPaneBnShow() {
    if (this.ivjPaneBnShow == null) {
      this.ivjPaneBnShow = new nc.ui.pub.beans.UIPanel();
      this.ivjPaneBnShow.setName("PaneBnShow");
      this.ivjPaneBnShow.setLayout(null);
      this.getPaneBnShow().add(this.getUIBnFormulaCheck(),
          this.getUIBnFormulaCheck().getName());
    }
    return this.ivjPaneBnShow;
  }

  private nc.ui.pub.beans.UIPanel getPaneEdit() {
    if (this.ivjPaneEdit == null) {
      this.ivjPaneEdit = new nc.ui.pub.beans.UIPanel();
      this.ivjPaneEdit.setName("PaneEdit");
      this.ivjPaneEdit.setLayout(new java.awt.BorderLayout());
      this.getPaneEdit().add(this.getUIScrollPane1(), "Center");
    }
    return this.ivjPaneEdit;
  }

  private nc.ui.pub.beans.UIPanel getPaneExplain() {
    if (this.ivjPaneExplain == null) {
      this.ivjPaneExplain = new nc.ui.pub.beans.UIPanel();
      this.ivjPaneExplain.setName("PaneExplain");
      this.ivjPaneExplain.setLayout(new java.awt.BorderLayout());
      this.getPaneExplain().add(this.getUIScrollPane2(), "Center");
    }
    return this.ivjPaneExplain;
  }

  private nc.ui.pub.beans.UIPanel getPaneTitle() {
    if (this.ivjPaneTitle == null) {
      this.ivjPaneTitle = new nc.ui.pub.beans.UIPanel();
      this.ivjPaneTitle.setName("PaneTitle");
      this.ivjPaneTitle.setLayout(null);
      this.getPaneTitle().add(this.getUILabGsEdit(),
          this.getUILabGsEdit().getName());
    }
    return this.ivjPaneTitle;
  }

  private void initConnections() {
    this.getUIBnLeft().addActionListener(this.ivjEventHandler);
    this.getUIBnRight().addActionListener(this.ivjEventHandler);
    this.getUIBnAnd().addActionListener(this.ivjEventHandler);
    this.getUIBnOr().addActionListener(this.ivjEventHandler);
    this.getUIBnNot().addActionListener(this.ivjEventHandler);
    this.getUICmbYwFunc().addActionListener(this.ivjEventHandler);
    this.getUIBnFormulaCheck().addActionListener(this.ivjEventHandler);
    this.getUIBnBusiAdd().addActionListener(this.ivjEventHandler);
    this.getUIBnBusiDel().addActionListener(this.ivjEventHandler);
    this.getUIBnBusiUndo().addActionListener(this.ivjEventHandler);
    this.getUIBnBusiClear().addActionListener(this.ivjEventHandler);
  }

  private void initialize() {
    this.setName("FormulaEditPan");
    this.setLayout(new FormulaLayout());
    this.setSize(613, 344);
    this.add(this.getPaneTitle(), this.getPaneTitle().getName());
    this.add(this.getPaneEdit(), this.getPaneEdit().getName());
    this.add(this.getPaneOper(), this.getPaneOper().getName());
    this.add(this.getPaneExplain(), this.getPaneExplain().getName());
    this.add(this.getPaneBnShow(), this.getPaneBnShow().getName());
    this.initConnections();
  }

  private void initListener() {
    this.getUICmbYwFunc().addActionListener(this.ivjEventHandler);
  }

  private void onChangeBusiFunc() {
    int idx = this.getUICmbYwFunc().getSelectedIndex();
    if (idx < 0) {
      return;
    }
    this.getUITxtFuncExplain().setText(
        this.m_model.getBusiRefinfo()[idx][2].toString());
  }

  private void onClear() {
    this.m_sLastValue = this.getUITxtFormulaEdit().getText();
    this.getUITxtFormulaEdit().setText("");
    this.m_vFormula.clear();
    this.m_sFormulaCode = new StringBuffer("");
  }

  private void onDel() {
    this.m_sLastValue = this.getUITxtFormulaEdit().getText();
    int iStart = this.getUITxtFormulaEdit().getSelectionStart();
    String sLeft = this.getUITxtFormulaEdit().getText().substring(0, iStart);
    // 找到左边and和or的个数
    int iand = this.getAndOrNum(sLeft, " and ");
    int ior = this.getAndOrNum(sLeft, " or ");
    int idelindex = iand + ior - 1;
    if (idelindex < 0) {
      idelindex = 0;
    }
    if (idelindex < this.m_vFormula.size()) {
      this.m_vFormula.remove(idelindex);
    }
    if (idelindex < this.m_vFormula.size()) {
      this.m_vFormula.remove(idelindex);
    }
    this.setFormula();
  }

  private void onFormulaCheck() {
    String sFormula = this.m_sFormulaCode.toString();
    if (sFormula != null && !"".equals(sFormula.trim())
        && sFormula.length() > 0) {
      try {
        nc.ui.bank_cvp.formulainterface.RefCompilerClient.check(sFormula);
      }
      catch (Exception e) {
        ExceptionUtils.wrappBusinessException(e.getMessage());
      }
      MessageDialog
          .showHintDlg(
              this,
              NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0019")/*提示*/,
              NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0020")/*公式检查成功*/);
    }
  }

  /**
   * Comment
   */
  private void onOperAnd() {
    String sFormula = this.getUITxtFormulaEdit().getText();
    if (sFormula == null || sFormula.length() == 0) {
      return;
    }

    String sRight = null;
    if (sFormula.length() > 5) {
      sRight = sFormula.substring(sFormula.length() - 5, sFormula.length());
      if (sRight.indexOf(" and ") != -1) {
        this.m_vFormula.remove(this.m_vFormula.size() - 1);
        this.getUITxtFormulaEdit().setText(
            sFormula.substring(0, sFormula.length() - 5));
      }
      if (sRight.indexOf(" or ") != -1) {
        this.m_vFormula.remove(this.m_vFormula.size() - 1);
        this.getUITxtFormulaEdit().setText(
            sFormula.substring(0, sFormula.length() - 4));
      }
    }
    // 将逻辑符的code和name保存起来
    String[] ss = new String[] {
      " and ", " and "
    };
    this.m_vFormula.add(ss);
    this.getUITxtFormulaEdit().insertText(" and ");
  }

  /**
   * Comment
   */
  private void onOperLeft() {
    this.m_sLastValue = this.getUITxtFormulaEdit().getText();
    this.getUITxtFormulaEdit().insertText("(");
  }

  /**
   * Comment
   */
  private void onOperNot() {
    this.getUITxtFormulaEdit().insertText("not ");
  }

  private void onOperOr() {
    String sFormula = this.getUITxtFormulaEdit().getText();
    if (sFormula == null || sFormula.length() == 0) {
      return;
    }

    String sRight = null;
    if (sFormula.length() > 5) {
      sRight = sFormula.substring(sFormula.length() - 5, sFormula.length());
      if (sRight.indexOf(" and ") != -1) {
        this.m_vFormula.remove(this.m_vFormula.size() - 1);
        this.getUITxtFormulaEdit().setText(
            sFormula.substring(0, sFormula.length() - 5));
      }
      if (sRight.indexOf(" or ") != -1) {
        this.m_vFormula.remove(this.m_vFormula.size() - 1);
        this.getUITxtFormulaEdit().setText(
            sFormula.substring(0, sFormula.length() - 4));
      }
    }
    // 将逻辑符的code和name保存起来
    String[] ss = new String[] {
      " or ", " or "
    };
    this.m_vFormula.add(ss);
    this.getUITxtFormulaEdit().insertText(" or ");
  }

  /**
   * Comment
   */
  private void onOperRight() {
    this.m_sLastValue = this.getUITxtFormulaEdit().getText();
    this.getUITxtFormulaEdit().insertText(")");
  }

  /**
   * 弹出业务函数条件设置对话框
   */
  private void onShowAddUI() {
    if (!this.checkInputOper()) {
      return;
    }
    this.m_sLastValue = this.getUITxtFormulaEdit().getText();
    int idx = this.getUICmbYwFunc().getSelectedIndex();
    if (idx < 0) {
      return;
    }
    if (this.m_model.getBusiRefinfo()[idx][1] == null) {
      return;
    }
    IRefReturn dlg = (IRefReturn) this.m_model.getBusiRefinfo()[idx][1];
    if (dlg.showModal() == UIDialog.ID_CANCEL) {
      return;
    }
    StringBuffer sReturn = new StringBuffer(dlg.getRefReturnCode());
    StringBuffer sReturnName = new StringBuffer(dlg.getRefReturnName());
    String[] sFormula = new String[] {
      sReturn.toString(), sReturnName.toString()
    };
    this.m_vFormula.add(sFormula);
    this.setFormula();
  }

  /**
   * Comment
   */
  private void onUndo() {
    if (this.m_sLastValue == null) {
      this.m_sLastValue = "";
    }
    this.getUITxtFormulaEdit().setText(this.m_sLastValue);
  }

  /**
   * 此处插入方法说明。 创建日期：(2002-8-21 10:57:16)
   */
  private void removeCmbListener() {
    this.getUICmbYwFunc().removeActionListener(this.ivjEventHandler);
  }

  /**
   * 设置当前的公式 创建日期：(2002-4-24 16:15:34)
   * 
   * @param sFormula
   *          java.lang.String
   */
  private void setFormula() {
    Object[] o = this.m_vFormula.toArray();
    String[] sFunc = null;
    StringBuffer sReturn = null;
    sReturn = new StringBuffer("");
    StringBuffer sReturnName = new StringBuffer("");
    String sCode = null;
    String sName = null;
    for (int i = 0; i < o.length; i++) {
      sFunc = (String[]) o[i];
      sCode = sFunc[0];
      sName = sFunc[1];
      if (i == 0) {
        if (sCode.indexOf(" and ") != -1) {
          sCode = sCode.substring(5, sCode.length());
          sName = sName.substring(5, sName.length());
        }
        if (sCode.indexOf(" or ") != -1) {
          sCode = sCode.substring(4, sCode.length());
          sName = sName.substring(4, sName.length());
        }
      }
      sReturn.append(sCode);
      sReturnName.append(sName);
    }
    this.getUITxtFormulaEdit().setText(sReturnName.toString());
    this.m_sFormulaCode = sReturn;

  }
}
