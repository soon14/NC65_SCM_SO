package nc.ui.so.m32.billui.rule;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 销售发票带出默认帐户
 * 
 * @since 6.0
 * @version 2011-11-17 上午08:49:59
 * @author 么贵敬
 */
public class CustBankAccRule {

  /**
   * 卡片
   */
  private BillCardPanel cardPanel;

  /**
   * 构造方法
   * 
   * @param cardPanel 卡片
   */
  public CustBankAccRule(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
  }

  /**
   * 设置默认的银行帐号和开户银行
   */
  public void setDefCustBankAcc() {

    IKeyValue keyValue = new CardKeyValue(this.cardPanel);

    String invoicecust =
        keyValue.getHeadStringValue(SaleInvoiceHVO.CINVOICECUSTID);
    if (PubAppTool.isNull(invoicecust)) {
      keyValue.setHeadValue(SaleInvoiceHVO.CCUSTBANKID, null);
      keyValue.setHeadValue(SaleInvoiceHVO.CCUSTBANKACCID, null);
      return;
    }

    String defacc = CustomerPubService.getDefaultBankAcc(invoicecust);
    keyValue.setHeadValue(SaleInvoiceHVO.CCUSTBANKACCID, defacc);

    // 执行关联公式，带出开户银行
    this.cardPanel.getBillData().loadEditHeadRelation(
        SaleInvoiceHVO.CCUSTBANKACCID);

  }
}
