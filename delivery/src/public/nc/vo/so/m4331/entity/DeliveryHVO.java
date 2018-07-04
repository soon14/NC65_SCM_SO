package nc.vo.so.m4331.entity;

import nc.vo.credit.creditaudit.entity.CreditAuditHVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class DeliveryHVO extends SuperVO {

  /** 审批人 */
  public static final String APPROVER = "approver";

  /** 制单人 */
  public static final String BILLMAKER = "billmaker";

  /** 业务流程 */
  public static final String CBIZTYPEID = "cbiztypeid";

  /** 发货单主表ID */
  public static final String CDELIVERYID = "cdeliveryid";

  /** 制单时间 */
  public static final String CREATIONTIME = "creationtime";

  /** 创建人 */
  public static final String CREATOR = "creator";

  /** 发货部门 */
  public static final String CSENDDEPTID = "csenddeptid";

  /** 发货部门版本 */
  public static final String CSENDDEPTVID = "csenddeptvid";

  /** 发货计划员 */
  public static final String CSENDEMPLOYEEID = "csendemployeeid";

  /**
   * 贸易术语（61）
   */
  public static final String CTRADEWORDID = "ctradewordid";

  /** 运输路线 */
  public static final String CTRANSPORTROUTEID = "ctransportrouteid";

  /** 运输方式 */
  public static final String CTRANSPORTTYPEID = "ctransporttypeid";

  /** 发货类型 */
  public static final String CTRANTYPEID = "ctrantypeid";

  /** 单据日期 */
  public static final String DBILLDATE = "dbilldate";

  /** 制单日期 */
  public static final String DMAKEDATE = "dmakedate";

  /** 状态 */
  public static final String FSTATUSFLAG = "fstatusflag";

  /** 打印次数 */
  public static final String IPRINTCOUNT = "iprintcount";

  /** 最后修改时间 */
  public static final String MODIFIEDTIME = "modifiedtime";

  /** 最后修改人 */
  public static final String MODIFIER = "modifier";

  /** 总数量 */
  public static final String NTOTALASTNUM = "ntotalastnum";

  /** 总件数 */
  public static final String NTOTALPIECE = "ntotalpiece";

  /** 总体积 */
  public static final String NTOTALVOLUME = "ntotalvolume";

  /** 总重量 */
  public static final String NTOTALWEIGHT = "ntotalweight";

  /** 集团 */
  public static final String PK_GROUP = "pk_group";

  /** 物流组织 */
  public static final String PK_ORG = "pk_org";

  /** 物流组织版本 */
  public static final String PK_ORG_V = "pk_org_v";

  private static final long serialVersionUID = 1L;

  /** 审批时间 */
  public static final String TAUDITTIME = "taudittime";

  /** 时间戳 */
  public static final String TS = "ts";

  /** 单据号 */
  public static final String VBILLCODE = "vbillcode";

  /** 自定义项1 */
  public static final String VDEF1 = "vdef1";

  /** 自定义项10 */
  public static final String VDEF10 = "vdef10";

  /** 自定义项11 */
  public static final String VDEF11 = "vdef11";

  /** 自定义项12 */
  public static final String VDEF12 = "vdef12";

  /** 自定义项13 */
  public static final String VDEF13 = "vdef13";

  /** 自定义项14 */
  public static final String VDEF14 = "vdef14";

  /** 自定义项15 */
  public static final String VDEF15 = "vdef15";

  /** 自定义项16 */
  public static final String VDEF16 = "vdef16";

  /** 自定义项17 */
  public static final String VDEF17 = "vdef17";

  /** 自定义项18 */
  public static final String VDEF18 = "vdef18";

  /** 自定义项19 */
  public static final String VDEF19 = "vdef19";

  /** 自定义项2 */
  public static final String VDEF2 = "vdef2";

  /** 自定义项20 */
  public static final String VDEF20 = "vdef20";

  /** 自定义项3 */
  public static final String VDEF3 = "vdef3";

  /** 自定义项4 */
  public static final String VDEF4 = "vdef4";

  /** 自定义项5 */
  public static final String VDEF5 = "vdef5";

  /** 自定义项6 */
  public static final String VDEF6 = "vdef6";

  /** 自定义项7 */
  public static final String VDEF7 = "vdef7";

  /** 自定义项8 */
  public static final String VDEF8 = "vdef8";

  /** 自定义项9 */
  public static final String VDEF9 = "vdef9";

  /** 备注 */
  public static final String VNOTE = "vnote";

  /** 发货类型 */
  public static final String VTRANTYPECODE = "vtrantypecode";

  public DeliveryHVO() {
    super();
  }

  public String getApprover() {
    return (String) this.getAttributeValue(DeliveryHVO.APPROVER);
  }

  public String getBillmaker() {
    return (String) this.getAttributeValue(DeliveryHVO.BILLMAKER);
  }

  public String getCbiztypeid() {
    return (String) this.getAttributeValue(DeliveryHVO.CBIZTYPEID);
  }

  public String getCdeliveryid() {
    return (String) this.getAttributeValue(DeliveryHVO.CDELIVERYID);
  }

  public String getCsenddeptid() {
    return (String) this.getAttributeValue(DeliveryHVO.CSENDDEPTID);
  }

  public String getCsenddeptvid() {
    return (String) this.getAttributeValue(DeliveryHVO.CSENDDEPTVID);
  }

  public String getCsendemployeeid() {
    return (String) this.getAttributeValue(DeliveryHVO.CSENDEMPLOYEEID);
  }

  public UFDateTime getCreationtime() {
    return (UFDateTime) this.getAttributeValue(DeliveryHVO.CREATIONTIME);
  }

  public String getCreator() {
    return (String) this.getAttributeValue(DeliveryHVO.CREATOR);
  }

  /**
   * 获取贸易术语
   *
   * @return 贸易术语
   */
  public String getCtradewordid() {
    return (String) this.getAttributeValue(DeliveryHVO.CTRADEWORDID);
  }

  public String getCtransportrouteid() {
    return (String) this.getAttributeValue(DeliveryHVO.CTRANSPORTROUTEID);
  }

  public String getCtransporttypeid() {
    return (String) this.getAttributeValue(DeliveryHVO.CTRANSPORTTYPEID);
  }

  public String getCtrantypeid() {
    return (String) this.getAttributeValue(DeliveryHVO.CTRANTYPEID);
  }

  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(DeliveryHVO.DBILLDATE);
  }

  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(CreditAuditHVO.DMAKEDATE);
  }

  public Integer getFstatusflag() {
    return (Integer) this.getAttributeValue(DeliveryHVO.FSTATUSFLAG);
  }

  public Integer getIprintcount() {
    return (Integer) this.getAttributeValue(DeliveryHVO.IPRINTCOUNT);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.delivery");
    return meta;
  }

  public UFDateTime getModifiedtime() {
    return (UFDateTime) this.getAttributeValue(DeliveryHVO.MODIFIEDTIME);
  }

  public String getModifier() {
    return (String) this.getAttributeValue(DeliveryHVO.MODIFIER);
  }

  public UFDouble getNtotalastnum() {
    return (UFDouble) this.getAttributeValue(DeliveryHVO.NTOTALASTNUM);
  }

  public UFDouble getNtotalpiece() {
    return (UFDouble) this.getAttributeValue(DeliveryHVO.NTOTALPIECE);
  }

  public UFDouble getNtotalvolume() {
    return (UFDouble) this.getAttributeValue(DeliveryHVO.NTOTALVOLUME);
  }

  public UFDouble getNtotalweight() {
    return (UFDouble) this.getAttributeValue(DeliveryHVO.NTOTALWEIGHT);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(DeliveryHVO.PK_GROUP);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(DeliveryHVO.PK_ORG);
  }

  public String getPk_org_v() {
    return (String) this.getAttributeValue(DeliveryHVO.PK_ORG_V);
  }

  public UFDate getTaudittime() {
    return (UFDate) this.getAttributeValue(DeliveryHVO.TAUDITTIME);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(DeliveryHVO.TS);
  }

  public String getVbillcode() {
    return (String) this.getAttributeValue(DeliveryHVO.VBILLCODE);
  }

  public String getVdef1() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF1);
  }

  public String getVdef10() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF10);
  }

  public String getVdef11() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF11);
  }

  public String getVdef12() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF12);
  }

  public String getVdef13() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF13);
  }

  public String getVdef14() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF14);
  }

  public String getVdef15() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF15);
  }

  public String getVdef16() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF16);
  }

  public String getVdef17() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF17);
  }

  public String getVdef18() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF18);
  }

  public String getVdef19() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF19);
  }

  public String getVdef2() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF2);
  }

  public String getVdef20() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF20);
  }

  public String getVdef3() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF3);
  }

  public String getVdef4() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF4);
  }

  public String getVdef5() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF5);
  }

  public String getVdef6() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF6);
  }

  public String getVdef7() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF7);
  }

  public String getVdef8() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF8);
  }

  public String getVdef9() {
    return (String) this.getAttributeValue(DeliveryHVO.VDEF9);
  }

  public String getVnote() {
    return (String) this.getAttributeValue(DeliveryHVO.VNOTE);
  }

  public String getVtrantypecode() {
    return (String) this.getAttributeValue(DeliveryHVO.VTRANTYPECODE);
  }

  public void setApprover(String approver) {
    this.setAttributeValue(DeliveryHVO.APPROVER, approver);
  }

  public void setBillmaker(String billmaker) {
    this.setAttributeValue(DeliveryHVO.BILLMAKER, billmaker);
  }

  public void setCbiztypeid(String cbiztypeid) {
    this.setAttributeValue(DeliveryHVO.CBIZTYPEID, cbiztypeid);
  }

  public void setCdeliveryid(String cdeliveryid) {
    this.setAttributeValue(DeliveryHVO.CDELIVERYID, cdeliveryid);
  }

  public void setCsenddeptid(String csenddeptid) {
    this.setAttributeValue(DeliveryHVO.CSENDDEPTID, csenddeptid);
  }

  public void setCsenddeptvid(String csenddeptvid) {
    this.setAttributeValue(DeliveryHVO.CSENDDEPTVID, csenddeptvid);
  }

  public void setCsendemployeeid(String csendemployeeid) {
    this.setAttributeValue(DeliveryHVO.CSENDEMPLOYEEID, csendemployeeid);
  }

  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(DeliveryHVO.CREATIONTIME, creationtime);
  }

  public void setCreator(String creator) {
    this.setAttributeValue(DeliveryHVO.CREATOR, creator);
  }

  /**
   * 设置贸易数据
   *
   * @param ctradewordid 贸易属于
   */
  public void setCtradewordid(String ctradewordid) {
    this.setAttributeValue(DeliveryHVO.CTRADEWORDID, ctradewordid);
  }

  public void setCtransportrouteid(String ctransportrouteid) {
    this.setAttributeValue(DeliveryHVO.CTRANSPORTROUTEID, ctransportrouteid);
  }

  public void setCtransporttypeid(String ctransporttypeid) {
    this.setAttributeValue(DeliveryHVO.CTRANSPORTTYPEID, ctransporttypeid);
  }

  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(DeliveryHVO.CTRANTYPEID, ctrantypeid);
  }

  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(DeliveryHVO.DBILLDATE, dbilldate);
  }

  public void setDmakedate(UFDate dmakedate) {
    this.setAttributeValue(CreditAuditHVO.DMAKEDATE, dmakedate);
  }

  public void setFstatusflag(Integer fstatusflag) {
    this.setAttributeValue(DeliveryHVO.FSTATUSFLAG, fstatusflag);
  }

  public void setIprintcount(Integer iprintcount) {
    this.setAttributeValue(DeliveryHVO.IPRINTCOUNT, iprintcount);
  }

  public void setModifiedtime(UFDateTime modifiedtime) {
    this.setAttributeValue(DeliveryHVO.MODIFIEDTIME, modifiedtime);
  }

  public void setModifier(String modifier) {
    this.setAttributeValue(DeliveryHVO.MODIFIER, modifier);
  }

  public void setNtotalastnum(UFDouble ntotalastnum) {
    this.setAttributeValue(DeliveryHVO.NTOTALASTNUM, ntotalastnum);
  }

  public void setNtotalpiece(UFDouble ntotalpiece) {
    this.setAttributeValue(DeliveryHVO.NTOTALPIECE, ntotalpiece);
  }

  public void setNtotalvolume(UFDouble ntotalvolume) {
    this.setAttributeValue(DeliveryHVO.NTOTALVOLUME, ntotalvolume);
  }

  public void setNtotalweight(UFDouble ntotalweight) {
    this.setAttributeValue(DeliveryHVO.NTOTALWEIGHT, ntotalweight);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(DeliveryHVO.PK_GROUP, pk_group);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(DeliveryHVO.PK_ORG, pk_org);
  }

  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(DeliveryHVO.PK_ORG_V, pk_org_v);
  }

  public void setTaudittime(UFDate taudittime) {
    this.setAttributeValue(DeliveryHVO.TAUDITTIME, taudittime);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(DeliveryHVO.TS, ts);
  }

  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(DeliveryHVO.VBILLCODE, vbillcode);
  }

  public void setVdef1(String vdef1) {
    this.setAttributeValue(DeliveryHVO.VDEF1, vdef1);
  }

  public void setVdef10(String vdef10) {
    this.setAttributeValue(DeliveryHVO.VDEF10, vdef10);
  }

  public void setVdef11(String vdef11) {
    this.setAttributeValue(DeliveryHVO.VDEF11, vdef11);
  }

  public void setVdef12(String vdef12) {
    this.setAttributeValue(DeliveryHVO.VDEF12, vdef12);
  }

  public void setVdef13(String vdef13) {
    this.setAttributeValue(DeliveryHVO.VDEF13, vdef13);
  }

  public void setVdef14(String vdef14) {
    this.setAttributeValue(DeliveryHVO.VDEF14, vdef14);
  }

  public void setVdef15(String vdef15) {
    this.setAttributeValue(DeliveryHVO.VDEF15, vdef15);
  }

  public void setVdef16(String vdef16) {
    this.setAttributeValue(DeliveryHVO.VDEF16, vdef16);
  }

  public void setVdef17(String vdef17) {
    this.setAttributeValue(DeliveryHVO.VDEF17, vdef17);
  }

  public void setVdef18(String vdef18) {
    this.setAttributeValue(DeliveryHVO.VDEF18, vdef18);
  }

  public void setVdef19(String vdef19) {
    this.setAttributeValue(DeliveryHVO.VDEF19, vdef19);
  }

  public void setVdef2(String vdef2) {
    this.setAttributeValue(DeliveryHVO.VDEF2, vdef2);
  }

  public void setVdef20(String vdef20) {
    this.setAttributeValue(DeliveryHVO.VDEF20, vdef20);
  }

  public void setVdef3(String vdef3) {
    this.setAttributeValue(DeliveryHVO.VDEF3, vdef3);
  }

  public void setVdef4(String vdef4) {
    this.setAttributeValue(DeliveryHVO.VDEF4, vdef4);
  }

  public void setVdef5(String vdef5) {
    this.setAttributeValue(DeliveryHVO.VDEF5, vdef5);
  }

  public void setVdef6(String vdef6) {
    this.setAttributeValue(DeliveryHVO.VDEF6, vdef6);
  }

  public void setVdef7(String vdef7) {
    this.setAttributeValue(DeliveryHVO.VDEF7, vdef7);
  }

  public void setVdef8(String vdef8) {
    this.setAttributeValue(DeliveryHVO.VDEF8, vdef8);
  }

  public void setVdef9(String vdef9) {
    this.setAttributeValue(DeliveryHVO.VDEF9, vdef9);
  }

  public void setVnote(String vnote) {
    this.setAttributeValue(DeliveryHVO.VNOTE, vnote);
  }

  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(DeliveryHVO.VTRANTYPECODE, vtrantypecode);
  }

}
