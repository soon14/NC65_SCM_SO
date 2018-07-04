package nc.ui.so.m32.billui.editor.headevent;

import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.rule.BodyUpdateByHead;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票表头发票折扣编辑事件
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-5-6 下午08:33:53
 */
public class InvoiceDisEditHandler {

  /**
   * 方法功能描述：发票折扣编辑后事件。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @author 冯加滨
   * @time 2010-5-6 下午08:33:38
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 更新表体发票折扣
    BodyUpdateByHead updateRule = new BodyUpdateByHead(keyValue);
    updateRule.updateAllBodyValueByHead(SaleInvoiceBVO.NINVOICEDISRATE,
        SaleInvoiceHVO.NHVOICEDISRATE);
    // 发票折扣变化，重新计算
    CardPanelCalculator calculator = new CardPanelCalculator(cardPanel);
    calculator.calculateAll(SaleInvoiceBVO.NINVOICEDISRATE);

    // 重新计算所有行的VAT信息
    CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
    vatcal.calVatAll();
  }
}
