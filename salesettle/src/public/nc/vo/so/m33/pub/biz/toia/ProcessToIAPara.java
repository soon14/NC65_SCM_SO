package nc.vo.so.m33.pub.biz.toia;

public class ProcessToIAPara {

  // 是否折扣
  private boolean bdiscountflag;

  // 是否劳务
  private boolean blaborflag;

  // 财务组织 OID
  private String finorgoid;

  // ID:用于唯一标识ProcessToIAPara对象
  private String id;

  // 物料 VID
  private String materialvid;

  // 出库单交易类型
  private String saleOutTransType;

  // 仓库
  private String stordocid;

  public String getFinorgoid() {
    return this.finorgoid;
  }

  public String getId() {
    return this.id;
  }

  public String getMaterialvid() {
    return this.materialvid;
  }

  public String getSaleOutTransType() {
    return this.saleOutTransType;
  }

  public String getStordocid() {
    return this.stordocid;
  }

  public boolean isBdiscountflag() {
    return this.bdiscountflag;
  }

  public boolean isBlaborflag() {
    return this.blaborflag;
  }

  public void setBdiscountflag(boolean bdiscountflag) {
    this.bdiscountflag = bdiscountflag;
  }

  public void setBlaborflag(boolean blaborflag) {
    this.blaborflag = blaborflag;
  }

  public void setFinorgoid(String finorgoid) {
    this.finorgoid = finorgoid;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setMaterialvid(String materialvid) {
    this.materialvid = materialvid;
  }

  public void setSaleOutTransType(String saleOutTransType) {
    this.saleOutTransType = saleOutTransType;
  }

  public void setStordocid(String stordocid) {
    this.stordocid = stordocid;
  }

}
