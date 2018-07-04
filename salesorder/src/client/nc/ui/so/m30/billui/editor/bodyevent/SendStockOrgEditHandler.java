package nc.ui.so.m30.billui.editor.bodyevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.SaleOrgPubService;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.DirectStoreRule;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;

public class SendStockOrgEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    int row = e.getRow();
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String cmaterialid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALID);
    // 物料为空不允许编辑
    if (PubAppTool.isNull(cmaterialid)) {
      e.setReturnValue(false);
      return;
    }
    // 来源于借出单不允许编辑
    String srcbilltype =
        keyValue.getBodyStringValue(row, SaleOrderBVO.VSRCTYPE);
    if (!PubAppTool.isNull(srcbilltype)
        && ICBillType.BorrowOut.getCode().equals(srcbilltype)) {
      e.setReturnValue(false);
      return;
    }
    // 发货库存组织VIDs
    String[] csendstockorgvids = null;
    try {
      String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
      String[] csendstockorgids =
          SaleOrgPubService.getStockOrgIDSBySaleOrgIDAndMaterialID(pk_org,
              cmaterialid);
      csendstockorgvids = this.getSendStockOrgVIDs(csendstockorgids);
    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }
    BillItem sendStockOrgItem =
        cardPanel.getBodyItem(SaleOrderBVO.CSENDSTOCKORGVID);
    UIRefPane sendStockOrgRefPane = (UIRefPane) sendStockOrgItem.getComponent();
    AbstractRefModel model = sendStockOrgRefPane.getRefModel();
    if (null != csendstockorgvids) {
      model.setFilterPks(csendstockorgvids);
    }
    else {
      model.setFilterPks(new String[0]);
    }

  }

  private String[] getSendStockOrgVIDs(String[] csendstockorgids)
      throws BusinessException {
    String[] csendstockorgvids = null;
    if (null == csendstockorgids || csendstockorgids.length == 0) {
      return csendstockorgvids;
    }

    // 转VID
    Map<String, String> hmSendStockOrgIDs =
        OrgUnitPubService.getNewVIDSByOrgIDS(csendstockorgids);

    if (hmSendStockOrgIDs != null) {
      List<String> alSendStockOrgVIDs = new ArrayList<String>();
      for (String value : hmSendStockOrgIDs.values()) {
        if (value != null && value.length() > 0) {
          alSendStockOrgVIDs.add(value);
        }
      }
      if (alSendStockOrgVIDs.size() > 0) {
        csendstockorgvids = alSendStockOrgVIDs.toArray(new String[0]);
      }
    }
    return csendstockorgvids;
  }

  public void afterEdit(CardBodyAfterEditEvent e, SaleOrderBillForm billform) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // 1.缓存旧的组织本币
    int ilength = rows.length;
    String[] oldcurrs = new String[ilength];
    for (int i = 0; i < ilength; i++) {
      oldcurrs[i] =
          keyValue.getBodyStringValue(rows[i], SaleOrderBVO.CCURRENCYID);
    }

    // 补充直运仓
    DirectStoreRule dirstorerule = new DirectStoreRule(keyValue);
    dirstorerule.setSendStordoc(rows);

    if (!this.isDirecttype(keyValue, billform)) {
      // 执行发货库存组织公式是为了根据发货库存组织带出主仓库
      this.execEditFormulas(cardPanel, SaleOrderBVO.CSENDSTOCKORGID, rows);
    }

    // 2.设置结算财务组织、应收组织、利润中心
    SaleOrgRelationRule relarule = new SaleOrgRelationRule(keyValue);
    relarule.setFinanceOrg(rows);
    // 计算结算财务组织对应的币种
    SOCurrencyRule currencyrule = new SOCurrencyRule(keyValue);
    currencyrule.setCurrency(rows);
    // 3.设置物流组织
    relarule.setTrafficOrg(rows);

    // 设置国家
    SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
    countryrule.setCountryInfo(rows);

    // 设置购销类型和三角贸易
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    int[] buychgrows = buyflgrule.getBuysellChgRow();
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    calculator.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());

    // 询税
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);
    int[] ratechgrows = taxInfo.getTaxChangeRows();
    calculator.calculate(ratechgrows, SaleOrderBVO.NTAXRATE);

    // 4.判定组织本币是否改变
    List<Integer> listchgrow = new ArrayList<Integer>();
    for (int i = 0; i < ilength; i++) {
      String newcurr =
          keyValue.getBodyStringValue(rows[i], SaleOrderBVO.CCURRENCYID);
      if (!PubAppTool.isEqual(oldcurrs[i], newcurr)) {
        listchgrow.add(rows[i]);
      }
    }

    int chgsize = listchgrow.size();
    if (chgsize > 0) {
      int[] chgrows = new int[chgsize];
      for (int i = 0; i < chgsize; i++) {
        chgrows[i] = listchgrow.get(i);
      }
      // 根据组织本位币重新计算折本汇率
      SOExchangeRateRule changeraterule = new SOExchangeRateRule(keyValue);
      changeraterule.calcBodyExchangeRates(chgrows);
      // 集团本位币汇率
      SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
      if (groupraterule.isGroupExchgRateChange(SaleOrderBVO.CCURRENCYID)) {
        groupraterule.calcGroupExchangeRate(chgrows);
      }
      // 全局本位币汇率
      SOGlobalExchangeRate globalerate = new SOGlobalExchangeRate(keyValue);
      if (globalerate.isGlobalExchgRateChange(SaleOrderBVO.CCURRENCYID)) {
        globalerate.calcGlobalExchangeRate(chgrows);
      }
      calculator.calculate(chgrows, SaleOrderBVO.NEXCHANGERATE);
    }
    // add by zhangby5 for 利润中心取值规则
    SOProfitCenterValueRule profitRule = new SOProfitCenterValueRule(keyValue);
    profitRule.setProfitCenterValue(SaleOrderBVO.CSPROFITCENTERVID,
        SaleOrderBVO.CSPROFITCENTERID, rows);
  }

  /**
   * 是否直运业务
   */
  private boolean isDirecttype(IKeyValue keyValue, SaleOrderBillForm billform) {
    String vtrantypecode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);

    // modify by zhangby5 从销售订单前台缓存中获取交易类型，如果不存在从后台获取，并缓存之
    M30TranTypeVO m30trantypevo =
        billform.getM30ClientContext().getTransType(vtrantypecode,
            AppContext.getInstance().getPkGroup());
    // 非直运
    if (DirectType.DIRECTTRAN_NO.equalsValue(m30trantypevo.getFdirecttype())) {
      return false;
    }
    return true;
  }

  private void execEditFormulas(BillCardPanel cardPanel, String key, int[] rows) {
    String[] editFormulas = cardPanel.getBodyItem(key).getEditFormulas();
    if (null != editFormulas && editFormulas.length != 0) {
      for (int row : rows) {
        cardPanel.setBodyValueAt(null, row, SaleOrderBVO.CSENDSTORDOCID);
        // 执行编辑公式
        cardPanel.getBillModel().execEditFormulasByKey(row, key);
      }
    }
  }
}
