package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOProfitCenterEditHandelRule;

@SuppressWarnings("restriction")
public class CsprofitcenteridEditHandle {

  public void afterEdit(CardBodyAfterEditEvent e, SaleOrderBillForm billform) {
    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    SOProfitCenterEditHandelRule profitEditRule =
        new SOProfitCenterEditHandelRule(keyValue);
    profitEditRule.afterEdit(rows);
  }
}
