package nc.ui.so.m30.billui.editor.headevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.so.m30.IQueryRelationOrg;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterCustomerRefUtils;
import nc.ui.so.m30.billui.rule.AssociateConstractRule;
import nc.ui.so.m30.billui.rule.ClearBodyValueRule;
import nc.ui.so.m30.billui.rule.CustBankAccRule;
import nc.ui.so.m30.billui.rule.MatchLargessRule;
import nc.ui.so.m30.billui.rule.SrcTypeRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.rule.PayTermRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.AskPriceRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.ReceiveCustDefAddrRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;
import nc.vo.so.pub.rule.SOCustRelaDefValueRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * 订单客户编辑事件
 * 
 * @since 6.0
 * @version 2011-6-8 上午10:57:10
 * @author fengjb
 */
@SuppressWarnings("restriction")
public class CustomerEditHandler {

  private SaleOrderBillForm billform;

  /**
   * 
   * @param e
   */
  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String oldCorigcurrencyid =
        keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    // 表体非空行
    BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
    int[] rows = countutil.getMarNotNullRows();

    // 客户相关的默认值
    keyValue.setHeadValue(SaleOrderHVO.CHRECEIVECUSTID, e.getValue());
    SOCustRelaDefValueRule defrule = new SOCustRelaDefValueRule(keyValue);
    defrule.setCustRelaDefValue();

    // 表头与表体收货地址
    String custid = (String) e.getValue();
    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    String[] defadds = CustomerPubService.getDefaultAddresses(new String[] {
      custid
    }, pk_org);
    String defaddValue = null;
    if (null != defadds && defadds.length > 0) {
      defaddValue = defadds[0];
    }
    keyValue.setHeadValue(SaleOrderHVO.CHRECEIVEADDID, defaddValue);
    for (int row : rows) {
      keyValue.setBodyValue(row, SaleOrderBVO.CRECEIVEADDRID, defaddValue);
    }

    // 设置默认收货客户地址
    String creceiveaddrid =
        keyValue.getHeadStringValue(SaleOrderHVO.CHRECEIVEADDID);
    for (int index : rows) {
      cardPanel.getBillModel().loadLoadRelationItemValue(index,
          SaleOrderBVO.CRECEIVECUSTID);
      keyValue.setBodyValue(index, SaleOrderBVO.CRECEIVEADDRID, creceiveaddrid);
    }

    // 设置表体收货地点，收货地区
    ReceiveCustDefAddrRule defaddrule = new ReceiveCustDefAddrRule(keyValue);
    defaddrule.setCustDefaultAddress(rows);

    // --设置收款协议信息
    PayTermRule payTermRule = new PayTermRule(keyValue);
    payTermRule.setPayTermInfo();
    // --客户默认开户银行
    CustBankAccRule bankaccrule = new CustBankAccRule(cardPanel);
    bankaccrule.setDefCustBankAcc();

    // 表体只有空行不用计算
    if (rows.length == 0) {
      return;
    }
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    if (defrule.isDiscountRateChg()) {
      calculator.calculate(rows, SaleOrderBVO.NDISCOUNTRATE);
    }
    
    // 3.设置结算财务组织、应收组织、利润中心
    SaleOrgRelationRule relarule = new SaleOrgRelationRule(keyValue);
    relarule.setFinanceOrg(rows);

    // 4.设置每行组织本位币
    SOCurrencyRule currule = new SOCurrencyRule(keyValue);
    currule.setCurrency(rows);
    // 5.计算表体行折本汇率
    SOExchangeRateRule exraterule = new SOExchangeRateRule(keyValue);
    exraterule.calcBodyExchangeRates(rows);
    // 6.全局本位币汇率
    SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
    globalraterule.calcGlobalExchangeRate(rows);

    // 7.集团本位币汇率
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
    groupraterule.calcGroupExchangeRate(rows);

    int[] changerows = exraterule.getRateChangeRow();
    calculator.calculate(changerows, SaleOrderBVO.NEXCHANGERATE);

    // 8.国家信息
    SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
    countryrule.setCountryInfo(rows);

    // 9.购销类型、三角贸易
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    int[] buychgrows = buyflgrule.getBuysellChgRow();
    calculator.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
    // 10. 询税
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);
    int[] ratechgrows = taxInfo.getTaxChangeRows();
    calculator.calculate(ratechgrows, SaleOrderBVO.NTAXRATE);

    // 11.询价
    String trantypecode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    SaleOrderClientContext clientcontex = this.billform.getM30ClientContext();
    M30TranTypeVO trantypevo =
        clientcontex.getTransType(trantypecode, pk_group);
    SaleOrderFindPriceConfig config =
        new SaleOrderFindPriceConfig(cardPanel, trantypevo);
    ClearBodyValueRule clearrule = new ClearBodyValueRule(keyValue, null);
    String newCorigcurrencyid =
        keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    // 币种没有改变则不需要清空价格和特征码相关字段 modify by zhangby5
    //根据鞍钢项目 宋国强 要求 去掉选择表头客户时将表体数据清空的操作
/*    boolean isCurrencyChange =
        !PubAppTool.isEqual(newCorigcurrencyid, oldCorigcurrencyid);
    if (isCurrencyChange) {
      clearrule.clearAllFfileKey(rows);
    }
    if (isFindPrice(config, keyValue)) {
      FindSalePrice findprice = new FindSalePrice(cardPanel, config);
      findprice.findPriceAfterEdit(rows, SaleOrderHVO.CCUSTOMERID);
    }
    else {
      if (isCurrencyChange) {
        clearrule.clearAllPriceKey(rows);
      }
    }*/

    // 12.关联合同
    AssociateConstractRule asctrule =
        new AssociateConstractRule(cardPanel, trantypevo);
    asctrule.associateCT(rows);

    // 13.整单重新匹配买赠
    MatchLargessRule matchlarrule = new MatchLargessRule(cardPanel, trantypevo);
    matchlarrule.matchLargess(rows);

    // 设置发货利润中心
    this.setCsprofitcenterID(billform, keyValue, rows);

    for (int row : rows) {
      // 计算冲抵前金额
      UFDouble norigtaxmny =
          keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGTAXMNY);
      UFDouble norigsubmny =
          keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGSUBMNY);
      keyValue.setBodyValue(row, SaleOrderBVO.NBFORIGSUBMNY,
          MathTool.add(norigtaxmny, norigsubmny));
    }

    // 14.表头价税合计
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

    // 15.编辑客户后，设置客户物料码(V63新加)
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.setCustMaterial(rows);
  }

  /**
   * 
   * 
   * @param e
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    SrcTypeRule srcrule = new SrcTypeRule(keyValue);
    e.setReturnValue(Boolean.valueOf(!srcrule.isBillSrcCT()));

    // 订单客户
    BillItem customeritem = cardPanel.getHeadTailItem(SaleOrderHVO.CCUSTOMERID);
    FilterCustomerRefUtils filterutils =
        new FilterCustomerRefUtils(customeritem);
    filterutils.filterRefByFrozenFlag(UFBoolean.FALSE);
  }

  /**
   * 
   * 
   * @return billform
   */
  public SaleOrderBillForm getBillform() {
    return this.billform;
  }

  /**
   * 
   * 
   * @param billform
   */
  public void setBillform(SaleOrderBillForm billform) {
    this.billform = billform;
  }

  private boolean isFindPrice(SaleOrderFindPriceConfig config,
      IKeyValue keyValue) {

    // 1.询价策略,判定是否询价
    Integer askrule = config.getAskPriceRule();
    if (AskPriceRule.ASKPRICE_NO.equalsValue(askrule)) {
      return false;
    }
    // 2.编辑字段是否触发询价
    if (!this.isTrigFindPrice(SaleOrderHVO.CORIGCURRENCYID, keyValue)) {
      return false;
    }

    return true;
  }

  private boolean isTrigFindPrice(String editkey, IKeyValue keyValue) {
    // 判断如果是价格项 就触发询价
    if (editkey.equals(SOItemKey.CPRICEITEMID)) {
      return true;
    }
    // 销售询价触发条件，判定是否询价
    String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String[] so21 = null;

    so21 = SOSysParaInitUtil.getSO21(pk_org);

    // 询价触发条件为空
    if (null == so21 || so21.length == 0) {
      return false;
    }
    for (String condition : so21) {
      if (editkey.equals(condition)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 查询发货库存组织、结算财务组织ID、应收组织ID、利润中心ID、默认物流组织、直运仓
   * 
   * @param keyValue
   * @param rows
   * @return
   */
  private Map<String, String[]> GetRelationOrg(IKeyValue keyValue, int[] rows) {

    Map<String, String[]> hmRelationOrgid = null;
    // 组织、客户、交易类型、物料参数准备
    String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String ccustomerid = keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);

    List<String> alMaterialid = new ArrayList<String>();

    for (int row : rows) {
      String cmaterialid =
          keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      if (PubAppTool.isNull(cmaterialid)) {
        continue;
      }
      alMaterialid.add(cmaterialid);
    }
    if (alMaterialid.size() == 0) {
      return null;
    }

    String[] cmaterialids = new String[alMaterialid.size()];
    alMaterialid.toArray(cmaterialids);

    String transtypeID = keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    // 查询结算财务组织ID、应收组织ID、利润中心ID和结算财务组织VID、应收组织VID、利润中心VID
    try {
      // 如果交易类型非空，按照交易类型获取直运仓

      IQueryRelationOrg service =
          NCLocator.getInstance().lookup(IQueryRelationOrg.class);
      hmRelationOrgid =
          service.querySaleRelationOrg(transtypeID, ccustomerid, pk_org,
              cmaterialids);

    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }
    return hmRelationOrgid;
  }

  /**
   * 是否直运业务
   */
  private boolean isDirecttype(IKeyValue keyValue, SaleOrderBillForm billform) {
    String vtrantypecode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    M30TranTypeVO m30trantypevo =
        billform.getM30ClientContext().getTransType(vtrantypecode,
            AppUiContext.getInstance().getPkGroup());
    // 非直运
    if (DirectType.DIRECTTRAN_NO.equalsValue(m30trantypevo.getFdirecttype())) {
      return false;
    }
    return true;
  }

  /**
   * 执行物料编辑公式 dongli2 2013.07.17
   * 
   * @param cardPanel
   * @param rows
   */
  private void execEditFormulas(BillCardPanel cardPanel, String[] key,
      int[] rows) {
    if (rows.length == 1) {
      for (String str : key) {
        // 执行编辑公式
        cardPanel.getBillModel().execEditFormulaByKey(rows[0], str);
      }
    }
    else {
      cardPanel.getBillModel().execEditFormulas(-1);
    }
  }

  /**
   * 设置发货利润中心
   * 
   * @param billform
   * @param keyValue
   * @param rows
   */
  private void setCsprofitcenterID(SaleOrderBillForm billform,
      IKeyValue keyValue, int[] rows) {
    // 直运业务，发货利润中心=结算利润中心
    if (this.isDirecttype(keyValue, billform)) {
      int length = rows.length;
      for (int index = 0; index < length; index++) {
        String cprofitcenterid =
            keyValue.getBodyStringValue(rows[index],
                SaleOrderBVO.CPROFITCENTERID);
        String cprofitcentervid =
            keyValue.getBodyStringValue(rows[index],
                SaleOrderBVO.CPROFITCENTERVID);
        keyValue.setBodyValue(rows[index], SaleOrderBVO.CSPROFITCENTERID,
            cprofitcenterid);
        keyValue.setBodyValue(rows[index], SaleOrderBVO.CSPROFITCENTERVID,
            cprofitcentervid);
      }
    }
    else {
      // 利润中心取值规则，非直运业务如此取值
      SOProfitCenterValueRule profitRule =
          new SOProfitCenterValueRule(keyValue);
      profitRule.setProfitCenterValue(SaleOrderBVO.CSPROFITCENTERVID,
          SaleOrderBVO.CSPROFITCENTERID, rows);
    }
  }

}
