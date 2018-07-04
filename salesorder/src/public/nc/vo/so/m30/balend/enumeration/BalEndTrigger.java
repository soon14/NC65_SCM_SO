package nc.vo.so.m30.balend.enumeration;

public enum BalEndTrigger {
  // 出库单确认应收
  OUT_INCOME("4C_INCOME"),
  // 出库单暂估应收
  OUT_ESTAR("4C_EASTAR"),
  // 出库单成本结算
  OUT_COST("4C_COST"),
  // 出库红蓝对冲
  OUT_RUSH("4C_RUSH"),
  // 出库单发出商品
  OUT_REGIST("4C_REGIST"),
  // 订单出库关闭
  OUT_CLOSE("4C_CLOSE"),
  // 出库单删除
  OUT_DELETE("4C_DELETE"),
  // 发票确认应收
  VOICE_INCOME("32_INCOME"),
  // 发票成本结算
  VOICE_COST("32_COST"),
  // 发票回冲应收
  VOICE_RUSH("32_RUSH"),
  // 发票差额传应收
  VOICE_ADJUST("32_ADJUST"),
  // 订单开票关闭
  VOICE_CLOSE("32_CLOSE"),
  // 发票删除
  VOICE_DELETE("32_DELETE"),
  // 途损单签字
  WAST_AUDIT("4453_AUDIT"),
  // 途损单删除
  WAST_DELETE("4453_DELETE");

  private String code;

  /**
   * BalEndTrigger 的构造子
   * 
   * @param trigger
   * @param name
   */
  private BalEndTrigger(
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
