package nc.ui.so.m38.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.so.m38.entity.PreOrderBVO;

/**
 * 预订单价格项编辑事件
 * 
 * @since 6.0
 * @version 2011-9-6 下午07:49:50
 * @author 王天文
 */

public class PriceItemEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    BillCardPanel cardPanel = e.getBillCardPanel();

    PreOrderFindPriceConfig config = new PreOrderFindPriceConfig(cardPanel);
    FindSalePrice findprice = new FindSalePrice(cardPanel, config);
    findprice.findPriceAfterEdit(rows, PreOrderBVO.CPRICEITEMID);
  }

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BodyEditEventUtil.getInstance().filterPricetype(e,
        PreOrderBVO.CPRICEITEMTABLEID, PreOrderBVO.CPRICEITEMID);
  }

}
