package nc.vo.so.pub.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.addrdoc.AddrdocPubService;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.bd.vat.TradeWordService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.org.OrgVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 销售管理预订单、销售订单设置国家信息的规则
 * 
 * @since 6.0
 * @version 2012-2-7 上午08:55:38
 * @author 刘景
 */
public class SOCountryInfoRule {

  private IKeyValue keyValue;

  public SOCountryInfoRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 设置销售预订单、订单的 发货国、收货国、报税国信息
   * 
   * @param rows
   */
  public void setCountryInfo(int[] rows) {
    // 收货国家单独设置
    this.setReceiveCountry(rows);
    // 发货国家、报税国家逻辑相似，为减少连接次数，合并查询
    String[] orgkeys = new String[] {
      SOItemKey.CSENDSTOCKORGID, SOItemKey.CSETTLEORGID
    };
    Map<String, OrgVO> mapcountcorp = this.getOrgCountCorpMap(rows, orgkeys);
    String saleorg = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    for (int row : rows) {
      this.setSendCountryValue(row, saleorg, mapcountcorp);
      this.setTaxCountryValue(row, saleorg, mapcountcorp);
    }
  }

  /**
   * 根据主组织所在国家，设置收货国、发货国、报税国信息，供报价单使用
   * 
   * @param rows
   */
  public void setCountryInfoByPk_Org(int[] rows) {

    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String[] keynames = new String[] {
      OrgVO.COUNTRYZONE
    };
    OrgVO[] orgvos = OrgUnitPubService.getOrgsByPks(new String[] {
      pk_org
    }, keynames);

    String orgcountry = orgvos[0].getCountryzone();
    for (int row : rows) {
      this.keyValue.setBodyValue(row, SOItemKey.CSENDCOUNTRYID, orgcountry);
      this.keyValue.setBodyValue(row, SOItemKey.CRECECOUNTRYID, orgcountry);
      this.keyValue.setBodyValue(row, SOItemKey.CTAXCOUNTRYID, orgcountry);
    }

  }

  /**
   * 设置销售预订单、订单的收货国家/地区
   * 
   * @param rows
   */
  public void setReceiveCountry(int[] rows) {
    boolean istradefactory = this.isTradeWordFactory();
    Map<String, String> custcountrymap =
        this.getCustCountryMap(rows, istradefactory);
    Map<String, String> addrcountrymap =
        this.getAddrCountryMap(rows, istradefactory);

    String custid = this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    for (int row : rows) {
      String receivecountry = null;
      if (istradefactory) {
        receivecountry = custcountrymap.get(custid);
      }
      else {
        String addr =
            this.keyValue.getBodyStringValue(row, SOItemKey.CRECEIVEADDRID);
        if (!PubAppTool.isNull(addr)) {
          receivecountry = addrcountrymap.get(addr);
        }
        /*** 冯加彬、陈恩宇 通版问题：客户地址上不录入国家，找不到收货国 begin ***/
        if (PubAppTool.isNull(receivecountry)) {
          /*** 冯加彬、陈恩宇 通版问题：客户地址上不录入国家，找不到收货国 end ***/
          String receivecust =
              this.keyValue.getBodyStringValue(row, SOItemKey.CRECEIVECUSTID);
          receivecountry = custcountrymap.get(receivecust);
        }
      }
      this.keyValue.setBodyValue(row, SOItemKey.CRECECOUNTRYID, receivecountry);
    }

  }

  /**
   * 设置销售预订单、订单的发货国家/地区
   * 
   * @param rows
   */
  public void setSendCountry(int[] rows) {

    String[] orgkeys = new String[] {
      SOItemKey.CSENDSTOCKORGID
    };
    Map<String, OrgVO> mapcountcorp = this.getOrgCountCorpMap(rows, orgkeys);
    String saleorg = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    for (int row : rows) {
      this.setSendCountryValue(row, saleorg, mapcountcorp);

    }
  }

  /**
   * 设置销售预订单、订单的报税国家/地区
   * 
   * @param rows
   */
  public void setTaxCountry(int[] rows) {

    String[] orgkeys = new String[] {
      SOItemKey.CSENDSTOCKORGID, SOItemKey.CSETTLEORGID
    };
    Map<String, OrgVO> mapcountcorp = this.getOrgCountCorpMap(rows, orgkeys);
    String saleorg = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    for (int row : rows) {
      this.setTaxCountryValue(row, saleorg, mapcountcorp);
    }
  }

  private Map<String, String> getAddrCountryMap(int[] rows,
      boolean istradefactory) {

    Set<String> setaddres = new HashSet<String>();

    for (int row : rows) {
      if (istradefactory) {
        continue;
      }

      String addr =
          this.keyValue.getBodyStringValue(row, SOItemKey.CRECEIVEADDRID);
      if (!PubAppTool.isNull(addr)) {
        setaddres.add(addr);
      }

    }

    if (setaddres.size() > 0) {
      String[] adddocids = setaddres.toArray(new String[setaddres.size()]);
      return AddrdocPubService.queryCountryByAddrdoc(adddocids);
    }

    return new HashMap<String, String>();
  }

  private String getCorp(Map<String, OrgVO> mapcountcorp, String key) {
    if (mapcountcorp.containsKey(key)) {
      return mapcountcorp.get(key).getPk_corp();
    }
    return null;
  }

  private String getCountry(Map<String, OrgVO> mapcountcorp, String key) {
    if (mapcountcorp.containsKey(key)) {
      return mapcountcorp.get(key).getCountryzone();
    }
    return null;
  }

  private Map<String, String> getCustCountryMap(int[] rows,
      boolean istradefactory) {

    Set<String> setcustid = new HashSet<String>();
    String custid = this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    for (int row : rows) {
      if (istradefactory) {
        setcustid.add(custid);
      }
      else {
        String addr =
            this.keyValue.getBodyStringValue(row, SOItemKey.CRECEIVEADDRID);
        if (PubAppTool.isNull(addr)) {
          String receivecust =
              this.keyValue.getBodyStringValue(row, SOItemKey.CRECEIVECUSTID);
          setcustid.add(receivecust);
        }
      }
    }

    if (setcustid.size() > 0) {
      String[] custids = setcustid.toArray(new String[setcustid.size()]);
      return CustomerPubService.queryCountryByCustomer(custids);
    }
    return new HashMap<String, String>();
  }

  private Map<String, OrgVO> getOrgCountCorpMap(int[] rows, String[] bodyorgkeys) {

    Map<String, OrgVO> orgCntyCrpMap = new HashMap<String, OrgVO>();

    Set<String> setorg = new HashSet<String>();
    String saleorg = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    setorg.add(saleorg);
    for (int row : rows) {
      for (String orgkey : bodyorgkeys) {
        String orgid = this.keyValue.getBodyStringValue(row, orgkey);
        if (!PubAppTool.isNull(orgid)) {
          setorg.add(orgid);
        }
      }
    }

    String[] orgids = setorg.toArray(new String[setorg.size()]);
    String[] keynames = new String[] {
      OrgVO.COUNTRYZONE, OrgVO.PK_CORP
    };
    OrgVO[] orgvos = OrgUnitPubService.getOrgsByPks(orgids, keynames);
    for (OrgVO orgvo : orgvos) {
      if (null == orgvo) {
        continue;
      }
      orgCntyCrpMap.put(orgvo.getPk_org(), orgvo);
    }
    return orgCntyCrpMap;
  }

  private boolean isTradeWordFactory() {
    String tradword = this.keyValue.getHeadStringValue(SOItemKey.CTRADEWORDID);
    if (PubAppTool.isNull(tradword)) {
      return false;
    }

    Map<String, UFBoolean> mapfactoty =
        TradeWordService.isTradeWordFactory(new String[] {
          tradword
        });

    return mapfactoty.get(tradword).booleanValue();
  }

  private void setSendCountryValue(int row, String saleorg,
      Map<String, OrgVO> mapcountcorp) {
    String sendorg =
        this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTOCKORGID);
    String sendcountry = null;
    if (!PubAppTool.isNull(sendorg)) {
      sendcountry = this.getCountry(mapcountcorp, sendorg);
    }
    else {
      sendcountry = this.getCountry(mapcountcorp, saleorg);
    }
    this.keyValue.setBodyValue(row, SOItemKey.CSENDCOUNTRYID, sendcountry);
  }

  private void setTaxCountryValue(int row, String saleorg,
      Map<String, OrgVO> mapcountcorp) {
    String sendorg =
        this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTOCKORGID);
    String setorg =
        this.keyValue.getBodyStringValue(row, SOItemKey.CSETTLEORGID);
    String taxcountry = null;
    if (!PubAppTool.isNull(sendorg) && !PubAppTool.isNull(setorg)) {
      String sendcorp = this.getCorp(mapcountcorp, sendorg);
      String setcorp = this.getCorp(mapcountcorp, setorg);

      // 跨公司
      if (!PubAppTool.isNull(sendcorp) && !PubAppTool.isNull(setcorp)
          && !PubAppTool.isEqual(sendcorp, setcorp)) {
        taxcountry = this.getCountry(mapcountcorp, setorg);
      }
      else {
        taxcountry = this.getCountry(mapcountcorp, sendorg);
      }
    }
    else if (PubAppTool.isNull(sendorg) && !PubAppTool.isNull(setorg)) {
      taxcountry = this.getCountry(mapcountcorp, setorg);
    }
    else if (!PubAppTool.isNull(sendorg) && PubAppTool.isNull(setorg)) {
      taxcountry = this.getCountry(mapcountcorp, sendorg);
    }
    else {
      taxcountry = this.getCountry(mapcountcorp, saleorg);
    }
    this.keyValue.setBodyValue(row, SOItemKey.CTAXCOUNTRYID, taxcountry);
  }
}
