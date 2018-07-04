package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterCustomerRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 客户编辑事件
 *
 */
public class OrderCustEditHandler {
  
  /**
   * 客户编辑事件
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    IKeyValue keyValue = new CardKeyValue( e.getBillCardPanel());
    // 设置客户约束条件
    BillItem ordercust = e.getBillCardPanel().getBodyItem(
        DeliveryBVO.CORDERCUSTID);
    FilterCustomerRefUtils custfilter = new FilterCustomerRefUtils(
        ordercust);
    custfilter.filterRefByFrozenFlag(UFBoolean.FALSE);
    custfilter.filterItemRefByOrg(keyValue.getHeadStringValue(DeliveryHVO.PK_ORG));
  }

}
