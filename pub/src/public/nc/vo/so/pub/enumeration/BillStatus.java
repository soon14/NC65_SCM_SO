package nc.vo.so.pub.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * 
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>销售订单、销售发票、销售待结算单、预订单、报价单单据状态枚举类
 * </ul>
 * 
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-1-27 上午11:18:34
 */
public class BillStatus extends MDEnum {

  /**
   * 审批通过
   */
  public static final int I_AUDIT = 2;

  public static final BillStatus AUDIT = MDEnum.valueOf(BillStatus.class,
      I_AUDIT);

  /**
   * 审批中
   */
  public static final int I_AUDITING = 7;

  public static final BillStatus AUDITING = MDEnum.valueOf(BillStatus.class,
      I_AUDITING);

  /**
   * 关闭
   */
  public static final int I_CLOSED = 4;

  public static final BillStatus CLOSED = MDEnum.valueOf(BillStatus.class,
      I_CLOSED);

  /**
   * 自由态
   */
  public static final int I_FREE = 1;

  public static final BillStatus FREE = MDEnum
      .valueOf(BillStatus.class, I_FREE);

  /**
   * 冻结态
   */
  public static final int I_FREEZE = 3;

  public static final BillStatus FREEZE = MDEnum.valueOf(BillStatus.class,
      I_FREEZE);

  /**
   * 单据失效
   */
  public static final int I_INVALIDATE = 5;

  public static final BillStatus INVALIDATE = MDEnum.valueOf(BillStatus.class,
      I_INVALIDATE);

  /**
   * 审批不通过
   */
  public static final int I_NOPASS = 8;

  public static final BillStatus NOPASS = MDEnum.valueOf(BillStatus.class,
      I_NOPASS);

  public BillStatus(IEnumValue enumvalue) {
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
