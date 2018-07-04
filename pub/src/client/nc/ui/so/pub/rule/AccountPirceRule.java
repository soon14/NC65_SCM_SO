package nc.ui.so.pub.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialSalePubService;
import nc.itf.scmpub.reference.uap.org.CostRegionPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.AccPriceGetqtRule;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 主记账单据取价规则
 * 
 * @since 6.3
 * @version 2014-06-27 09:34:58
 * @author 刘景
 */
public class AccountPirceRule {

  private BillCardPanel cardPanel;

  private IKeyValue keyValue;

  private Integer fetchmode;

  private SaleOrderFindPriceConfig config;

  /**
   * 构造方法
   * 
   * @param cardPanel 卡片面板
   * @param fetchmode 主记账单价询价规则
   * @param config 销售订单询价配 置类
   */
  public AccountPirceRule(BillCardPanel cardPanel, Integer fetchmode,
      SaleOrderFindPriceConfig config) {
    this.cardPanel = cardPanel;
    this.keyValue = new CardKeyValue(cardPanel);
    this.fetchmode = fetchmode;
    this.config = config;
  }

  /**
   * 设置主记账单价
   * 
   * @param rows
   */
  public void setLargessPrice(int[] rows) {
    // 价格0
    if (AccPriceGetqtRule.ZERO_QT.equalsValue(this.fetchmode)) {
      this.setZeroPrice(rows);

    }
    // 参考售价
    else if (AccPriceGetqtRule.MARSSORG_REQT.equalsValue(this.fetchmode)) {
      this.setResalePrice(rows);

    }
    // 最低售价
    else if (AccPriceGetqtRule.MARSSORG_LOWQT.equalsValue(this.fetchmode)) {
      this.setMinPrice(rows);

    }
    // 参考成本
    else if (AccPriceGetqtRule.MARSSORG_COSETQT.equalsValue(this.fetchmode)) {
      this.setCostPrice(rows);

    }
    // 正常询价
    else if (AccPriceGetqtRule.ASK_SALEQT.equalsValue(this.fetchmode)) {
      this.setFindPrice(rows);
    }
  }

  private void setFindPrice(int[] rows) {
    FindSalePrice findprice = new FindSalePrice(this.cardPanel, this.config);
    findprice.forceFindAccPrice(rows, SOItemKey.CMATERIALVID);
  }

  private void setCostPrice(int[] rows) {
    Map<String, String> mapcostorg = this.getCostOrg(rows);
    for (int row : rows) {
      String maroid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      if (PubAppTool.isNull(maroid)) {
        continue;
      }
      String sendstock =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTOCKORGID);
      if (PubAppTool.isNull(sendstock)) {
        continue;
      }
      String store =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTORDOCID);
      String key = sendstock + store;
      String costorg = mapcostorg.get(key);
      String[] pk_materials = new String[] {
        maroid
      };
      Map<String, UFDouble> mapprice =
          MaterialPubService.queryCostPriceByPks(pk_materials, costorg);
      this.keyValue
          .setBodyValue(row, SOItemKey.NACCPRICE, mapprice.get(maroid));

    }
  }

  private Map<String, String> getCostOrg(int[] rows) {
    Map<String, String> mapCostOrg = null;
    List<String> alsendstock = new ArrayList<String>();
    List<String> alstore = new ArrayList<String>();
    for (int row : rows) {
      String maroid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      if (PubAppTool.isNull(maroid)) {
        continue;
      }
      String sendstock =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTOCKORGID);
      if (PubAppTool.isNull(sendstock)) {
        continue;
      }
      alsendstock.add(sendstock);
      String store =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTORDOCID);
      alstore.add(store);
    }
    if (alsendstock.size() == 0) {
      return mapCostOrg;
    }
    String[] stockorgids = new String[alsendstock.size()];
    alsendstock.toArray(stockorgids);

    String[] stordocids = new String[alstore.size()];
    alstore.toArray(stordocids);

    mapCostOrg =
        CostRegionPubService.queryCostRegionIDByStockOrgsAndStordocs(
            stockorgids, stordocids);
    return mapCostOrg;
  }

  private void setMinPrice(int[] rows) {

    String[] marvids = this.getMaterialVIDs(rows);
    if (marvids.length == 0) {
      return;
    }
    String saleorg = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    Map<String, UFDouble> mapPrice =
        MaterialSalePubService.queryMinprice(marvids, saleorg);
    for (int row : rows) {
      String marvid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
      if (PubAppTool.isNull(marvid)) {
        continue;
      }
      UFDouble price = mapPrice.get(marvid);
      this.keyValue.setBodyValue(row, SOItemKey.NACCPRICE, price);
    }

  }

  private void setResalePrice(int[] rows) {
    String[] marvids = this.getMaterialVIDs(rows);
    if (marvids.length == 0) {
      return;
    }
    String saleorg = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    Map<String, UFDouble> mapPrice =
        MaterialSalePubService.queryResaleprice(marvids, saleorg);
    for (int row : rows) {
      String marvid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
      if (PubAppTool.isNull(marvid)) {
        continue;
      }
      UFDouble price = mapPrice.get(marvid);
      this.keyValue.setBodyValue(row, SOItemKey.NACCPRICE, price);
    }
  }

  private String[] getMaterialVIDs(int[] rows) {

    Set<String> setmarvid = new HashSet<String>();
    for (int row : rows) {
      String marvid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
      if (PubAppTool.isNull(marvid)) {
        continue;
      }
      setmarvid.add(marvid);
    }
    String[] marvids = new String[setmarvid.size()];
    setmarvid.toArray(marvids);
    return marvids;
  }

  /**
   * 设置赠品行价格为0
   * 
   * @param rows
   */
  private void setZeroPrice(int[] rows) {
    for (int row : rows) {
      this.keyValue.setBodyValue(row, SOItemKey.NACCPRICE, UFDouble.ZERO_DBL);
    }
  }
}
