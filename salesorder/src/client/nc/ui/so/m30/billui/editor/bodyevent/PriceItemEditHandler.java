package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;

public class PriceItemEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);

    BillCardPanel cardPanel = e.getBillCardPanel();
    SaleOrderFindPriceConfig config = new SaleOrderFindPriceConfig(cardPanel);
    FindSalePrice findprice = new FindSalePrice(cardPanel, config);
    findprice.findPriceAfterEdit(rows, SaleOrderBVO.CPRICEITEMID);
  }

  /**
   * 价格项编辑前事件
   * 
   * @param e 表体编辑前事件
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BodyEditEventUtil.getInstance().filterPricetype(e,
        SaleOrderBVO.CPRICEITEMTABLEID, SaleOrderBVO.CPRICEITEMID);
  }
}
