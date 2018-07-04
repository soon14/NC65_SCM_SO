package nc.ui.so.tranmatrel.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.AddAction;
import nc.ui.so.base.rule.IBillRule;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.tranmatrel.rule.BillDefaultValueRule;
import nc.ui.so.tranmatrel.view.CardForm;
import nc.ui.uif2.UIState;
import nc.vo.trade.checkrule.VOChecker;
import nc.vo.uif2.LoginContext;

@SuppressWarnings("serial")
public class TranMatRelAddAction extends AddAction {

  private CardForm billForm;

  public CardForm getBillForm() {
    return this.billForm;
  }

  public void setBillForm(CardForm billForm) {
    this.billForm = billForm;
  }

  @Override
  protected boolean isActionEnable() {
    boolean enable = true;

    LoginContext context = this.getBillForm().getModel().getContext();
    if (VOChecker.isEmpty(context.getPk_org())) {
      enable = false;
    }
    else if (this.getBillForm().getModel().getSelectedData() != null) {
      enable = false;
    }
    else {
      enable = this.getBillForm().getModel().getUiState() == UIState.NOT_EDIT;
    }

    return enable;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    super.doAction(e);

    BillCardPanel panel = this.getBillForm().getBillCardPanel();
    CardKeyValue keyvalue = new CardKeyValue(panel);

    // 新增单据的默认值
    IBillRule rule = new BillDefaultValueRule();
    rule.process(keyvalue, this.getBillForm().getModel());

  }
}
