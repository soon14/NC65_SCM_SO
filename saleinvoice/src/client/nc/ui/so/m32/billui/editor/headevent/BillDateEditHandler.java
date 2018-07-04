package nc.ui.so.m32.billui.editor.headevent;

import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.rule.BodyUpdateByHead;
import nc.vo.so.m32.rule.ExchangeRateRule;
import nc.vo.trade.checkrule.VOChecker;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>单据日期编辑事件处理
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-5-6 下午08:03:23
 */
public class BillDateEditHandler {

  /**
   * 方法功能描述：单据日期编辑后事件。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @author 冯加滨
   * @time 2010-5-6 下午08:29:55
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);

    // 更新表体单据日期
    BodyUpdateByHead updateRule = new BodyUpdateByHead(keyValue);
    updateRule.updateAllBodyValueByHead(SaleInvoiceBVO.DBILLDATE,
        SaleInvoiceHVO.DBILLDATE);

    // 取当期折本汇率
    UFDouble oldExchgrate =
        keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NEXCHANGERATE);
    if (null == oldExchgrate) {
      oldExchgrate = UFDouble.ZERO_DBL;
    }

    ExchangeRateRule exchgRule = new ExchangeRateRule(keyValue);
    exchgRule.calcExchangeRate();

    UFDouble newExchgrate =
        keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NEXCHANGERATE);
    // 折本汇率变化，重新计算
    if (!VOChecker.isEmpty(newExchgrate)
        && newExchgrate.compareTo(oldExchgrate) != 0) {
      CardPanelCalculator calc = new CardPanelCalculator(cardPanel);
      calc.calculateAll(SaleInvoiceHVO.NEXCHANGERATE);
      // 重新计算所有行的VAT信息
      CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
      vatcal.calVatAll();
    }

  }
}
