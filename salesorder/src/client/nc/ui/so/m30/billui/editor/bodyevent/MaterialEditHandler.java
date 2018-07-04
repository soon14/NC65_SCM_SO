package nc.ui.so.m30.billui.editor.bodyevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.so.m30.IQueryRelationOrg;
import nc.itf.so.m30.ref.ct.mz3.CTmZ3ServicesUtil;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.bd.ref.AbstractRefTreeModel;
import nc.ui.bd.ref.model.MaterialMultiVersionRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.scmf.ic.onhand.OnhandPanelAdaptor;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
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
import nc.ui.so.pub.rule.AccountPirceRule;
import nc.vo.ct.business.enumeration.Ninvctlstyle;
import nc.vo.ct.entity.CtBusinessVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.scmpub.res.billtype.ICBillType;
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

/**
 * 
 * @since 6.1
 * @version 2012-12-20 15:54:57
 * @author liangjm
 */
public class MaterialEditHandler {

  /**
   * 
   * 
   * @param e
   * @param billform
   */
  public void afterEdit(CardBodyAfterEditEvent e, SaleOrderBillForm billform) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    CardPanelValueUtils cardutils = new CardPanelValueUtils(cardPanel);
    int editrow = e.getRow();
    String vsrctype =
        keyValue.getBodyStringValue(editrow, SaleOrderBVO.VSRCTYPE);
    // 删除已绑定的买赠促销类型
    keyValue.setBodyValue(editrow, SaleOrderBVO.CBUYPROMOTTYPEID, null);
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
    boolean isLock = cardPanel.getBodyItem("blargessflag").isLock();
    if (!isLock) {
      keyValue.setBodyValue(editrow, SaleOrderBVO.BLARGESSFLAG, null);
    }
    // --参照多选处理
    RefMoreSelectedUtils utils = new RefMoreSelectedUtils(cardPanel);
    int[] rows = utils.refMoreSelected(editrow, e.getKey(), true);
    editrow = editrow + rows.length - 1;

    // --2.物料默认单位,并计算换算率
    SOUnitDefaultRule unitdef = new SOUnitDefaultRule(keyValue);
    unitdef.setDefaultSaleUnit(rows);

    // 执行附单位或者主单位的编辑公式(通过公司适配报价单位取值方式，默认取附单位)
    this.execEditFormulas(cardPanel, new String[] {
      SaleOrderBVO.CUNITID, SaleOrderBVO.CASTUNITID
    }, rows);
    /*
     * 性能优化 ： 物料多选已经执行了公式，此处不执行 add by zhangby5 // -执行物料编辑公式
     * this.execEditFormulas(cardPanel, SaleOrderBVO.CMATERIALVID, rows);
     */
    SOUnitChangeRateRule unitrate = new SOUnitChangeRateRule(keyValue);
    // 性能优化：批量处理 add by zhangby5
    unitrate.calcAstAndQtChangeRate(rows);
    
    
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    SaleOrderClientContext cache = billform.getM30ClientContext();
    M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
    
    // 单位是否改变
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    //刘景 效率优化，单价金额算法交易类型可以传递进去
    calculator.setTranTypeVO(m30transvo);
    boolean isunitchg =
        unitdef.isUnitChange(editrow, oldunitid, oldastunitid, oldqtunitid);
    if (isunitchg) {
      clearrule.clearBodyNumPirceMnyValue(editrow);
    }
    else {
      calculator.calculate(editrow, SaleOrderBVO.NNUM);
    }
    // 来源于分类销售合同时需要重算下数量，与刘达确定后，定于主数量换算其他数量(单据上只有物料分类时，没有单位，编辑数量无法联动计算，会造成数据错误)
    calRefClassCTNum(rows, keyValue, ctMap, calculator);

    // --3.根据销售业务委托关系获得默认发货库存组织、直运仓
    // --4.发货库存组织变化后更新结算财务组织、应收组织、利润中心
    SaleOrgRelationRule orgrelarule = new SaleOrgRelationRule(keyValue);
    orgrelarule.setFinanceStockOrg(rows, this.GetRelationOrg(keyValue, rows));

    // 设置主仓库逻辑，直运业务不取主仓库
    if (!this.isDirecttype(keyValue, billform)) {
      // 执行发货库存组织编辑公式是为了根据发货库存组织带出主仓库
      this.execEditFormulas(cardPanel, new String[] {
        SaleOrderBVO.CSENDSTOCKORGID
      }, rows);
    }
    // --5.根据仓库所属库存组织或者发货库存组织查询物流委托关系获得默认物流组织
    orgrelarule.setTrafficOrg(rows);

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

    // --10.设置默认收货客户------要在设置国家之前
    String headreceivecustid =
        keyValue.getHeadStringValue(SaleOrderHVO.CHRECEIVECUSTID);
    if (!PubAppTool.isNull(headreceivecustid)) {
      setReceCustAndAdr(keyValue, rows, SaleOrderBVO.CRECEIVECUSTID,
          headreceivecustid);
    }
    else {
      SOCustRelaDefValueRule custrefrule = new SOCustRelaDefValueRule(keyValue);
      custrefrule.setRelaReceiveCust(rows);
    }

    // --设置客户收货地址
    String headreceiveaddid =
        keyValue.getHeadStringValue(SaleOrderHVO.CHRECEIVEADDID);
    ReceiveCustDefAddrRule defaddrule = new ReceiveCustDefAddrRule(keyValue);
    if (!PubAppTool.isNull(headreceiveaddid)) {
      defaddrule.setCustAddDocByAddr(rows);
    }
    else {
      defaddrule.setCustDefaultAddress(rows);
    }

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
    // 传入的boolean值表示不需要执行
    defrule.setBodyDefValue(rows, true);

    // --11.询价
    // jilu for 633 来源合同的订单物料分类不询价不计算
    // 判断来源的合同订单交易类型是否是物料分类，是的话不询价不计算
    boolean isFindPrice = false;
    String cctmanagebid =
        keyValue.getBodyStringValue(editrow, SaleOrderBVO.CCTMANAGEBID);
    if (CTBillType.SaleDaily.getCode().equals(vsrctype) && null != ctMap
        && ctMap.containsKey(cctmanagebid)) {

      CtBusinessVO busiVO = ctMap.get(cctmanagebid);
      if (null != busiVO) {
        boolean isMarbasclass =
            Ninvctlstyle.MARBASCLASS.value().equals(busiVO.getNinvctlstyle());
        if (isMarbasclass) {
          isFindPrice = true;
        }
      }
    }
    // end
  
    SaleOrderFindPriceConfig config =
        new SaleOrderFindPriceConfig(cardPanel, m30transvo);
    FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
    // jilu for 633 来源合同的订单物料分类不询价不计算
    if (!isFindPrice) {
      findPrice.findPriceAfterEdit(rows, SaleOrderBVO.CMATERIALVID);
    }
    // end

    // 询主记账单价
    AccountPirceRule rule =
        new AccountPirceRule(cardPanel, m30transvo.getNaccpricerule(), config);
    rule.setLargessPrice(rows);
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
    // jilu for 633 来源合同的订单物料分类不询价不计算
    // if (calRowList.size() > 0) {
    if (calRowList.size() > 0 && !isFindPrice) {
      // --没有关联合行则要用参照合同带过来的主数量进行单价金额计算
      Integer[] rowsTemp = calRowList.toArray(new Integer[calRowList.size()]);
      int[] calRows = new int[rowsTemp.length];
      for (int i = 0; i < rowsTemp.length; i++) {
        calRows[i] = rowsTemp[i].intValue();
      }
      calculator.calculate(calRows, SaleOrderBVO.NNUM);
    }
    // 13.匹配买赠、捆绑(2013.11.25 赠品兑付的销售订单，不匹配买赠设置)
    boolean isblrgcash = m30transvo.getBlrgcashflag().booleanValue();
    if (!isunitchg && !isblrgcash) {
      int[] matchrows = new int[] {
        editrow
      };
      MatchBindLargessRule matchrule =
          new MatchBindLargessRule(cardPanel, m30transvo);
      matchrule.matchBindLargess(matchrows);
    }
    // 编辑物料后，设置客户物料码(V63新加)
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.setCustMaterial(rows);

    this.freshQueryOnHandInfoPanel(cardPanel, billform);

    // 设置主仓库逻辑，直运业务不取主仓库
    if (!this.isDirecttype(keyValue, billform)) {
      // 执行发货库存组织编辑公式是为了根据发货库存组织带出主仓库
      this.execEditFormulas(cardPanel, new String[] {
        SaleOrderBVO.CSENDSTOCKORGID
      }, rows);
    }
    
    // 利润中心取值规则，直运非直运业务均如此取值
    SOProfitCenterValueRule profitRule =
        new SOProfitCenterValueRule(keyValue);
    profitRule.setProfitCenterValue(SaleOrderBVO.CSPROFITCENTERVID,
        SaleOrderBVO.CSPROFITCENTERID, rows);
    // --13.计算合计
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();
  }

  private void setReceCustAndAdr(IKeyValue keyValue, int[] rows, String key,
      String headreceiveaddid) {
    for (int row : rows) {
      keyValue.setBodyValue(row, key, headreceiveaddid);
    }
  }

  private void calRefClassCTNum(int[] rows, IKeyValue keyValue,
      Map<String, CtBusinessVO> ctMap, SaleOrderCalculator calculator) {
    List<Integer> rowlist = new ArrayList<Integer>();
    for (int row : rows) {
      String vsrctype = keyValue.getBodyStringValue(row, SaleOrderBVO.VSRCTYPE);
      String cctmanagebid =
          keyValue.getBodyStringValue(row, SaleOrderBVO.CCTMANAGEBID);
      if (CTBillType.SaleDaily.getCode().equals(vsrctype) && null != ctMap
          && ctMap.containsKey(cctmanagebid)) {

        CtBusinessVO busiVO = ctMap.get(cctmanagebid);
        if (null != busiVO) {
          boolean isMarbasclass =
              Ninvctlstyle.MARBASCLASS.value().equals(busiVO.getNinvctlstyle());
          boolean isWithOut =
              Ninvctlstyle.WITHOUT.value().equals(busiVO.getNinvctlstyle());
          if (isMarbasclass || isWithOut) {
            rowlist.add(row);
          }
        }
      }
    }

    int size = rowlist.size();
    int[] index = new int[size];
    for (int i = 0; i < size; i++) {
      index[i] = rowlist.get(i).intValue();
    }
    if (size == 0) {
      return;
    }
    calculator.calculate(index, SaleOrderBVO.NNUM);
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
   * 
   * 
   * @param e
   * @param billform
   */
  public void beforeEdit(CardBodyBeforeEditEvent e, SaleOrderBillForm billform) {

    int row = e.getRow();
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 物料分类编码
    String marbasclass = null;
    // 赠品行
    String srclarid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CLARGESSSRCID);
    if (!PubAppTool.isNull(srclarid)) {
      e.setReturnValue(false);
      return;
    }

    String vsrctype = keyValue.getBodyStringValue(row, SaleOrderBVO.VSRCTYPE);
    if (ICBillType.BorrowOut.getCode().equals(vsrctype)) {
      e.setReturnValue(false);
      return;
    }
    if (CTBillType.SaleDaily.getCode().equals(vsrctype)) {
      String cctmanagebid =
          keyValue.getBodyStringValue(row, SaleOrderBVO.CCTMANAGEBID);

      // 表体来源合同bid
      CtBusinessVO ctvo = this.queryCtBusinessByPks(billform, cctmanagebid);
      if (null != ctvo
          && Ninvctlstyle.MARBASCLASS.equalsValue(ctvo.getNinvctlstyle())) {
        // 物料分类编码
        marbasclass = ctvo.getPk_marbasclass();
      }
    }
    UIRefPane ufPanel =
        (UIRefPane) cardPanel.getBodyItem(SaleOrderBVO.CMATERIALVID)
            .getComponent();
    ((AbstractRefTreeModel) ufPanel.getRefModel()).setClassWherePart("");
    FilterMaterialRefUtils filter = new FilterMaterialRefUtils(ufPanel);
    if (!PubAppTool.isNull(marbasclass)) {
      filter.filterRefByMarBasClass(marbasclass);
    }
    AbstractRefModel model = ufPanel.getRefModel();
    if (model instanceof MaterialMultiVersionRefModel) {
      ufPanel.setMultiSelectedEnabled(true);
    }
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
    boolean fromCtClass = false;
    int classRow = -1;
    for (int i = 0; i < length; i++) {
      vsrctype = keyValue.getBodyStringValue(rows[i], SaleOrderBVO.VSRCTYPE);
      cctmanagebid =
          keyValue.getBodyStringValue(rows[i], SaleOrderBVO.CCTMANAGEBID);
      // -- 来源合同的
      if (vsrctype != null && CTBillType.SaleDaily.getCode().equals(vsrctype)) {
        if (ctMap != null && ctMap.containsKey(cctmanagebid)) {
          CtBusinessVO busiVO = ctMap.get(cctmanagebid);
          if (busiVO != null
              && Ninvctlstyle.MARBASCLASS.value().equals(
                  busiVO.getNinvctlstyle())) {
            fromCtClass = true;
            classRow = rows[i];
          }
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
        if (fromCtClass && classRow > -1) {
          this.copyRowData(classRow, rows[i], keyValue);
        }
        else {
          assRowList.add(Integer.valueOf(rows[i]));
        }
      }
    }
  }

  /**
   * 数据copy
   * 
   * @param fromRow
   * @param toRow
   * @param keyValue
   */
  private void copyRowData(int fromRow, int toRow, CardKeyValue keyValue) {
    String[] copyKeys =
        new String[] {
          SaleOrderBVO.CCTMANAGEBID, SaleOrderBVO.CCTMANAGEID,
          SaleOrderBVO.VCTCODE, SaleOrderBVO.VSRCTYPE, SaleOrderBVO.VSRCTYPE,
          SaleOrderBVO.VSRCTRANTYPE, SaleOrderBVO.VSRCCODE,
          SaleOrderBVO.VSRCROWNO, SaleOrderBVO.CSRCID, SaleOrderBVO.CSRCBID,
          SaleOrderBVO.VFIRSTTYPE, SaleOrderBVO.VFIRSTTRANTYPE,
          SaleOrderBVO.VFIRSTCODE, SaleOrderBVO.CFIRSTID,
          SaleOrderBVO.CFIRSTBID, SaleOrderBVO.VFIRSTROWNO,
          SaleOrderBVO.CPROJECTID, SaleOrderBVO.VBDEF1, SaleOrderBVO.VBDEF2,
          SaleOrderBVO.VBDEF3, SaleOrderBVO.VBDEF4,
          SaleOrderBVO.VBDEF5,
          SaleOrderBVO.VBDEF6,
          SaleOrderBVO.VBDEF7,
          SaleOrderBVO.VBDEF8,
          SaleOrderBVO.VBDEF9,
          SaleOrderBVO.VBDEF10,
          SaleOrderBVO.VBDEF11,
          SaleOrderBVO.VBDEF12,
          SaleOrderBVO.VBDEF13,
          SaleOrderBVO.VBDEF14,
          SaleOrderBVO.VBDEF15,
          SaleOrderBVO.VBDEF16,
          SaleOrderBVO.VBDEF17,
          SaleOrderBVO.VBDEF18,
          SaleOrderBVO.VBDEF19,
          SaleOrderBVO.VBDEF20,

          // 数量
          SaleOrderBVO.NNUM,
          SaleOrderBVO.NASTNUM,
          SaleOrderBVO.NQTUNITNUM,
          // 原币单价
          SaleOrderBVO.NQTORIGTAXPRICE,
          SaleOrderBVO.NQTORIGPRICE,
          SaleOrderBVO.NQTORIGTAXNETPRC,
          SaleOrderBVO.NQTORIGNETPRICE,
          // 主原币单价
          SaleOrderBVO.NORIGPRICE,
          SaleOrderBVO.NORIGTAXPRICE,
          SaleOrderBVO.NORIGNETPRICE,
          SaleOrderBVO.NORIGTAXNETPRICE,
          // 金额
          SaleOrderBVO.NORIGMNY,
          SaleOrderBVO.NORIGTAXMNY,
          SaleOrderBVO.NORIGDISCOUNT,
          SaleOrderBVO.NCALTAXMNY,
          // 本币单价
          SaleOrderBVO.NQTTAXNETPRICE, SaleOrderBVO.NQTNETPRICE,
          SaleOrderBVO.NQTTAXPRICE,
          SaleOrderBVO.NQTPRICE,
          // 主本币单价
          SaleOrderBVO.NPRICE, SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NNETPRICE,
          SaleOrderBVO.NTAXNETPRICE,
          // 本币金额
          SaleOrderBVO.NTAX, SaleOrderBVO.NMNY, SaleOrderBVO.NTAXMNY,
          SaleOrderBVO.NDISCOUNT,
          // 集团金额
          SaleOrderBVO.NGROUPMNY, SaleOrderBVO.NGROUPTAXMNY,
          // 全局金额
          SaleOrderBVO.NGLOBALMNY, SaleOrderBVO.NGLOBALTAXMNY

        };
    for (String key : copyKeys) {
      keyValue.setBodyValue(toRow, key, keyValue.getBodyValue(fromRow, key));
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

  private CtBusinessVO queryCtBusinessByPks(SaleOrderBillForm billform,
      String cctmanagebid) {
    try {
      Map<String, CtBusinessVO> mapCtInfo =
          CTmZ3ServicesUtil.queryCtBusinessByPks(new String[] {
            cctmanagebid
          });
      if (null == mapCtInfo || mapCtInfo.size() == 0) {
        return null;
      }
      // 填充合同缓存信息
      this.cacheCtMap(billform, mapCtInfo);
      return mapCtInfo.get(cctmanagebid);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
