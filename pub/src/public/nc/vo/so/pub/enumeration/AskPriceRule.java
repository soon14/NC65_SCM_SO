package nc.vo.so.pub.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * 询价规则
 * （不询价/正常询价/询非促销价）
 * 
 * @version 6.0
 * @author 刘志伟
 */
public class AskPriceRule extends MDEnum {

  /** 不询价 */
  public static final AskPriceRule ASKPRICE_NO = MDEnum.valueOf(
      AskPriceRule.class, Integer.valueOf(1));

  /** 正常询价 */
  public static final AskPriceRule ASKPRICE_NORMAL = MDEnum.valueOf(
      AskPriceRule.class, Integer.valueOf(2));

  /** 询非促销价 */
  public static final AskPriceRule ASKPRICE_UNSPROMOTION = MDEnum.valueOf(
      AskPriceRule.class, Integer.valueOf(3));

  public AskPriceRule(IEnumValue enumValue) {
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
