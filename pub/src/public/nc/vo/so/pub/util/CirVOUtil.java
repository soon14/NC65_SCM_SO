package nc.vo.so.pub.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDate;

public class CirVOUtil {

  private CirVOUtil() {
    super();
  }

  public static <E extends CircularlyAccessibleValueObject> List<E> combineBill(
      E[] voa, E[] vob) {
    List<E> list = new ArrayList<E>();
    for (E vo : voa) {
      list.add(vo);
    }
    for (E vo : vob) {
      list.add(vo);
    }
    return list;
  }

  /**
   * 方法功能描述：当前headvo与来源聚合vo的headvo比较，判断非空字段是否一致
   * <p>
   * <b>参数说明</b>
   * 
   * @param VOs - 聚合vo
   * @param curHeadVO - 表头vo
   * @param itemKeys - 非空字段key
   * @param includeDate - 是否包括日期型字段
   * @return
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-8 下午09:03:17
   */
  public static boolean existDifferNotNullItems(AggregatedValueObject[] vOs,
      CircularlyAccessibleValueObject curHeadVO, String[] itemKeys,
      boolean includeDate) {
    if (ArrayUtil.isEmpty(vOs) || (curHeadVO == null)
        || ArrayUtil.isEmpty(itemKeys)) {
      return false;
    }
    boolean exist = false;
    for (AggregatedValueObject vo : vOs) {
      exist =
          CirVOUtil.existDifferNotNullItems(vo.getParentVO(), curHeadVO,
              itemKeys, includeDate);
      if (exist) {
        exist = true;
      }
    }
    return exist;
  }

  /**
   * 方法功能描述：当前headvo与来源headvo比较，判断非空字段是否一致
   * 目前用于发票参照增行时候的表头vo比较
   * <p>
   * <b>参数说明</b>
   * 
   * @param srcHeadVO 来源headervo
   * @param curHeadVO 当前headervo
   * @param itemKeys - 非空字段key
   * @param includeDate - 是否处理日期型
   * @return
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-8 下午01:26:40
   */
  public static boolean existDifferNotNullItems(
      CircularlyAccessibleValueObject srcHeadVO,
      CircularlyAccessibleValueObject curHeadVO, String[] itemKeys,
      boolean includeDate) {
    if ((srcHeadVO == null) || (curHeadVO == null)
        || ArrayUtil.isEmpty(itemKeys)) {
      return false;
    }

    boolean exist = false;
    for (String key : itemKeys) {
      if ((srcHeadVO.getAttributeValue(key) != null)
          && !srcHeadVO.getAttributeValue(key).equals(
              curHeadVO.getAttributeValue(key))) {
        if (!includeDate
            && (srcHeadVO.getAttributeValue(key) instanceof UFDate)) {
          continue;
        }
        exist = true;
      }
    }
    return exist;
  }

  /**
   * 得到VO数组中按某个域得到的MAP,取MAP的所有KEY得到所有此KEY的值
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaPlan
   *          VO数组
   * @param sField
   *          域名称
   * @param T
   *          域类型
   * @return 按某个域得到的MAP。HashMap<T,T>。T为该字段的类型。使用时只取此MAP的KEY即可。
   *         <p>
   * @author wangyf
   * @param <E>
   * @time 2009-6-29 下午03:09:18
   */
  @SuppressWarnings("unchecked")
  public static <E> Set<E> getDistinctFieldSet(
      CircularlyAccessibleValueObject[] voaPlan, String sField) {

    if (voaPlan == null) {
      return null;
    }

    Set<E> hsetValue = new HashSet<E>();
    E oValue = null;
    int iLen = voaPlan.length;
    for (int j = 0; j < iLen; j++) {

      oValue = (E) voaPlan[j].getAttributeValue(sField);

      // 不含空及重复键
      if (!StringUtil.isEmptyTrimSpace(oValue) && !hsetValue.contains(oValue)) {
        hsetValue.add(oValue);
      }
    }

    iLen = hsetValue.size();
    if (iLen == 0) {
      hsetValue = null;
    }

    return hsetValue;

  }
}
