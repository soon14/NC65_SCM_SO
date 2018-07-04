package nc.ui.so.m30.billui.editor.bodyevent;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderTranTypeUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOTaxInfoRule;

/**
 * 结算财务组织编辑事件
 * 
 * @since 6.0
 * @version 2011-6-9 上午11:03:31
 * @author fengjb
 */
public class SettleOrgEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyvalue = new CardKeyValue(cardPanel);
    String trantypeid = keyvalue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    SaleOrderTranTypeUtil dirtypeutil = new SaleOrderTranTypeUtil();
    e.setReturnValue(!dirtypeutil.isDirectTran(trantypeid));

  }

  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // 缓存原本币
    int ilength = rows.length;
    String[] oldcurrs = new String[ilength];
    for (int i = 0; i < ilength; i++) {
      oldcurrs[i] =
          keyValue.getBodyStringValue(rows[i], SaleOrderBVO.CCURRENCYID);
    }

    // 1.计算结算财务组织对应的币种
    SOCurrencyRule currencyrule = new SOCurrencyRule(keyValue);
    currencyrule.setCurrency(rows);
    // 2.判定组织本币是否改变
    List<Integer> listchgrow = new ArrayList<Integer>();
    for (int i = 0; i < ilength; i++) {
      String newcurr =
          keyValue.getBodyStringValue(rows[i], SaleOrderBVO.CCURRENCYID);
      if (!PubAppTool.isEqual(oldcurrs[i], newcurr)) {
        listchgrow.add(rows[i]);
      }
    }
    int chgsize = listchgrow.size();
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    if (chgsize > 0) {
      int[] curchgrows = new int[chgsize];
      for (int i = 0; i < chgsize; i++) {
        curchgrows[i] = listchgrow.get(i);
      }
      // 根据组织本位币重新计算折本汇率
      SOExchangeRateRule changeraterule = new SOExchangeRateRule(keyValue);
      changeraterule.calcBodyExchangeRates(curchgrows);
      // 集团本位币汇率
      SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
      if (groupraterule.isGroupExchgRateChange(SaleOrderBVO.CCURRENCYID)) {
        groupraterule.calcGroupExchangeRate(curchgrows);
      }
      // 全局本位币汇率
      SOGlobalExchangeRate globalerate = new SOGlobalExchangeRate(keyValue);
      if (globalerate.isGlobalExchgRateChange(SaleOrderBVO.CCURRENCYID)) {
        globalerate.calcGlobalExchangeRate(curchgrows);
      }
      calculator.calculate(curchgrows, SaleOrderBVO.NEXCHANGERATE);
    }

    // 2.设置国家
    SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
    countryrule.setCountryInfo(rows);

    // 3.设置购销类型和三角贸易
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    int[] buychgrows = buyflgrule.getBuysellChgRow();
    calculator.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());

    // 4.询税
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);
    int[] taxchgrows = taxInfo.getTaxChangeRows();
    calculator.calculate(taxchgrows, SaleOrderBVO.NTAXRATE);
  }
}
