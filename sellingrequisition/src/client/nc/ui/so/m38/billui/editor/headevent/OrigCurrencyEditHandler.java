package nc.ui.so.m38.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m38.billui.pub.PreOrderCalculator;
import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyUpdateByHead;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;

/**
 * 原币币种编辑事件
 * 
 * @since 6.0
 * @version 2011-6-14 上午09:23:15
 * @author fengjb
 */
public class OrigCurrencyEditHandler {

  public void afteEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();
    // 1.更新表体行币种值
    BodyUpdateByHead updaterule = new BodyUpdateByHead(keyValue);
    updaterule.updateAllBodyValueByHead(PreOrderBVO.CORIGCURRENCYID,
        PreOrderHVO.CORIGCURRENCYID);
    // 2.计算表体行折本汇率
    SOExchangeRateRule exraterule = new SOExchangeRateRule(keyValue);
    exraterule.calcAllBodyExchangeRate();
    // 3.全局本位币汇率
    SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
    if (globalraterule.isGlobalExchgRateChange(PreOrderHVO.CORIGCURRENCYID)) {
      globalraterule.calcGlobalExchangeRate(rows);
    }

    // 4.集团本位币汇率
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
    if (groupraterule.isGroupExchgRateChange(PreOrderHVO.CORIGCURRENCYID)) {
      groupraterule.calcGroupExchangeRate(rows);
    }
    // 5.数量单价金额运算
    PreOrderCalculator calculator = new PreOrderCalculator(cardPanel);
    for (int row : rows) {
      calculator.calculate(row, PreOrderBVO.NEXCHANGERATE);
    }
    // 6.询价
    PreOrderFindPriceConfig config = new PreOrderFindPriceConfig(cardPanel);
    FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
    findPrice.findPriceAfterEdit(rows, PreOrderHVO.CORIGCURRENCYID);

  }
}
