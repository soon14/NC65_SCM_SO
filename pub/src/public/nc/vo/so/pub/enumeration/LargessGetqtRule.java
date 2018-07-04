package nc.vo.so.pub.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * 赠品取价规则
 * （价格0/询销售价/参考售价/最低售价/参考成本）
 * 
 * @version 6.0
 * @author 刘志伟
 */
public class LargessGetqtRule extends MDEnum {

  /** 询销售价 :按照正常订单的报价规则进行取价 */
  public static final LargessGetqtRule ASK_SALEQT = MDEnum.valueOf(
      LargessGetqtRule.class, Integer.valueOf(2));

  /** 参考成本 :取物料档案销售组织页签上的参考成本 */
  public static final LargessGetqtRule MARSSORG_COSETQT = MDEnum.valueOf(
      LargessGetqtRule.class, Integer.valueOf(5));

  /** 最低售价 :取物料档案销售组织页签上最近售价 */
  public static final LargessGetqtRule MARSSORG_LOWQT = MDEnum.valueOf(
      LargessGetqtRule.class, Integer.valueOf(4));

  /** 参考售价:物料档案销售组织页签上的参考售价 */
  public static final LargessGetqtRule MARSSORG_REQT = MDEnum.valueOf(
      LargessGetqtRule.class, Integer.valueOf(3));

  /** 价格0 :订单上赠品行物料的价格为0 */
  public static final LargessGetqtRule ZERO_QT = MDEnum.valueOf(
      LargessGetqtRule.class, Integer.valueOf(1));

  public LargessGetqtRule(IEnumValue enumValue) {
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
