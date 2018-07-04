package nc.vo.so.pub.util;

import nc.vo.pub.lang.UFDouble;

/**
 * 
 * @since 6.0
 * @version 2011-8-31 下午12:58:35
 * @author 么贵敬
 */
public class SOMathUtil {

  /**
   * 判断UFDouble 是否为0 nc.vo.pubapp.pattern.pub.MathTool.isZero(UFDouble d) 判断不准确
   * 
   * @param d
   * @return
   */
  public static boolean isZero(UFDouble d) {
    if (null == d) {
      return true;
    }
    if (d.compareTo(UFDouble.ZERO_DBL) == 0) {
      return true;
    }
    return false;
  }

}
