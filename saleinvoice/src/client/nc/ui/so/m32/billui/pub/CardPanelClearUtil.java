package nc.ui.so.m32.billui.pub;

import nc.vo.so.m32.entity.SaleInvoiceBVO;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 卡片清除工具
 * 
 * @since 6.3
 * @version 2012-12-21 下午01:01:34
 * @author yaogj
 */
public class CardPanelClearUtil {

  private BillCardPanel cardPanel;

  /**
   * CardPanelClearUtil 的构造子
   * 
   * @param cardPanel
   */
  public CardPanelClearUtil(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
  }

  /**
   * 方法功能描述：清除卡片界面表头表体数据。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author 冯加滨
   * @time 2010-4-26 下午04:52:41
   */
  public void clearValue() {

    CardKeyValue keyValue = new CardKeyValue(this.cardPanel);
    // 清空表头字段值
    BillItem[] headItems = this.cardPanel.getHeadItems();
    for (BillItem item : headItems) {
      item.setValue(item.getDefaultValue());
    }

    // 清空表体字段值
    BillItem[] bodyItems = this.cardPanel.getBodyItems();
    for (BillItem bodyitem : bodyItems) {
      // 表体行号不应清除
      if (SaleInvoiceBVO.CROWNO.equals(bodyitem.getKey())) {
        continue;
      }
      int rowcount = this.cardPanel.getRowCount();
      for (int i = 0; i < rowcount; i++) {

        keyValue.setBodyValue(i, bodyitem.getKey(), bodyitem.getDefaultValue());

      }
    }

    BillItem[] tailItems = this.cardPanel.getTailItems();
    for (BillItem item : tailItems) {

      item.setValue(item.getDefaultValue());

    }

  }
}
