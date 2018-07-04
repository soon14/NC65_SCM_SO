package nc.vo.so.pub.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>字符串的实用操作
 * <li>
 * <li>
 * </ul>
 * 
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2009-12-31 上午10:54:29
 */
public class StringUtil {

  private StringUtil() {
    super();
  }
  
  /**
   * 
   * 方法功能描述：去掉字串数组中的重复串。
   * <p>
   * <b>参数说明</b>
   * 
   * @param ss
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-13 下午06:32:45
   */
  public static String[] getDistinct(String[] ss) {
    if ((null == ss) || (0 == ss.length)) {
      return ss;
    }
    List<String> ssList = Arrays.asList(ss);
    Set<String> ssSet = new HashSet<String>(ssList);
    return ssSet.toArray(new String[ssSet.size()]);
  }

  /**
   * 
   * 方法功能描述：判断一个对象toString去掉后空格后是否为空。
   * <p>
   * <b>参数说明</b>
   * 
   * @param o
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-31 上午10:59:14
   */
  public static boolean isEmptyTrimSpace(Object o) {
    if (null == o) {
      return true;
    }
    return nc.vo.jcom.lang.StringUtil.isEmptyWithTrim(o.toString());
  }

}
