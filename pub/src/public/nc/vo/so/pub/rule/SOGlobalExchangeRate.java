package nc.vo.so.pub.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOCurrencyUtil;

public class SOGlobalExchangeRate {

  private static final String[] GLOBAL_CLEARKEY = new String[] {
    SOItemKey.NGLOBALEXCHGRATE, SOItemKey.NGLOBALMNY, SOItemKey.NGLOBALTAXMNY
  };

  private IKeyValue keyValue;

  public SOGlobalExchangeRate(IKeyValue keyvalue) {
    this.keyValue = keyvalue;
  }

  public boolean isGlobalExchgRateChange(String editcurkey) {
    SOCurrencyUtil currate = SOCurrencyUtil.getInstance();
    if (SOItemKey.CORIGCURRENCYID.equals(editcurkey)
        && currate.isLocalCurToGlobalMoney()) {
      return true;
    }
    if (SOItemKey.CCURRENCYID.equals(editcurkey)
        && currate.isOrgCurToGlobalMoney()) {
      return true;
    }
    return false;
  }

  public void calcGlobalExchangeRate(int[] rows) {
    this.clearValue(rows);
    SOCurrencyUtil currate = SOCurrencyUtil.getInstance();
    // 判断是否启用全局本位币
    if (currate.isGlobalCurrencyEnable()) {
      // 基于原币
      if (currate.isLocalCurToGlobalMoney()) {
        this.setLocalCurGlobalRate(rows);
      }
      else {
        // 单据日期
        UFDate billdate = this.keyValue.getHeadUFDateValue(SOItemKey.DBILLDATE);
        this.setGlobalRateByCurKeyAndDate(rows, SOItemKey.CCURRENCYID, billdate);
      }
    }
    else {
      for (int row : rows) {
        this.keyValue.setBodyValue(row, SOItemKey.NGLOBALEXCHGRATE, null);
      }
    }
  }

  /**
   * 原本币都在表体且按照系统业务日期计算全局折本汇率
   * 
   * @param rows
   */
  public void calcGlobalExchgRateAtBodyByBusidate(int[] rows) {
    //this.clearValue(rows);
    SOCurrencyUtil currate = SOCurrencyUtil.getInstance();
    // 判断是否启用全局本位币
    if (currate.isGlobalCurrencyEnable()) {
      UFDate busidate = AppContext.getInstance().getBusiDate();
      // 基于原币
      if (currate.isLocalCurToGlobalMoney()) {
        this.setGlobalRateByCurKeyAndDate(rows, SOItemKey.CORIGCURRENCYID,
            busidate);
      }
      else {
        this.setGlobalRateByCurKeyAndDate(rows, SOItemKey.CCURRENCYID, busidate);
      }
    }
  }

  private void clearValue(int[] rows) {

    for (int row : rows) {
      for (String key : SOGlobalExchangeRate.GLOBAL_CLEARKEY) {
        this.keyValue.setBodyValue(row, key, null);
      }
    }

  }

  public boolean isGlobalExchgRateEdit(int row) {
    boolean isedit = true;
    // 原币币种
    String localcurid =
        this.keyValue.getHeadStringValue(SOItemKey.CORIGCURRENCYID);
    // 组织本位币
    String orgcurid =
        this.keyValue.getBodyStringValue(row, SOItemKey.CCURRENCYID);

    // 全局本位币
    String globalcur = SOCurrencyUtil.getGlobalLocalCurrency();

    SOCurrencyUtil curutil = SOCurrencyUtil.getInstance();
    if (curutil.isGlobalCurrencyEnable()) {
      // 基于原币折算
      if (curutil.isLocalCurToGlobalMoney()) {
        isedit =
            !PubAppTool.isNull(localcurid) && !globalcur.equals(localcurid);
      }
      // 基于本币折算
      else {
        isedit = !PubAppTool.isNull(orgcurid) && !globalcur.equals(orgcurid);
      }
    }
    else {
      isedit = false;
    }
    return isedit;
  }

  private void setGlobalRateByCurKeyAndDate(int[] rows, String curkey,
      UFDate date) {
    // 本位币
    Set<String> setcurr = new HashSet<String>();
    for (int row : rows) {
      String currency = this.keyValue.getBodyStringValue(row, curkey);
      if (null != date && !PubAppTool.isNull(currency)) {
        setcurr.add(currency);
      }
    }
    Map<String, UFDouble> mapglobalrate = new HashMap<String, UFDouble>();
    for (String curr : setcurr) {
      UFDouble globalrate =
          SOCurrencyUtil.getGlobalLocalCurrencyBuyRate(curr, date);
      mapglobalrate.put(curr, globalrate);
    }

    for (int row : rows) {
      String currency = this.keyValue.getBodyStringValue(row, curkey);
      if (null != date && !PubAppTool.isNull(currency)) {
        this.keyValue.setBodyValue(row, SOItemKey.NGLOBALEXCHGRATE,
            mapglobalrate.get(currency));
      }
    }
  }

  private void setLocalCurGlobalRate(int[] rows) {

    // 单据日期
    UFDate billdate = this.keyValue.getHeadUFDateValue(SOItemKey.DBILLDATE);
    // 原币币种
    String orgcurrency =
        this.keyValue.getHeadStringValue(SOItemKey.CORIGCURRENCYID);
    UFDouble localcurrate = null;
    if (null != billdate && !PubAppTool.isNull(orgcurrency)) {
      localcurrate =
          SOCurrencyUtil.getGlobalLocalCurrencyBuyRate(orgcurrency, billdate);
    }

    for (int row : rows) {
      this.keyValue.setBodyValue(row, SOItemKey.NGLOBALEXCHGRATE, localcurrate);
    }

  }

}
