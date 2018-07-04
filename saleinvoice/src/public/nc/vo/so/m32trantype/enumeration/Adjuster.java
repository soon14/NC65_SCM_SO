package nc.vo.so.m32trantype.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * 调整项
 * 
 * @since 6.0
 * @version 2012-2-8 下午01:44:33
 * @author 么贵敬
 */
public class Adjuster extends MDEnum {

  /**
   * 调折扣
   */
  public static final Adjuster ADJUSTDISCOUNT = MDEnum.valueOf(Adjuster.class,
      Integer.valueOf(0));

  /**
   * 调单价
   */
  public static final Adjuster ADJUSTPRICE = MDEnum.valueOf(Adjuster.class,
      Integer.valueOf(1));

  /**
   * 
   * Adjuster 的构造子
   * 
   * @param enumvalue
   */
  public Adjuster(IEnumValue enumvalue) {
    super(enumvalue);
  }

}
