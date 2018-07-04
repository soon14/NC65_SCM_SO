package nc.vo.so.pub.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOCurrencyUtil;

public class SOGroupExchangeRate {

  private static final String[] GROUP_CLEARKEY = new String[] {
    SOItemKey.NGROUPEXCHGRATE, SOItemKey.NGROUPTAXMNY, SOItemKey.NGROUPMNY
  };

  private IKeyValue keyValue;

  public SOGroupExchangeRate(IKeyValue keyvalue) {
    this.keyValue = keyvalue;
  }

  public boolean isGroupExchgRateChange(String editcurkey) {
    SOCurrencyUtil currate = SOCurrencyUtil.getInstance();
    if (SOItemKey.CORIGCURRENCYID.equals(editcurkey)
        && currate.isLocalCurToGroupMoney()) {
      return true;
    }
    if (SOItemKey.CCURRENCYID.equals(editcurkey)
        && currate.isOrgCurToGroupMoney()) {
      return true;
    }
    return false;
  }

  public void calcGroupExchangeRate(int[] rows) {
    this.clearValue(rows);
    SOCurrencyUtil currate = SOCurrencyUtil.getInstance();
    // 判断是否启用集团本位币
    if (currate.isGroupCurrencyEnable()) {
      // 基于原币
      if (currate.isLocalCurToGroupMoney()) {
        this.setLocalCurGroupRate(rows);
      }
      else {
        // 单据日期
        UFDate billdate = this.keyValue.getHeadUFDateValue(SOItemKey.DBILLDATE);
        this.setGroupRateByCurKeyAndDate(rows, SOItemKey.CCURRENCYID, billdate);
      }
    }
  }

  /**
   * 原本币都在表体且按照系统业务日期计算集团折本汇率
   * 
   * @param rows
   */
  public void calcGroupExchgRateAtBodyByBusidate(int[] rows) {
    //this.clearValue(rows);
    SOCurrencyUtil currate = SOCurrencyUtil.getInstance();
    // 判断是否启用集团本位币
    if (currate.isGroupCurrencyEnable()) {
      UFDate busidate = AppContext.getInstance().getBusiDate();
      // 基于原币
      if (currate.isLocalCurToGroupMoney()) {
        this.setGroupRateByCurKeyAndDate(rows, SOItemKey.CORIGCURRENCYID,
            busidate);
      }
      else {
        this.setGroupRateByCurKeyAndDate(rows, SOItemKey.CCURRENCYID, busidate);
      }
    }
  }

  private void clearValue(int[] rows) {
    for (int row : rows) {
      for (String key : SOGroupExchangeRate.GROUP_CLEARKEY) {
        this.keyValue.setBodyValue(row, key, null);
      }
    }
  }

  private void setLocalCurGroupRate(int[] rows) {
    // 单据日期
    UFDate billdate = this.keyValue.getHeadUFDateValue(SOItemKey.DBILLDATE);
    // 原币币种
    String orgcurrency =
        this.keyValue.getHeadStringValue(SOItemKey.CORIGCURRENCYID);
    UFDouble localcurrate = null;
    if (null != billdate && !PubAppTool.isNull(orgcurrency)) {
      localcurrate =
          SOCurrencyUtil.getGroupLocalCurrencyBuyRate(orgcurrency, billdate);
    }

    for (int row : rows) {
      this.keyValue.setBodyValue(row, SOItemKey.NGROUPEXCHGRATE, localcurrate);
    }
  }

  private void setGroupRateByCurKeyAndDate(int[] rows, String curkey,
      UFDate date) {
    // 本位币
    Set<String> setcurr = new HashSet<String>();
    for (int row : rows) {
      String currency = this.keyValue.getBodyStringValue(row, curkey);
      if (null != date && !PubAppTool.isNull(currency)) {
        setcurr.add(currency);
      }
    }
    Map<String, UFDouble> mapgrouprate = new HashMap<String, UFDouble>();
    for (String curr : setcurr) {
      UFDouble grouprate =
          SOCurrencyUtil.getGroupLocalCurrencyBuyRate(curr, date);
      mapgrouprate.put(curr, grouprate);
    }
    for (int row : rows) {
      String currency = this.keyValue.getBodyStringValue(row, curkey);
      if (null != date && !PubAppTool.isNull(currency)) {
        this.keyValue.setBodyValue(row, SOItemKey.NGROUPEXCHGRATE,
            mapgrouprate.get(currency));
      }
    }
  }

  public boolean isGroupExchgRateEdit(int row) {
    boolean isedit = true;
    // 原币币种
    String localcurid =
        this.keyValue.getHeadStringValue(SOItemKey.CORIGCURRENCYID);
    // 组织本位币
    String orgcurid =
        this.keyValue.getBodyStringValue(row, SOItemKey.CCURRENCYID);

    // 集团本位币
    String groupcur = SOCurrencyUtil.getGroupLocalCurrency();

    SOCurrencyUtil curutil = SOCurrencyUtil.getInstance();
    if (curutil.isGroupCurrencyEnable()) {
      // 基于原币折算
      if (curutil.isLocalCurToGroupMoney()) {
        isedit = !PubAppTool.isNull(localcurid) && !groupcur.equals(localcurid);
      }
      // 基于本币折算
      else {
        isedit = !PubAppTool.isNull(orgcurid) && !groupcur.equals(orgcurid);
      }
    }
    else {
      isedit = false;
    }
    return isedit;
  }
}
