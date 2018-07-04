package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.bd.ref.model.CustAddressDefaultRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;

/**
 * 
 * @since 6.35
 * @version 2013-12-19 13:50:08
 * @author liangjm
 */
public class ReceiveaddrEditHandler {

  /**
   * 
   * 
   * @param e
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 表体非空行
    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();

    // 表体收货地址默认值
    String headreccustid =
        keyValue.getHeadStringValue(SOItemKey.CRECEIVECUSTID);
    String receiveaddid = keyValue.getHeadStringValue(SOItemKey.CRECEIVEADDRID);
    for (int row : rows) {
      String bodyreccustid =
          keyValue.getBodyStringValue(row, SOItemKey.CRECEIVECUSTID);
      if (headreccustid.equals(bodyreccustid)) {
        keyValue.setBodyValue(row, SOItemKey.CRECEIVEADDRID, receiveaddid);
      }
    }
  }

  /**
   * 
   * 
   * @param e
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    BillItem item = cardPanel.getHeadItem(SOItemKey.CRECEIVEADDRID);
    UIRefPane uirefpane = (UIRefPane) item.getComponent();
    CustAddressDefaultRefModel model =
        (CustAddressDefaultRefModel) uirefpane.getRefModel();

    // 按照客户和组织过滤
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String customer = keyValue.getHeadStringValue(SOItemKey.CRECEIVECUSTID);
    model.setPk_org(pk_org);
    model.setPk_customer(customer);

  }
}
