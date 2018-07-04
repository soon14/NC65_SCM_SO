package nc.ui.so.mreturncondition.ref;

/**
 * 此处插入类型说明。 创建日期：(2002-3-25 14:27:51)
 *
 * @作者：张春明
 */
import java.util.ArrayList;
import java.util.List;

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
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.trade.checkrule.VOChecker;

public class FormulaView extends UIPanel {

  class IvjEventHandler implements java.awt.event.ActionListener {

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      if (e.getSource() == FormulaView.this.getUIBnPlus()) {
        FormulaView.this.connEtoC1();
      }
      if (e.getSource() == FormulaView.this.getUIBnSub()) {
        FormulaView.this.connEtoC2();
      }
      if (e.getSource() == FormulaView.this.getUIBnMultiply()) {
        FormulaView.this.connEtoC3();
      }
      if (e.getSource() == FormulaView.this.getUIBnDivide()) {
        FormulaView.this.connEtoC4();
      }
      if (e.getSource() == FormulaView.this.getUIBnLeft()) {
        FormulaView.this.connEtoC5();
      }
      if (e.getSource() == FormulaView.this.getUIBnRight()) {
        FormulaView.this.connEtoC6();
      }
      if (e.getSource() == FormulaView.this.getUIBnMore()) {
        FormulaView.this.connEtoC7();
      }
      if (e.getSource() == FormulaView.this.getUIBnLess()) {
        FormulaView.this.connEtoC8();
      }
      if (e.getSource() == FormulaView.this.getUIBnEqual()) {
        FormulaView.this.connEtoC9();
      }
      if (e.getSource() == FormulaView.this.getUICmbSysFunc()) {
        FormulaView.this.connEtoC10();
      }
      if (e.getSource() == FormulaView.this.getUICmbYwFunc()) {
        FormulaView.this.connEtoC11();
      }
      if (e.getSource() == FormulaView.this.getUIBnSysRef()) {
        FormulaView.this.connEtoC12();
      }
      if (e.getSource() == FormulaView.this.getUIBnBusiRef()) {
        FormulaView.this.connEtoC13();
      }
      if (e.getSource() == FormulaView.this.getUIBnFormuShow()) {
        FormulaView.this.connEtoC14();
      }
      if (e.getSource() == FormulaView.this.getUIBnFormulaCheck()) {
        FormulaView.this.connEtoC15();
      }
      if (e.getSource() == FormulaView.this.getUIBnAnd()) {
        FormulaView.this.connEtoC16();
      }
      if (e.getSource() == FormulaView.this.getUIBnOr()) {
        FormulaView.this.connEtoC17();
      }
      if (e.getSource() == FormulaView.this.getUIBnNot()) {
        FormulaView.this.connEtoC18();
      }
      if (e.getSource() == FormulaView.this.getUIBnBusiAdd()) {
        FormulaView.this.connEtoC19();
      }
      if (e.getSource() == FormulaView.this.getUIBnBusiDel()) {
        FormulaView.this.connEtoC20();
      }
      if (e.getSource() == FormulaView.this.getUIBnBusiUndo()) {
        FormulaView.this.connEtoC21();
      }
      if (e.getSource() == FormulaView.this.getUIBnBusiClear()) {
        FormulaView.this.connEtoC22();
      }
    }
  }

  private static final long serialVersionUID = 1536665331448117510L;

  private IvjEventHandler ivjEventHandler = new IvjEventHandler();

  private UIPanel ivjPaneBnShow;

  private UIPanel ivjPaneEdit;

  private UIPanel ivjPaneExplain;

  private UIPanel ivjPaneOper;

  private UIPanel ivjPaneTitle;

  private UIButton ivjUIBnAnd;

  private UIButton ivjUIBnBusiAdd;

  private UIButton ivjUIBnBusiClear;

  private UIButton ivjUIBnBusiDel;

  private UIButton ivjUIBnBusiRef;

  private UIButton ivjUIBnBusiUndo;

  private UIButton ivjUIBnDivide;

  private UIButton ivjUIBnEqual;

  private UIButton ivjUIBnFormulaCheck;

  private UIButton ivjUIBnFormuShow;

  private UIButton ivjUIBnLeft;

  private UIButton ivjUIBnLess;

  private UIButton ivjUIBnMore;

  private UIButton ivjUIBnMultiply;

  private UIButton ivjUIBnNot;

  private UIButton ivjUIBnOr;

  // 程序内部产生的变量

  private UIButton ivjUIBnPlus;

  private UIButton ivjUIBnRight;

  private UIButton ivjUIBnSub;

  private UIButton ivjUIBnSysRef;

  private UIComboBox ivjUICmbSysFunc;

  private UIComboBox ivjUICmbYwFunc;

  private UILabel ivjUILabFuncSm;

  private UILabel ivjUILabGsEdit;

  private UILabel ivjUILabYwFunc;

  private UIScrollPane ivjUIScrollPane1;

  private UIScrollPane ivjUIScrollPane2;

  private TextAreaEdit ivjUITxtFormulaEdit;

  private UITextArea ivjUITxtFormulaShow;

  private UITextArea ivjUITxtFuncExplain;

  private int m_iBusiCounter;

  private int[][] m_iBusiPos;

  private FormulaEditorModel m_model;

  private String[][] m_sBusiBlock;

  private StringBuffer m_sFormulaCode = new StringBuffer("");

  private String m_sLastValue;

  private List<String[]> m_vFormula = new ArrayList<String[]>();

  private List<String> m_vReplaced;

  public FormulaView() {
    super();
    this.initialize();
  }

  public FormulaView(boolean p0) {
    super(p0);
  }

  public FormulaView(java.awt.LayoutManager p0) {
    super(p0);
  }

  public FormulaView(java.awt.LayoutManager p0, boolean p1) {
    super(p0, p1);
  }

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

  public void checkFormula() {
    this.onFormulaCheck();
  }

  public String getFormula() {
    if (this.m_model.isFormulaEditFunName()) {
      String sFormulaEdit = this.getUITxtFormulaEdit().getText();
      String sFormula = this.changeFormula(sFormulaEdit, false);
      return sFormula;
    }
    return this.getUITxtFormulaEdit().getText();
  }

  public String getFormulaCode() {
    return this.m_sFormulaCode.toString();
  }

  public String[][] getFormulaElem() {
    this.m_sBusiBlock = this.m_model.getBusiFuncBlock();
    String sFunc = this.getFormula();
    this.m_iBusiCounter = 0;
    this.m_iBusiPos = new int[sFunc.length()][2];
    this.getBusiFuncPos(sFunc, 0);
    int iCounter = 0;
    List<String> vElements = new ArrayList<String>();
    while (true) {
      if (this.m_iBusiPos[iCounter][0] == 0
          && this.m_iBusiPos[iCounter][1] == 0) {
        break;
      }
      vElements.add(sFunc.substring(this.m_iBusiPos[iCounter][0],
          this.m_iBusiPos[iCounter][1] + 1));
      iCounter++;
    }
    int iSize = vElements.size();
    if (iSize == 0) {
      return null;
    }
    String[][] sElem = new String[iSize][0];
    for (int i = 0; i < vElements.size(); i++) {
      String str = vElements.get(i).toString();
      String sType = str.substring(0, str.indexOf("("));
      String sParam = str.substring(str.indexOf("(") + 1, str.length() - 1);
      String[] sTmp = this.getParametors(sParam);
      if (sTmp != null && sTmp.length != 0) {
        String[] str1 = new String[sTmp.length + 1];
        str1[0] = sType;
        for (int j = 0; j < sTmp.length; j++) {
          str1[j + 1] = sTmp[j];
        }
        sElem[i] = str1;
      }
      else {
        sElem[i] = new String[] {
          sType
        };
      }
    }
    int iCoun = sElem.length;
    if (!VOChecker.isEmpty(sElem) && sElem.length != 0) {
      for (int i = 0; i < sElem.length; i++) {
        String[] st1 = sElem[i];
        if (st1 == null) {
          continue;
        }
        for (int j = i + 1; j < sElem.length; j++) {
          String[] st2 = sElem[j];
          if (st2 == null) {
            continue;
          }
          if (st2.length == st1.length) {
            boolean bSame = true;
            for (int k = 0; k < st2.length; k++) {
              if (!st1[k].equals(st2[k])) {
                bSame = false;
                break;
              }
            }
            if (bSame) {
              iCoun--;
              sElem[j] = null;
            }
          }
          else {
            break;
          }
        }
      }
    }
    String[][] sNew = new String[iCoun][0];
    iCounter = 0;
    for (int i = 0; i < sElem.length; i++) {
      if (sElem[i] != null) {
        sNew[iCounter] = sElem[i];
        iCounter++;
      }
    }
    return sNew;
  }

  public String getFormulaName() {
    return this.getUITxtFormulaEdit().getText();
  }

  public String getFormulaShow() {
    String sFormula = this.getFormula();
    String[][] sBlock = null;
    int iCount = 0;
    int iLen = sFormula.length();
    while (iCount < iLen) {
      this.m_vReplaced = new ArrayList<String>();
      if (this.m_model == null) {
        sBlock = null;
      }
      else {
        sBlock = this.m_model.getSysFuncBlock();
      }
      if (sBlock != null) {
        for (int i = 0; i < sBlock.length; i++) {
          int idx = sFormula.indexOf(sBlock[i][0]);
          if (idx < 0) {
            continue;
          }
          int iEnd = sFormula.indexOf(sBlock[i][1], idx);
          String sFunc = sFormula.substring(idx, iEnd + 1);
          if (!this.isReplaced(sFunc)) {
            this.m_vReplaced.add(sFunc);
            String newfunc = this.m_model.getSysFunc()[i][2];
            iEnd = sFormula.indexOf("(", idx) - 1;
            sFormula = this.replaceString(idx, iEnd, sFormula, newfunc);
          }
        }
      }
      this.m_vReplaced = new ArrayList<String>();
      if (this.m_model == null) {
        sBlock = null;
      }
      else {
        sBlock = this.m_model.getBusiFuncBlock();
      }
      if (sBlock != null) {
        for (int i = 0; i < sBlock.length; i++) {
          int idx = sFormula.indexOf(sBlock[i][0]);
          if (idx < 0) {
            continue;
          }
          int iEnd = sFormula.indexOf(sBlock[i][1], idx);
          String sFunc = sFormula.substring(idx, iEnd + 1);
          if (!this.isReplaced(sFunc)) {
            continue;
          }
          String sCode = this.getCodebyFunc(sFunc);
          String sName = "";
          boolean expr1 = !VOChecker.isEmpty(sCode);
          boolean expr2 = this.m_model.getBusiRefinfo()[i][1] != null;
          if (expr1 && expr2) {
            sName = this.getNameByCode(sCode);
          }
          else {
            sName = sCode + "";
          }
          this.m_vReplaced.add(sFunc);
          String newfunc =
              this.m_model.getBusiRefinfo()[i][3].toString() + "(" + sName
                  + ")";
          sFormula = this.replaceString(idx, iEnd, sFormula, newfunc);
        }
      }
      iCount++;
    }
    return sFormula;
  }

  public TextAreaEdit getUITxtFormulaEdit() {
    if (this.ivjUITxtFormulaEdit == null) {
      this.ivjUITxtFormulaEdit = new TextAreaEdit();
      this.ivjUITxtFormulaEdit.setName("UITxtFormulaEdit");
      this.ivjUITxtFormulaEdit.setBorder(new javax.swing.border.EtchedBorder());
      this.ivjUITxtFormulaEdit.setBounds(0, 0, 4, 20);
      this.ivjUITxtFormulaEdit.setLineWrap(true);
      this.ivjUITxtFormulaEdit.setAutoscrolls(true);
      this.ivjUITxtFormulaEdit.setEditable(false);
    }
    return this.ivjUITxtFormulaEdit;
  }

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
    int ic = 0;
    int in = 0;
    this.m_vFormula.clear();
    int i = 0;
    int j = 0;
    for (; i < sCode.length() && j < sName.length();) {
      iandcode = sCode.indexOf(" and ", i);
      iorcode = sCode.indexOf(" or ", i);
      iandname = sName.indexOf(" and ", j);
      iorname = sName.indexOf(" or ", j);
      if (iandcode == -1 && iorcode == -1) {
        sc = sCode.substring(ic, sCode.length());
        i = sCode.length();
      }
      else if (iandcode == -1 && iorcode != -1) {
        sc = sCode.substring(ic, iorcode);
        ic = iorcode;
        i = iorcode + 4;
      }
      else if (iandcode != -1 && iorcode == -1) {
        sc = sCode.substring(ic, iandcode);
        ic = iandcode;
        i = iandcode + 5;
      }
      else if (iandcode != -1 && iorcode != -1 && iandcode < iorcode) {
        sc = sCode.substring(ic, iandcode);
        ic = iandcode;
        i = iandcode + 5;
      }
      else if (iandcode != -1 && iorcode != -1 && iandcode > iorcode) {
        sc = sCode.substring(ic, iorcode);
        ic = iorcode;
        i = iorcode + 4;
      }
      if (iandname == -1 && iorname == -1) {
        sn = sName.substring(in, sName.length());
        j = sName.length();
      }
      else if (iandname == -1 && iorname != -1) {
        sn = sName.substring(in, iorname);
        in = iorname;
        j = iorname + 4;
      }
      else if (iandname != -1 && iorname == -1) {
        sn = sName.substring(in, iandname);
        in = iandname;
        j = iandname + 5;
      }
      else if (iandname != -1 && iorname != -1 && iandname < iorname) {
        sn = sName.substring(in, iandname);
        in = iandname;
        j = iandname + 5;
      }
      else if (iandname != -1 && iorname != -1 && iandname > iorname) {
        sn = sName.substring(in, iorname);
        in = iorname;
        j = iorname + 4;
      }
      this.m_vFormula.add(new String[] {
        sc, sn
      });
    }

    this.setFormula();
  }

  public void setDefFormDisplay(String sFor) {
    this.getUITxtFormulaShow().setText(sFor);
  }

  public void setDefFormula(String sFormula) {
    String sFormulaEdit = null;
    if (this.m_model.isFormulaEditFunName()) {
      sFormulaEdit = this.changeFormula(sFormula, true);
    }
    else {
      sFormulaEdit = sFormula;
    }
    this.getUITxtFormulaEdit().setText(sFormulaEdit);
    this.getUITxtFormulaShow().setText(this.getFormulaShow());
  }

  @Override
  public void setEnabled(boolean enable) {
    this.getUIBnBusiRef().setEnabled(enable);
    this.getUIBnDivide().setEnabled(enable);
    this.getUIBnEqual().setEnabled(enable);
    this.getUIBnLeft().setEnabled(enable);
    this.getUIBnLess().setEnabled(enable);
    this.getUIBnMore().setEnabled(enable);
    this.getUIBnMultiply().setEnabled(enable);
    this.getUIBnPlus().setEnabled(enable);
    this.getUIBnRight().setEnabled(enable);
    this.getUIBnSub().setEnabled(enable);
    this.getUIBnAnd().setEnabled(enable);
    this.getUIBnOr().setEnabled(enable);
    this.getUIBnNot().setEnabled(enable);
    this.getUIBnBusiAdd().setEnabled(enable);
    this.getUIBnBusiDel().setEnabled(enable);
    this.getUIBnBusiUndo().setEnabled(enable);
    this.getUIBnBusiClear().setEnabled(enable);
    this.getUIBnSysRef().setEnabled(enable);
    this.getUITxtFormulaEdit().setEditable(enable);
  }

  public void setModel(FormulaEditorModel model) {
    this.removeCmbListener();
    this.getUICmbSysFunc().removeAllItems();
    this.getUICmbYwFunc().removeAllItems();
    this.m_model = model;
    String[][] sSysFunc = this.m_model.getSysFunc();
    if (sSysFunc != null && sSysFunc.length != 0) {
      this.getUICmbSysFunc().setEnabled(true);
      this.getUIBnSysRef().setEnabled(true);
      for (int i = 0; i < sSysFunc.length; i++) {
        this.getUICmbSysFunc().addItem(sSysFunc[i][0].toString());
      }
    }
    else {
      this.getUIBnSysRef().setEnabled(false);
      this.getUICmbSysFunc().setEnabled(false);
    }
    Object[][] oYwFunc = this.m_model.getBusiRefinfo();
    if (oYwFunc != null && oYwFunc.length != 0) {
      this.getUICmbYwFunc().setEnabled(true);
      this.getUIBnBusiRef().setEnabled(true);
      for (int i = 0; i < oYwFunc.length; i++) {
        this.getUICmbYwFunc().addItem(oYwFunc[i][3].toString());
      }
    }
    else {
      this.getUICmbYwFunc().setEnabled(false);
      this.getUIBnBusiRef().setEnabled(false);
    }
    this.getUITxtFormulaEdit().setInsertType(this.m_model.getInputType());
    this.getUITxtFormulaEdit().setControl(this.m_model.getInputControl());
    this.getUITxtFormulaEdit().setBusiBlock(this.m_model.getBusiFuncBlock());
    this.getUITxtFormulaEdit().setSysBlock(this.m_model.getSysFuncBlock());
    this.initListener();
    this.onChangeBusiFunc();
    this.onChangeSysFunc();
  }

  public String trimSubString(String sWhole, String sSub) {
    String str = sWhole;
    while (str.indexOf(sSub) >= 0) {
      str =
          str.substring(0, str.indexOf(sSub))
              + str.substring(str.indexOf(sSub) + sSub.length(), str.length());
    }
    return str;
  }

  void connEtoC1() {
    this.onOperAdd();
  }

  void connEtoC10() {
    this.onChangeSysFunc();
  }

  void connEtoC11() {
    this.onChangeBusiFunc();
  }

  void connEtoC12() {
    this.onSysFuncRef();
  }

  void connEtoC13() {
    this.onShowRefUI();
  }

  void connEtoC14() {
    this.onShowFormula();
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

  void connEtoC2() {
    this.onOperSub();
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

  void connEtoC3() {
    this.onOperMultiply();
  }

  void connEtoC4() {
    this.onOperDivide();
  }

  void connEtoC5() {
    this.onOperLeft();
  }

  void connEtoC6() {
    this.onOperRight();
  }

  void connEtoC7() {
    this.onOperMore();
  }

  void connEtoC8() {
    this.onOperLess();
  }

  void connEtoC9() {
    this.onOperEqual();
  }

  UIPanel getPaneBnShow() {
    if (this.ivjPaneBnShow == null) {
      this.ivjPaneBnShow = new nc.ui.pub.beans.UIPanel();
      this.ivjPaneBnShow.setName("PaneBnShow");
      this.ivjPaneBnShow.setLayout(null);
      this.getPaneBnShow().add(this.getUIBnFormulaCheck(),
          this.getUIBnFormulaCheck().getName());
    }
    return this.ivjPaneBnShow;
  }

  UIPanel getPaneEdit() {
    if (this.ivjPaneEdit == null) {
      this.ivjPaneEdit = new nc.ui.pub.beans.UIPanel();
      this.ivjPaneEdit.setName("PaneEdit");
      this.ivjPaneEdit.setLayout(new java.awt.BorderLayout());
      this.getPaneEdit().add(this.getUIScrollPane1(), "Center");
    }
    return this.ivjPaneEdit;
  }

  UIPanel getPaneExplain() {
    if (this.ivjPaneExplain == null) {
      this.ivjPaneExplain = new nc.ui.pub.beans.UIPanel();
      this.ivjPaneExplain.setName("PaneExplain");
      this.ivjPaneExplain.setLayout(new java.awt.BorderLayout());
      this.getPaneExplain().add(this.getUIScrollPane2(), "Center");
    }
    return this.ivjPaneExplain;
  }

  UIPanel getPaneOper() {
    if (this.ivjPaneOper == null) {
      this.ivjPaneOper = new nc.ui.pub.beans.UIPanel();
      this.ivjPaneOper.setName("PaneOper");
      this.ivjPaneOper.setLayout(null);
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

  UIPanel getPaneTitle() {
    if (this.ivjPaneTitle == null) {
      this.ivjPaneTitle = new nc.ui.pub.beans.UIPanel();
      this.ivjPaneTitle.setName("PaneTitle");
      this.ivjPaneTitle.setLayout(null);
      this.getPaneTitle().add(this.getUILabGsEdit(),
          this.getUILabGsEdit().getName());
    }
    return this.ivjPaneTitle;
  }

  UIButton getUIBnAnd() {
    if (this.ivjUIBnAnd == null) {
      this.ivjUIBnAnd = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnAnd.setName("UIBnAnd");
      this.ivjUIBnAnd.setText("&&");
      this.ivjUIBnAnd.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnAnd.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnAnd.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnAnd.setBounds(495, 1, 50, 22);
      this.ivjUIBnAnd.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnAnd;
  }

  UIButton getUIBnBusiAdd() {
    if (this.ivjUIBnBusiAdd == null) {
      this.ivjUIBnBusiAdd = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnBusiAdd.setName("UIBnBusiAdd");
      this.ivjUIBnBusiAdd.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID(
          "4006006_0", "04006006-0079")/* @res "增加" */);
      this.ivjUIBnBusiAdd.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnBusiAdd.setBounds(470, 1, 70, 22);
    }
    return this.ivjUIBnBusiAdd;
  }

  UIButton getUIBnBusiClear() {
    if (this.ivjUIBnBusiClear == null) {
      this.ivjUIBnBusiClear = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnBusiClear.setName("UIBnBusiClear");
      this.ivjUIBnBusiClear.setText(nc.ui.ml.NCLangRes.getInstance()
          .getStrByID("4006006_0", "04006006-0070")/* @res "清空" */);
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
      this.ivjUIBnBusiDel.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID(
          "4006006_0", "04006006-0071")/* @res "删除" */);
      this.ivjUIBnBusiDel.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnBusiDel.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnBusiDel.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnBusiDel.setBounds(0, 1, 50, 22);
      this.ivjUIBnBusiDel.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnBusiDel;
  }

  UIButton getUIBnBusiRef() {
    if (this.ivjUIBnBusiRef == null) {
      this.ivjUIBnBusiRef = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnBusiRef.setName("UIBnBusiRef");
      this.ivjUIBnBusiRef.setText(NCLangRes.getInstance().getStrByID(
          "4006006_0", "04006006-0077")/*参照*/);
      this.ivjUIBnBusiRef.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnBusiRef.setBounds(420, 29, 70, 22);
    }
    return this.ivjUIBnBusiRef;
  }

  UIButton getUIBnBusiUndo() {
    if (this.ivjUIBnBusiUndo == null) {
      this.ivjUIBnBusiUndo = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnBusiUndo.setName("UIBnBusiUndo");
      this.ivjUIBnBusiUndo.setText(nc.ui.ml.NCLangRes.getInstance().getStrByID(
          "4006006_0", "04006006-0072")/* @res "撤销" */);
      this.ivjUIBnBusiUndo.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnBusiUndo.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnBusiUndo.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnBusiUndo.setBounds(165, 1, 50, 22);
      this.ivjUIBnBusiUndo.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnBusiUndo;
  }

  UIButton getUIBnDivide() {
    if (this.ivjUIBnDivide == null) {
      this.ivjUIBnDivide = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnDivide.setName("UIBnDivide");
      this.ivjUIBnDivide.setText("/");
      this.ivjUIBnDivide.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnDivide.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnDivide.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnDivide.setBounds(165, 1, 50, 22);
      this.ivjUIBnDivide.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnDivide;
  }

  UIButton getUIBnEqual() {
    if (this.ivjUIBnEqual == null) {
      this.ivjUIBnEqual = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnEqual.setName("UIBnEqual");
      this.ivjUIBnEqual.setText("=");
      this.ivjUIBnEqual.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnEqual.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnEqual.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnEqual.setBounds(440, 1, 50, 22);
      this.ivjUIBnEqual.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnEqual;
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

  UIButton getUIBnFormuShow() {
    if (this.ivjUIBnFormuShow == null) {
      this.ivjUIBnFormuShow = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnFormuShow.setName("UIBnFormuShow");
      this.ivjUIBnFormuShow.setText(NCLangRes.getInstance().getStrByID(
          "4006006_0", "04006006-0078")/*公式显示*/);
      this.ivjUIBnFormuShow.setBounds(0, 1, 110, 22);
      this.ivjUIBnFormuShow.setMargin(new java.awt.Insets(2, 0, 2, 0));
    }
    return this.ivjUIBnFormuShow;
  }

  UIButton getUIBnLeft() {
    if (this.ivjUIBnLeft == null) {
      this.ivjUIBnLeft = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnLeft.setName("UIBnLeft");
      this.ivjUIBnLeft.setText("(");
      this.ivjUIBnLeft.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnLeft.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnLeft.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnLeft.setBounds(0, 1, 50, 22);
      this.ivjUIBnLeft.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnLeft;
  }

  UIButton getUIBnLess() {
    if (this.ivjUIBnLess == null) {
      this.ivjUIBnLess = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnLess.setName("UIBnLess");
      this.ivjUIBnLess.setText("<");
      this.ivjUIBnLess.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnLess.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnLess.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnLess.setBounds(385, 1, 50, 22);
      this.ivjUIBnLess.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnLess;
  }

  UIButton getUIBnMore() {
    if (this.ivjUIBnMore == null) {
      this.ivjUIBnMore = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnMore.setName("UIBnMore");
      this.ivjUIBnMore.setText(">");
      this.ivjUIBnMore.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnMore.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnMore.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnMore.setBounds(330, 1, 50, 22);
      this.ivjUIBnMore.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnMore;
  }

  UIButton getUIBnMultiply() {
    if (this.ivjUIBnMultiply == null) {
      this.ivjUIBnMultiply = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnMultiply.setName("UIBnMultiply");
      this.ivjUIBnMultiply.setText("*");
      this.ivjUIBnMultiply.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnMultiply.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnMultiply.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnMultiply.setBounds(110, 1, 50, 22);
      this.ivjUIBnMultiply.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnMultiply;
  }

  UIButton getUIBnNot() {
    if (this.ivjUIBnNot == null) {
      this.ivjUIBnNot = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnNot.setName("UIBnNot");
      this.ivjUIBnNot.setText("not");
      this.ivjUIBnNot.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnNot.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnNot.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnNot.setBounds(605, 1, 50, 22);
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
      this.ivjUIBnOr.setBounds(550, 1, 50, 22);
      this.ivjUIBnOr.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnOr;
  }

  UIButton getUIBnPlus() {
    if (this.ivjUIBnPlus == null) {
      this.ivjUIBnPlus = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnPlus.setName("UIBnPlus");
      this.ivjUIBnPlus.setText("+");
      this.ivjUIBnPlus.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnPlus.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnPlus.setBounds(0, 1, 50, 22);
      this.ivjUIBnPlus.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnPlus.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnPlus;
  }

  UIButton getUIBnRight() {
    if (this.ivjUIBnRight == null) {
      this.ivjUIBnRight = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnRight.setName("UIBnRight");
      this.ivjUIBnRight.setText(")");
      this.ivjUIBnRight.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnRight.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnRight.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnRight.setBounds(55, 1, 50, 22);
      this.ivjUIBnRight.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnRight;
  }

  UIButton getUIBnSub() {
    if (this.ivjUIBnSub == null) {
      this.ivjUIBnSub = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnSub.setName("UIBnSub");
      this.ivjUIBnSub.setText("-");
      this.ivjUIBnSub.setMaximumSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnSub.setPreferredSize(new java.awt.Dimension(50, 22));
      this.ivjUIBnSub.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnSub.setBounds(55, 1, 50, 22);
      this.ivjUIBnSub.setMinimumSize(new java.awt.Dimension(50, 22));
    }
    return this.ivjUIBnSub;
  }

  UIButton getUIBnSysRef() {
    if (this.ivjUIBnSysRef == null) {
      this.ivjUIBnSysRef = new nc.ui.pub.beans.UIButton();
      this.ivjUIBnSysRef.setName("UIBnSysRef");
      this.ivjUIBnSysRef.setText(NCLangRes.getInstance().getStrByID(
          "4006006_0", "04006006-0079")/*增加*/);
      this.ivjUIBnSysRef.setMargin(new java.awt.Insets(2, 0, 2, 0));
      this.ivjUIBnSysRef.setBounds(172, 29, 70, 22);
    }
    return this.ivjUIBnSysRef;
  }

  UIComboBox getUICmbSysFunc() {
    if (this.ivjUICmbSysFunc == null) {
      this.ivjUICmbSysFunc = new nc.ui.pub.beans.UIComboBox();
      this.ivjUICmbSysFunc.setName("UICmbSysFunc");
      this.ivjUICmbSysFunc.setBounds(65, 29, 102, 22);
    }
    return this.ivjUICmbSysFunc;
  }

  UIComboBox getUICmbYwFunc() {
    if (this.ivjUICmbYwFunc == null) {
      this.ivjUICmbYwFunc = new nc.ui.pub.beans.UIComboBox();
      this.ivjUICmbYwFunc.setName("UICmbYwFunc");
      this.ivjUICmbYwFunc.setBounds(345, 1, 120, 22);
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
          "4006006_0", "04006006-0052")/*公式编辑*/);
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
          "4006006_0", "04006006-0076")/*业务函数:*/);
      this.ivjUILabYwFunc.setBounds(280, 1, 60, 22);
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

  UITextArea getUITxtFormulaShow() {
    if (this.ivjUITxtFormulaShow == null) {
      this.ivjUITxtFormulaShow = new nc.ui.pub.beans.UITextArea();
      this.ivjUITxtFormulaShow.setName("UITxtFormulaShow");
      this.ivjUITxtFormulaShow.setBorder(new javax.swing.border.EtchedBorder());
      this.ivjUITxtFormulaShow.setEditable(false);
      this.ivjUITxtFormulaShow.setBounds(0, 0, 4, 20);
      this.ivjUITxtFormulaShow.setLineWrap(true);
      this.ivjUITxtFormulaShow.setAutoscrolls(true);
    }
    return this.ivjUITxtFormulaShow;
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

  private int countOfSubString(String sWhole, String sSub) {
    int iSubCount = 0;
    int iCurPos = 0;
    int iSubLen = sSub.length();
    if (sWhole.indexOf(sSub) < 0) {
      return iSubCount;
    }
    iCurPos = 0;
    iCurPos = sWhole.indexOf(sSub) + iSubLen;
    iSubCount++;
    while (true) {
      iCurPos = sWhole.indexOf(sSub, iCurPos);
      if (iCurPos < 0) {
        break;
      }
      iSubCount++;
      iCurPos = iCurPos + iSubLen;
    }
    return iSubCount;
  }

  private int getAndOrNum(String s, String ss) {
    int iindex = 0;
    int iReturn = 0;
    int i = 0;
    for (; i < s.length();) {
      iindex = s.indexOf(ss, i);
      if (iindex == -1) {
        break;
      }

      i = iindex + ss.length();
      iReturn++;
    }
    return iReturn;
  }

  private String getBusiFuncPos(String st, int istart) {
    String str = st;
    int i = 0;
    for (; i < this.m_sBusiBlock.length;) {
      int idx1 = str.indexOf(this.m_sBusiBlock[i][0], istart);
      if (idx1 < 0) {
        i++;
        continue;
      }
      int idx2 = str.indexOf(")", idx1);
      String sub = str.substring(idx1, idx2 + 1);
      while (this.countOfSubString(sub, "(") != this.countOfSubString(sub, ")")) {
        idx2 = str.indexOf(")", idx2 + 1);
        sub = str.substring(idx1, idx2 + 1);
      }
      if (this.isMinBlock(sub)) {
        str = this.replaceString(str, idx1, idx2);
        this.m_iBusiPos[this.m_iBusiCounter][0] = idx1;
        this.m_iBusiPos[this.m_iBusiCounter][1] = idx2;
        this.m_iBusiCounter++;
      }
      else {
        str = this.getBusiFuncPos(str, idx1 + this.m_sBusiBlock[i][0].length());
      }
    }
    return st;
  }

  private String getCodebyFunc(String sFunc) {
    int istart = sFunc.indexOf("(");
    int iEnd = sFunc.indexOf(")");
    return sFunc.substring(istart + 1, iEnd);
  }

  /**
   * 获得将要用来存储或进行公式运算的公式 创建日期：(2002-4-19 9:23:16)
   * 
   * @return java.lang.String
   */
  private String getNameByCode(String code) {
    String sName = null;
    Object[][] oYwFunc = this.m_model.getBusiRefinfo();
    for (int i = 0; i < oYwFunc.length; i++) {
      if (oYwFunc[i][0].toString().equals(code)) {
        sName = oYwFunc[i][3].toString() + "()";
      }
      else if (oYwFunc[i][0].toString().indexOf(code) != -1) {
        sName = oYwFunc[i][3].toString() + "(";
      }
    }
    return sName;
  }

  private String[] getParametors(String sParm) {
    if (sParm == null || sParm.trim().equals("")) {
      return null;
    }
    String strsParm = this.trimSubString(sParm, "\"");
    List<String> v0 = new ArrayList<String>();
    if (strsParm.indexOf(",") < 0) {
      v0.add(strsParm);
    }
    else {
      int iNum = this.countOfSubString(strsParm, ",");
      for (int i = 0; i < iNum; i++) {
        String s1 = strsParm.substring(0, strsParm.indexOf(","));
        v0.add(s1);
        strsParm =
            strsParm.substring(strsParm.indexOf(",") + 1, strsParm.length());
      }
      v0.add(strsParm);
    }
    String[] sP = new String[v0.size()];
    for (int i = 0; i < v0.size(); i++) {
      sP[i] = v0.get(i).toString();
    }
    return sP;
  }

  private void initConnections() {
    this.getUIBnPlus().addActionListener(this.ivjEventHandler);
    this.getUIBnSub().addActionListener(this.ivjEventHandler);
    this.getUIBnMultiply().addActionListener(this.ivjEventHandler);
    this.getUIBnDivide().addActionListener(this.ivjEventHandler);
    this.getUIBnLeft().addActionListener(this.ivjEventHandler);
    this.getUIBnRight().addActionListener(this.ivjEventHandler);
    this.getUIBnMore().addActionListener(this.ivjEventHandler);
    this.getUIBnLess().addActionListener(this.ivjEventHandler);
    this.getUIBnEqual().addActionListener(this.ivjEventHandler);
    this.getUIBnAnd().addActionListener(this.ivjEventHandler);
    this.getUIBnOr().addActionListener(this.ivjEventHandler);
    this.getUIBnNot().addActionListener(this.ivjEventHandler);
    this.getUICmbSysFunc().addActionListener(this.ivjEventHandler);
    this.getUICmbYwFunc().addActionListener(this.ivjEventHandler);
    this.getUIBnSysRef().addActionListener(this.ivjEventHandler);
    this.getUIBnBusiRef().addActionListener(this.ivjEventHandler);
    this.getUIBnFormuShow().addActionListener(this.ivjEventHandler);
    this.getUIBnFormulaCheck().addActionListener(this.ivjEventHandler);
    this.getUIBnBusiAdd().addActionListener(this.ivjEventHandler);
    this.getUIBnBusiDel().addActionListener(this.ivjEventHandler);
    this.getUIBnBusiUndo().addActionListener(this.ivjEventHandler);
    this.getUIBnBusiClear().addActionListener(this.ivjEventHandler);
  }

  private void initialize() {
    this.setName("FormulaEditPan");
    this.setSize(613, 344);
    this.setLayout(new FormulaLayout());
    this.add(this.getPaneTitle(), this.getPaneTitle().getName());
    this.add(this.getPaneEdit(), this.getPaneEdit().getName());
    this.add(this.getPaneOper(), this.getPaneOper().getName());
    this.add(this.getPaneExplain(), this.getPaneExplain().getName());
    this.add(this.getPaneBnShow(), this.getPaneBnShow().getName());
    this.initConnections();
  }

  private void initListener() {
    this.getUICmbSysFunc().addActionListener(this.ivjEventHandler);
    this.getUICmbYwFunc().addActionListener(this.ivjEventHandler);

  }

  private boolean isMinBlock(String s) {
    for (int i = 0; i < this.m_sBusiBlock.length; i++) {
      if (s.indexOf(this.m_sBusiBlock[i][0], s.indexOf("(")) < 0) {
        continue;
      }
      return false;
    }
    return true;
  }

  private boolean isReplaced(String str) {
    if (this.m_vReplaced == null || this.m_vReplaced.size() == 0) {
      return false;
    }
    String tmp = null;
    for (int i = 0; i < this.m_vReplaced.size(); i++) {
      tmp = this.m_vReplaced.get(i).toString().trim();
      if (tmp.equals(str)) {
        return true;
      }
    }
    return false;
  }

  private void onChangeBusiFunc() {
    int idx = this.getUICmbYwFunc().getSelectedIndex();
    if (idx < 0) {
      return;
    }
    this.getUITxtFuncExplain().setText(
        this.m_model.getBusiRefinfo()[idx][2].toString());

  }

  private void onChangeSysFunc() {
    int idx = this.getUICmbSysFunc().getSelectedIndex();
    if (idx < 0) {
      return;
    }
    this.getUITxtFuncExplain().setText(this.m_model.getSysFunc()[idx][1]);
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
    if (iStart == this.getUITxtFormulaEdit().getText().length()) {
      return;
    }
    String sLeft = this.getUITxtFormulaEdit().getText().substring(0, iStart);
    int iand = this.getAndOrNum(sLeft, " and ");
    int ior = this.getAndOrNum(sLeft, " or ");
    int idelindex = iand + ior;
    if (idelindex < 0) {
      idelindex = 0;
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
        MessageDialog.showHintDlg(
            this,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0",
                "04006006-0019")/*@res "提示"*/, e.getMessage());
        ExceptionUtils.wrappException(e);
      }
      MessageDialog
          .showHintDlg(
              this,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006006_0", "04006006-0019")/*@res "提示"*/,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006006_0", "04006006-0020")/*@res "公式检查成功"*/);
    }
  }

  private void onOperAdd() {
    this.getUITxtFormulaEdit().insertText("+");
  }

  private void onOperAnd() {
    this.getUITxtFormulaEdit().insertText("&&");
  }

  private void onOperDivide() {
    this.getUITxtFormulaEdit().insertText("/");
  }

  private void onOperEqual() {
    this.getUITxtFormulaEdit().insertText("=");
  }

  private void onOperLeft() {
    this.m_sLastValue = this.getUITxtFormulaEdit().getText();
    this.getUITxtFormulaEdit().insertText("(");
  }

  private void onOperLess() {
    this.getUITxtFormulaEdit().insertText("<");
    return;
  }

  private void onOperMore() {
    this.getUITxtFormulaEdit().insertText(">");
    return;
  }

  private void onOperMultiply() {
    this.getUITxtFormulaEdit().insertText("*");
  }

  private void onOperNot() {
    this.getUITxtFormulaEdit().insertText("not ");
  }

  private void onOperOr() {
    this.getUITxtFormulaEdit().insertText("||");
  }

  private void onOperRight() {
    this.m_sLastValue = this.getUITxtFormulaEdit().getText();
    this.getUITxtFormulaEdit().insertText(")");
  }

  private void onOperSub() {
    this.getUITxtFormulaEdit().insertText("-");
  }

  private void onShowAddUI() {
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
    if (VOChecker.isEmpty(sReturn)) {
      return;
    }
    if (this.getUITxtFormulaEdit().getText() == null
        || this.getUITxtFormulaEdit().getText().trim().equals("")
        || this.getUITxtFormulaEdit().getText().trim().equals("(")) {
      if (sReturn.length() > 3) {
        int start = sReturn.toString().indexOf(" and ");
        if (start >= 0) {
          sReturn.delete(start, start + 5);
        }
        else {
          start = sReturn.toString().indexOf(" or ");
          if (start >= 0) {
            sReturn.delete(start, start + 4);
          }
        }
      }
      if (sReturnName.length() > 3) {
        int start = sReturnName.toString().indexOf(" and ");
        if (start >= 0) {
          sReturnName.delete(start, start + 5);
        }
        else {
          start = sReturnName.toString().indexOf(" or ");
          if (start >= 0) {
            sReturnName.delete(start, start + 4);
          }
        }
      }
    }
    String[] sFormula = new String[] {
      sReturn.toString(), sReturnName.toString()
    };
    this.m_vFormula.add(sFormula);
    this.setFormula();
  }

  private void onShowFormula() {
    String sFormula = this.getFormulaShow();
    this.getUITxtFormulaShow().setText(sFormula);
  }

  private void onShowRefUI() {
    int idx = this.getUICmbYwFunc().getSelectedIndex();
    if (idx < 0) {
      return;
    }
    if (this.m_model.getBusiRefinfo()[idx][1] == null) {
      String sFunc = this.m_model.getBusiFuncBlock()[idx][0].toString();
      this.getUITxtFormulaEdit().insertText(sFunc + ")");
    }
    else {
      UIDialog dlg = (UIDialog) this.m_model.getBusiRefinfo()[idx][1];
      dlg.showModal();
    }
    this.onShowFormula();

  }

  private void onSysFuncRef() {
    int idx = this.getUICmbSysFunc().getSelectedIndex();
    if (idx < 0) {
      return;
    }
    String sFunc = this.getUICmbSysFunc().getSelectedItem().toString().trim();
    this.getUITxtFormulaEdit().insertText(sFunc);
    this.onShowFormula();
  }

  private void onUndo() {
    if (this.m_sLastValue == null) {
      this.m_sLastValue = "";
    }
    this.getUITxtFormulaEdit().setText(this.m_sLastValue);
  }

  private void removeCmbListener() {
    this.getUICmbSysFunc().removeActionListener(this.ivjEventHandler);
    this.getUICmbYwFunc().removeActionListener(this.ivjEventHandler);
  }

  private String replaceString(int iStart, int iEnd, String oldstr, String str) {
    String befor = oldstr.substring(0, iStart);
    String behind = oldstr.substring(iEnd + 1, oldstr.length());
    return befor + str + behind;
  }

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
        if (-1 != sCode.indexOf(" and ")) {
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
