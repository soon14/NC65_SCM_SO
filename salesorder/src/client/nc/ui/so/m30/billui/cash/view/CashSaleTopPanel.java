package nc.ui.so.m30.billui.cash.view;

import javax.swing.BorderFactory;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITextField;
import nc.ui.pub.beans.textfield.UITextType;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

public class CashSaleTopPanel extends UIPanel {

  private static final long serialVersionUID = 2230657051464621872L;

  private UILabel label1;

  private UILabel label2;

  private UILabel label3;

  private UILabel label4;

  private UITextField orgsummnyTextField;

  private UITextField summnyTextField;

  private UITextField hxmnyTextField;

  private UITextField thisreceivemnyTextField;

  private SoBalanceVO sobalancevo;

  public CashSaleTopPanel() {
    this.initUI();
  }

  private UILabel getLabel1() {
    if (this.label1 == null) {
      this.label1 = new UILabel(NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0249")/*冲抵前金额*/);
      this.label1.setBounds(10, 10, 70, 22);
    }
    return this.label1;
  }

  private UILabel getLabel2() {
    if (this.label2 == null) {
      this.label2 = new UILabel(NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0250")/*冲抵金额*/);
      this.label2.setBounds(200, 10, 70, 22);
    }
    return this.label2;
  }

  private UILabel getLabel3() {
    if (this.label3 == null) {
      this.label3 = new UILabel(NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0251")/*收款核销金额*/);
      this.label3.setBounds(390, 10, 75, 22);
    }
    return this.label3;
  }

  private UILabel getLabel4() {
    if (this.label4 == null) {
      this.label4 = new UILabel(NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0252")/*本次收款金额*/);
      this.label4.setBounds(585, 10, 75, 22);
    }
    return this.label4;
  }

  public UITextField getOrgsummnyTextField() {
    if (this.orgsummnyTextField == null) {
      this.orgsummnyTextField = new UITextField();
      this.orgsummnyTextField.setTextType(UITextType.TextDbl);
      this.orgsummnyTextField.setBounds(80, 10, 100, 22);
      this.orgsummnyTextField.setEditable(false);
    }
    return this.orgsummnyTextField;
  }

  public UITextField getSummnyTextField() {
    if (this.summnyTextField == null) {
      this.summnyTextField = new UITextField();
      this.summnyTextField.setTextType(UITextType.TextDbl);
      this.summnyTextField.setBounds(270, 10, 100, 22);
      this.summnyTextField.setEditable(false);
    }
    return this.summnyTextField;
  }

  public UITextField getHxmnyTextField() {
    if (this.hxmnyTextField == null) {
      this.hxmnyTextField = new UITextField();
      this.hxmnyTextField.setTextType(UITextType.TextDbl);
      this.hxmnyTextField.setBounds(465, 10, 100, 22);
      this.hxmnyTextField.setEditable(false);
    }
    return this.hxmnyTextField;
  }

  public UITextField getThisreceivemnyTextField() {
    if (this.thisreceivemnyTextField == null) {
      this.thisreceivemnyTextField = new UITextField();
      this.thisreceivemnyTextField.setTextType(UITextType.TextDbl);
      this.thisreceivemnyTextField.setBounds(660, 10, 100, 22);
    }
    return this.thisreceivemnyTextField;
  }

  public void initUI() {
    this.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 5));
    this.setLayout(null);
    // this.setSize(800, 22);
    this.add(this.getLabel1());
    this.add(this.getOrgsummnyTextField());
    this.add(this.getLabel2());
    this.add(this.getSummnyTextField());
    this.add(this.getLabel3());
    this.add(this.getHxmnyTextField());
    this.add(this.getLabel4());
    this.add(this.getThisreceivemnyTextField());
  }

  public SoBalanceVO getSobalancevo() {
    return this.sobalancevo;
  }

  public void setSobalancevo(SoBalanceVO sobalancevo) {
    this.sobalancevo = sobalancevo;
  }

}
