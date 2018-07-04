package nc.vo.so.mreturncondition.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ReturnConditionVO extends SuperVO {

  /** dr */
  public static final String DR = "dr";

  /** 集团 */
  public static final String PK_GROUP = "pk_group";

  /** 业务单元 */
  public static final String PK_ORG = "pk_org";

  /** 退货条件主键 */
  public static final String PK_RETURNCNDTN = "pk_returncndtn";

  /** 时间戳 */
  public static final String TS = "ts";

  /** 退货条件编码 */
  public static final String VCONDITIONCODE = "vconditioncode";

  /** 退货条件名称 */
  public static final String VCONDITIONNAME = "vconditionname";

  /** 退货条件名称2 */
  public static final String VCONDITIONNAME2 = "vconditionname2";

  /** 退货条件名称3 */
  public static final String VCONDITIONNAME3 = "vconditionname3";

  /** 退货条件名称4 */
  public static final String VCONDITIONNAME4 = "vconditionname4";

  /** 退货条件名称5 */
  public static final String VCONDITIONNAME5 = "vconditionname5";

  /** 退货条件名称6 */
  public static final String VCONDITIONNAME6 = "vconditionname6";

  /** 退货条件表达式编码 */
  public static final String VEXPRESSCODE = "vexpresscode";

  /** 退货条件说明 */
  public static final String VEXPRESSDETAIL = "vexpressdetail";

  /** 退货条件表达式名称 */
  public static final String VEXPRESSNAME = "vexpressname";

  private static final long serialVersionUID = -8677341527228284196L;

  public Integer getDr() {
    return (Integer) this.getAttributeValue(ReturnConditionVO.DR);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.ReturnCondition");
    return meta;
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(ReturnConditionVO.PK_GROUP);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(ReturnConditionVO.PK_ORG);
  }

  public String getPk_returncndtn() {
    return (String) this.getAttributeValue(ReturnConditionVO.PK_RETURNCNDTN);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(ReturnConditionVO.TS);
  }

  public String getVconditioncode() {
    return (String) this.getAttributeValue(ReturnConditionVO.VCONDITIONCODE);
  }

  public String getVconditionname() {
    return (String) this.getAttributeValue(ReturnConditionVO.VCONDITIONNAME);
  }

  public String getVconditionname2() {
    return (String) this.getAttributeValue(ReturnConditionVO.VCONDITIONNAME2);
  }

  public String getVconditionname3() {
    return (String) this.getAttributeValue(ReturnConditionVO.VCONDITIONNAME3);
  }

  public String getVconditionname4() {
    return (String) this.getAttributeValue(ReturnConditionVO.VCONDITIONNAME4);
  }

  public String getVconditionname5() {
    return (String) this.getAttributeValue(ReturnConditionVO.VCONDITIONNAME5);
  }

  public String getVconditionname6() {
    return (String) this.getAttributeValue(ReturnConditionVO.VCONDITIONNAME6);
  }

  public String getVexpresscode() {
    return (String) this.getAttributeValue(ReturnConditionVO.VEXPRESSCODE);
  }

  public String getVexpressdetail() {
    return (String) this.getAttributeValue(ReturnConditionVO.VEXPRESSDETAIL);
  }

  public String getVexpressname() {
    return (String) this.getAttributeValue(ReturnConditionVO.VEXPRESSNAME);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(ReturnConditionVO.DR, dr);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(ReturnConditionVO.PK_GROUP, pk_group);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(ReturnConditionVO.PK_ORG, pk_org);
  }

  public void setPk_returncndtn(String pk_returncndtn) {
    this.setAttributeValue(ReturnConditionVO.PK_RETURNCNDTN, pk_returncndtn);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(ReturnConditionVO.TS, ts);
  }

  public void setVconditioncode(String vconditioncode) {
    this.setAttributeValue(ReturnConditionVO.VCONDITIONCODE, vconditioncode);
  }

  public void setVconditionname(String vconditionname) {
    this.setAttributeValue(ReturnConditionVO.VCONDITIONNAME, vconditionname);
  }

  public void setVconditionname2(String vconditionname2) {
    this.setAttributeValue(ReturnConditionVO.VCONDITIONNAME2, vconditionname2);
  }

  public void setVconditionname3(String vconditionname3) {
    this.setAttributeValue(ReturnConditionVO.VCONDITIONNAME3, vconditionname3);
  }

  public void setVconditionname4(String vconditionname4) {
    this.setAttributeValue(ReturnConditionVO.VCONDITIONNAME4, vconditionname4);
  }

  public void setVconditionname5(String vconditionname5) {
    this.setAttributeValue(ReturnConditionVO.VCONDITIONNAME5, vconditionname5);
  }

  public void setVconditionname6(String vconditionname6) {
    this.setAttributeValue(ReturnConditionVO.VCONDITIONNAME6, vconditionname6);
  }

  public void setVexpresscode(String vexpresscode) {
    this.setAttributeValue(ReturnConditionVO.VEXPRESSCODE, vexpresscode);
  }

  public void setVexpressdetail(String vexpressdetail) {
    this.setAttributeValue(ReturnConditionVO.VEXPRESSDETAIL, vexpressdetail);
  }

  public void setVexpressname(String vexpressname) {
    this.setAttributeValue(ReturnConditionVO.VEXPRESSNAME, vexpressname);
  }

}
