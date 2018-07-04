package nc.ui.so.m4331.billui.pub.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.org.OrgVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.itf.scmpub.reference.uap.bd.addrdoc.AddrdocPubService;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.bd.vat.TradeWordService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;

import nc.bs.ml.NCLangResOnserver;

/**
 * 检查收货地址是否会影响国家，如果会影响国家则清空当前编辑的收货地址
 * 
 * @since 6.3
 * @version 2013-10-21 11:08:44
 * @author liujingn
 */
public class CheckCountryChangeRule {

  private IKeyValue keyValue;

  /**
   * 构造方法
   * 
   * @param keyValue
   */
  public CheckCountryChangeRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 检查地址是否影响收货国家
   * 
   * @param rows
   * @param key
   * @param pos
   * @param oldValues
   */
  public void checkRecCountry(int[] rows, String key, PosEnum pos,
      String[] oldValues) {
    boolean istradefactory = this.isTradeWordFactory();
    Map<String, String> custcountrymap =
        this.getCustCountryMap(rows, istradefactory);
    Map<String, String> addrcountrymap =
        this.getAddrCountryMap(rows, istradefactory);
    StringBuilder noPassRows = new StringBuilder();
    for (int i = 0; i < rows.length; i++) {
      int row = rows[i];
      String receivecountry = null;
      if (istradefactory) {
        String custid =
            this.keyValue.getBodyStringValue(row, DeliveryBVO.CORDERCUSTID);
        receivecountry = custcountrymap.get(custid);
      }
      else {
        String addr =
            this.keyValue.getBodyStringValue(row, DeliveryBVO.CRECEIVEADDRID);
        if (!PubAppTool.isNull(addr)) {
          receivecountry = addrcountrymap.get(addr);
        }
        else {
          String receivecust =
              this.keyValue.getBodyStringValue(row, DeliveryBVO.CRECEIVECUSTID);
          receivecountry = custcountrymap.get(receivecust);
        }
      }
      String oldRecCountry =
          this.keyValue.getBodyStringValue(row, DeliveryBVO.CRECECOUNTRYID);

      if (!PubAppTool.isEqual(oldRecCountry, receivecountry)) {
        noPassRows.append(row + ",");
        if (pos == PosEnum.body) {
          // 设回原值
          this.keyValue.setBodyValue(row, key, oldValues[i]);
        }
      }
    }
    if (pos == PosEnum.head) {
      // 设回原值
      this.keyValue.setHeadValue(key, oldValues[0]);
    }
    if (noPassRows.length() > 0) {
      noPassRows = noPassRows.deleteCharAt(noPassRows.length() - 1);
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006002_0", "04006002-0178", null, new String[] {
            noPassRows.toString()
          }/*@res 第{0}行收货国家发生改变，请重新编辑。*/));
    }

  }

  /**
   * 检查地址是否影响发货国家、报税国家
   * 
   * @param rows
   * @param key
   * @param oldValue
   */
  public void checkSendAndTaxCountry(int[] rows, String key, String[] oldValue) {
    String[] orgkeys = new String[] {
      DeliveryBVO.CSENDSTOCKORGID, DeliveryBVO.CSETTLEORGID
    };
    Map<String, String[]> mapcountcorp = this.getOrgCountCorpMap(rows, orgkeys);
    List<Integer> noPassRows = new ArrayList<Integer>();
    for (int i = 0; i < rows.length; i++) {
      boolean passed =
          this.setSendCountryValue(rows[i], key, oldValue[i], mapcountcorp);
      if (passed) {
        passed =
            this.setTaxCountryValue(rows[i], key, oldValue[i], mapcountcorp);
      }
      if (!passed) {
        noPassRows.add(Integer.valueOf(rows[i]));
      }
    }
    if (noPassRows.size() > 0) {
      StringBuilder sb = new StringBuilder();
      for (Integer row : noPassRows) {
        sb.append(row.toString() + ", ");
      }
      sb = sb.deleteCharAt(sb.length() - 1);
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006002_0", "04006002-0177", null, new String[] {
            noPassRows.toString()
          }/*@res 第{0}行发货国家或报税国家发生改变，请重新编辑。*/));
    }

  }

  private boolean setSendCountryValue(int row, String key, String oldValue,
      Map<String, String[]> mapcountcorp) {
    String sendorg =
        this.keyValue.getBodyStringValue(row, DeliveryBVO.CSENDSTOCKORGID);
    String saleorg =
        this.keyValue.getBodyStringValue(row, DeliveryBVO.CSALEORGID);
    String newSendCountry = null;
    if (!PubAppTool.isNull(sendorg)) {
      newSendCountry = this.getMapCountryCorp(mapcountcorp, sendorg, 0);
    }
    else {
      newSendCountry = this.getMapCountryCorp(mapcountcorp, saleorg, 0);
    }
    String oldSendCountry =
        this.keyValue.getBodyStringValue(row, DeliveryBVO.CSENDCOUNTRYID);
    if (newSendCountry != oldSendCountry
        && !newSendCountry.equals(oldSendCountry)) {
      // 设回原值
      this.keyValue.setBodyValue(row, key, oldValue);
      return false;
    }
    return true;
  }

  private boolean setTaxCountryValue(int row, String key, String oldValue,
      Map<String, String[]> mapcountcorp) {
    String sendorg =
        this.keyValue.getBodyStringValue(row, DeliveryBVO.CSENDSTOCKORGID);
    String setorg =
        this.keyValue.getBodyStringValue(row, DeliveryBVO.CSETTLEORGID);
    String saleorg =
        this.keyValue.getBodyStringValue(row, DeliveryBVO.CSALEORGID);
    String newTaxCountry = null;
    if (!PubAppTool.isNull(sendorg) && !PubAppTool.isNull(setorg)) {
      String sendcorp = this.getMapCountryCorp(mapcountcorp, sendorg, 1);
      String setcorp = this.getMapCountryCorp(mapcountcorp, setorg, 1);

      // 跨公司
      if (!PubAppTool.isNull(sendcorp) && !PubAppTool.isNull(setcorp)
          && !PubAppTool.isEqual(sendcorp, setcorp)) {
        newTaxCountry = this.getMapCountryCorp(mapcountcorp, setorg, 0);
      }
      else {
        newTaxCountry = this.getMapCountryCorp(mapcountcorp, sendorg, 0);
      }
    }
    else if (PubAppTool.isNull(sendorg) && !PubAppTool.isNull(setorg)) {
      newTaxCountry = this.getMapCountryCorp(mapcountcorp, setorg, 0);
    }
    else if (!PubAppTool.isNull(sendorg) && PubAppTool.isNull(setorg)) {
      newTaxCountry = this.getMapCountryCorp(mapcountcorp, sendorg, 0);
    }
    else {
      newTaxCountry = this.getMapCountryCorp(mapcountcorp, saleorg, 0);
    }
    String taxCountry =
        this.keyValue.getBodyStringValue(row, DeliveryBVO.CTAXCOUNTRYID);
    if (newTaxCountry != taxCountry && !newTaxCountry.equals(taxCountry)) {
      // 设回原值
      this.keyValue.setBodyValue(row, key, oldValue);
      return false;
    }
    return true;
  }

  private Map<String, String[]> getOrgCountCorpMap(int[] rows,
      String[] bodyorgkeys) {

    Map<String, String[]> mapcountcorp = new HashMap<String, String[]>();

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
      String[] countryids = new String[2];
      if (null == orgvo) {
        continue;
      }
      // 国家
      countryids[0] = orgvo.getCountryzone();
      // 公司
      countryids[1] = orgvo.getPk_corp();
      mapcountcorp.put(orgvo.getPk_org(), countryids);
    }
    return mapcountcorp;
  }

  private Map<String, String> getCustCountryMap(int[] rows,
      boolean istradefactory) {

    Set<String> setcustid = new HashSet<String>();

    for (int row : rows) {
      String custid =
          this.keyValue.getBodyStringValue(row, DeliveryBVO.CORDERCUSTID);
      if (istradefactory) {
        setcustid.add(custid);
      }
      else {
        String addr =
            this.keyValue.getBodyStringValue(row, DeliveryBVO.CRECEIVEADDRID);
        if (PubAppTool.isNull(addr)) {
          String receivecust =
              this.keyValue.getBodyStringValue(row, DeliveryBVO.CRECEIVECUSTID);
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

  private String getMapCountryCorp(Map<String, String[]> mapcountcorp,
      String key, int index) {
    return mapcountcorp.containsKey(key) ? mapcountcorp.get(key)[index] : null;
  }

  private boolean isTradeWordFactory() {
    String tradword =
        this.keyValue.getHeadStringValue(DeliveryHVO.CTRADEWORDID);
    if (PubAppTool.isNull(tradword)) {
      return false;
    }

    Map<String, UFBoolean> mapfactoty =
        TradeWordService.isTradeWordFactory(new String[] {
          tradword
        });

    return mapfactoty.get(tradword).booleanValue();
  }

  private Map<String, String> getAddrCountryMap(int[] rows,
      boolean istradefactory) {
    Set<String> setaddres = new HashSet<String>();
    for (int row : rows) {
      if (istradefactory) {
        continue;
      }
      String addr =
          this.keyValue.getBodyStringValue(row, DeliveryBVO.CRECEIVEADDRID);
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
}
