package nc.ui.so.m30.pub;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;

public class CardPanelUtil {
  private BillCardPanel card;

  public CardPanelUtil(BillCardPanel cardPanel) {
    this.card = cardPanel;
  }

  /**
   * 方法功能描述：为表体行字段赋值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param value
   * @param iRow
   * @param itemkey
   *          <p>
   * 
   * @deprecated 请使用CardPanelValueUtils中的方法
   * @see CardPanelValueUtils setBodyValue
   */
  @Deprecated
  public void setCellValue(Object value, int iRow, String itemkey) {
    String key = itemkey;
    BillItem item = this.card.getBodyItem(itemkey);
    if (item.getDataType() == IBillItem.UFREF) {
      key = itemkey + "_ID";
    }
    this.card.getBillModel().setValueAt(value, iRow, key);
    this.card.getBillModel().loadLoadRelationItemValue(iRow, key);
    this.card.getBillModel().loadEditRelationItemValue(iRow, itemkey);
  }

  /**
   * 方法功能描述：为表头字段赋值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param sItemKey
   * @param value
   *          <p>
   * 
   * @deprecated 请使用CardPanelValueUtils中的方法
   * @see CardPanelValueUtils setHeadTailValue
   */
  @Deprecated
  public void setHeadTailItemValue(String sItemKey, Object value) {
    BillItem item = this.card.getHeadItem(sItemKey);
    if (item != null) {
      this.card.setHeadItem(sItemKey, value);
    }
    else {
      this.card.setTailItem(sItemKey, value);
    }
    this.card.getBillData().loadEditHeadRelation(sItemKey);
  }

}
