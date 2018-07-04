package nc.pubitf.so.m30.sobalance;

import java.io.Serializable;
import java.util.List;
/**
  * 必选维度：开票客户、应收组织、币种
  * 可选维度：结算财务组织、产品线、销售订单类型、销售渠道类型、销售组织、销售部门、销售业务员、订单客户。
  * 其中，结算财务组织、产品线可能有多个值，按或的逻辑进行查询。
*/

public class ArGatherbillQueryCondition implements Serializable {

  private static final long serialVersionUID = -6731200231233064192L;

  private String ccustomerid;
  private String cinvoicecustid;
  private String carorgid;
  private String corigcurrencyid;
  private List<String> csettleorgids;
  private List<String> cprodlineids;
  private String vtrantypecode;
  private String cchanneltypeid;
  private String pk_org;
  private String cdeptid;
  private String cemployeeid;
  
  public String getCcustomerid() {
    return this.ccustomerid;
  }
  public void setCcustomerid(String ccustomerid) {
    this.ccustomerid = ccustomerid;
  }
  public String getCinvoicecustid() {
    return this.cinvoicecustid;
  }
  public void setCinvoicecustid(String cinvoicecustid) {
    this.cinvoicecustid = cinvoicecustid;
  }
  public String getCarorgid() {
    return this.carorgid;
  }
  public void setCarorgid(String carorgid) {
    this.carorgid = carorgid;
  }
  public String getCorigcurrencyid() {
    return this.corigcurrencyid;
  }
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.corigcurrencyid = corigcurrencyid;
  }
  public List<String> getCsettleorgids() {
    return this.csettleorgids;
  }
  public void setCsettleorgids(List<String> csettleorgids) {
    this.csettleorgids = csettleorgids;
  }
  public List<String> getCprodlineids() {
    return this.cprodlineids;
  }
  public void setCprodlineids(List<String> cprodlineids) {
    this.cprodlineids = cprodlineids;
  }
  public String getVtrantypecode() {
    return this.vtrantypecode;
  }
  public void setVtrantypecode(String vtrantypecode) {
    this.vtrantypecode = vtrantypecode;
  }
  public String getCchanneltypeid() {
    return this.cchanneltypeid;
  }
  public void setCchanneltypeid(String cchanneltypeid) {
    this.cchanneltypeid = cchanneltypeid;
  }
  public String getPk_org() {
    return this.pk_org;
  }
  public void setPk_org(String pk_org) {
    this.pk_org = pk_org;
  }
  public String getCdeptid() {
    return this.cdeptid;
  }
  public void setCdeptid(String cdeptid) {
    this.cdeptid = cdeptid;
  }
  public String getCemployeeid() {
    return this.cemployeeid;
  }
  public void setCemployeeid(String cemployeeid) {
    this.cemployeeid = cemployeeid;
  }
  
  
}
