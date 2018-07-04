package nc.ui.so.m30.billui.editor.bodyevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.so.m30.IQueryRelationOrg;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.ui.scmf.ic.onhand.OnhandPanelAdaptor;
import nc.ui.so.m30.billui.rule.AssociateConstractRule;
import nc.ui.so.m30.billui.rule.BodyDefaultValueRule;
import nc.ui.so.m30.billui.rule.ClearBodyValueRule;
import nc.ui.so.m30.billui.rule.MatchBindLargessRule;
import nc.ui.so.m30.billui.rule.RelateRowDeleteRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.ct.business.enumeration.Ninvctlstyle;
import nc.vo.ct.entity.CtBusinessVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
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
import nc.vo.so.pub.rule.SOUnitChangeRateRule;
import nc.vo.so.pub.rule.SOUnitDefaultRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.uapbd.custmaterial.CustMaterialVO;
import nc.vo.uapbd.custom.CustomVO;

/**
 * 客户物料码编辑事件
 * 
 * @since 6.3
 * @version 2012-12-11 13:12:14
 * @author liangjm
 */

public class CustMaterialEditHandler {

  /**
   * 客户物料码编辑后
   * 
   * @param e
   * @param billform
   * 
   */

  public void afterEdit(CardBodyAfterEditEvent e, SaleOrderBillForm billform) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int editrow = e.getRow();
    // 旧的单位
    String oldmarvid = (String) e.getOldValue();
    String oldunitid = this.getMaterialUnitID(oldmarvid);
    String oldastunitid =
        keyValue.getBodyStringValue(editrow, SaleOrderBVO.CASTUNITID);
    String oldqtunitid =
        keyValue.getBodyStringValue(editrow, SaleOrderBVO.CQTUNITID);

    // 缓存原始行ID
    String oldbid =
        keyValue.getBodyStringValue(editrow, SaleOrderBVO.CSALEORDERBID);
    // 删除买赠、捆绑匹配行
    String[] srcbids = new String[] {
      oldbid
    };
    RelateRowDeleteRule delrule = new RelateRowDeleteRule(keyValue);
    int[] todelrows = delrule.getRelaDeleteRows(srcbids);
    if (todelrows.length > 0) {
      for (int delrow : todelrows) {
        if (delrow < editrow) {
          editrow--;
        }
      }
      cardPanel.getBillModel().delLine(todelrows);
    }
    // --1.清空非数量单价金额字段信息
    Map<String, CtBusinessVO> ctMap = billform.getCtMap();
    ClearBodyValueRule clearrule = new ClearBodyValueRule(keyValue, ctMap);
    clearrule.clearBodyNoNumPriceMnyValue(editrow);
    // --参照多选处理
    RefMoreSelectedUtils utils = new RefMoreSelectedUtils(cardPanel);
    int[] rows = utils.refMoreSelected(editrow, e.getKey(), true);
    editrow = editrow + rows.length - 1;

    // --2.物料默认单位,并计算换算率
    SOUnitDefaultRule unitdef = new SOUnitDefaultRule(keyValue);
    unitdef.setDefaultSaleUnit(rows);
    SOUnitChangeRateRule unitrate = new SOUnitChangeRateRule(keyValue);
    //性能优化：批量处理 add by zhangby5
  	unitrate.calcAstAndQtChangeRate(rows);

    // 单位是否改变
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    boolean isunitchg =
        unitdef.isUnitChange(editrow, oldunitid, oldastunitid, oldqtunitid);
    if (isunitchg) {
      clearrule.clearBodyNumPirceMnyValue(editrow);
    }
    else {
      calculator.calculate(editrow, SaleOrderBVO.NNUM);
    }

    // --3.根据销售业务委托关系获得默认发货库存组织、直运仓
    // --4.发货库存组织变化后更新结算财务组织、应收组织、利润中心
    // --5.根据物流委托关系获得默认物流组织
    SaleOrgRelationRule orgrelarule = new SaleOrgRelationRule(keyValue);
    orgrelarule.setFinanceStockOrg(rows,
        this.GetRelationOrg(keyValue, rows));

    // --6.计算结算财务组织对应的币种
    SOCurrencyRule currencyrule = new SOCurrencyRule(keyValue);
    currencyrule.setCurrency(rows);
    // --7.根据组织本位币重新计算折本汇率
    SOExchangeRateRule changeraterule = new SOExchangeRateRule(keyValue);
    changeraterule.calcBodyExchangeRates(rows);
    // --8.集团本位币汇率
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
    groupraterule.calcGroupExchangeRate(rows);

    // --9.全局本位币汇率
    SOGlobalExchangeRate globalerate = new SOGlobalExchangeRate(keyValue);
    globalerate.calcGlobalExchangeRate(rows);
    calculator.calculate(rows, SaleOrderBVO.NEXCHANGERATE);

    // 设置默认收货客户------要在设置国家之前
    SOCustRelaDefValueRule custrefrule = new SOCustRelaDefValueRule(keyValue);
    custrefrule.setRelaReceiveCust(rows);
    
    // 设置默认收获地址
    ReceiveCustDefAddrRule defaddrule = new ReceiveCustDefAddrRule(keyValue);
    defaddrule.setCustDefaultAddress(rows);

    // 4.设置国家
    SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
    countryrule.setCountryInfo(rows);

    // 5.购销类型和三角贸易
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    int[] buychgrows = buyflgrule.getBuysellChgRow();
    calculator.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
    // 询税
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);
    int[] ratechgrows = taxInfo.getTaxChangeRows();
    calculator.calculate(ratechgrows, SaleOrderBVO.NTAXRATE);

    // 设置表体默认值
    BodyDefaultValueRule defrule = new BodyDefaultValueRule(keyValue);
    defrule.setBodyDefValue(rows);

    // --11.询价
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    SaleOrderClientContext cache = billform.getM30ClientContext();
    M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
    SaleOrderFindPriceConfig config =
        new SaleOrderFindPriceConfig(cardPanel, m30transvo);
    FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
    findPrice.findPriceAfterEdit(rows, SaleOrderBVO.CMATERIALVID);
    // --12.关联合同
    // 合同处理
    List<Integer> assRowList = new ArrayList<Integer>();
    List<Integer> calRowList = new ArrayList<Integer>();
    this.filterRows(billform, rows, assRowList, calRowList);
    if (assRowList.size() > 0) {
      // --关联合同
      Integer[] rowsTemp = assRowList.toArray(new Integer[assRowList.size()]);
      int[] assRows = new int[rowsTemp.length];
      for (int i = 0; i < rowsTemp.length; i++) {
        assRows[i] = rowsTemp[i].intValue();
      }
      AssociateConstractRule asctrule =
          new AssociateConstractRule(cardPanel, m30transvo);
      asctrule.associateCT(assRows);
    }
    if (calRowList.size() > 0) {
      // --没有关联合行则要用参照合同带过来的主数量进行单价金额计算
      Integer[] rowsTemp = calRowList.toArray(new Integer[calRowList.size()]);
      int[] calRows = new int[rowsTemp.length];
      for (int i = 0; i < rowsTemp.length; i++) {
        calRows[i] = rowsTemp[i].intValue();
      }
      calculator.calculate(calRows, SaleOrderBVO.NNUM);
    }
    // 13.匹配买赠、捆绑
    if (!isunitchg) {
      int[] matchrows = new int[] {
        editrow
      };
      MatchBindLargessRule matchrule = new MatchBindLargessRule(cardPanel, m30transvo);
      matchrule.matchBindLargess(matchrows);
    }
    // --13.计算合计
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

    // 编辑客户物料码设置生产厂商、供应商
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.setMaterials(rows);
    
    // 设置主仓库逻辑，直运业务不取主仓库 add by jilu for EHP1合到633 20140703
    if (!this.isDirecttype(keyValue, billform)) {
      // 执行发货库存组织编辑公式是为了根据发货库存组织带出主仓库
			this.execEditFormulas(cardPanel,
					new String[] { SaleOrderBVO.CSENDSTOCKORGID }, rows);
    }
    // --14.根据仓库所属库存组织或者发货库存组织查询物流委托关系获得默认物流组织
    orgrelarule.setTrafficOrg(rows);
    
    // 设置发货利润中心
 	this.setCsprofitcenterID(billform, keyValue, rows);

    this.freshQueryOnHandInfoPanel(cardPanel, billform);
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
				String cprofitcenterid = keyValue.getBodyStringValue(
						rows[index], SaleOrderBVO.CPROFITCENTERID);
				String cprofitcentervid = keyValue.getBodyStringValue(
						rows[index], SaleOrderBVO.CPROFITCENTERVID);
				keyValue.setBodyValue(rows[index],
						SaleOrderBVO.CSPROFITCENTERID, cprofitcenterid);
				keyValue.setBodyValue(rows[index],
						SaleOrderBVO.CSPROFITCENTERVID, cprofitcentervid);
			}
		} else {
			// 利润中心取值规则，非直运业务如此取值
			SOProfitCenterValueRule profitRule = new SOProfitCenterValueRule(
					keyValue);
			profitRule.setProfitCenterValue(SaleOrderBVO.CSPROFITCENTERVID,
					SaleOrderBVO.CSPROFITCENTERID, rows);
		}
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
		} else {
			cardPanel.getBillModel().execEditFormulas(-1);
		}
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

  private void cacheCtMap(SaleOrderBillForm billForm,
      Map<String, CtBusinessVO> mapCtInfo) {
    Map<String, CtBusinessVO> ctMap = billForm.getCtMap();
    if (ctMap == null) {
      billForm.setCtMap(mapCtInfo);
    }
    // 复制当前map信息到ctMap
    else {
      ctMap.putAll(mapCtInfo);
    }
  }

  /**
   * @assRowList 需要关联合同行
   * @calRowList 需要单独处理单价金额计算行
   */
  private void filterRows(SaleOrderBillForm billform, int[] rows,
      List<Integer> assRowList, List<Integer> calRowList) {
    BillCardPanel billCardPanel = billform.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(billCardPanel);
    Map<String, CtBusinessVO> ctMap = billform.getCtMap();
    int length = rows.length;
    String cctmanagebid = null;
    String vsrctype = null;
    for (int i = 0; i < length; i++) {
      vsrctype = keyValue.getBodyStringValue(rows[i], SaleOrderBVO.VSRCTYPE);
      cctmanagebid =
          keyValue.getBodyStringValue(rows[i], SaleOrderBVO.CCTMANAGEBID);
      // -- 来源合同的
      if (vsrctype != null && CTBillType.SaleDaily.getCode().equals(vsrctype)) {
        if (ctMap != null && ctMap.containsKey(cctmanagebid)) {
          CtBusinessVO busiVO = ctMap.get(cctmanagebid);
          // -- 来源合同行是物料控制允许重新关联合同
          if (busiVO != null
              && Ninvctlstyle.MATERIAL.value().equals(busiVO.getNinvctlstyle())) {
            assRowList.add(Integer.valueOf(rows[i]));
          }
          // -- 来源合同行是物料基本分类控制、不控制的：新物料都不允许关联合同，但要用主数量计算
          else {
            calRowList.add(Integer.valueOf(rows[i]));
          }
        }
        else {
          calRowList.add(Integer.valueOf(rows[i]));
        }
      }
      // -- 来源不是合同的：新物料关联合同
      else {
        assRowList.add(Integer.valueOf(rows[i]));
      }
    }
  }

  private void freshQueryOnHandInfoPanel(BillCardPanel cardPanel,
      SaleOrderBillForm billform) {

    OnhandPanelAdaptor adaptor = billform.getExtendedPanel();
    if (null == adaptor) {
      return;
    }

    if (!adaptor.isComponentDisplayable()) {
      return;
    }
    int row = cardPanel.getBillTable().getSelectedRow();
    if (row < 0) {
      adaptor.clearData();
      return;
    }
    adaptor.freshData(row);
  }

  private String getMaterialUnitID(String materialvid) {
    if (PubAppTool.isNull(materialvid)) {
      return null;
    }
    String[] pks = new String[] {
      materialvid
    };
    Map<String, String> mapmeas = MaterialPubService.queryMaterialMeasdoc(pks);
    return mapmeas.get(materialvid);
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
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    // 设置可用多选
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    BillItem item = cardPanel.getBodyItem(SOItemKey.CCUSTMATERIALID);
    UIRefPane uirefpane = (UIRefPane) item.getComponent();
    uirefpane.setMultiSelectedEnabled(true);
    // 按照客户过滤
    String customer = keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(CustMaterialVO.getDefaultTableName() + "."
        + CustMaterialVO.PK_CUSTOMER + " ='" + customer + "' or "
        + CustMaterialVO.getDefaultTableName() + "."
        + CustMaterialVO.PK_CUSTOMER + " in  (select " + CustomVO.MAINCUSTOM
        + " from " + CustomVO.getDefaultTableName() + " where "
        + CustomVO.SUBCUSTOM + " = '" + customer + "')");
    // 按照组织过滤
    String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String pk_group = keyValue.getHeadStringValue(SOItemKey.PK_GROUP);
    wheresql.append(" and ");
    wheresql.append(CustMaterialVO.getDefaultTableName() + "."
        + CustMaterialVO.PK_ORG, new String[] {
      pk_org, pk_group
    });
    uirefpane.setWhereString(wheresql.toString());
  }
}
