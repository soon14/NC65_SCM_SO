package nc.vo.so.pub.enumeration;

/**
 * 销售管理组件枚举
 * 
 * @since 6.0
 * @version 2012-5-17 上午09:55:19
 * @author 么贵敬
 */
public enum SOComponent {
  /**
   * 销售费用单
   */
  Arsub("m35"),

  /**
   * 发货单
   */
  Delivery("m4331"),

  /**
   * 销售发票
   */
  Invoice("m32"),

  /**
   * 销售订单
   */
  Order("m30"),

  /**
   * 预订单
   */
  PreOrder("m38"),

  /**
   * 销售报价单
   */
  SaleQuotation("m4310"),

  /**
   * 退货管理
   */
  Returnpolicy("returnpolicy");

  // 组件编码
  private String component;

  /**
   * 获得组件
   * 
   * @return 组件名
   */
  public String getComponent() {
    return this.component;
  }

  private SOComponent(String component) {
    this.component = component;
  }
}
