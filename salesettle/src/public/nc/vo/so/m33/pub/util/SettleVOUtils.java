package nc.vo.so.m33.pub.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

public class SettleVOUtils {

  @SuppressWarnings("unchecked")
  public <E, T extends AbstractBill> Map<E, List<T>> splitVOByHeadItem(
      T[] sqdvos, String itemKeyName) {
    if (sqdvos == null) {
      return null;
    }
    Map<E, List<T>> map = new HashMap<E, List<T>>();
    E key = null;
    List<T> list = null;
    for (T svo : sqdvos) {
      key = (E) svo.getParentVO().getAttributeValue(itemKeyName);
      list = map.get(key);
      if (list == null) {
        list = new ArrayList<T>();
        map.put(key, list);
      }
      list.add(svo);
    }
    return map;

  }
}
