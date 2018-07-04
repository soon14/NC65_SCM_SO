package nc.ui.so.m38.arrange.editor.eidthandler;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.scmpub.ref.FilterFreeCustRefUtils;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class FreeCustEditHandler {

  public void beforeEdit(BillListPanel listPanel, PushBillEvent e) {
    int row = e.getEditEvent().getRow();
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);
    if (!this.isEditable(keyValue)) {
      e.setEditable(false);
      return;
    }
    String cust = keyValue.getHeadStringValue(SaleOrderHVO.CCUSTOMERID);
    BillItem freecustitem = listPanel.getBodyItem(SaleOrderHVO.CFREECUSTID);
    FilterFreeCustRefUtils utils = new FilterFreeCustRefUtils(freecustitem);
    utils.filterItemRefByCust(cust);
  }

  private boolean isEditable(IKeyValue keyValue) {

    UFBoolean isFreeCust = UFBoolean.FALSE;
    String customerid = keyValue.getHeadStringValue(SaleOrderHVO.CCUSTOMERID);

    CustomerVO[] vos = CustomerPubService.getCustomerVO(new String[] {
      customerid
    }, new String[] {
      CustomerVO.ISFREECUST
    });
    if (vos != null && vos.length > 0) {
      isFreeCust = vos[0].getIsfreecust();
    }
    return isFreeCust.booleanValue();
  }
}
