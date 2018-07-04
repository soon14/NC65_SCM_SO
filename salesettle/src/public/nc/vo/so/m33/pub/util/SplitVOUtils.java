package nc.vo.so.m33.pub.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class SplitVOUtils<E extends CircularlyAccessibleValueObject> {

  /**
   * 根据分单条件对一组CircularlyAccessibleValueObject进行分单
   * 
   * @param voaSource 需分单的VO数组
   * @param saKey 分单依据KEY数组，如new String[]{"hid","bid"}
   * @return 经过分单的VO数组
   */
  @SuppressWarnings("unchecked")
  public E[][] getSplitVOs(E[] voaSource, String[] saKey) {
    if (saKey == null || saKey.length == 0) {
      return null;
    }

    // 结构：KEY 分单的所有KEY值相加
    // VALUE ArrayList 体[i]
    Map<String, List<E>> hmapRetVO = new HashMap<String, List<E>>();

    // 分单的KEY的个数
    int iKeyNum = saKey.length;

    // 按分单值相同的放入同一个HASH中
    int iLen = voaSource.length;
    String sTempValue = null;
    for (int i = 0; i < iLen; i++) {
      if (voaSource[i] == null) {
        continue;
      }
      // 表头的KEY
      StringBuilder sKey = new StringBuilder("");
      for (int j = 0; j < iKeyNum; j++) {
        sTempValue =
            ValueUtils.getString(voaSource[i].getAttributeValue(saKey[j]));
        sKey.append(sTempValue == null ? "NULL" : sTempValue);
      }

      // 加入到HASH中
      if (!hmapRetVO.containsKey(sKey.toString())) {
        List<E> listItem = new ArrayList<E>();
        listItem.add(voaSource[i]);
        hmapRetVO.put(sKey.toString(), listItem);
      }
      else {
        List<E> listItem = hmapRetVO.get(sKey.toString());
        listItem.add(voaSource[i]);
      }
    }
    if (hmapRetVO.size() == 0) {
      return null;
    }

    // 从HASH中取出所有VO
    E[][] voaRet = null;
    try {
      voaRet =
          (E[][]) Array.newInstance(voaSource.getClass(), hmapRetVO.size());

      Iterator<List<E>> iteror = hmapRetVO.values().iterator();
      int i = 0;
      while (iteror.hasNext()) {
        // 取得VEC
        List<E> listItem = iteror.next();
        // 申请一个新VO
        voaRet[i] =
            (E[]) Array.newInstance(voaSource.getClass(), listItem.size());
        listItem.toArray(voaRet[i]);
        i++;
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    return voaRet;
  }

}
