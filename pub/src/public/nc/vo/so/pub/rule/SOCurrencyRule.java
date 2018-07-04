package nc.vo.so.pub.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class SOCurrencyRule {
  private IKeyValue keyValue;

  public SOCurrencyRule(IKeyValue keyvalue) {
    this.keyValue = keyvalue;
  }

  public void setCurrency(int[] rows) {
    Set<String> settleorgs = new HashSet<String>();
    for (int row : rows) {
      String csettleorgid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSETTLEORGID);
      if (!PubAppTool.isNull(csettleorgid)) {
        settleorgs.add(csettleorgid);
      }
    }

    Map<String, String> orgCurrMap = null;
    if (settleorgs.size() > 0) {
      String[] setorgs = new String[settleorgs.size()];
      settleorgs.toArray(setorgs);
      orgCurrMap = this.getOrgCurrByPks(setorgs);
    }
    if (null == orgCurrMap) {
      orgCurrMap = new HashMap<String, String>();
    }
    for (int row : rows) {
      String csettleorgid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSETTLEORGID);
      if (PubAppTool.isNull(csettleorgid)) {
        this.keyValue.setBodyValue(row, SOItemKey.CCURRENCYID, null);
      }
      else {
        this.keyValue.setBodyValue(row, SOItemKey.CCURRENCYID,
            orgCurrMap.get(csettleorgid));
      }
    }
  }

  private Map<String, String> getOrgCurrByPks(String[] pks) {
    Map<String, String> orgCurrMap = null;
    orgCurrMap = OrgUnitPubService.queryOrgCurrByPk(pks);
    return orgCurrMap;
  }
}
