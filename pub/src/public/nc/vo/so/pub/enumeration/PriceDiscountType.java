package nc.vo.so.pub.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

public class PriceDiscountType extends MDEnum {

  /** 调整折扣 */
  public static final PriceDiscountType DISCOUNTTYPE = MDEnum.valueOf(
      PriceDiscountType.class, Integer.valueOf(1));

  /** 调整单价 */
  public static final PriceDiscountType PRICETYPE = MDEnum.valueOf(
      PriceDiscountType.class, Integer.valueOf(2));

  public PriceDiscountType(IEnumValue enumValue) {
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
