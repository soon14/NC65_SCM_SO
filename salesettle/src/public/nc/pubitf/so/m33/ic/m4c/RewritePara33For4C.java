package nc.pubitf.so.m33.ic.m4c;

import nc.vo.pub.lang.UFDouble;

/**
 * IRewriteSquareOutPrice接口参数
 * @since 6.0
 * @version 2011-9-26 下午08:08:17
 * @author zhangcheng
 */
public class RewritePara33For4C {
  /**
   *销售出库单表体ID
   */
  private String coutbid;
 
  /**
   *销售出库单原币无税净价
   */
  private UFDouble norignetprice;

  /**
   *销售出库单原币无税单价
   */
  private UFDouble norigprice;

  /**
   *销售出库单原币含税净价
   */
  private UFDouble norigtaxnetprice;

  /**
   *销售出库单原币含税单价
   */
  private UFDouble norigtaxprice;
  
  /**
   *销售出库单本币无税单价
   */
  private UFDouble nprice;
  
  /**
   *销售出库单本币无税净价
   */
  private UFDouble nnetprice;
  
  /**
   *销售出库单本币含税净价
   */
  private UFDouble ntaxnetprice;

  /**
   *销售出库单本币含税单价
   */
  private UFDouble ntaxprice;

  public String getCoutbid() {
    return this.coutbid;
  }

  public void setCoutbid(String coutbid) {
    this.coutbid = coutbid;
  }

  public UFDouble getNorignetprice() {
    return this.norignetprice;
  }

  public void setNorignetprice(UFDouble norignetprice) {
    this.norignetprice = norignetprice;
  }

  public UFDouble getNorigprice() {
    return this.norigprice;
  }

  public void setNorigprice(UFDouble norigprice) {
    this.norigprice = norigprice;
  }

  public UFDouble getNorigtaxnetprice() {
    return this.norigtaxnetprice;
  }

  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.norigtaxnetprice = norigtaxnetprice;
  }

  public UFDouble getNorigtaxprice() {
    return this.norigtaxprice;
  }

  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.norigtaxprice = norigtaxprice;
  }

  public UFDouble getNprice() {
    return this.nprice;
  }

  public void setNprice(UFDouble nprice) {
    this.nprice = nprice;
  }

  public UFDouble getNnetprice() {
    return this.nnetprice;
  }

  public void setNnetprice(UFDouble nnetprice) {
    this.nnetprice = nnetprice;
  }

  public UFDouble getNtaxnetprice() {
    return this.ntaxnetprice;
  }

  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.ntaxnetprice = ntaxnetprice;
  }

  public UFDouble getNtaxprice() {
    return this.ntaxprice;
  }

  public void setNtaxprice(UFDouble ntaxprice) {
    this.ntaxprice = ntaxprice;
  }
}
