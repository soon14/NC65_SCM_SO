package nc.vo.so.m30trantype.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * 直运类型标记
 * （非直运/直运采购/直运调拨）
 * 
 * @version 6.0
 * @author 刘志伟
 */
public class DirectType extends MDEnum {

  /** 非直运 */
  public static final DirectType DIRECTTRAN_NO = MDEnum.valueOf(
      DirectType.class, Integer.valueOf(1));

  /** 直运采购 */
  public static final DirectType DIRECTTRAN_PO = MDEnum.valueOf(
      DirectType.class, Integer.valueOf(2));

  /** 直运调拨 */
  public static final DirectType DIRECTTRAN_TO = MDEnum.valueOf(
      DirectType.class, Integer.valueOf(3));

  public DirectType(IEnumValue enumValue) {
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
