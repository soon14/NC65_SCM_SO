package nc.ui.so.m30.billui.rule;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class CustBankAccRule {

  private BillCardPanel cardPanel;

  public CustBankAccRule(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
  }

  public void setDefCustBankAcc() {

    IKeyValue keyValue = new CardKeyValue(cardPanel);

    String invoicecust =
        keyValue.getHeadStringValue(SaleOrderHVO.CINVOICECUSTID);
    if (PubAppTool.isNull(invoicecust)) {
      keyValue.setHeadValue(SaleOrderHVO.CCUSTBANKID, null);
      keyValue.setHeadValue(SaleOrderHVO.CCUSTBANKACCID, null);
      return;
    }

    String defacc = CustomerPubService.getDefaultBankAcc(invoicecust);
    keyValue.setHeadValue(SaleOrderHVO.CCUSTBANKACCID, defacc);

    // 执行关联公式，带出开户银行
    this.cardPanel.getBillData().loadEditHeadRelation(
        SaleOrderHVO.CCUSTBANKACCID);

  }
}
