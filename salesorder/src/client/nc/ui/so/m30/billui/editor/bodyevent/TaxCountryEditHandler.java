package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;

/**
 * 报税国编辑后事件
 * 
 * @since 6.0
 * @version 2012-2-7 下午03:52:49
 * @author 么贵敬
 */
public class TaxCountryEditHandler {

  /**
   * 
   * @param e 表体编辑后事件
   * @param rows 改变的行
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);

    int[] buychgrows = buyflgrule.getBuysellChgRow();
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    calculator.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());

    // 询税
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);

    int[] chgrows = taxInfo.getTaxChangeRows();
    calculator.calculate(chgrows, SaleOrderBVO.NTAXRATE);

  }
}
