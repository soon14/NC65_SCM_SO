package nc.vo.so.report.reportpub;

import nc.pub.smart.metadata.Field;
import nc.pub.smart.model.descriptor.AggrDescriptor;
import nc.pub.smart.model.descriptor.AggrItem;
import nc.pub.smart.model.descriptor.GroupItem;

/**
 * 汇总描述器
 * 
 * @since 6.3
 * @version 2012-9-8 下午01:34:38
 * @author 梁吉明
 */
public class ReportAggDes extends AggrDescriptor {

  private static final long serialVersionUID = 8396219445897227181L;

  /**
   * 设置汇总描述器的汇总字段和分组字段
   * 
   * @param groupkeys
   * @param aggkeys
   */
  public ReportAggDes(String[] groupkeys, String[] aggkeys) {

    // 1. 添加汇总字段
    AggrItem[] aggitems = this.getAggItems(aggkeys);
    this.setAggrFields(aggitems);

    // 2. 添加分组字段
    GroupItem[] groupitems = this.getGropItems(groupkeys);
    this.setGroupFields(groupitems);
  }

  private GroupItem[] getGropItems(String[] groupkeys) {
    GroupItem[] groupitems = new GroupItem[groupkeys.length];
    int i = 0;
    for (String key : groupkeys) {
      Field field = new Field();
      field.setFldname(key);
      groupitems[i] = new GroupItem(field);
      i++;
    }
    return groupitems;
  }

  private AggrItem[] getAggItems(String[] aggkeys) {
    AggrItem[] aggitems = new AggrItem[aggkeys.length];
    int i = 0;
    for (String aggkey : aggkeys) {
      aggitems[i] = new AggrItem(aggkey, 0, aggkey);
      i++;
    }
    return aggitems;
  }
}
