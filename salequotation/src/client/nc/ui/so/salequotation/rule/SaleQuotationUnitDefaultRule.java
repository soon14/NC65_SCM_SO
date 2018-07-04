package nc.ui.so.salequotation.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.trade.checkrule.VOChecker;

public class SaleQuotationUnitDefaultRule {

  /**
   * 设置业务单位和报价单位为物料默认销售单位
   * 
   * @param rows
   */
  public void setDefaultSaleUnit(IKeyValue keyValue, int[] rows) {
    Set<String> setmarid = new HashSet<String>();
    for (int row : rows) {
      setmarid.add(keyValue.getBodyStringValue(row,
          SalequotationBVO.PK_MATERIAL_V));
    }
    String[] marids = new String[setmarid.size()];
    setmarid.toArray(marids);

    Map<String, String> mapunit = null;
    mapunit = MaterialPubService.querySaleMeasdocIDByPks(marids);
    for (int row : rows) {
      String marterialvid =
          keyValue.getBodyStringValue(row, SalequotationBVO.PK_MATERIAL_V);
      String defaultunit = null;
      if (null != mapunit) {
        defaultunit = mapunit.get(marterialvid);
      }

      if (VOChecker.isEmpty(defaultunit)) {
        String unitid =
            keyValue.getBodyStringValue(row, SalequotationBVO.PK_UNIT);
        keyValue.setBodyValue(row, SalequotationBVO.CASTUNITID, unitid);
        keyValue.setBodyValue(row, SalequotationBVO.CQTUNITID, unitid);
      }
      else {
        keyValue.setBodyValue(row, SalequotationBVO.CASTUNITID, defaultunit);
        keyValue.setBodyValue(row, SalequotationBVO.CQTUNITID, defaultunit);
      }
    }
  }

}
