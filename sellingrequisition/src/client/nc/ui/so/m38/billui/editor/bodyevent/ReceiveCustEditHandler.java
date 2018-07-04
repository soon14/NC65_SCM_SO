package nc.ui.so.m38.billui.editor.bodyevent;

import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.keyrela.PreOrderKeyrela;
import nc.vo.so.pub.keyvalue.IKeyRela;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.ReceiveCustDefAddrRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;

/**
 * 预订单收货客户编辑事件
 * 
 * @since 6.0
 * @version 2012-2-8 上午15:36:21
 * @author 刘景
 */
public class ReceiveCustEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);

    // 1.设置默认收货地址
    IKeyRela keyRela = new PreOrderKeyrela();
    ReceiveCustDefAddrRule defaddrule =
        new ReceiveCustDefAddrRule(keyValue, keyRela);
    defaddrule.setCustDefaultAddress(rows);
    // 2.设置收货国家/地区
    SOCountryInfoRule conutryinforule = new SOCountryInfoRule(keyValue);
    conutryinforule.setReceiveCountry(rows);
    // 3.设置购销类型和三角贸易
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    int[] buysellrows = buyflgrule.getBuysellChgRow();
    calculator.calculate(buysellrows, SOCalConditionRule.getCalPriceKey());
    // 4.询税
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);
    int[] taxchgrows = taxInfo.getTaxChangeRows();
    // 5.以税率触发数量单价金额运算
    calculator.calculate(taxchgrows, PreOrderBVO.NTAXRATE);

  }

}
