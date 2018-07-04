package nc.ui.so.m38.arrange.editor.eidthandler;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.scmpub.ref.FilterCountryAreaRefUtils;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class OrigAreaEditHandler {

  public void beforeEdit(BillListPanel listPanel, PushBillEvent e) {

    int row = e.getEditEvent().getRow();
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);
    // Ô­²ú¹ú
    String origcountryid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CORIGCOUNTRYID);
    if (PubAppTool.isNull(origcountryid)) {
      e.setEditable(Boolean.FALSE);
      return;
    }
    FilterCountryAreaRefUtils filter =
        new FilterCountryAreaRefUtils((UIRefPane) listPanel.getBodyItem(
            SaleOrderBVO.CORIGAREAID).getComponent());
    filter.filterItemRefBy(origcountryid);
  }

}
