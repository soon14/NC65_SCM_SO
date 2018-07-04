package nc.vo.so.pub.tolerance;

/**
 * 销售订单放弃检查接口
 * 
 * @since 6.0
 * @version 2011-12-28 下午01:08:02
 * @author fengjb
 */
public interface IAbandonToleranceCheck {
  /**
   * 放弃发货单容差异常检查
   * 
   * @author 祝会征
   * @time 2010-8-31 下午01:41:35
   */
  void abandonDeliveryToleranceCheck();

  /**
   * 放弃销售发票容差异常检查
   * 
   * @author 祝会征
   */
  void abandonInvoinceToleranceCheck();

  /**
   * 放弃销售订单容差异常检查
   * 
   * @author 祝会征
   */
  void abandonOrderToleranceCheck();

  /**
   * 放弃预订单容差异常检查
   * 
   * @author 祝会征
   */
  void abandonPreOrderToleranceCheck();

  /**
   * 放弃销售出库容差异常检查
   * 
   * @author 祝会征
   */
  void abandonOutToleranceCheck();

  /**
   * 放弃合同容差异常检查
   */
  void abandonCtToleranceCheck();

  /**
   * 放弃标准订单容差异常检查
   */
  void abandonCustomerPOToleranceCheck();
}
