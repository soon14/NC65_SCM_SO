package nc.vo.so.pub.enumeration;

/**
 * 销售管理单据状态枚举
 * 
 * @since 6.0
 * @version 2011-12-8 下午03:51:10
 * @author 么贵敬
 */
public enum BillStatusEnum {
  AUDIT(BillStatus.AUDIT.getIntegerValue()),
  AUDITING(BillStatus.AUDITING.getIntegerValue()),
  CLOSED(BillStatus.CLOSED.getIntegerValue()),
  FREE(BillStatus.FREE.getIntegerValue()),
  FREEZE(BillStatus.FREEZE.getIntegerValue()),
  INVALIDATE(BillStatus.INVALIDATE.getIntegerValue()),
  NOPASS(BillStatus.NOPASS.getIntegerValue());

  private Integer value;

  private BillStatusEnum(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return this.value;
  }
}
