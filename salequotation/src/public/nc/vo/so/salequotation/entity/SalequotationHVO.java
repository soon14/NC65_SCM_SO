package nc.vo.so.salequotation.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 在此处简要描述此类的功能 </b>
 * <p>
 * 在此处添加此类的描述信息
 * </p>
 * 创建日期:2009-10-17 11:07:09
 * 
 * @author
 * @version NCPrj ??
 */
public class SalequotationHVO extends SuperVO {

  // 审批人
  public static final String APPROVER = "approver";

  public static final String BILLMAKER = "billmaker";

  // 业务员
  public static final String CEMPLOYEEID = "cemployeeid";

  // 创建时间
  public static final String CREATIONTIME = "creationtime";

  // 创建人
  public static final String CREATOR = "creator";

  // 运输方式
  public static final String CSENDTYPEID = "csendtypeid";

  // 交易类型id
  public static final String CTRANTYPEID = "ctrantypeid";

  // 制单时间
  public static final String DBILLDATE = "dbilldate";

  // 失效日期
  public static final String DENDDATE = "denddate";

  // 报价日期
  public static final String DQUOTEDATE = "dquotedate";

  // dr
  public static final String DR = "dr";

  // 单据状态
  public static final String FSTATUSFLAG = "fstatusflag";

  // 最后修改时间
  public static final String MODIFIEDTIME = "modifiedtime";

  // 最后修改人
  public static final String MODIFIER = "modifier";

  // 整单折扣(%)
  public static final String NDISCOUNT = "ndiscount";

  // 价税合计
  public static final String NTOTALMNY = "ntotalmny";

  // 总数量
  public static final String NTOTALNUM = "ntotalnum";

  // 制单人
  public static final String OPERATOR = "operator";

  // 结算方式
  public static final String PK_BALATYPE = "pk_balatype";

  // 渠道类型
  public static final String PK_CHANNELTYPE = "pk_channeltype";

  // 币种
  public static final String PK_CURRTYPE = "pk_currtype";

  // 客户
  public static final String PK_CUSTOMER = "pk_customer";

  // 部门最新版本
  public static final String PK_DEPT = "pk_dept";

  // 部门
  public static final String PK_DEPT_V = "pk_dept_v";

  // 集团
  public static final String PK_GROUP = "pk_group";

  // 销售组织最新版本
  public static final String PK_ORG = "pk_org";

  // 销售组织
  public static final String PK_ORG_V = "pk_org_v";

  // 收款协议
  public static final String PK_PAYTERM = "pk_payterm";

  // 销售报价单主表主键
  public static final String PK_SALEQUOTATION = "pk_salequotation";

  // 审批时间
  public static final String TAUDITTIME = "taudittime";

  // 时间戳
  public static final String TS = "ts";

  // 报价单号
  public static final String VBILLCODE = "vbillcode";

  // 自定义项1
  public static final String VDEF1 = "vdef1";

  // 自定义项10
  public static final String VDEF10 = "vdef10";

  // 自定义项11
  public static final String VDEF11 = "vdef11";

  // 自定义项12
  public static final String VDEF12 = "vdef12";

  // 自定义项13
  public static final String VDEF13 = "vdef13";

  // 自定义项14
  public static final String VDEF14 = "vdef14";

  // 自定义项15
  public static final String VDEF15 = "vdef15";

  // 自定义项16
  public static final String VDEF16 = "vdef16";

  // 自定义项17
  public static final String VDEF17 = "vdef17";

  // 自定义项18
  public static final String VDEF18 = "vdef18";

  // 自定义项19
  public static final String VDEF19 = "vdef19";

  // 自定义项2
  public static final String VDEF2 = "vdef2";

  // 自定义项20
  public static final String VDEF20 = "vdef20";

  // 自定义项3
  public static final String VDEF3 = "vdef3";

  // 自定义项4
  public static final String VDEF4 = "vdef4";

  // 自定义项5
  public static final String VDEF5 = "vdef5";

  // 自定义项6
  public static final String VDEF6 = "vdef6";

  // 自定义项7
  public static final String VDEF7 = "vdef7";

  // 自定义项8
  public static final String VDEF8 = "vdef8";

  // 自定义项9
  public static final String VDEF9 = "vdef9";

  // 备注
  public static final String VNOTE = "vnote";

  // 报价单类型
  public static final String VTRANTYPE = "vtrantype";

  // 制单日期
  public static final String DMAKEDATE = "dmakedate";

  /**
   * 整单来源单据类型
   */
  public static final String VBILLSRCTYPE = "vbillsrctype";

  /**
   * 整单来源单据ID
   */
  public static final String CBILLSRCID = "cbillsrcid";

  /**
   * 
   */
  private static final long serialVersionUID = -3298693994899701407L;

  /**
   * 按照默认方式创建构造子.
   * 
   * 创建日期:2009-10-17 11:07:09
   */
  public SalequotationHVO() {
    super();
  }

  public String getApprover() {
    return (String) this.getAttributeValue(SalequotationHVO.APPROVER);
  }

  public String getBillmaker() {
    return (String) this.getAttributeValue(SalequotationHVO.BILLMAKER);
  }

  public String getCemployeeid() {
    return (String) this.getAttributeValue(SalequotationHVO.CEMPLOYEEID);
  }

  /**
   * 属性creationtime的Getter方法. 创建日期:2009-10-17 11:07:09
   * 
   * @return nc.vo.pub.lang.UFDateTime
   */
  public nc.vo.pub.lang.UFDateTime getCreationtime() {
    return (nc.vo.pub.lang.UFDateTime) this
        .getAttributeValue(SalequotationHVO.CREATIONTIME);
  }

  public String getCreator() {
    return (String) this.getAttributeValue(SalequotationHVO.CREATOR);
  }

  public String getCsendtypeid() {
    return (String) this.getAttributeValue(SalequotationHVO.CSENDTYPEID);
  }

  public String getCtrantypeid() {
    return (String) this.getAttributeValue(SalequotationHVO.CTRANTYPEID);
  }

  /**
   * 属性dauditdate的Getter方法. 创建日期:2009-10-17 11:07:09
   * 
   * @return nc.vo.pub.lang.UFDate
   */
  public nc.vo.pub.lang.UFDate getDauditdate() {
    return (nc.vo.pub.lang.UFDate) this.getAttributeValue("dauditdate");
  }

  public UFDateTime getDbilldate() {
    return (UFDateTime) this.getAttributeValue(SalequotationHVO.DBILLDATE);
  }

  public UFDate getDenddate() {
    return (UFDate) this.getAttributeValue(SalequotationHVO.DENDDATE);
  }

  public UFDate getDquotedate() {
    return (UFDate) this.getAttributeValue(SalequotationHVO.DQUOTEDATE);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SalequotationHVO.DR);
  }

  public Integer getFstatusflag() {
    return (Integer) this.getAttributeValue(SalequotationHVO.FSTATUSFLAG);
  }

  /**
   * 属性iprintcount的Getter方法. 创建日期:2009-10-17 11:07:09
   * 
   * @return java.lang.Integer
   */
  public java.lang.Integer getIprintcount() {
    return (java.lang.Integer) this.getAttributeValue("iprintcount");
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.SalequotationHVO");
    return meta;
  }

  public UFDateTime getModifiedtime() {
    return (UFDateTime) this.getAttributeValue(SalequotationHVO.MODIFIEDTIME);
  }

  public String getModifier() {
    return (String) this.getAttributeValue(SalequotationHVO.MODIFIER);
  }

  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SalequotationHVO.NDISCOUNT);
  }

  /**
   * 属性nexchangerate的Getter方法. 创建日期:2009-10-17 11:07:09
   * 
   * @return nc.vo.pub.lang.UFDouble
   */
  public nc.vo.pub.lang.UFDouble getNexchangerate() {
    return (nc.vo.pub.lang.UFDouble) this.getAttributeValue("nexchangerate");
  }

  public UFDouble getNtotalmny() {
    return (UFDouble) this.getAttributeValue(SalequotationHVO.NTOTALMNY);
  }

  public UFDouble getNtotalnum() {
    return (UFDouble) this.getAttributeValue(SalequotationHVO.NTOTALNUM);
  }

  // public String getOperator() {
  // return (String) this.getAttributeValue(SalequotationHVO.OPERATOR);
  // }

  /**
   * <p>
   * 取得父VO主键字段.
   * <p>
   * 创建日期:2009-10-17 11:07:09
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getParentPKFieldName() {
    return null;
  }

  public String getPk_balatype() {
    return (String) this.getAttributeValue(SalequotationHVO.PK_BALATYPE);
  }

  public String getPk_channeltype() {
    return (String) this.getAttributeValue(SalequotationHVO.PK_CHANNELTYPE);
  }

  public String getPk_currtype() {
    return (String) this.getAttributeValue(SalequotationHVO.PK_CURRTYPE);
  }

  public String getPk_customer() {
    return (String) this.getAttributeValue(SalequotationHVO.PK_CUSTOMER);
  }

  public String getPk_dept() {
    return (String) this.getAttributeValue(SalequotationHVO.PK_DEPT);
  }

  public String getPk_dept_v() {
    return (String) this.getAttributeValue(SalequotationHVO.PK_DEPT_V);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(SalequotationHVO.PK_GROUP);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(SalequotationHVO.PK_ORG);
  }

  public String getPk_org_v() {
    return (String) this.getAttributeValue(SalequotationHVO.PK_ORG_V);
  }

  public String getPk_payterm() {
    return (String) this.getAttributeValue(SalequotationHVO.PK_PAYTERM);
  }

  public String getPk_salequotation() {
    return (String) this.getAttributeValue(SalequotationHVO.PK_SALEQUOTATION);
  }

  /**
   * <p>
   * 取得表主键.
   * <p>
   * 创建日期:2009-10-17 11:07:09
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getPKFieldName() {
    return "pk_salequotation";
  }

  /**
   * <p>
   * 返回表名称.
   * <p>
   * 创建日期:2009-10-17 11:07:09
   * 
   * @return java.lang.String
   */
  @Override
  public java.lang.String getTableName() {
    return "so_salequotation";
  }

  public UFDate getTaudittime() {
    return (UFDate) this.getAttributeValue(SalequotationHVO.TAUDITTIME);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SalequotationHVO.TS);
  }

  public String getVbillcode() {
    return (String) this.getAttributeValue(SalequotationHVO.VBILLCODE);
  }

  public String getVdef1() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF1);
  }

  public String getVdef10() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF10);
  }

  public String getVdef11() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF11);
  }

  public String getVdef12() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF12);
  }

  public String getVdef13() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF13);
  }

  public String getVdef14() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF14);
  }

  public String getVdef15() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF15);
  }

  public String getVdef16() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF16);
  }

  public String getVdef17() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF17);
  }

  public String getVdef18() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF18);
  }

  public String getVdef19() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF19);
  }

  public String getVdef2() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF2);
  }

  public String getVdef20() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF20);
  }

  public String getVdef3() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF3);
  }

  public String getVdef4() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF4);
  }

  public String getVdef5() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF5);
  }

  public String getVdef6() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF6);
  }

  public String getVdef7() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF7);
  }

  public String getVdef8() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF8);
  }

  public String getVdef9() {
    return (String) this.getAttributeValue(SalequotationHVO.VDEF9);
  }

  public String getVnote() {
    return (String) this.getAttributeValue(SalequotationHVO.VNOTE);
  }

  public String getVtrantype() {
    return (String) this.getAttributeValue(SalequotationHVO.VTRANTYPE);
  }

  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(SalequotationHVO.DMAKEDATE);
  }

  public String getVbillsrctype() {
    return (String) this.getAttributeValue(SalequotationHVO.VBILLSRCTYPE);
  }

  public String getCbillsrcid() {
    return (String) this.getAttributeValue(SalequotationHVO.CBILLSRCID);
  }

  public void setApprover(String approver) {
    this.setAttributeValue(SalequotationHVO.APPROVER, approver);
  }

  public void setBillmaker(String billmaker) {
    this.setAttributeValue(SalequotationHVO.BILLMAKER, billmaker);
  }

  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(SalequotationHVO.CEMPLOYEEID, cemployeeid);
  }

  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(SalequotationHVO.CREATIONTIME, creationtime);
  }

  public void setCreator(String creator) {
    this.setAttributeValue(SalequotationHVO.CREATOR, creator);
  }

  public void setCsendtypeid(String csendtypeid) {
    this.setAttributeValue(SalequotationHVO.CSENDTYPEID, csendtypeid);
  }

  public void setCtrantypeid(String ctrantypeid) {
    this.setAttributeValue(SalequotationHVO.CTRANTYPEID, ctrantypeid);
  }

  /**
   * 属性dauditdate的Setter方法. 创建日期:2009-10-17 11:07:09
   * 
   * @param newDauditdate
   *          nc.vo.pub.lang.UFDate
   */
  public void setDauditdate(nc.vo.pub.lang.UFDate newDauditdate) {
    this.setAttributeValue("dauditdate", newDauditdate);
  }

  public void setDbilldate(UFDateTime dbilldate) {
    this.setAttributeValue(SalequotationHVO.DBILLDATE, dbilldate);
  }

  public void setDenddate(UFDate denddate) {
    this.setAttributeValue(SalequotationHVO.DENDDATE, denddate);
  }

  public void setDquotedate(UFDate dquotedate) {
    this.setAttributeValue(SalequotationHVO.DQUOTEDATE, dquotedate);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SalequotationHVO.DR, dr);
  }

  public void setFstatusflag(Integer fstatusflag) {
    this.setAttributeValue(SalequotationHVO.FSTATUSFLAG, fstatusflag);
  }

  /**
   * 属性iprintcount的Setter方法. 创建日期:2009-10-17 11:07:09
   * 
   * @param newIprintcount
   *          java.lang.Integer
   */
  public void setIprintcount(java.lang.Integer newIprintcount) {
    this.setAttributeValue("iprintcount", newIprintcount);
  }

  public void setModifiedtime(UFDateTime modifiedtime) {
    this.setAttributeValue(SalequotationHVO.MODIFIEDTIME, modifiedtime);
  }

  public void setModifier(String modifier) {
    this.setAttributeValue(SalequotationHVO.MODIFIER, modifier);
  }

  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SalequotationHVO.NDISCOUNT, ndiscount);
  }

  /**
   * 属性nexchangerate的Setter方法. 创建日期:2009-10-17 11:07:09
   * 
   * @param newNexchangerate
   *          nc.vo.pub.lang.UFDouble
   */
  public void setNexchangerate(nc.vo.pub.lang.UFDouble newNexchangerate) {
    this.setAttributeValue("nexchangerate", newNexchangerate);
  }

  public void setNtotalmny(UFDouble ntotalmny) {
    this.setAttributeValue(SalequotationHVO.NTOTALMNY, ntotalmny);
  }

  // public void setOperator(String operator) {
  // this.setAttributeValue(SalequotationHVO.OPERATOR, operator);
  // }

  public void setNtotalnum(UFDouble ntotalnum) {
    this.setAttributeValue(SalequotationHVO.NTOTALNUM, ntotalnum);
  }

  public void setPk_balatype(String pk_balatype) {
    this.setAttributeValue(SalequotationHVO.PK_BALATYPE, pk_balatype);
  }

  public void setPk_channeltype(String pk_channeltype) {
    this.setAttributeValue(SalequotationHVO.PK_CHANNELTYPE, pk_channeltype);
  }

  public void setPk_currtype(String pk_currtype) {
    this.setAttributeValue(SalequotationHVO.PK_CURRTYPE, pk_currtype);
  }

  public void setPk_customer(String pk_customer) {
    this.setAttributeValue(SalequotationHVO.PK_CUSTOMER, pk_customer);
  }

  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(SalequotationHVO.PK_DEPT, pk_dept);
  }

  public void setPk_dept_v(String pk_dept_v) {
    this.setAttributeValue(SalequotationHVO.PK_DEPT_V, pk_dept_v);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(SalequotationHVO.PK_GROUP, pk_group);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(SalequotationHVO.PK_ORG, pk_org);
  }

  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SalequotationHVO.PK_ORG_V, pk_org_v);
  }

  public void setPk_payterm(String pk_payterm) {
    this.setAttributeValue(SalequotationHVO.PK_PAYTERM, pk_payterm);
  }

  public void setPk_salequotation(String pk_salequotation) {
    this.setAttributeValue(SalequotationHVO.PK_SALEQUOTATION, pk_salequotation);
  }

  public void setTaudittime(UFDate taudittime) {
    this.setAttributeValue(SalequotationHVO.TAUDITTIME, taudittime);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SalequotationHVO.TS, ts);
  }

  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(SalequotationHVO.VBILLCODE, vbillcode);
  }

  public void setVdef1(String vdef1) {
    this.setAttributeValue(SalequotationHVO.VDEF1, vdef1);
  }

  public void setVdef10(String vdef10) {
    this.setAttributeValue(SalequotationHVO.VDEF10, vdef10);
  }

  public void setVdef11(String vdef11) {
    this.setAttributeValue(SalequotationHVO.VDEF11, vdef11);
  }

  public void setVdef12(String vdef12) {
    this.setAttributeValue(SalequotationHVO.VDEF12, vdef12);
  }

  public void setVdef13(String vdef13) {
    this.setAttributeValue(SalequotationHVO.VDEF13, vdef13);
  }

  public void setVdef14(String vdef14) {
    this.setAttributeValue(SalequotationHVO.VDEF14, vdef14);
  }

  public void setVdef15(String vdef15) {
    this.setAttributeValue(SalequotationHVO.VDEF15, vdef15);
  }

  public void setVdef16(String vdef16) {
    this.setAttributeValue(SalequotationHVO.VDEF16, vdef16);
  }

  public void setVdef17(String vdef17) {
    this.setAttributeValue(SalequotationHVO.VDEF17, vdef17);
  }

  public void setVdef18(String vdef18) {
    this.setAttributeValue(SalequotationHVO.VDEF18, vdef18);
  }

  public void setVdef19(String vdef19) {
    this.setAttributeValue(SalequotationHVO.VDEF19, vdef19);
  }

  public void setVdef2(String vdef2) {
    this.setAttributeValue(SalequotationHVO.VDEF2, vdef2);
  }

  public void setVdef20(String vdef20) {
    this.setAttributeValue(SalequotationHVO.VDEF20, vdef20);
  }

  public void setVdef3(String vdef3) {
    this.setAttributeValue(SalequotationHVO.VDEF3, vdef3);
  }

  public void setVdef4(String vdef4) {
    this.setAttributeValue(SalequotationHVO.VDEF4, vdef4);
  }

  public void setVdef5(String vdef5) {
    this.setAttributeValue(SalequotationHVO.VDEF5, vdef5);
  }

  public void setVdef6(String vdef6) {
    this.setAttributeValue(SalequotationHVO.VDEF6, vdef6);
  }

  public void setVdef7(String vdef7) {
    this.setAttributeValue(SalequotationHVO.VDEF7, vdef7);
  }

  public void setVdef8(String vdef8) {
    this.setAttributeValue(SalequotationHVO.VDEF8, vdef8);
  }

  public void setVdef9(String vdef9) {
    this.setAttributeValue(SalequotationHVO.VDEF9, vdef9);
  }

  public void setVnote(String vnote) {
    this.setAttributeValue(SalequotationHVO.VNOTE, vnote);
  }

  public void setVtrantype(String vtrantype) {
    this.setAttributeValue(SalequotationHVO.VTRANTYPE, vtrantype);
  }

  public void setDmakedate(UFDate dmakedate) {
    this.setAttributeValue(SalequotationHVO.DMAKEDATE, dmakedate);
  }

  public void setVbillsrctype(String vbillsrctype) {
    this.setAttributeValue(SalequotationHVO.VBILLSRCTYPE, vbillsrctype);
  }

  public void setCbillsrcid(String cbillsrcid) {
    this.setAttributeValue(SalequotationHVO.CBILLSRCID, cbillsrcid);
  }

}
