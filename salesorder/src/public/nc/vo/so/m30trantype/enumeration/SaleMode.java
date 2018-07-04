package nc.vo.so.m30trantype.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * 销售模式
 * （普通/退货/退换货/普通+退货/普通+退换货）
 * 
 * @version 6.0
 * @author 刘志伟
 */
public class SaleMode extends MDEnum {

  /** 普通 */
  public static final SaleMode MODE_COMMON = MDEnum.valueOf(SaleMode.class,
      Integer.valueOf(1));

  /** 普通+退货 */
  public static final SaleMode MODE_COMMONRETURN = MDEnum.valueOf(
      SaleMode.class, Integer.valueOf(4));

  /** 普通+退换货 */
  public static final SaleMode MODE_COMMONRETURNCHANGE = MDEnum.valueOf(
      SaleMode.class, Integer.valueOf(5));

  /** 退货 */
  public static final SaleMode MODE_RETURN = MDEnum.valueOf(SaleMode.class,
      Integer.valueOf(2));

  /** 退换货 */
  public static final SaleMode MODE_RETURNCHANGE = MDEnum.valueOf(
      SaleMode.class, Integer.valueOf(3));

  public SaleMode(IEnumValue enumValue) {
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
