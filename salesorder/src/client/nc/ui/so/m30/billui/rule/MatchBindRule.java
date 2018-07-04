package nc.ui.so.m30.billui.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.self.ISaleOrderBusi;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.util.BillRowNoUtils;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.bd.material.sale.MaterialBindleVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.bind.OrderBindMatchPara;
import nc.vo.so.m30.bind.OrderBindMatchResult;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30.rule.DirectStoreRule;
import nc.vo.so.pub.SOItemKey;
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

/**
 * 匹配捆绑物料
 * 
 * @since 6.0
 * @version 2011-6-9 下午03:44:48
 * @author fengjb
 */

public class MatchBindRule {

  private static final int BIND_PRICETYPE = 0;

  private static final int FIND_PRICETYPE = 1;

  private IKeyValue keyValue;

  private BillCardPanel cardPanel;

  public MatchBindRule(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
    this.keyValue = new CardKeyValue(cardPanel);
  }

  public void matchBind(int[] rows) {
    // 1.返回需要捆绑的行
    int[] bindrows = this.getNeedBindRows(rows);
    if (bindrows.length == 0) {
      return;
    }
    // 2.删除原捆绑行,更新捆绑行最新位置
    bindrows = this.processOldBind(bindrows);
    // 3.组织数据后台查询
    OrderBindMatchPara[] bindparas = this.getBindMatchPara(bindrows);
    // 4.调用接口后台查询捆绑信息
    ISaleOrderBusi bussrv =
        NCLocator.getInstance().lookup(ISaleOrderBusi.class);
    OrderBindMatchResult[] matchresults = null;
    try {
      matchresults = bussrv.matchBind(bindparas);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    // 6.如果有匹配上的，给提示信息
    if (this.isCanMatch(matchresults)
        && UIDialog.ID_OK == MessageDialog
        .showOkCancelDlg(this.cardPanel, NCLangRes.getInstance()
            .getStrByID("4006011_0", "04006011-0010")/*提示*/, NCLangRes
            .getInstance().getStrByID("4006011_0", "04006011-0259")/*是否捆绑物料？*/)) {
      // 7.填充表体行ID
      FillBodyIDRule fbir = new FillBodyIDRule(this.keyValue);
      fbir.fillBodyId(bindrows);
      // 8.增加捆绑信息到界面
      this.addBindResultToUI(bindrows, matchresults);
    }
  }

  private boolean isCanMatch(OrderBindMatchResult[] matchresults) {
    boolean ismatch = false;
    if (null != matchresults) {
      for (OrderBindMatchResult result : matchresults) {
        if (null != result.getMatchBindleVOs()
            && result.getMatchBindleVOs().length > 0) {
          ismatch = true;
          break;
        }
      }
    }
    return ismatch;
  }

  private void addBindResultToUI(int[] bindrows,
      OrderBindMatchResult[] matchresults) {
    for (int i = 0; i < matchresults.length; i++) {
      MaterialBindleVO[] bindles = matchresults[i].getMatchBindleVOs();
      if (null == bindles || bindles.length == 0) {
        continue;
      }
      // 新插入行
      int[] insertrows = new int[bindles.length];
      // 价格获取方式
      int[] pricetype = new int[bindles.length];
      int j = 0;

      int currrow = bindrows[i];
      String csrcorderbid =
          this.keyValue.getBodyStringValue(currrow, SaleOrderBVO.CSALEORDERBID);
      UFDouble nnum =
          this.keyValue.getBodyUFDoubleValue(currrow, SaleOrderBVO.NNUM);
      for (MaterialBindleVO bindle : bindles) {
        int insertrow = currrow + 1 + j;
        SaleOrderBVO orderbvo =
            this.chgBindleToOrder(csrcorderbid, nnum, bindle);
        if (insertrow >= this.keyValue.getBodyCount()) {
          this.cardPanel.addLine();
          this.cardPanel.getBillModel().setBodyRowVO(orderbvo, insertrow);
          BillRowNoUtils.addLineRowNos(this.cardPanel, SaleOrderBVO.CROWNO, 1);
        }
        else {
          this.cardPanel.getBillModel().insertRow(insertrow);
          this.cardPanel.getBillModel().setBodyRowVO(orderbvo, insertrow);
          BillRowNoUtils.insertLineRowNos(this.cardPanel, SaleOrderBVO.CROWNO,
              insertrow + 1, 1);
        }

        insertrows[j] = insertrow;
        pricetype[j] = bindle.getPricetype().intValue();
        j++;
      }
      // 新增行界面数据处理
      this.updateUIData(insertrows, pricetype);
      
      //TODO 刘景 捆绑买赠在没有单位的情况下，就设置了数量，导致设置数量时候精度错误。不确定是uap问题，先暂时这么处理。
      //nc.ui.pub.bill.BillModel.setBillItemDecimalByRow(BillItem item, int row)
      for(int row:insertrows){
        this.keyValue.setBodyValue(row, SOItemKey.NNUM,
            this.keyValue.getBodyValue(row, SOItemKey.NNUM));
        this.keyValue.setBodyValue(row, SOItemKey.NQTUNITNUM,
            this.keyValue.getBodyValue(row, SOItemKey.NQTUNITNUM));
        this.keyValue.setBodyValue(row, SOItemKey.NASTNUM,
            this.keyValue.getBodyValue(row, SOItemKey.NASTNUM));
      }
      
      // 更新原始匹配行索引
      this.updateBindRow(bindrows, i + 1, insertrows.length);
    }
  }

  private SaleOrderBVO chgBindleToOrder(String csrcorderbid, UFDouble nnum,
      MaterialBindleVO bindle) {
    SaleOrderBVO orderbvo = new SaleOrderBVO();
    orderbvo.setCbindsrcid(csrcorderbid);
    orderbvo.setBbindflag(UFBoolean.TRUE);
    orderbvo.setCmaterialvid(bindle.getPk_bindle());
    UFDouble bindnum = new UFDouble(bindle.getBindlenum().intValue());
    orderbvo.setNnum(nnum.multiply(bindnum));
    // 和需求确认单价默认放在函数单价上
    orderbvo.setNorigtaxprice(bindle.getPrice());
    return orderbvo;
  }

  private void updateBindRow(int[] bindrows, int startindex, int insertlinesize) {
    for (int i = startindex; i < bindrows.length; i++) {
      bindrows[i] = bindrows[i] + insertlinesize;
    }
  }

  private void updateUIData(int[] insertrows, int[] pricetype) {

    CardPanelValueUtils util = new CardPanelValueUtils(this.cardPanel);
    for (int row : insertrows) {
      String marvid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
      util.setBodyValueForEdit(marvid, row, SaleOrderBVO.CMATERIALVID);
    }
    this.keyValue.getBodyStringValue(1, SaleOrderBVO.NITEMDISCOUNTRATE);
    // 设置行的默认值
    BodyDefaultValueRule defvaluerule = new BodyDefaultValueRule(this.keyValue);
    defvaluerule.setBodyDefValue(insertrows);
    // 设置默认收货客户
    SOCustRelaDefValueRule custrefrule =
        new SOCustRelaDefValueRule(this.keyValue);
    custrefrule.setRelaReceiveCust(insertrows);

    ReceiveCustDefAddrRule defaddrule =
        new ReceiveCustDefAddrRule(this.keyValue);
    defaddrule.setCustDefaultAddress(insertrows);
    // 默认业务单位、报检单位
    SOUnitDefaultRule unitrule = new SOUnitDefaultRule(this.keyValue);
    unitrule.setDefaultSaleUnit(insertrows);

    // 换算率
    SOUnitChangeRateRule changeraterule =
        new SOUnitChangeRateRule(this.keyValue);
    //性能优化：批量处理 add by zhangby5
    changeraterule.calcAstAndQtChangeRate(insertrows);
    // 数量计算
    SaleOrderCalculator calcultor = new SaleOrderCalculator(this.cardPanel);
    calcultor.calculateOnlyNum(insertrows, SaleOrderBVO.NNUM);

    // 关联发货库存组织
    SaleOrgRelationRule orgrelarule = new SaleOrgRelationRule(this.keyValue);
    orgrelarule.setSendStockOrg(insertrows);

    DirectStoreRule dirstorerule = new DirectStoreRule(this.keyValue);
    dirstorerule.setSendStordoc(insertrows);

    orgrelarule.setTrafficOrg(insertrows);
    // 关联财务、应收、利润中心
    orgrelarule.setFinanceOrg(insertrows);

    // 设置国家信息
    SOCountryInfoRule countryrule = new SOCountryInfoRule(this.keyValue);
    countryrule.setCountryInfo(insertrows);
    // 购销类型
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(this.keyValue);
    buyflgrule.setBuysellAndTriaFlag(insertrows);

    // 这里不需要计算 买赠捆绑下面会根据捆绑设置来计算
    // int[] buychgrows = buyflgrule.getBuysellChgRow();
    // calcultor.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
    // 询税
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(this.keyValue);
    taxInfo.setTaxInfoByBodyPos(insertrows);
    int[] taxchgrows = taxInfo.getTaxChangeRows();
    calcultor.calculate(taxchgrows, SaleOrderBVO.NTAXRATE);

    // 组织本位币
    SOCurrencyRule currule = new SOCurrencyRule(this.keyValue);
    currule.setCurrency(insertrows);

    SOExchangeRateRule exraterule = new SOExchangeRateRule(this.keyValue);
    exraterule.calcBodyExchangeRates(insertrows);
    // 7.全局本位币汇率
    SOGlobalExchangeRate globalraterule =
        new SOGlobalExchangeRate(this.keyValue);
    globalraterule.calcGlobalExchangeRate(insertrows);

    // 8.集团本位币汇率
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(this.keyValue);
    groupraterule.calcGroupExchangeRate(insertrows);

    int[] findpricerows = this.getFindPriceRow(insertrows, pricetype);
    if (findpricerows.length > 0) {
      SaleOrderFindPriceConfig config =
          new SaleOrderFindPriceConfig(this.cardPanel);
      FindSalePrice findprice = new FindSalePrice(this.cardPanel, config);
      findprice.forceFindPrice(findpricerows);
    }
    int[] bindpricerows = this.getBindPriceRow(insertrows, pricetype);
    if (bindpricerows.length > 0) {
      // 单价金额计算
      calcultor.calculate(bindpricerows, SaleOrderBVO.NORIGTAXPRICE);
    }
    // this.cardPanel.getBillModel().loadLoadRelationItemValue(insertrows);

    // 为新增行设置客户物料码
    SOCustMaterialInfoRule socustmar =
        new SOCustMaterialInfoRule(this.keyValue);
    socustmar.setCustMaterial(insertrows);
  }

  private int[] getBindPriceRow(int[] insertrows, int[] pricetype) {

    List<Integer> listbindprice = new ArrayList<Integer>();
    int i = 0;
    for (int row : insertrows) {
      if (MatchBindRule.BIND_PRICETYPE == pricetype[i]) {
        listbindprice.add(Integer.valueOf(row));
      }
      i++;
    }
    int[] bindpricerows = new int[listbindprice.size()];
    i = 0;
    for (Integer row : listbindprice) {
      bindpricerows[i] = row.intValue();
      i++;
    }

    return bindpricerows;

  }

  private int[] getFindPriceRow(int[] insertrows, int[] pricetype) {

    List<Integer> listfindprice = new ArrayList<Integer>();
    int i = 0;
    for (int row : insertrows) {
      if (MatchBindRule.FIND_PRICETYPE == pricetype[i]) {
        listfindprice.add(Integer.valueOf(row));
      }
      i++;
    }
    int[] findpricerows = new int[listfindprice.size()];
    i = 0;
    for (Integer row : listfindprice) {
      findpricerows[i] = row.intValue();
      i++;
    }

    return findpricerows;
  }

  private int[] processOldBind(int[] bindrows) {
    Set<String> setsrcbid = new HashSet<String>();
    for (int bindrow : bindrows) {
      String bid =
          this.keyValue.getBodyStringValue(bindrow, SaleOrderBVO.CSALEORDERBID);
      if (!PubAppTool.isNull(bid)) {
        setsrcbid.add(bid);
      }
    }
    this.deleteOldBindRows(setsrcbid);
    int j = 0;
    for (int i = 0; i < this.keyValue.getBodyCount(); i++) {
      String bid =
          this.keyValue.getBodyStringValue(i, SaleOrderBVO.CSALEORDERBID);
      if (setsrcbid.contains(bid)) {
        bindrows[j] = i;
        j++;
      }
    }
    return bindrows;
  }

  private OrderBindMatchPara[] getBindMatchPara(int[] bindrows) {

    OrderBindMatchPara[] matchparas = new OrderBindMatchPara[bindrows.length];
    int i = 0;
    String csaleorgid = this.keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    UFDate dbilldate = this.keyValue.getHeadUFDateValue(SaleOrderHVO.DBILLDATE);
    for (int row : bindrows) {
      String cmarterialvid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
      UFDouble nnum =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);
      matchparas[i] =
          new OrderBindMatchPara(csaleorgid, cmarterialvid, nnum, dbilldate);
      i++;
    }
    return matchparas;
  }

  private void deleteOldBindRows(Set<String> setsrcbid) {
    List<Integer> listdelete = new ArrayList<Integer>();
    for (int i = 0; i < this.keyValue.getBodyCount(); i++) {
      String bindsrcid =
          this.keyValue.getBodyStringValue(i, SaleOrderBVO.CBINDSRCID);
      if (!PubAppTool.isNull(bindsrcid) && setsrcbid.contains(bindsrcid)) {
        listdelete.add(Integer.valueOf(i));
      }
    }
    if (listdelete.size() > 0) {
      int[] delrows = new int[listdelete.size()];
      int i = 0;
      for (Integer row : listdelete) {
        delrows[i] = row.intValue();
        i++;
      }
      this.cardPanel.getBillModel().delLine(delrows);
    }
  }

  private int[] getNeedBindRows(int[] rows) {

    int[] bindrows = null;
    List<Integer> listneedrow = new ArrayList<Integer>();
    UFDate dbilldate = this.keyValue.getHeadUFDateValue(SaleOrderHVO.DBILLDATE);
    // 日期为空都不用匹配
    if (null == dbilldate) {
      bindrows = new int[0];
      return bindrows;
    }
    for (int row : rows) {
      // 赠品行不捆绑
      UFBoolean largess =
          this.keyValue.getBodyUFBooleanValue(row, SaleOrderBVO.BLARGESSFLAG);
      if (null != largess && largess.booleanValue()) {
        continue;
      }
      // 物料为空行不捆绑
      String cmarterialvid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
      if (PubAppTool.isNull(cmarterialvid)) {
        continue;
      }
      // 数量为空或者红字不捆绑----------2012.04.13 把数量改成负的或者0 需要删除原捆绑行（么贵敬）
      // UFDouble nnum =
      // this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);
      // if (null == nnum || nnum.compareTo(UFDouble.ZERO_DBL) <= 0) {
      // continue;
      // }
      // 捆绑相关不匹配
      UFBoolean bindflag =
          this.keyValue.getBodyUFBooleanValue(row, SaleOrderBVO.BBINDFLAG);
      if (null != bindflag && bindflag.booleanValue()) {
        continue;
      }
      // 退换货行不捆绑
      Integer retexchange =
          this.keyValue.getBodyIntegerValue(row, SaleOrderBVO.FRETEXCHANGE);
      if (Fretexchange.EXCHANGE.equalsValue(retexchange)
          || Fretexchange.WITHDRAW.equalsValue(retexchange)) {
        continue;
      }
      UFDouble  nnum= this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);
      UFDouble  nastnum= this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NASTNUM);
      if(MathTool.isZero(nnum)||MathTool.isZero(nastnum)){
        continue;
      }
      listneedrow.add(Integer.valueOf(row));
    }
    bindrows = new int[listneedrow.size()];
    int i = 0;
    for (Integer needrow : listneedrow) {
      bindrows[i] = needrow.intValue();
      i++;
    }
    return bindrows;
  }
}
