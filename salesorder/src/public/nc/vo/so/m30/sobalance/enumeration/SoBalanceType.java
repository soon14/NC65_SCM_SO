package nc.vo.so.m30.sobalance.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

public class SoBalanceType extends MDEnum {

  // 订单收款核销
  public static final SoBalanceType SOBALANCE_ORDERBAL =
      MDEnum.valueOf(SoBalanceType.class, Integer.valueOf(0));

  // 财务核销
  public static final SoBalanceType SOBALANCE_FINBAL =
      MDEnum.valueOf(SoBalanceType.class, Integer.valueOf(1));

  public SoBalanceType(
      IEnumValue enumValue) {
    super(enumValue);
  }

  public int getIntValue() {
    return Integer.parseInt(this.value().toString());
  }

  public Integer getIntegerValue() {
    return Integer.valueOf(this.value().toString());
  }

  public String getStringValue() {
    return this.value().toString();
  }

}
