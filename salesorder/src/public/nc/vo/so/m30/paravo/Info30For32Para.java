package nc.vo.so.m30.paravo;

import java.io.Serializable;

/**
 * 出库单到发票 需要从订单上补充的数据
 * 
 * @since 6.0
 * @version 2011-10-11 下午02:47:06
 * @author 么贵敬
 */
public class Info30For32Para implements Serializable {
  private static final long serialVersionUID = -7534005225836085119L;

  /** 渠道类型 */
  private String cchanneltypeid;

  /** 合同表体主键 */
  private String cctmanagebid;

  /** 合同表头ID */
  private String cctmanageid;

  /** 开户银行账户 */
  private String ccustbankaccid;

  /** 开户银行 */
  private String ccustbankid;

  /** 散户ID */
  private String cfreecustid;

  /** 收款协议 */
  private String cpaytermid;

  public String getCchanneltypeid() {
    return this.cchanneltypeid;
  }

  public String getCctmanagebid() {
    return this.cctmanagebid;
  }

  public String getCctmanageid() {
    return this.cctmanageid;
  }

  public String getCcustbankaccid() {
    return this.ccustbankaccid;
  }

  public String getCcustbankid() {
    return this.ccustbankid;
  }

  public String getCfreecustid() {
    return this.cfreecustid;
  }

  public String getCpaytermid() {
    return this.cpaytermid;
  }

  public void setCchanneltypeid(String cchanneltypeid) {
    this.cchanneltypeid = cchanneltypeid;
  }

  public void setCctmanagebid(String cctmanagebid) {
    this.cctmanagebid = cctmanagebid;
  }

  public void setCctmanageid(String cctmanageid) {
    this.cctmanageid = cctmanageid;
  }

  public void setCcustbankaccid(String ccustbankaccid) {
    this.ccustbankaccid = ccustbankaccid;
  }

  public void setCcustbankid(String ccustbankid) {
    this.ccustbankid = ccustbankid;
  }

  public void setCfreecustid(String cfreecustid) {
    this.cfreecustid = cfreecustid;
  }

  public void setCpaytermid(String cpaytermid) {
    this.cpaytermid = cpaytermid;
  }

}
