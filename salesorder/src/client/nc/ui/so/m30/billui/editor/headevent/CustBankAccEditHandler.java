package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.bd.ref.model.CustBankaccDefaultRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.FilterCustBankaccUtil;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 客户开户银行编辑事件
 * 
 * @since 6.0
 * @version 2011-7-11 下午02:04:32
 * @author fengjb
 */
public class CustBankAccEditHandler {

  public void beforeEdit(CardHeadTailBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    String invoicecustid =
        keyValue.getHeadStringValue(SaleOrderHVO.CINVOICECUSTID);
    String currtype = keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    String custbank = keyValue.getHeadStringValue(SaleOrderHVO.CCUSTBANKID);
    if (PubAppTool.isNull(invoicecustid)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

    BillItem bankaccitem = cardPanel.getHeadItem(SaleOrderHVO.CCUSTBANKACCID);
    UIRefPane bankaccref = (UIRefPane) bankaccitem.getComponent();
    CustBankaccDefaultRefModel bankaccrefmodel =
        (CustBankaccDefaultRefModel) bankaccref.getRefModel();
    bankaccrefmodel.setPk_cust(invoicecustid);
    
    
    FilterCustBankaccUtil refUtil =
        new FilterCustBankaccUtil(bankaccitem);
    refUtil.filterItemRefByCustAndBank(invoicecustid, custbank, currtype);
  }

}
