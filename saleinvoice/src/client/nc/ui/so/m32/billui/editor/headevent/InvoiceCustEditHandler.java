package nc.ui.so.m32.billui.editor.headevent;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterCustomerRefUtils;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.m32.billui.rule.CustBankAccRule;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.pub.SaleInvoiceKeyRela;
import nc.vo.so.m32.rule.ExchangeRateRule;
import nc.vo.so.m32.rule.PriceMnyClearRule;
import nc.vo.so.m32.rule.VATDefaultRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.trade.checkrule.VOChecker;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票表头开票客户编辑事件处理
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-3-1 下午03:34:43
 */
public class InvoiceCustEditHandler {
  
  
  
  /**
   * 编辑前事件
   * 
   * @param e
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e){
    IKeyValue keyValue = new CardKeyValue( e.getBillCardPanel());
    // 设置开票客户约束条件
    BillItem invoicecust =
        e.getBillCardPanel().getHeadItem(SaleInvoiceHVO.CINVOICECUSTID);
    FilterCustomerRefUtils custfilter = new FilterCustomerRefUtils(invoicecust);
    custfilter.filterRefByFrozenFlag(UFBoolean.FALSE);
    custfilter.filterItemRefByOrg(keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG));
  }

  /**
   * 方法功能描述：销售发票表头开票客户编辑后事件。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @author 冯加滨
   * @time 2010-4-27 下午01:59:43
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();

    String invoicecust =
        keyValue.getHeadStringValue(SaleInvoiceHVO.CINVOICECUSTID);
    String pk_org = keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    String oldcurrency =
        keyValue.getHeadStringValue(SaleInvoiceHVO.CORIGCURRENCYID);

    // --客户默认开户银行
    CustBankAccRule bankaccrule = new CustBankAccRule(cardPanel);
    bankaccrule.setDefCustBankAcc();

    String currency =
        CustomerPubService.getDefaultCurrtype(invoicecust, pk_org);
    CardPanelCalculator calc = new CardPanelCalculator(cardPanel);
    // 如果原来的原币和最新获取到的默认交易币种不同，则重新获取折本汇率
    if (null != currency && !currency.equals(oldcurrency)) {
      // 缓存原来币种
      UFDouble oldExchgrate =
          keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NEXCHANGERATE);
      if (null == oldExchgrate) {
        oldExchgrate = UFDouble.ZERO_DBL;
      }
      // 设置新币种
      keyValue.setHeadValue(SaleInvoiceHVO.CORIGCURRENCYID, currency);
      
      String ccurrencyid =
          keyValue.getHeadStringValue(SaleInvoiceHVO.CCURRENCYID);
      
      if (!PubAppTool.isEqual(ccurrencyid, currency)) {
        // 清空表体单价金额字段
        PriceMnyClearRule clearrule = new PriceMnyClearRule(keyValue);
        clearrule.clearAllBodyItem();
      }
      
      // 获取新的折本汇率
      ExchangeRateRule chgraterule = new ExchangeRateRule(keyValue);
      chgraterule.calcExchangeRate();
      UFDouble newExchgrate =
          keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NEXCHANGERATE);
      // 折本汇率变化，重新计算
      if (!VOChecker.isEmpty(newExchgrate)
          && newExchgrate.compareTo(oldExchgrate) != 0) {
        calc.calculateAll(SaleInvoiceHVO.NEXCHANGERATE);
      }

    }

    // ２. 询税
    SaleInvoiceKeyRela keyrala = new SaleInvoiceKeyRela();
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue, keyrala);
    taxInfo.setTaxInfoByHeadPos(rows);
    int[] ratechgrows = taxInfo.getTaxChangeRows();
    calc.calculate(ratechgrows, SaleInvoiceBVO.NTAXRATE);

    VATDefaultRule vatrule = new VATDefaultRule(keyValue);
    vatrule.setCustvatCode();

    // 3.重新计算所有行的VAT信息
    CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
    vatcal.calVatAll();

  }

}
