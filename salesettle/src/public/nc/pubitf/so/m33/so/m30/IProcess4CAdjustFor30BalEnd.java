package nc.pubitf.so.m33.so.m30;

public interface IProcess4CAdjustFor30BalEnd {

  /**
   * 方法功能描述：订单结算关闭后出库单做过暂估应收需要生成对应的回冲应收单
   * <p>
   * <b>参数说明</b>
   * 
   * @param ordBids -- 下游出库单做过暂估应收的订单行ID数组
   *          <p>
   * @author zhangcheng
   * @time 2010-8-31 下午03:22:04
   */
  void process4CAdjust(String[] ordBids);

  /**
   * 方法功能描述：订单结算关闭后出库单做过发出商品需要生成贷方发出商品
   * <p>
   * <b>参数说明</b>
   * 
   * @param ordBids -- 下游出库单做过发出商品的订单行ID数组
   *          <p>
   * @author zhangcheng
   * @time 2010-8-31 下午03:22:04
   */
  void process4CReg(String[] ordBids);

  /**
   * 方法功能描述：订单结算打开后出库单做过暂估应收，将生成对应的回冲应收单取消
   * <p>
   * <b>参数说明</b>
   * 
   * @param ordBids -- 下游出库单做过暂估应收的订单行ID数组
   *          <p>
   * @author zhangcheng
   * @time 2010-8-31 下午03:22:04
   */
  void unProcess4CAdjust(String[] ordBids);

  /**
   * 方法功能描述：订单结算打开后出库单做过发出商品，将生成对应的贷方发出商品取消
   * <p>
   * <b>参数说明</b>
   * 
   * @param ordBids -- 下游出库单做过发出商品的订单行ID数组
   *          <p>
   * @author zhangcheng
   * @time 2010-8-31 下午03:22:04
   */
  void unProcess4CReg(String[] ordBids);

}
