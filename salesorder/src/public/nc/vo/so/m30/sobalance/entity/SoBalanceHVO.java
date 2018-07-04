package nc.vo.so.m30.sobalance.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SoBalanceHVO extends SuperVO {

  public static final String ENTITYNAME = "so.so_balance";

  // 应收组织
  public static final String CARORGID = "carorgid";

  // 销售渠道类型
  public static final String CCHANNELTYPEID = "cchanneltypeid";

  // 订单客户
  public static final String CCUSTOMERID = "ccustomerid";

  // 部门
  public static final String CDEPTID = "cdeptid";

  // 业务员
  public static final String CEMPLOYEEID = "cemployeeid";

  // 开票客户
  public static final String CINVOICECUSTID = "cinvoicecustid";

  // 币种
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  // 收款协议
  public static final String CPAYTERMID = "cpaytermid";

  // 销售订单主实体
  public static final String CSALEORDERID = "csaleorderid";

  // 订单收款核销主实体
  public static final String CSOBALANCEID = "csobalanceid";

  // dr
  public static final String DR = "dr";

  // 订单已核销金额
  public static final String NTOTALORIGBALMNY = "ntotalorigbalmny";

  // 价税合计
  public static final String NTOTALORIGTAXMNY = "ntotalorigtaxmny";

  // 订单已收款金额
  public static final String NTOTALPAYMNY = "ntotalpaymny";

  // 集团
  public static final String PK_GROUP = "pk_group";

  // 销售组织
  public static final String PK_ORG = "pk_org";

  // 销售组织版本
  public static final String PK_ORG_V = "pk_org_v";

  // 时间戳
  public static final String TS = "ts";

  // 订单号
  public static final String VBILLCODE = "vbillcode";

  // 单据日期
  public static final String DBILLDATE = "dbilldate";

  // 销售订单类型
  public static final String VTRANTYPECODE = "vtrantypecode";

  private static final long serialVersionUID = 5052804469928804398L;

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SoBalanceHVO.ENTITYNAME);
    return meta;
  }

  public String getCarorgid() {
    return (String) this.getAttributeValue(SoBalanceHVO.CARORGID);
  }

  public void setCarorgid(String carorgid) {
    this.setAttributeValue(SoBalanceHVO.CARORGID, carorgid);
  }

  public String getCchanneltypeid() {
    return (String) this.getAttributeValue(SoBalanceHVO.CCHANNELTYPEID);
  }

  public void setCchanneltypeid(String cchanneltypeid) {
    this.setAttributeValue(SoBalanceHVO.CCHANNELTYPEID, cchanneltypeid);
  }

  public String getCcustomerid() {
    return (String) this.getAttributeValue(SoBalanceHVO.CCUSTOMERID);
  }

  public void setCcustomerid(String ccustomerid) {
    this.setAttributeValue(SoBalanceHVO.CCUSTOMERID, ccustomerid);
  }

  public String getCdeptid() {
    return (String) this.getAttributeValue(SoBalanceHVO.CDEPTID);
  }

  public void setCdeptid(String cdeptid) {
    this.setAttributeValue(SoBalanceHVO.CDEPTID, cdeptid);
  }

  public String getCemployeeid() {
    return (String) this.getAttributeValue(SoBalanceHVO.CEMPLOYEEID);
  }

  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(SoBalanceHVO.CEMPLOYEEID, cemployeeid);
  }

  public String getCinvoicecustid() {
    return (String) this.getAttributeValue(SoBalanceHVO.CINVOICECUSTID);
  }

  public void setCinvoicecustid(String cinvoicecustid) {
    this.setAttributeValue(SoBalanceHVO.CINVOICECUSTID, cinvoicecustid);
  }

  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(SoBalanceHVO.CORIGCURRENCYID);
  }

  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(SoBalanceHVO.CORIGCURRENCYID, corigcurrencyid);
  }

  public String getCpaytermid() {
    return (String) this.getAttributeValue(SoBalanceHVO.CPAYTERMID);
  }

  public void setCpaytermid(String cpaytermid) {
    this.setAttributeValue(SoBalanceHVO.CPAYTERMID, cpaytermid);
  }

  public String getCsaleorderid() {
    return (String) this.getAttributeValue(SoBalanceHVO.CSALEORDERID);
  }

  public void setCsaleorderid(String csaleorderid) {
    this.setAttributeValue(SoBalanceHVO.CSALEORDERID, csaleorderid);
  }

  public String getCsobalanceid() {
    return (String) this.getAttributeValue(SoBalanceHVO.CSOBALANCEID);
  }

  public void setCsobalanceid(String csobalanceid) {
    this.setAttributeValue(SoBalanceHVO.CSOBALANCEID, csobalanceid);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SoBalanceHVO.DR);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SoBalanceHVO.DR, dr);
  }

  public UFDouble getNtotalorigbalmny() {
    return (UFDouble) this.getAttributeValue(SoBalanceHVO.NTOTALORIGBALMNY);
  }

  public void setNtotalorigbalmny(UFDouble ntotalorigbalmny) {
    this.setAttributeValue(SoBalanceHVO.NTOTALORIGBALMNY, ntotalorigbalmny);
  }

  public UFDouble getNtotalorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SoBalanceHVO.NTOTALORIGTAXMNY);
  }

  public void setNtotalorigtaxmny(UFDouble ntotalorigtaxmny) {
    this.setAttributeValue(SoBalanceHVO.NTOTALORIGTAXMNY, ntotalorigtaxmny);
  }

  public UFDouble getNtotalpaymny() {
    return (UFDouble) this.getAttributeValue(SoBalanceHVO.NTOTALPAYMNY);
  }

  public void setNtotalpaymny(UFDouble ntotalpaymny) {
    this.setAttributeValue(SoBalanceHVO.NTOTALPAYMNY, ntotalpaymny);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(SoBalanceHVO.PK_GROUP);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(SoBalanceHVO.PK_GROUP, pk_group);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(SoBalanceHVO.PK_ORG);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(SoBalanceHVO.PK_ORG, pk_org);
  }

  public String getPk_org_v() {
    return (String) this.getAttributeValue(SoBalanceHVO.PK_ORG_V);
  }

  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SoBalanceHVO.PK_ORG_V, pk_org_v);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SoBalanceHVO.TS);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SoBalanceHVO.TS, ts);
  }

  public String getVbillcode() {
    return (String) this.getAttributeValue(SoBalanceHVO.VBILLCODE);
  }

  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(SoBalanceHVO.VBILLCODE, vbillcode);
  }

  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SoBalanceHVO.DBILLDATE);
  }

  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SoBalanceHVO.DBILLDATE, dbilldate);
  }

  public String getVtrantypecode() {
    return (String) this.getAttributeValue(SoBalanceHVO.VTRANTYPECODE);
  }

  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(SoBalanceHVO.VTRANTYPECODE, vtrantypecode);
  }

}
