package nc.vo.so.m33.pub.exchange;

import java.io.Serializable;

import nc.vo.pub.AggregatedValueObject;

public class ExchangeSourceVo implements Serializable {

  private static final long serialVersionUID = 6470723854276401302L;

  private AggregatedValueObject[] bills;

  private String destBillType;

  private String pk_group;

  private String srcBillTransType;

  private String srcBillType;

  public AggregatedValueObject[] getBills() {
    return this.bills;
  }

  public String getDestBillType() {
    return this.destBillType;
  }

  public String getPk_group() {
    return this.pk_group;
  }

  public String getSrcBillTransType() {
    return this.srcBillTransType;
  }

  public String getSrcBillType() {
    return this.srcBillType;
  }

  public void setBills(AggregatedValueObject[] bills) {
    this.bills = bills;
  }

  public void setDestBillType(String destBillType) {
    this.destBillType = destBillType;
  }

  public void setPk_group(String pk_group) {
    this.pk_group = pk_group;
  }

  public void setSrcBillTransType(String srcBillTransType) {
    this.srcBillTransType = srcBillTransType;
  }

  public void setSrcBillType(String srcBillType) {
    this.srcBillType = srcBillType;
  }

}
