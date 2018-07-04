package nc.pubitf.so.m4331.so.m30;

import nc.vo.pub.lang.UFDouble;

/**
 * 销售订单价格修订回写发货单价格
 * 
 * @since 6.0
 * @version 2011-3-25 上午11:42:06
 * @author 祝会征
 */
public class RewritePara4331For30PriceChg {

  /**
   * 变化的字段key
   */
  private String key;

  /** 销售订单表体id */
  private String srcBid;

  /** 变化后的价格 */
  private UFDouble price;

  /**
   * 
   * @param cdeliverybid
   * @param outnum
   * @param bclosed
   */
  public RewritePara4331For30PriceChg(String srcBid, UFDouble price, String key) {

    this.srcBid = srcBid;
    this.price = price;
    this.key = key;
  }

  public String getKey() {
    return this.key;
  }

  public String getSrcBid() {
    return this.srcBid;
  }

  public UFDouble getPrice() {
    return this.price;
  }
}
