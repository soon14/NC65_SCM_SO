package nc.ui.so.m38.arrange.editor.eidthandler;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;

public class CalTaxMnyEditHandler {

  public void beforeEdit(BillListPanel listPanel, PushBillEvent e) {
    int row = e.getEditEvent().getRow();
    IKeyValue keyvalue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);
    SOBuysellTriaRule rule = new SOBuysellTriaRule(keyvalue);
    e.setEditable(rule.isBuysellFlagOut(row));
  }

}
