package nc.ui.so.m38.arrange.editor.eidthandler;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.scmpub.ref.FilterTaxCodeRefUtils;
import nc.ui.so.m38.arrange.pub.M38ArrangeModelCalculator;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOTaxInfoRule;

public class TaxCodeEditHandler {

  public void beforeEdit(BillListPanel listPanel, PushBillEvent e) {
    int row = e.getEditEvent().getRow();
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);
    // 报税国为空，不可编辑
    String ctaxcountryid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CTAXCOUNTRYID);
    if (PubAppTool.isNull(ctaxcountryid)) {
      e.setEditable(false);
      return;
    }
    Integer fbuysellflag =
        keyValue.getBodyIntegerValue(row, SaleOrderBVO.FBUYSELLFLAG);
    BillItem item = listPanel.getBodyItem(SaleOrderBVO.CTAXCODEID);

    FilterTaxCodeRefUtils filter =
        new FilterTaxCodeRefUtils((UIRefPane) item.getComponent());
    filter.filterItemRefWithCompatible(ctaxcountryid,
        BuySellFlagEnum.valueOf(fbuysellflag));

  }

  public void afterEdit(BillListPanel listPanel, PushBillEvent e) {

    int row = e.getEditEvent().getRow();
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);
    int[] rows = new int[] {
      row
    };
    // 1.设置税率、扣税类别
    SOTaxInfoRule taxinfo = new SOTaxInfoRule(keyValue);
    taxinfo.setTaxTypeAndRate(rows);
    M38ArrangeModelCalculator calculator =
        new M38ArrangeModelCalculator(listPanel);
    calculator.calculate(rows, SaleOrderBVO.NTAXRATE);
  }

}
