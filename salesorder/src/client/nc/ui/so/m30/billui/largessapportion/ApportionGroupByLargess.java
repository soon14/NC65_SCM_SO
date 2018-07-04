package nc.ui.so.m30.billui.largessapportion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 按买赠分摊时的分组关系
 * 
 * @since 6.0
 * @version 2011-7-13 上午09:59:25
 * @author fengjb
 */
public class ApportionGroupByLargess implements IApportionGroup {

  @Override
  public List<List<Integer>> getApportionGroupRows(IKeyValue keyValue,
      List<Integer> rowlist) {

    MapList<String, Integer> maplarg = new MapList<String, Integer>();

    Set<String> setlarsrc = this.getLargessSrcID(keyValue, rowlist);
    for (Integer row : rowlist) {

      // 订单行BID
      String bid =
          keyValue.getBodyStringValue(row.intValue(),
              SaleOrderBVO.CSALEORDERBID);
      // 赠品行来源ID
      String larsrcid =
          keyValue.getBodyStringValue(row.intValue(),
              SaleOrderBVO.CLARGESSSRCID);
      if (!setlarsrc.contains(bid) && !setlarsrc.contains(larsrcid)) {
        continue;
      }

      if (PubAppTool.isNull(larsrcid)) {
        maplarg.put(bid, row);
      }
      else {
        maplarg.put(larsrcid, row);
      }

    }
    List<List<Integer>> indexgroups = new ArrayList<List<Integer>>();
    for (Entry<String, List<Integer>> entry : maplarg.entrySet()) {
      List<Integer> rowgroup = entry.getValue();
      // 只有一行的说明不存在买赠关系
      if (null != rowgroup && rowgroup.size() > 1) {
        indexgroups.add(rowgroup);
      }
    }

    return indexgroups;
  }

  private Set<String> getLargessSrcID(IKeyValue keyValue, List<Integer> rowlist) {
    Set<String> setid = new HashSet<String>();
    for (Integer row : rowlist) {
      String larsrcid =
          keyValue.getBodyStringValue(row.intValue(),
              SaleOrderBVO.CLARGESSSRCID);
      if (PubAppTool.isNull(larsrcid)) {
        continue;
      }
      setid.add(larsrcid);
    }
    return setid;
  }
}
