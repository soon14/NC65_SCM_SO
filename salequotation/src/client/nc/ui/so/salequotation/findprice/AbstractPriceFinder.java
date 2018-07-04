package nc.ui.so.salequotation.findprice;

import nc.ui.pubapp.AppUiContext;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.salequotation.model.FindPriceService;

public abstract class AbstractPriceFinder {

  private FindPriceService findPriceService;

  private String pk_group;

  /**
   * 表体事件触发询价
   * 
   * @param e
   * @param rows
   *          触发询价的所有行
   */
  public abstract void findPrice(CardBodyAfterEditEvent e, int rows);

  /**
   * 表头事件触发询价
   * 
   * @param e
   */
  public abstract void findPrice(CardHeadTailAfterEditEvent e);

  public FindPriceService getFindPriceService() {
    return this.findPriceService;
  }

  public String getPk_group() {
    if (null == this.pk_group) {
      this.pk_group = AppUiContext.getInstance().getPkGroup();
    }
    return this.pk_group;
  }

  public void setFindPriceService(FindPriceService findPriceService) {
    this.findPriceService = findPriceService;
  }

  public void setPk_group(String pkGroup) {
    this.pk_group = pkGroup;
  }

  protected boolean ifHeadItemValueNotNull(BillCardPanel cardPanel,
      String itemKey) {
    BillItem item = cardPanel.getHeadItem(itemKey);
    if (item != null) {
      Object value = item.getValueObject();
      if (value != null) {
        return true;
      }
    }
    return false;
  }
}
