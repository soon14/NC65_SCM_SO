package nc.ui.so.m38.billui.editor.bodyevent;

import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;

/**
 * 预订单自由辅助属性编辑事件
 * 
 * @since 6.0
 * @version 2011-6-14 上午10:14:17
 * @author fengjb
 */
public class FreeEditHandler {

  /**
   * 
   * 
   * @param e
   */
  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    BillCardPanel cardPanel = e.getBillCardPanel();
    PreOrderFindPriceConfig config = new PreOrderFindPriceConfig(cardPanel);
    FindSalePrice findprice = new FindSalePrice(cardPanel, config);
    findprice.findPriceAfterEdit(rows, e.getKey());
    // 编辑自由辅助属性后，设置客户物料码(V63新加)
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.setCustMaterial(rows);
  }

}
