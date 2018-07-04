package nc.pubitf.so.m30.balend;

import nc.vo.so.m30.balend.enumeration.BalOpenTrigger;

public class BalOpenPara {

  String[] orderbids;

  BalOpenTrigger trigger;

  /**
   * SaleOrderBalEndPara 的构造子
   * 
   * @param orderbids
   * @param trigger
   */
  public BalOpenPara(String[] orderbids, BalOpenTrigger trigger) {
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
  public BalOpenTrigger getTrigger() {
    return this.trigger;
  }

}
