package nc.itf.so.orderprofit;

import nc.pub.smart.context.SmartContext;

/**
 * 订单毛利分析接口
 * 
 * @since 6.0
 * @version 2011-3-24 下午01:44:36
 * @author 么贵敬
 */
public interface IOrderProfitMaintain {

  /**
   * 创建临时表返回临时表表名
   * 
   * @param content
   * @return
   */
  String getTempName(SmartContext content);

}
