package nc.vo.so.m33.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

public class SquareType extends MDEnum {

  /**
   * 确认应收
   */
  public static final SquareType SQUARETYPE_AR = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(1));

  /**
   * 回冲应收
   */
  public static final SquareType SQUARETYPE_ARRUSH = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(3));

  /**
   * 差额传应收
   */
  public static final SquareType SQUARETYPE_BALANCEAR = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(7));

  /**
   * 暂估应收
   */
  public static final SquareType SQUARETYPE_ET = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(2));

  /**
   * 成本结算
   */
  public static final SquareType SQUARETYPE_IA = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(4));

  /**
   * 无
   */
  public static final SquareType SQUARETYPE_NULL = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(0));

  /**
   * 出库对冲
   */
  public static final SquareType SQUARETYPE_OUTRUSH = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(6));

  /**
   * 计入发出商品贷方
   */
  public static final SquareType SQUARETYPE_REG_CREDIT = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(8));

  /**
   * 计入发出商品借方
   */
  public static final SquareType SQUARETYPE_REG_DEBIT = MDEnum.valueOf(
      SquareType.class, Integer.valueOf(5));

  public SquareType(IEnumValue enumValue) {
    super(enumValue);
  }

  public Integer getIntegerValue() {
    return Integer.valueOf(this.value().toString());
  }

  public int getIntValue() {
    return Integer.parseInt(this.value().toString());
  }

  public String getStringValue() {
    return this.value().toString();
  }
}
