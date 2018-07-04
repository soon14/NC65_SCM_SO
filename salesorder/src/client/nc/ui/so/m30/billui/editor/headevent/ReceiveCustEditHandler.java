package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.RecCustDefAddrByHeadRecInfo;
import nc.vo.so.pub.rule.SOCustRelaDefValueByHeadRecInfo;

/**
 * 
 * @since 6.35
 * @version 2013-12-24 16:41:19
 * @author liangjm
 */
public class ReceiveCustEditHandler {

  private SaleOrderBillForm billform;

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

    // 表体收货客户默认值
    SOCustRelaDefValueByHeadRecInfo defrule =
        new SOCustRelaDefValueByHeadRecInfo(keyValue);
    defrule.setCustRelaDefValue();

    // 表体收货地址默认值
    RecCustDefAddrByHeadRecInfo custdefrule =
        new RecCustDefAddrByHeadRecInfo(keyValue);
    custdefrule.setCustDefaultAddress(rows);

  }

  /**
   * 
   * 
   * @return billform
   */
  public SaleOrderBillForm getBillform() {
    return this.billform;
  }

  /**
   * 
   * 
   * @param billform
   */
  public void setBillform(SaleOrderBillForm billform) {
    this.billform = billform;
  }

}
