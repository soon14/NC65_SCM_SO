package nc.pubitf.so.m30.balend;

import nc.vo.so.m30.balend.enumeration.BalEndTrigger;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售订单结算关闭参数
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-7-14 上午09:17:29
 */
public class BalEndPara {

  String[] orderbids;

  BalEndTrigger trigger;

  /**
   * SaleOrderBalEndPara 的构造子
   * 
   * @param orderbids
   * @param trigger
   */
  public BalEndPara(String[] orderbids, BalEndTrigger trigger) {
    this.orderbids = orderbids;
    this.trigger = trigger;
  }

  /**
   * 方法功能描述：返回订单行ID数组。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-14 上午09:24:22
   */
  public String[] getOrderbids() {
    return this.orderbids;
  }

  /**
   * 方法功能描述：返回触发点。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-14 上午09:25:12
   */
  public BalEndTrigger getTrigger() {
    return this.trigger;
  }
}
