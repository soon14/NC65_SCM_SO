package nc.vo.so.m30.sobalance.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

public class VerifyProcessType extends MDEnum {

  // Õ¨±“÷÷∫Àœ˙
  public static final VerifyProcessType VERIFY_ENQUALCURID = MDEnum.valueOf(
      VerifyProcessType.class, Integer.valueOf(0));

  public VerifyProcessType(IEnumValue enumValue) {
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
