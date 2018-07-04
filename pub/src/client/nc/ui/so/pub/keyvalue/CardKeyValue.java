package nc.ui.so.pub.keyvalue;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.vo.so.pub.keyvalue.AbstractKeyValue;

public class CardKeyValue extends AbstractKeyValue {

  private BillCardPanel card;

  private CardPanelValueUtils valueUtil;

  /**
   * CardKeyValue 的构造子
   * 
   * @param cardPanel
   */
  public CardKeyValue(BillCardPanel cardPanel) {
    this.card = cardPanel;
    this.valueUtil = new CardPanelValueUtils(this.card);
  }

  @Override
  public int getBodyCount() {
    return this.card.getBillModel().getRowCount();
  }

  @Override
  public Object getBodyValue(int index, String key) {

    return this.valueUtil.getBodyValue(index, key);
  }

  public BillCardPanel getCard() {
    return this.card;
  }

  @Override
  public Object getHeadValue(String key) {
    return this.valueUtil.getHeadTailValue(key);
  }

  @Override
  public RowStatus getRowStatus(int index) {

    RowStatus flag = RowStatus.UNCHANGED;

    int rowstate = this.card.getBillModel().getRowState(index);
    if (BillModel.NORMAL == rowstate) {
      flag = RowStatus.UNCHANGED;
    }
    else if (BillModel.MODIFICATION == rowstate) {
      flag = RowStatus.UPDATED;
    }
    else if (BillModel.ADD == rowstate) {
      flag = RowStatus.NEW;
    }
    else if (BillModel.DELETE == rowstate) {
      flag = RowStatus.DELETED;
    }
    return flag;
  }

  @Override
  public void setBodyValue(int index, String key, Object value) {
    this.valueUtil.setBodyValue(value, index, key);

  }

  @Override
  public void setHeadValue(String key, Object value) {
    this.valueUtil.setHeadTailValue(key, value);
  }

}
