package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;

public class CaltaxMnyEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    IKeyValue value = new CardKeyValue(e.getBillCardPanel());
    SOBuysellTriaRule rule = new SOBuysellTriaRule(value);
    boolean out = rule.isBuysellFlagOut(e.getRow());
    if (out) {
      e.setReturnValue(Boolean.TRUE);
    }
    else {
      e.setReturnValue(Boolean.FALSE);
    }

  }
}
