package nc.vo.so.pub.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.trade.checkrule.VOChecker;

public class SOUnitDefaultRule {

  private IKeyValue keyValue;

  public SOUnitDefaultRule(
      IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 设置主单位
   * 
   * @param rows
   */
  public void setUnit(int[] rows) {
    Set<String> setmarid = new HashSet<String>();
    for (int row : rows) {
      String unitid = this.keyValue.getBodyStringValue(row, SOItemKey.CUNITID);
      String marterialvid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
      if (PubAppTool.isNull(marterialvid) || !PubAppTool.isNull(unitid)) {
        continue;
      }
      setmarid.add(marterialvid);
    }
    Map<String, String> mapunit = null;
    if (setmarid.size() > 0) {
      String[] marids = new String[setmarid.size()];
      setmarid.toArray(marids);

      mapunit = MaterialPubService.queryMaterialMeasdoc(marids);

    }
    for (int row : rows) {
      String marterialvid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
      String newunit = null;
      if (null != mapunit) {
        newunit = mapunit.get(marterialvid);
      }
      String unitid = this.keyValue.getBodyStringValue(row, SOItemKey.CUNITID);
      if (VOChecker.isEmpty(unitid)) {
        this.keyValue.setBodyValue(row, SOItemKey.CUNITID, newunit);
      }
    }
  }

  /**
   * 设置业务单位和报价单位为物料默认销售单位
   * 
   * @param rows
   */
  public void setDefaultSaleUnit(int[] rows) {
    Set<String> setmarid = new HashSet<String>();
    for (int row : rows) {
      String marterialvid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
      if (PubAppTool.isNull(marterialvid)) {
        continue;
      }
      setmarid.add(marterialvid);
    }
    Map<String, String> mapunit = null;
    if (setmarid.size() > 0) {
      String[] marids = new String[setmarid.size()];
      setmarid.toArray(marids);

      mapunit = MaterialPubService.querySaleMeasdocIDByPks(marids);

    }
    for (int row : rows) {
      String marterialvid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
      String defaultunit = null;
      if (null != mapunit) {
        defaultunit = mapunit.get(marterialvid);
      }
      String unitid = this.keyValue.getBodyStringValue(row, SOItemKey.CUNITID);
      this.keyValue.setBodyValue(row, SOItemKey.CQTUNITID, unitid);
      if (VOChecker.isEmpty(defaultunit)) {
        this.keyValue.setBodyValue(row, SOItemKey.CASTUNITID, unitid);
      }
      else {
        this.keyValue.setBodyValue(row, SOItemKey.CASTUNITID, defaultunit);
      }
    }
  }

  public boolean isUnitChange(int row, String oldunitid, String oldastunitid,
      String oldqtunitid) {
    // 旧的主单位、业务单位、报价单位为空，则认为单位精度改变
    if (PubAppTool.isNull(oldunitid) || PubAppTool.isNull(oldastunitid)
        || PubAppTool.isNull(oldqtunitid)) {
      return true;
    }

    String newunitid = this.keyValue.getBodyStringValue(row, SOItemKey.CUNITID);
    String newastunitid =
        this.keyValue.getBodyStringValue(row, SaleOrderBVO.CASTUNITID);
    String newqtunitid =
        this.keyValue.getBodyStringValue(row, SaleOrderBVO.CQTUNITID);
    // 新的物料主单位、业务单位、报价单位为空，则认为单位精度改变
    if (PubAppTool.isNull(newunitid) || PubAppTool.isNull(newastunitid)
        || PubAppTool.isNull(newqtunitid)) {
      return true;
    }

    // 所有单位都相同，则单位精度未改变
    if (oldunitid.equals(newunitid) && oldastunitid.equals(newastunitid)
        && oldqtunitid.equals(newqtunitid)) {
      return false;
    }

    return true;
  }

}
