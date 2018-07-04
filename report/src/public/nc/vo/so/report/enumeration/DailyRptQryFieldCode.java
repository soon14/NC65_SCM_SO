package nc.vo.so.report.enumeration;

/**
 * 综合日报查询条件字段枚举类
 * 
 * @since 6.3
 * @version 2013-4-26 下午04:40:39
 * @author tianft
 */
public enum DailyRptQryFieldCode {
  saleOrderStatus("saleorderfstatusflag"), // 销售订单状态
  generalStatus("generalstatusflag"), // 出库单单据状态
  saleInvoiceStatus("saleinvoicefstatusflag"), // 销售发票单据状态
  deliveryStatus("deliveryfstatusflag");// 发货单单据状态

  private String code;

  private DailyRptQryFieldCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }

}
