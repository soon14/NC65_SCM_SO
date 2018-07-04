package nc.vo.so.pub.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 批编辑公共规则类
 * 批编辑的时候比对他依赖的字段 是否跟第一行数据一致，如果一致保留编辑后的值，如果不一致则清空相应值
 * 
 * @since 6.0
 * @version 2012-6-28 上午11:10:45
 * @author 么贵敬
 */
public class SOBatcheEditRule {
  private String[] bykeys;

  private IKeyValue keyValue;

  // public SOBatcheEditRule(BillCardPanel cardPanel, String[] bykeys) {
  // this.keyValue = new CardKeyValue(cardPanel);
  // this.bykeys = bykeys;
  // }

  /**
   * 
   * @param keyValue
   * @param bykeys 数据依赖的字段
   */
  public SOBatcheEditRule(IKeyValue keyValue, String[] bykeys) {
    this.keyValue = keyValue;
    this.bykeys = bykeys;
  }

  public void clearRows(int[] rows, String clearkey) {
    if (rows.length < 2) {
      return;
    }

    List<Integer> needclearrows = this.getNeedClearRows(rows);

    for (Integer i : needclearrows) {
      this.keyValue.setBodyValue(i.intValue(), clearkey, null);
    }

  }

  public void clearRows(int[] rows, String[] clearkeys) {
    if (rows.length < 2) {
      return;
    }

    List<Integer> needclearrows = this.getNeedClearRows(rows);
    for (String key : clearkeys) {
      for (Integer i : needclearrows) {
        this.keyValue.setBodyValue(i.intValue(), key, null);
      }
    }
  }

  /**
   * 需要清除数据的行
   * 
   * @param rows
   * @return
   */
  private List<Integer> getNeedClearRows(int[] rows) {
    Map<String, Object> onemap = new HashMap<String, Object>();
    for (String key : this.bykeys) {
      onemap.put(key, this.keyValue.getBodyValue(rows[0], key));
    }
    List<Integer> needclearrows = new ArrayList<Integer>();
    Object newob = null;
    Object oldob = null;
    for (int i : rows) {
      for (String key : this.bykeys) {
        newob = this.keyValue.getBodyValue(i, key);
        oldob = onemap.get(key);
        if (null != newob) {
          if (!newob.equals(oldob)) {
            needclearrows.add(Integer.valueOf(i));
          }
        }
        else if (null != oldob) {
          needclearrows.add(Integer.valueOf(i));
        }
      }
    }
    return needclearrows;
  }
}
