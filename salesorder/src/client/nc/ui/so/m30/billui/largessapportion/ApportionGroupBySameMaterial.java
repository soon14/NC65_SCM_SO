package nc.ui.so.m30.billui.largessapportion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 按同物料分摊时分组关系
 * 
 * @since 6.0
 * @version 2011-7-13 上午10:38:05
 * @author fengjb
 */

public class ApportionGroupBySameMaterial implements IApportionGroup {

  @Override
  public List<List<Integer>> getApportionGroupRows(IKeyValue keyValue,
      List<Integer> rowlist) {

    Set<String> setlarmarid = this.getLargessMarID(keyValue, rowlist);
    MapList<String, Integer> mlgrouprow = new MapList<String, Integer>();
    for (Integer row : rowlist) {
      UFDouble num =
          keyValue.getBodyUFDoubleValue(row.intValue(), SaleOrderBVO.NNUM);
      if (null == num || num.compareTo(UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      String marid =
          keyValue.getBodyStringValue(row.intValue(), SaleOrderBVO.CMATERIALID);
      if (setlarmarid.contains(marid)) {
        mlgrouprow.put(marid, row);
      }
    }

    List<List<Integer>> indexgroups = new ArrayList<List<Integer>>();
    // 按物料分组
    for (Entry<String, List<Integer>> entry : mlgrouprow.entrySet()) {
      List<Integer> rowgroup = entry.getValue();
      if (null != rowgroup && rowgroup.size() > 1) {
        indexgroups.add(rowgroup);
      }
    }
    return indexgroups;
  }

  private Set<String> getLargessMarID(IKeyValue keyValue, List<Integer> rowlist) {
    Set<String> setid = new HashSet<String>();
    for (Integer row : rowlist) {
      UFBoolean larflag =
          keyValue.getBodyUFBooleanValue(row.intValue(),
              SaleOrderBVO.BLARGESSFLAG);
      if (null == larflag || !larflag.booleanValue()) {
        continue;
      }
      String marid =
          keyValue.getBodyStringValue(row.intValue(), SaleOrderBVO.CMATERIALID);

      setid.add(marid);
    }
    return setid;
  }
}
