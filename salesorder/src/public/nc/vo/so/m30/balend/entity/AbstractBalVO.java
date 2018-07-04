package nc.vo.so.m30.balend.entity;

import nc.vo.pub.lang.UFBoolean;

public abstract class AbstractBalVO {

  protected UFBoolean bautocost;

  protected UFBoolean bautoincome;

  protected String orderbid;

  protected String orderid;

  public String getOrderid() {
    return this.orderid;
  }

  public void setOrderid(String orderid) {
    this.orderid = orderid;
  }

  protected Integer preartype;

  protected Integer preiatype;

  public abstract String getBalbilltype();

  public UFBoolean getBautocost() {
    if (null == this.bautocost) {
      return UFBoolean.FALSE;
    }
    return this.bautocost;
  }

  public UFBoolean getBautoincome() {
    if (null == this.bautoincome) {
      return UFBoolean.FALSE;
    }
    return this.bautoincome;
  }

  public String getOrderbid() {
    return this.orderbid;
  }

  public Integer getPreartype() {
    return this.preartype;
  }

  public Integer getPreiatype() {
    return this.preiatype;
  }

  public abstract boolean isCostbal();

  public abstract boolean isIncomebal();

  public void setBautocost(UFBoolean bautocost) {
    this.bautocost = bautocost;
  }

  public void setBautoincome(UFBoolean bautoincome) {
    this.bautoincome = bautoincome;
  }

  public void setOrderbid(String orderbid) {
    this.orderbid = orderbid;
  }

  public void setPreartype(Integer preartype) {
    this.preartype = preartype;
  }

  public void setPreiatype(Integer preiatype) {
    this.preiatype = preiatype;
  }

}
