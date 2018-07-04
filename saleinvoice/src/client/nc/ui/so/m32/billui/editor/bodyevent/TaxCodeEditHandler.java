package nc.ui.so.m32.billui.editor.bodyevent;

import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.vattax.vo.CalVatFieldValues;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOTaxInfoRule;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTaxCodeRefUtils;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 税码编辑后
 * 
 * @since 6.0
 * @version 2012-3-13 上午08:23:25
 * @author 么贵敬
 */
public class TaxCodeEditHandler {

  /**
   * 编辑后事件
   * 1、询税
   * 2、触发单价金额算法
   * 
   * @param e
   */
  public void afterEdit(CardBodyAfterEditEvent e) {

    int row = e.getRow();
    int[] rows = new int[] {
      row
    };
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 缓存旧的数据
    CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
    CalVatFieldValues oldvat = vatcal.getVatFieldValues(row);
    String oldtaxid = (String) e.getOldValue();
    oldvat.setCtaxcodeid(oldtaxid);
    // 设置税率、扣税类别
    SOTaxInfoRule taxinfo = new SOTaxInfoRule(keyValue);
    taxinfo.setTaxTypeAndRate(rows);
    // 以税率触发单价金额算法
    CardPanelCalculator calculator = new CardPanelCalculator(cardPanel);
    calculator.calculate(rows, PreOrderBVO.NTAXRATE);

    // 计算VAT合计
    vatcal.calculateVatWhenEditVat(row, oldvat);
  }

  /**
   * 税码编辑前事件
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    // 报税国为空，不可编辑
    String ctaxcountryid =
        keyValue.getHeadStringValue(SaleInvoiceHVO.CTAXCOUNTRYID);
    if (PubAppTool.isNull(ctaxcountryid)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    Integer fbuysellflag =
        keyValue.getHeadIntegerValue(SaleInvoiceHVO.FBUYSELLFLAG);
    BillItem item = cardPanel.getBodyItem(SaleInvoiceBVO.CTAXCODEID);

    FilterTaxCodeRefUtils filter =
        new FilterTaxCodeRefUtils((UIRefPane) item.getComponent());
    filter.filterItemRefWithCompatible(ctaxcountryid,
        BuySellFlagEnum.valueOf(fbuysellflag));
  }

}
