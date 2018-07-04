package nc.vo.so.pub.util;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Map;

public class SOVOChecker {

  /**
   * 检查参数是否为空。
   * 
   * @return boolean 如果被检查值为null，返回true。
   *         如果value的类型为String，并且value.length()为0，返回true。
   *         如果value的类型为Object[]，并且value.length为0，返回true。
   *         如果value的类型为Collection，并且value.size()为0，返回true。
   *         如果value的类型为Map,并且value.size()为0，返回true。
   *         如果value的类型为Dictionary，并且value.size()为0，返回true。 否则返回false。
   * @param value
   *          被检查值。
   */
  @SuppressWarnings("rawtypes")
  public static boolean isEmpty(Object value) {
    if (value == null) {
      return true;
    }
    if (value instanceof String && ((String) value).trim().length() <= 0) {
      return true;
    }
    if (value instanceof Object[] && ((Object[]) value).length <= 0) {
      return true;
    }
    if (value instanceof Collection && ((Collection) value).size() <= 0) {
      return true;
    }
    if (value instanceof Map && ((Map) value).size() <= 0) {
      return true;
    }
    if (value instanceof Dictionary && ((Dictionary) value).size() <= 0) {
      return true;
    }
    return false;
  }

}
