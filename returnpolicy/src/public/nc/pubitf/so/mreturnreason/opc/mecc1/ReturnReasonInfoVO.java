package nc.pubitf.so.mreturnreason.opc.mecc1;

/**
 * IQueryReturnReasonInfo.query接口返回值结构
 * 包含：退货原因id、退货原因编码、退货原因名称
 * 
 * @since 6.0
 * @version 2011-12-28 下午03:00:34
 * @author zhangcheng
 */

public class ReturnReasonInfoVO {
  /**
   * 集团
   */
  public String pk_group;

  /**
   * 业务单元（销售组织）
   */
  public String pk_org;

  /**
   * 退货原因id
   */
  public String pk_returnreason;

  /**
   * 退货原因编码
   */
  public String vreasoncode;

  /**
   * 退货原因名称
   */
  public String vreasonname;

  /**
   * 退货原因名称2
   */
  public String vreasonname2;

  /**
   * 退货原因名称3
   */
  public String vreasonname3;

  /**
   * 退货原因名称4
   */
  public String vreasonname4;

  /**
   * 退货原因名称5
   */
  public String vreasonname5;

  /**
   * 退货原因名称6
   */
  public String vreasonname6;

  public String getPk_group() {
    return this.pk_group;
  }

  public String getPk_org() {
    return this.pk_org;
  }

  public String getPk_returnreason() {
    return this.pk_returnreason;
  }

  public String getVreasoncode() {
    return this.vreasoncode;
  }

  public String getVreasonname() {
    return this.vreasonname;
  }

  public String getVreasonname2() {
    return this.vreasonname2;
  }

  public String getVreasonname3() {
    return this.vreasonname3;
  }

  public String getVreasonname4() {
    return this.vreasonname4;
  }

  public String getVreasonname5() {
    return this.vreasonname5;
  }

  public String getVreasonname6() {
    return this.vreasonname6;
  }

  public void setPk_group(String pk_group) {
    this.pk_group = pk_group;
  }

  public void setPk_org(String pk_org) {
    this.pk_org = pk_org;
  }

  public void setPk_returnreason(String pk_returnreason) {
    this.pk_returnreason = pk_returnreason;
  }

  public void setVreasoncode(String vreasoncode) {
    this.vreasoncode = vreasoncode;
  }

  public void setVreasonname(String vreasonname) {
    this.vreasonname = vreasonname;
  }

  public void setVreasonname2(String vreasonname2) {
    this.vreasonname2 = vreasonname2;
  }

  public void setVreasonname3(String vreasonname3) {
    this.vreasonname3 = vreasonname3;
  }

  public void setVreasonname4(String vreasonname4) {
    this.vreasonname4 = vreasonname4;
  }

  public void setVreasonname5(String vreasonname5) {
    this.vreasonname5 = vreasonname5;
  }

  public void setVreasonname6(String vreasonname6) {
    this.vreasonname6 = vreasonname6;
  }

}
