package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterCustomerRefUtils;
import nc.ui.so.m30.billui.rule.CustBankAccRule;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;

public class InvoiceCustEditHandler {

  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // １--客户默认开户银行
    CustBankAccRule bankaccrule = new CustBankAccRule(cardPanel);
    bankaccrule.setDefCustBankAcc();
    // 表体非空行
    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();
    
    // ２. 询税,设置VAT税信息时需要涉及到购销类型，三角贸易等信息，这里如果，这些信息为空，就不需要询税
    String customer = keyValue.getHeadStringValue(SaleOrderHVO.CCUSTOMERID);
    if(!PubAppTool.isNull(customer)){      
      SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
      taxInfo.setTaxInfoByBodyPos(rows);
      int[] ratechgrows = taxInfo.getTaxChangeRows();
      SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
      calculator.calculate(ratechgrows, SaleOrderBVO.NTAXRATE);
    }
  }

  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    // 开票客户
    BillItem invoicecustomeritem =
        e.getBillCardPanel().getHeadTailItem(SaleOrderHVO.CINVOICECUSTID);
    FilterCustomerRefUtils invoicefilterutils =
        new FilterCustomerRefUtils(invoicecustomeritem);
    invoicefilterutils.filterRefByFrozenFlag(UFBoolean.FALSE);

  }

}
