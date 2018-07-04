package nc.ui.so.m30.billui.largessapportion;

import java.util.ArrayList;
import java.util.List;

import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 按整单分摊时的分组关系
 * 
 * @since 6.0
 * @version 2011-7-13 上午10:20:52
 * @author fengjb
 */

public class ApportionGroupByOne implements IApportionGroup {

  @Override
  public List<List<Integer>> getApportionGroupRows(IKeyValue keyValue,
      List<Integer> rowlist) {
    List<List<Integer>> indexgroups = new ArrayList<List<Integer>>();
    if (null != rowlist && rowlist.size() > 1) {
      indexgroups.add(rowlist);
    }
    return indexgroups;
  }
}
