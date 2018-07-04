package nc.ui.so.m38.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;

public class GlobalExchgRateEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    int editrow = e.getRow();
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    SOGlobalExchangeRate globalrate = new SOGlobalExchangeRate(keyValue);
    e.setReturnValue(globalrate.isGlobalExchgRateEdit(editrow));

  }
}
