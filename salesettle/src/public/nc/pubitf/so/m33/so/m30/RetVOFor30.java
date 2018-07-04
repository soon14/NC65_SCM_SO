package nc.pubitf.so.m33.so.m30;

import nc.vo.pub.lang.UFBoolean;

public class RetVOFor30 {

  // 订单行id
  private String bid;

  // 是否后应收结算关闭
  private UFBoolean bsquarearfinish;

  // 是否成本结算关闭
  private UFBoolean bsquareiafinish;

  public String getBid() {
    return this.bid;
  }

  public UFBoolean getBsquarearfinish() {
    return this.bsquarearfinish;
  }

  public UFBoolean getBsquareiafinish() {
    return this.bsquareiafinish;
  }

  public void setBid(String bidt) {
    this.bid = bidt;
  }

  public void setBsquarearfinish(UFBoolean bsquarearfinisht) {
    this.bsquarearfinish = bsquarearfinisht;
  }

  public void setBsquareiafinish(UFBoolean bsquareiafinisht) {
    this.bsquareiafinish = bsquareiafinisht;
  }

}
