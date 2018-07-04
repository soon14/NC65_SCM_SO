package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterCountryAreaRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 原产地区编辑事件
 * 1、原产国家没有输入的话 不允许编辑
 * 2、按照原产国过滤行政区划
 * 
 * @since 6.0
 * @version 2012-3-8 上午09:07:55
 * @author 么贵敬
 */
public class OrigAreaEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();
    // 原产国
    String origcountryid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CORIGCOUNTRYID);
    if (PubAppTool.isNull(origcountryid)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

    FilterCountryAreaRefUtils filter =
        new FilterCountryAreaRefUtils((UIRefPane) cardPanel.getBodyItem(
            SaleOrderBVO.CORIGAREAID).getComponent());
    filter.filterItemRefBy(origcountryid);

  }
}
