package nc.ui.so.salequotation.handler.body;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTaxCodeRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.ui.so.salequotation.pub.SalequoCalculator;
import nc.vo.so.pub.keyvalue.IKeyRela;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.keyrela.SalequoKeyRela;

public class TaxCodeEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {
    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    BillCardPanel cardpanel = e.getBillCardPanel();
    IKeyValue keyvalue = new CardKeyValue(cardpanel);
    IKeyRela keyRela = new SalequoKeyRela();

    SOTaxInfoRule taxinfo = new SOTaxInfoRule(keyvalue, keyRela);
    taxinfo.setTaxTypeAndRate(rows);

    // 调用数量单价金额算法
    SalequoCalculator calculator = new SalequoCalculator(cardpanel);
    calculator.calculate(rows, SalequotationBVO.NTAXRATE);
  }

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();
    // 报税国为空，不可编辑
    String ctaxcountryid =
        keyValue.getBodyStringValue(row, SalequotationBVO.CTAXCOUNTRYID);

    Integer fbuysellflag =
        keyValue.getBodyIntegerValue(row, SalequotationBVO.FBUYSELLFLAG);
    BillItem item = cardPanel.getBodyItem(SalequotationBVO.CTAXCODEID);

    FilterTaxCodeRefUtils filter =
        new FilterTaxCodeRefUtils((UIRefPane) item.getComponent());
    filter.filterItemRefWithCompatible(ctaxcountryid,
        BuySellFlagEnum.valueOf(fbuysellflag));
  }
}
