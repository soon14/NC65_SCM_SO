package nc.ui.so.m38.arrange.editor.eidthandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.SaleOrgPubService;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.so.m38.arrange.pub.M38ArrangeModelCalculator;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.DirectStoreRule;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 发货库存组织过滤
 * <p>据销售组织ID、物料ID,得到销售业务委托关系允许的发货的库存组织ID[]", </p>
 * 
 * @since 6.0
 * @version 2011-3-26 下午02:23:14
 * @author 刘志伟
 */
public class SendStockOrgEditHandler {

  public void beforeEdit(BillListPanel listPanel, PushBillEvent e) {
    int row = e.getEditEvent().getRow();
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);

    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    String cmaterialid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALID);
    // 发货库存组织VIDs
    String[] csendstockorgvids = null;
    if (cmaterialid == null || cmaterialid.trim().length() == 0) {
      return;
    }
    try {
      String[] csendstockorgids =
          SaleOrgPubService.getStockOrgIDSBySaleOrgIDAndMaterialID(pk_org,
              cmaterialid);
      csendstockorgvids = this.getSendStockOrgVIDs(csendstockorgids);
    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }

    if (!VOChecker.isEmpty(csendstockorgvids)) {
      BillItem billItem = listPanel.getBodyItem(SaleOrderBVO.CSENDSTOCKORGVID);
      UIRefPane sendStockOrgRefPane = (UIRefPane) billItem.getComponent();
      AbstractRefModel model = sendStockOrgRefPane.getRefModel();
      model.setFilterPks(csendstockorgvids);
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
        if ((value != null) && (value.length() > 0)) {
          alSendStockOrgVIDs.add(value);
        }
      }
      if (alSendStockOrgVIDs.size() > 0) {
        csendstockorgvids = alSendStockOrgVIDs.toArray(new String[0]);
      }
    }
    return csendstockorgvids;
  }

  public void afterEdit(BillListPanel listPanel, PushBillEvent e) {

    int row = e.getEditEvent().getRow();
    ListKeyValue keyValue =
        new ListKeyValue(listPanel, row, ListTemplateType.SUB);

    // 1.缓存旧的组织本币
    String oldcurr = keyValue.getBodyStringValue(row, SaleOrderBVO.CCURRENCYID);

    int[] rows = new int[] {
      row
    };
    // 补充直运仓
    DirectStoreRule dirstorerule = new DirectStoreRule(keyValue);
    dirstorerule.setSendStordoc(rows);
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

    M38ArrangeModelCalculator calculator =
        new M38ArrangeModelCalculator(listPanel);
    calculator.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());

    // 询税
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);
    int[] ratechgrows = taxInfo.getTaxChangeRows();
    calculator.calculate(ratechgrows, SaleOrderBVO.NTAXRATE);

    // 4.判定组织本币是否改变
    String newcurr = keyValue.getBodyStringValue(row, SaleOrderBVO.CCURRENCYID);
    if (!PubAppTool.isEqual(oldcurr, newcurr)) {

      // 根据组织本位币重新计算折本汇率
      SOExchangeRateRule changeraterule = new SOExchangeRateRule(keyValue);
      changeraterule.calcBodyExchangeRates(rows);
      // 集团本位币汇率
      SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
      if (groupraterule.isGroupExchgRateChange(SaleOrderBVO.CCURRENCYID)) {
        groupraterule.calcGroupExchangeRate(rows);
      }
      // 全局本位币汇率
      SOGlobalExchangeRate globalerate = new SOGlobalExchangeRate(keyValue);
      if (globalerate.isGlobalExchgRateChange(SaleOrderBVO.CCURRENCYID)) {
        globalerate.calcGlobalExchangeRate(rows);
      }
      calculator.calculate(rows, SaleOrderBVO.NEXCHANGERATE);
    }
  }
}
