package nc.vo.so.pub.votools;

/**
 * 类型说明。 创建日期：(2004-3-17 21:29:11)
 * 
 * @author：李振 修改日期：(2004-3-17 21:29:11) 修改人：@author：李振 修改原因： 其它说明：
 */

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

public class SoVoTools {

  private SoVoTools() {
    super();
  }

  /**
   * 获得两个UFDouble中最大精度
   * 
   * <p>
   * <b>参数说明</b>
   * 
   * @param d0
   * @param d1
   * @return int
   *         <p>
   * @time 2010-10-20 上午10:02:20
   */
  public static int getMaxDigit(UFDouble d0, UFDouble d1) {
    if ((d0 == null) && (d1 == null)) {
      return -1;
    }
    if (d0 == null) {
      return Math.abs(d1.getPower());
    }
    else if (d1 == null) {
      return Math.abs(d0.getPower());
    }
    else {
      if (d0.doubleValue() == 0) {
        return Math.abs(d1.getPower());
      }
      else if (d1.doubleValue() == 0) {
        return Math.abs(d0.getPower());
      }
      else {
        return Math.max(Math.abs(d0.getPower()), Math.abs(d1.getPower()));
      }
    }

  }

  /**
   * 获得两个UFDouble加和：取最大精度
   * 
   * <p>
   * <b>参数说明</b>
   * 
   * @param d0
   * @param d1
   * @return UFDouble
   *         <p>
   * @time 2010-10-20 上午10:02:20
   */
  public static UFDouble getMnyAdd(UFDouble d0, UFDouble d1) {

    return SoVoTools.getMnyAdd(d0, d1, SoVoTools.getMaxDigit(d0, d1));

  }

  /**
   * 获得两个UFDouble中加和
   * 
   * <p>
   * <b>参数说明</b>
   * 
   * @param d0
   * @param d1
   * @return int
   *         <p>
   * @time 2010-10-20 上午10:02:20
   */
  public static UFDouble getMnyAdd(UFDouble d0, UFDouble d1, int digit) {
    if ((d0 == null) && (d1 == null)) {
      return null;
    }
    UFDouble ufd0 = d0 == null ? UFDouble.ZERO_DBL : d0;
    UFDouble ufd1 = d1 == null ? UFDouble.ZERO_DBL : d1;
    ufd0 = ufd0.setScale(0 - digit, UFDouble.ROUND_HALF_UP);
    ufd1 = ufd1.setScale(0 - digit, UFDouble.ROUND_HALF_UP);

    return ufd0.add(ufd1).setScale(0 - digit, UFDouble.ROUND_HALF_UP);

  }

  /**
   * 获得两个UFDouble减差：取最大精度
   * 
   * <p>
   * <b>参数说明</b>
   * 
   * @param d0
   * @param d1
   * @return UFDouble
   *         <p>
   * @time 2010-10-20 上午10:02:20
   */
  public static UFDouble getMnySub(UFDouble d0, UFDouble d1) {
    return SoVoTools.getMnySub(d0, d1, SoVoTools.getMaxDigit(d0, d1));
  }

  /**
   * 获得两个UFDouble减差
   * 
   * <p>
   * <b>参数说明</b>
   * 
   * @param d0
   * @param d1
   * @return UFDouble
   *         <p>
   * @time 2010-10-20 上午10:02:20
   */
  public static UFDouble getMnySub(UFDouble d0, UFDouble d1, int digit) {
    if ((d0 == null) && (d1 == null)) {
      return null;
    }
    UFDouble ufd0 = d0 == null ? new UFDouble(0) : d0;
    UFDouble ufd1 = d1 == null ? new UFDouble(0) : d1;
    ufd0 = ufd0.setScale(0 - digit, UFDouble.ROUND_HALF_UP);
    ufd1 = ufd1.setScale(0 - digit, UFDouble.ROUND_HALF_UP);

    return ufd0.sub(ufd1).setScale(0 - digit, UFDouble.ROUND_HALF_UP);

  }

  /**
   * 获得表体UFDouble类型key的合计
   * 
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param key
   * @return retmny
   *         <p>
   * @time 2010-10-20 上午10:02:20
   */
  public static UFDouble getTotalMny(CircularlyAccessibleValueObject[] vos,
      String key) {
    UFDouble retmny = null;
    if ((vos == null) || (vos.length <= 0) || (key == null)) {
      return retmny;
    }
    UFDouble dtemp = null;
    try {
      for (int i = 0; i < vos.length; i++) {
        dtemp = (UFDouble) vos[i].getAttributeValue(key);
        if (dtemp != null) {
          retmny = SoVoTools.getMnyAdd(retmny, dtemp);
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return retmny;
  }

  /**
   * 获得表体行两个key合计值之差
   * 
   * <p>
   * <b>参数说明</b>
   * 
   * @param d0
   * @param d1
   * @return UFDouble
   *         <p>
   * @time 2010-10-20 上午10:02:20
   */
  public static UFDouble getTotalMnySub(CircularlyAccessibleValueObject[] vos,
      String key, CircularlyAccessibleValueObject[] vos1, String key1) {
    UFDouble d0 = SoVoTools.getTotalMny(vos, key);
    UFDouble d1 = SoVoTools.getTotalMny(vos1, key1);
    return SoVoTools.getMnySub(d0, d1);
  }

  public static String[] getVOPKValues(IBill[] vos) {
    if ((vos == null) || (vos.length <= 0)) {
      return null;
    }
    List<String> vlist = new ArrayList<String>();
    String value = null;
    int loop = vos.length;
    for (int i = 0; i < loop; i++) {
      value = vos[i].getPrimaryKey();
      if ((value != null) && !vlist.contains(value)) {
        vlist.add(value);
      }
    }
    return vlist.toArray(new String[vlist.size()]);
  }

  public static String[] getVOPKValues(SuperVO[] vos) {

    if ((vos == null) || (vos.length <= 0)) {
      return null;
    }
    List<String> vlist = new ArrayList<String>();
    String value = null;
    int loop = vos.length;
    for (int i = 0; i < loop; i++) {
      value = vos[i].getPrimaryKey();
      if ((value != null) && !vlist.contains(value)) {
        vlist.add(value);
      }
    }
    return vlist.toArray(new String[vlist.size()]);
  }

  public static String[] getVOsOnlyValues(
      CircularlyAccessibleValueObject[] vos, String key) {

    if ((vos == null) || (vos.length <= 0) || (key == null)
        || (key.length() <= 0)) {
      return null;
    }
    List<String> vlist = new ArrayList<String>();
    String value = null;
    int loop = vos.length;
    for (int i = 0; i < loop; i++) {
      value = (String) vos[i].getAttributeValue(key);
      if ((value != null) && !vlist.contains(value)) {
        vlist.add(value);
      }
    }
    return vlist.toArray(new String[vlist.size()]);
  }

  public static String[] getVOsOnlyValues(IBill[] vos, String key) {

    if ((vos == null) || (vos.length <= 0) || (key == null)
        || (key.length() <= 0)) {
      return null;
    }
    List<String> vlist = new ArrayList<String>();
    String value = null;
    int loop = vos.length;
    for (int i = 0; i < loop; i++) {
      value = (String) vos[i].getParent().getAttributeValue(key);
      if ((value != null) && !vlist.contains(value)) {
        vlist.add(value);
      }
    }
    return vlist.toArray(new String[vlist.size()]);
  }

}
