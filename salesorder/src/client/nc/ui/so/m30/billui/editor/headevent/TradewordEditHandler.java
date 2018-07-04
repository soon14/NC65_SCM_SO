package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;

/**
 * 贸易术语编辑事件
 * 
 * @since 6.0
 * @version 2012-3-15 上午09:16:42
 * @author 么贵敬
 */
public class TradewordEditHandler {

  /**
   * 编辑后匹配发货国
   * 重新取税
   * 计算单价金额
   * 
   * @param e
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 表体非空行
    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();
    // 1.发货国
    SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
    countryrule.setReceiveCountry(rows);

    // 2.购销类型、三角贸易
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    int[] buychgrows = buyflgrule.getBuysellChgRow();
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    calculator.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
    // 10. 询税
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);
    int[] ratechgrows = taxInfo.getTaxChangeRows();
    calculator.calculate(ratechgrows, SaleOrderBVO.NTAXRATE);

  }

}
