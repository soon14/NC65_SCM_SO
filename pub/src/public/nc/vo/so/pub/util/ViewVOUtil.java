package nc.vo.so.pub.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.trade.checkrule.VOChecker;

public class ViewVOUtil {

  private ViewVOUtil() {
    super();
  }

  @SuppressWarnings("unchecked")
  public static <T extends Object, E extends SuperVO> T[] getDistinctFieldArray(
      AbstractDataView[] voaCheck, Class<E> voClass, String sField,
      Class<T> sFieldClass) {
    List<E> list = new ArrayList<E>();
    for (AbstractDataView adv : voaCheck) {
      list.add((E) adv.getVO(voClass));
    }
    E[] oaRet = null;
    oaRet = Constructor.declareArray(voClass, list.size());
    return ViewVOUtil.getDistinctFieldArray(list.toArray(oaRet), sField,
        sFieldClass);
  }

  @SuppressWarnings("unchecked")
  private static <T extends Object> T[] getDistinctFieldArray(
      CircularlyAccessibleValueObject[] voaPlan, String sField,
      Class<T> sFieldClass) {

    Set<T> mapValue = new HashSet<T>();
    T oValue = null;
    int iLen = voaPlan.length;
    for (int j = 0; j < iLen; j++) {

      oValue = (T) voaPlan[j].getAttributeValue(sField);

      // ²»º¬¿Õ¼°ÖØ¸´¼ü
      if (!VOChecker.isEmpty(oValue) && !mapValue.contains(oValue)) {
        mapValue.add(oValue);
      }
    }

    iLen = mapValue.size();
    if (iLen == 0) {
      return null;
    }

    T[] oaRet = null;
    oaRet = Constructor.declareArray(sFieldClass, iLen);
    mapValue.toArray(oaRet);
    return oaRet;
  }

}
