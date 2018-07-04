package nc.vo.so.m30.balend.enumeration;

public enum BalOpenTrigger {
  // 出库单取消应收结算
  OUT_NOINCOME("4C_NOINCOME"),
  // 出库单取消成本结算
  OUT_NOCOST("4C_NOCOST"),
  // 取消出库红蓝对冲
  OUT_NORUSH("4C_NORUSH"),
  // 订单出库打开
  OUT_OPEN("4C_OPEN"),
  // 发票取消应收结算
  VOICE_NOINCOME("32_NOINCOME"),
  // 发票取消成本结算
  VOICE_NOCOST("32_NOCOST"),
  // 订单开票打开
  VOICE_OPEN("32_OPEN"),
  // 途损单弃审
  WAST_UNAUDIT("4453_UNAUDIT"),
  // 途损单新增
  WAST_ADD("4453_ADD");

  private String code;

  /**
   * BalEndTrigger 的构造子
   * 
   * @param trigger
   * @param name
   */
  private BalOpenTrigger(
      String code) {
    this.code = code;
  }

  /**
   * 方法功能描述：返回订单结算关闭触发点。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-14 上午10:59:08
   */
  public String getCode() {
    return this.code;
  }
}
