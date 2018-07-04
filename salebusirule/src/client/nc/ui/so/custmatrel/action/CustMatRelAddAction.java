package nc.ui.so.custmatrel.action;

import java.awt.event.ActionEvent;

import nc.vo.trade.checkrule.VOChecker;
import nc.vo.uif2.LoginContext;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.AddAction;
import nc.ui.so.base.rule.IBillRule;
import nc.ui.so.custmatrel.rule.BillDefaultValueRule;
import nc.ui.so.custmatrel.view.CardForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.UIState;

/**
 * 客户与物料关系定义-新增
 * 
 * @since 6.1
 * @version 2013-04-03 09:34:07
 * @author yixl
 */
public class CustMatRelAddAction extends AddAction {

  private static final long serialVersionUID = -8699612712051837254L;

  private CardForm billForm;

  /**
   * 获得billForm
   * 
   * @return CardForm
   */
  public CardForm getBillForm() {
    return this.billForm;
  }

  /**
   * 设置billForm
   * 
   * @param billForm
   */
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
