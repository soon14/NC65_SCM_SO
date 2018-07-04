package nc.vo.so.m30.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

public class Largesstype extends MDEnum {
  // 未分摊
  public static final Largesstype NOAPPORTION = MDEnum.valueOf(
      Largesstype.class, Integer.valueOf(1));

  // 被分摊物料
  public static final Largesstype APPORTIONMATERIAL = MDEnum.valueOf(
      Largesstype.class, Integer.valueOf(2));

  // 分摊赠品
  public static final Largesstype APPORTIONLARGESS = MDEnum.valueOf(
      Largesstype.class, Integer.valueOf(3));

  public Largesstype(IEnumValue enumvalue) {
    super(enumvalue);
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
