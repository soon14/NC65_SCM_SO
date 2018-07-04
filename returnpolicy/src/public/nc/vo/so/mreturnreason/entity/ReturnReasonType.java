package nc.vo.so.mreturnreason.entity;

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
public class ReturnReasonType extends MDEnum {
  // 新产品退货
  public static final ReturnReasonType RETURN_NEWPRODUCT = MDEnum.valueOf(
      ReturnReasonType.class, Integer.valueOf(2));

  // 其他退货
  public static final ReturnReasonType RETURN_OTHER = MDEnum.valueOf(
      ReturnReasonType.class, Integer.valueOf(4));

  // 质量退货
  public static final ReturnReasonType RETURN_QUALITY = MDEnum.valueOf(
      ReturnReasonType.class, Integer.valueOf(1));

  // 配额能正常退货
  public static final ReturnReasonType RETURN_QUOTA = MDEnum.valueOf(
      ReturnReasonType.class, Integer.valueOf(3));

  public ReturnReasonType(IEnumValue enumvalue) {
    super(enumvalue);
  }

}
