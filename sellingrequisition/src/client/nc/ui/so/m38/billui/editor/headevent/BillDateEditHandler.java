package nc.ui.so.m38.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m38.billui.pub.PreOrderCalculator;
import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyUpdateByHead;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;

public class BillDateEditHandler {

  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 1.更新表体日期
    BodyUpdateByHead updaterule = new BodyUpdateByHead(keyValue);
    updaterule.updateAllBodyValueByHead(PreOrderBVO.DBILLDATE,
        PreOrderHVO.DBILLDATE);

    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();
    UFDouble[] oldexrates = new UFDouble[rows.length];
    int i = 0;
    for (int row : rows) {
      oldexrates[i] =
          keyValue.getBodyUFDoubleValue(row, PreOrderBVO.NEXCHANGERATE);
      i++;
    }
    // 2.重新计算表体行折本汇率
    SOExchangeRateRule exraterule = new SOExchangeRateRule(keyValue);
    exraterule.calcAllBodyExchangeRate();
    // 3.重新计算
    PreOrderCalculator calculator = new PreOrderCalculator(cardPanel);
    i = 0;
    for (int row : rows) {
      UFDouble newexrate =
          keyValue.getBodyUFDoubleValue(row, PreOrderBVO.NEXCHANGERATE);
      if (null == newexrate || null == oldexrates[i]
          || newexrate.compareTo(oldexrates[i]) != 0) {
        calculator.calculate(row, PreOrderBVO.NEXCHANGERATE);
      }
      i++;
    }
    // 4.询价
    PreOrderFindPriceConfig config = new PreOrderFindPriceConfig(cardPanel);
    FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
    findPrice.findPriceAfterEdit(rows, PreOrderHVO.DBILLDATE);
  }
}
