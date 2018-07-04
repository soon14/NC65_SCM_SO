package nc.ui.so.m32.billui.editor.bodyevent;

import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.vattax.vo.CalVatFieldValues;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;

/**
 * 销售发票表体税率编辑事件处理
 * 
 * @since 6.1
 * @version 2012-11-21 12:48:23
 * @author 冯加彬
 */
public class TaxRateEditHandler {

  /**
   * 税率编辑后事件
   * 
   * @param e
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    int row = e.getRow();
    BillCardPanel cardpanel = e.getBillCardPanel();

    // 缓存旧的数据
    CardVATCalculator vatcal = new CardVATCalculator(cardpanel);
    CalVatFieldValues oldvat = vatcal.getVatFieldValues(row);
    UFDouble oldtaxrate = (UFDouble) e.getOldValue();
    oldvat.setNtaxrate(oldtaxrate);
    // 调用数量单价金额的换算
    CardPanelCalculator calculator = new CardPanelCalculator(cardpanel);
    calculator.calculate(row, SaleInvoiceBVO.NTAXRATE);

    // 计算VAT合计
    vatcal.calculateVatWhenEditVat(row, oldvat);
  }

}
