package nc.vo.so.m32.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票对冲标志枚举类
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-4-28 下午02:37:01
 */
public class OpposeFlag extends MDEnum {

  /**
   * 被对冲
   */
  public static final OpposeFlag FINSH = MDEnum.valueOf(OpposeFlag.class,
      Integer.valueOf(1));

  /**
   * 对冲生成
   */
  public static final OpposeFlag GENERAL = MDEnum.valueOf(OpposeFlag.class,
      Integer.valueOf(2));

  /**
   * 正常
   */
  public static final OpposeFlag NORMAL = MDEnum.valueOf(OpposeFlag.class,
      Integer.valueOf(0));

  public OpposeFlag(IEnumValue enumvalue) {
    super(enumvalue);
  }

}
