package nc.pubitf.so.m33.arap;

import nc.vo.pub.lang.UFDouble;

/**
 * 销售结算监听财务核销接口使用到的vo结构
 * 
 * @since 6.0
 * @version 2011-9-2 上午11:16:41
 * @author zhangcheng
 */
public class SqCtrlARVerifyVO {
  /**
   * 销售待结算单行id
   */
  private String csalesquarebid;

  /**
   * 销售结算单据行id（出库单、销售发票行id）
   */
  private String csquarebillbid;

  /**
   * 财务核销金额
   */
  private UFDouble npayBillmny;

  public String getCsalesquarebid() {
    return this.csalesquarebid;
  }

  public String getCsquarebillbid() {
    return this.csquarebillbid;
  }

  public UFDouble getNpayBillmny() {
    return this.npayBillmny;
  }

  public void setCsalesquarebid(String csalesquarebid) {
    this.csalesquarebid = csalesquarebid;
  }

  public void setCsquarebillbid(String csquarebillbid) {
    this.csquarebillbid = csquarebillbid;
  }

  public void setNpayBillmny(UFDouble npayBillmny) {
    this.npayBillmny = npayBillmny;
  }

}
