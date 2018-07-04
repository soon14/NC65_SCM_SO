package nc.ui.so.m32.billui.editor.bodyevent;

import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.bd.ref.model.CustAddressDefaultRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 收货地址编辑事件
 * 
 * @since 6.3
 * @version 2013-05-21 14:22:28
 * @author liujingn
 */
public class ReceAddrEditHandler {

  /**
   * 
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    BillItem item = cardPanel.getBodyItem(SaleInvoiceBVO.CRECEIVEADDRID);
    UIRefPane uirefpane = (UIRefPane) item.getComponent();
    CustAddressDefaultRefModel model =
        (CustAddressDefaultRefModel) uirefpane.getRefModel();

    // 按照客户和组织过滤
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int editrow = e.getRow();
    String pk_org = keyValue.getHeadStringValue(SaleInvoiceBVO.PK_ORG);
    String customer =
        keyValue.getBodyStringValue(editrow, SaleInvoiceBVO.CRECEIVECUSTID);
    model.setPk_org(pk_org);
    model.setPk_customer(customer);
  }
}
