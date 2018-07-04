package nc.vo.so.pub.util;

import java.lang.reflect.Array;
import java.util.List;

public class ListUtil {

  private ListUtil() {
    super();
  }
  
  /**
   * List是否为空。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param list
   * @return <p>
   * @author wangyf
   * @time 2010-3-15 上午11:49:20
   */
  public static <E> boolean isEmpty(List<E> list) {
    if ((list == null) || (list.size() == 0)) {
      return true;
    }
    return false;

  }

  /**
   * 方法功能描述：list转化为数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param <E>
   * @param list
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-14 上午11:33:28
   */
  @SuppressWarnings("unchecked")
  public static <E> E[] toArray(List<E> list) {
    if (ListUtil.isEmpty(list)) {
      return null;
    }
    E notNullItem = null;
    boolean find = false;
    for (E e : list) {
      if (find) {
        break;
      }
      if (e != null) {
        notNullItem = e;
        find = true;
      }
    }
    
    E[] result = null;
    if (notNullItem != null) {
      result = (E[]) Array.newInstance(notNullItem.getClass(), list.size());
      result = list.toArray(result);
    }
    return result;
  }

}
