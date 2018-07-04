package nc.vo.so.m30.balend.enumeration;


public enum VirtualBalType {

  // 出库单已结算
  BALED(0),

  // 出库单半结算(即销售出库单下游发票已经结算或者销售出库单做过出库对冲已经无法再结算)
  HALFBAL(1),

  // 出库单未结算
  NOTBAL(2);

  private Integer code;

  private VirtualBalType(int code) {
    this.code = Integer.valueOf(code);
  }

  public Integer getCode() {
    return this.code;
  }
}
