package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;

public class QualitylevelEditHandler {
  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);

    BillCardPanel cardPanel = e.getBillCardPanel();
    SaleOrderFindPriceConfig config = new SaleOrderFindPriceConfig(cardPanel);
    FindSalePrice findprice = new FindSalePrice(cardPanel, config);
    findprice.findPriceAfterEdit(rows, SaleOrderBVO.CQUALITYLEVELID);
  }

}
