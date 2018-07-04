package nc.ui.so.m38.billui.editor.bodyevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.keyrela.PreOrderKeyrela;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyRela;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.ReceiveCustDefAddrRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;
import nc.vo.so.pub.rule.SOCustRelaDefValueRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;
import nc.vo.so.pub.rule.SOUnitDefaultRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;

import nc.itf.so.m38.IQueryRelationOrg;

import nc.bs.framework.common.NCLocator;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.ui.so.m38.billui.pub.BodyDefaultRule;
import nc.ui.so.m38.billui.pub.ClearBodyValueRule;
import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 
 * @since 6.3
 * @version 2012-12-24 09:50:07
 * @author liangjm
 */
public class MaterialEditHandler {

  /**
   * 物料 编辑后
   * 
   * @param e
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    // --参照多选处理
    RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());
    int[] rows = utils.refMoreSelected(e.getRow(), e.getKey(), true);

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // 清空表体字段
    ClearBodyValueRule cbvr = new ClearBodyValueRule(keyValue);
    cbvr.clearBodyValue(rows);

    // --1.通过物料id获得物料默认单位,并计算换算率
    SOUnitDefaultRule unitdef = new SOUnitDefaultRule(keyValue);
    unitdef.setDefaultSaleUnit(rows);

    SOUnitChangeRateRule unitrate = new SOUnitChangeRateRule(keyValue);
    for (int row : rows) {
      unitrate.calcAstChangeRate(row);
      unitrate.calcQtChangeRate(row);
    }
    // 表体默认值
    BodyDefaultRule defrule = new BodyDefaultRule(keyValue);
    defrule.setBodyDefaultValue(rows);

    // 设置默认收货客户
    SOCustRelaDefValueRule custrefrule = new SOCustRelaDefValueRule(keyValue);
    custrefrule.setRelaReceiveCust(rows);

    IKeyRela keyRela = new PreOrderKeyrela();
    ReceiveCustDefAddrRule defaddrule =
        new ReceiveCustDefAddrRule(keyValue, keyRela);
    defaddrule.setCustDefaultAddress(rows);
    // 根据组织委托关系设置默认组织
    SaleOrgRelationRule orgrelarule = new SaleOrgRelationRule(keyValue);
    orgrelarule.setFinanceStockOrg(rows,
        this.GetRelationOrg(keyValue, rows));
    
    orgrelarule.setTrafficOrg(rows);

    // 计算结算财务组织对应的币种
    SOCurrencyRule currencyrule = new SOCurrencyRule(keyValue);
    currencyrule.setCurrency(rows);
    // 根据组织本位币重新计算折本汇率
    SOExchangeRateRule changeraterule = new SOExchangeRateRule(keyValue);
    changeraterule.calcBodyExchangeRates(rows);
    // 集团本位币汇率
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
    groupraterule.calcGroupExchangeRate(rows);
    // 全局本位币汇率
    SOGlobalExchangeRate globalerate = new SOGlobalExchangeRate(keyValue);
    globalerate.calcGlobalExchangeRate(rows);

    // 设置国家
    SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
    countryrule.setCountryInfo(rows);

    // 设置购销类型和三角贸易
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    // 询税
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);

    // 询价
    PreOrderFindPriceConfig config = new PreOrderFindPriceConfig(cardPanel);
    FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
    findPrice.findPriceAfterEdit(rows, SaleOrderBVO.CMATERIALVID);

    // 编辑物料后，设置客户物料码(V63新加)
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.setCustMaterial(rows);
  }

  /**
   * 物料 编辑前
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 修订时已经有下游单据的不允许修改
    Integer billstatus = keyValue.getHeadIntegerValue(PreOrderHVO.FSTATUSFLAG);
    int row = e.getRow();
    UFDouble narrnum = keyValue.getBodyUFDoubleValue(row, PreOrderBVO.NARRNUM);

    if (BillStatus.AUDIT.equalsValue(billstatus) && !MathTool.isZero(narrnum)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    // 已经行关闭的不允许修改
    UFBoolean bclose =
        keyValue.getBodyUFBooleanValue(row, PreOrderBVO.BLINECLOSE);
    if (null != bclose && bclose.booleanValue()) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    // 设置物料可以多选
    UIRefPane refPane =
        (UIRefPane) cardPanel.getBodyItem(PreOrderBVO.CMATERIALVID)
            .getComponent();
    refPane.setMultiSelectedEnabled(true);
    // 设置物料约束条件
    BillItem cmaterialvid = cardPanel.getBodyItem(PreOrderBVO.CMATERIALVID);
    FilterMaterialRefUtils utils =
        new FilterMaterialRefUtils((UIRefPane) cmaterialvid.getComponent());
    String pk_org = keyValue.getHeadStringValue(PreOrderHVO.PK_ORG);
    utils.filterItemRefByOrg(pk_org);
    e.setReturnValue(Boolean.TRUE);
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

    // 查询结算财务组织ID、应收组织ID、利润中心ID和结算财务组织VID、应收组织VID、利润中心VID
    try {

      IQueryRelationOrg service =
          NCLocator.getInstance().lookup(IQueryRelationOrg.class);
      hmRelationOrgid =
          service.querySaleRelationOrg(ccustomerid, pk_org, cmaterialids);

    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }
    return hmRelationOrgid;
  }
}
