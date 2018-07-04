package nc.pubitf.so.m4331.opc.mecc1;

/**
 * IQuerySendInfo.query接口返回值结构
 * 包含：发货单表体id、发货联系人id、发货联系电话、要求收货日期
 * 
 * @since 6.0
 * @version 2011-12-28 下午02:34:00
 * @author zhangcheng
 */

public class ReturnSendInfoVO {

  /** 发货单子表ID */
  public String cdeliverybid;

  /** 发货联系人 */
  public String csendpersonid;

  /** 要求收货日期 */
  public String dreceivedate;

  /** 发货联系电话 */
  public String vsendtel;

  public String getCdeliverybid() {
    return this.cdeliverybid;
  }

  public String getCsendpersonid() {
    return this.csendpersonid;
  }

  public String getDreceivedate() {
    return this.dreceivedate;
  }

  public String getVsendtel() {
    return this.vsendtel;
  }

  public void setCdeliverybid(String cdeliverybid) {
    this.cdeliverybid = cdeliverybid;
  }

  public void setCsendpersonid(String csendpersonid) {
    this.csendpersonid = csendpersonid;
  }

  public void setDreceivedate(String dreceivedate) {
    this.dreceivedate = dreceivedate;
  }

  public void setVsendtel(String vsendtel) {
    this.vsendtel = vsendtel;
  }

}
