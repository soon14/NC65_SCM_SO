package nc.pubitf.so.m33.arap;

public enum AccountDateType {

  /**
   * 出库日期
   */
  OUT_STORE_DATE(0),

  /**
   * 出库签字日期
   */
  OUTSTORE_SIGNATURE_DATE(1),

  /**
   * 销售合同生效日期
   */
  SALE_CONTRACT_EFFECTIVE_DATE(12),

  /**
   * 销售发票审核日期
   */
  SALE_INVOICE_APPROVE_DATE(3),

  /**
   * 销售开票日期
   */
  SALE_MAKE_BILL_DATE(2),

  /**
   * 销售订单日期
   */
  SALE_ORDER_DATE(11);

  private int code;

  private AccountDateType(int code) {
    this.code = code;
  }

  /**
   * 
   * @return 编码
   */
  public int getCode() {
    return this.code;
  }

}
