package nc.ui.so.m32.billui.editor.headevent;

import nc.ui.bd.ref.model.CustBankaccDefaultRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.FilterCustBankaccUtil;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * 客户开户银行帐户编辑事件
 * 
 * @since 6.0
 * @version 2011-7-12 下午01:25:51
 * @author 么贵敬
 */
public class CustBankaccEditHandler {

  /**
   * 
   * @param e
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);

    String bankid = keyValue.getHeadStringValue(SaleInvoiceHVO.CCUSTBANKID);
    if (StringUtil.isEmptyWithTrim(bankid)) {
      cardPanel.getBillData().loadEditHeadRelation(
          SaleInvoiceHVO.CCUSTBANKACCID);
    }
  }

  /**
   * 
   * @param e
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    // 客户开户银行帐号受客户、开户银行、币种的限制
    String invoicecust =
        keyValue.getHeadStringValue(SaleInvoiceHVO.CINVOICECUSTID);
    String custbank = keyValue.getHeadStringValue(SaleInvoiceHVO.CCUSTBANKID);
    String currtype = keyValue.getHeadStringValue(SaleInvoiceHVO.CORIGCURRENCYID);
    BillItem bankaccitem = cardPanel.getHeadItem(SaleInvoiceHVO.CCUSTBANKACCID);
    
    // jilu for 633 项目提出的BUG，道明光学，客户银行帐号无法参照问题
    UIRefPane refpanel = (UIRefPane) bankaccitem.getComponent();
    CustBankaccDefaultRefModel bankacc =
        (CustBankaccDefaultRefModel) refpanel.getRefModel();

    bankacc.setPk_cust(null);
    bankacc.addWherePart(null);
    bankacc.setWherePart(null);

    FilterCustBankaccUtil refUtil =
        new FilterCustBankaccUtil(bankaccitem);
    refUtil.filterItemRefByCustAndBank(invoicecust, custbank, currtype);
  }
}
