package nc.pubitf.so.m30.opc.mecc;

import nc.vo.pub.lang.UFBoolean;

/**
 * IQuerySaleOrderIDByMecc.query接口返回值结构
 * 包含：电子销售预订单头ID、电子销售预订单行ID、销售订单头ID、销售订单行ID、出库关闭状态
 * 
 * @since 6.0
 * @version 2012-02-14 下午03:00:34
 * @author 刘景
 */
public class SaleOrderInfoVO {
  /**
   * 出库关闭状态
   */
  private UFBoolean bboutendflag;

  /**
   * 销售订单行ID
   */
  private String csaleorderbid;

  /**
   * 销售订单头ID
   */
  private String csaleorderid;

  /**
   * 电子销售预订单行ID
   */
  private String csrcbid;

  /**
   * 电子销售预订单头ID
   */
  private String csrcid;

  public UFBoolean getBboutendflag() {
    return this.bboutendflag;
  }

  public String getCsaleorderbid() {
    return this.csaleorderbid;
  }

  public String getCsaleorderid() {
    return this.csaleorderid;
  }

  public String getCsrcbid() {
    return this.csrcbid;
  }

  public String getCsrcid() {
    return this.csrcid;
  }

  public void setBboutendflag(UFBoolean bboutendflag) {
    this.bboutendflag = bboutendflag;
  }

  public void setCsaleorderbid(String csaleorderbid) {
    this.csaleorderbid = csaleorderbid;
  }

  public void setCsaleorderid(String csaleorderid) {
    this.csaleorderid = csaleorderid;
  }

  public void setCsrcbid(String csrcbid) {
    this.csrcbid = csrcbid;
  }

  public void setCsrcid(String csrcid) {
    this.csrcid = csrcid;
  }

}
