package nc.ui.so.m32.billui.editor.bodyevent;

import nc.vo.scmpub.vattax.vo.CalVatFieldValues;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;

/**
 * 扣税类型字段编辑事件
 * 
 * @since 6.0
 * @version 2011-12-31 下午02:22:54
 * @author 么贵敬
 */
public class TaxTypeFlagEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {

    int row = e.getRow();
    int[] rows = new int[] {
      row
    };
    BillCardPanel cardPanel = e.getBillCardPanel();
    // 缓存旧的数据
    CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
    CalVatFieldValues oldvat = vatcal.getVatFieldValues(row);
    Integer oldtaxtype = (Integer) e.getOldValue();
    oldvat.setFtaxtypeflag(oldtaxtype);
    // 调用单价金额算法（已税率触发）
    CardPanelCalculator calculator = new CardPanelCalculator(cardPanel);
    calculator.calculate(rows, SaleInvoiceBVO.NTAXRATE);
    // 计算VAT合计
    vatcal.calculateVatWhenEditVat(row, oldvat);
  }

}
