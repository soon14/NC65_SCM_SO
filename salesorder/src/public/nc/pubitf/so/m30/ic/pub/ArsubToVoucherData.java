package nc.pubitf.so.m30.ic.pub;

import nc.vo.pub.lang.UFDouble;

/**
 * 库存单据审核或弃审时点客户费用单传凭证，库存提供参数
 * 
 * @since 6.3
 * @version 2014-07-09 11:16:48
 * @author 刘景
 */
public class ArsubToVoucherData {

  /**
   * 订单行id
   */
  private String saleorderbid;

  /**
   * 本次出库主数量或者本次签收主数量
   */
  private UFDouble nnum;

  /**
   * 获取销售订单行id
   * 
   * @return 销售订单行id
   */
  public String getSaleorderbid() {
    return this.saleorderbid;
  }

  /**
   * 
   * 设置销售订单行id
   * 
   * @param saleorderbid
   */
  public void setSaleorderbid(String saleorderbid) {
    this.saleorderbid = saleorderbid;
  }

  /**
   * 
   * 获取本次出库主数量或者本次签收主数量
   * 
   * @return UFDouble
   * 
   */
  public UFDouble getNnum() {
    return this.nnum;
  }

  /**
   * 设置本次出库主数量或者本次签收主数量
   * 
   * @param nnum
   */
  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }

}
