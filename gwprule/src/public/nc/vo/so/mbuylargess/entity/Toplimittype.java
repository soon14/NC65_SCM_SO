package nc.vo.so.mbuylargess.entity;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <b> 在此处简要描述此类的功能 </b>
 * <p>
 * 在此处添加此类的描述信息
 * </p>
 * 创建日期:${vmObject.createdDate}
 * 
 * @author
 * @version NCPrj ??
 */
public class Toplimittype extends MDEnum {
  // 数量
  public static final Toplimittype LIMIT_NUM = MDEnum.valueOf(
      Toplimittype.class, Integer.valueOf(0));

  // 不控制
  public static final Toplimittype LIMIT_NO = MDEnum.valueOf(
      Toplimittype.class, Integer.valueOf(2));

  // 金额
  public static final Toplimittype LIMIT_MNY = MDEnum.valueOf(
      Toplimittype.class, Integer.valueOf(1));

  public Toplimittype(
      IEnumValue enumvalue) {
    super(enumvalue);
  }

}
