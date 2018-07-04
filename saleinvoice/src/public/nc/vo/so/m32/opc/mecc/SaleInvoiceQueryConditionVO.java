package nc.vo.so.m32.opc.mecc;

import java.io.Serializable;

/**
 * 查询条件VO
 * 
 * @since 6.3
 * @version 2012-10-23 下午12:57:42
 * @author 梁吉明
 */
public class SaleInvoiceQueryConditionVO implements Serializable {

  private static final long serialVersionUID = -872100620100984383L;

  // 订单客户
  private String[] customer;

  // 起始日期
  private String begindate;

  // 截止日期
  private String enddate;

  // 单据状态
  private String[] billstatus;

  public String[] getCustomer() {
    return this.customer;
  }

  public void setCustomer(String[] customer) {
    this.customer = customer;
  }

  public String getBegindate() {
    return this.begindate;
  }

  public void setBegindate(String begindate) {
    this.begindate = begindate;
  }

  public String getEnddate() {
    return this.enddate;
  }

  public void setEnddate(String enddate) {
    this.enddate = enddate;
  }

  public String[] getBillstatus() {
    return this.billstatus;
  }

  public void setBillstatus(String[] billstatus) {
    this.billstatus = billstatus;
  }

}
