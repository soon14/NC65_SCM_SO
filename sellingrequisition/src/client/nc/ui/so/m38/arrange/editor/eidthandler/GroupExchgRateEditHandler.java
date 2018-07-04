package nc.ui.so.m38.arrange.editor.eidthandler;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOGroupExchangeRate;

public class GroupExchgRateEditHandler {

  public void beforeEdit(BillListPanel listPanel, PushBillEvent e) {

    int row = e.getEditEvent().getRow();
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);
    SOGroupExchangeRate globalrate = new SOGroupExchangeRate(keyValue);
    e.setEditable(globalrate.isGroupExchgRateEdit(row));
  }

}
