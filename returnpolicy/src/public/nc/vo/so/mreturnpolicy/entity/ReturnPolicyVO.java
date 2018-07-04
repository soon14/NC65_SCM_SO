package nc.vo.so.mreturnpolicy.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ReturnPolicyVO extends SuperVO {

  /** 执行结束日期 */
  public static final String DENDDATE = "denddate";

  /** dr */
  public static final String DR = "dr";

  /** 执行开始日期 */
  public static final String DSTARTDATE = "dstartdate";

  /** 集团 */
  public static final String PK_GROUP = "pk_group";

  /** 业务单元 */
  public static final String PK_ORG = "pk_org";

  /** 退货政策主键 */
  public static final String PK_RETURNPOLICY = "pk_returnpolicy";

  /** 时间戳 */
  public static final String TS = "ts";

  /** 退货政策表达式编码 */
  public static final String VEXPRESSCODE = "vexpresscode";

  /** 退货政策逻辑表达式 */
  public static final String VEXPRESSNAME = "vexpressname";

  /** 退货政策编码 */
  public static final String VPOLICYCODE = "vpolicycode";

  /** 退货政策说明 */
  public static final String VPOLICYDETAIL = "vpolicydetail";

  /** 退货政策名称 */
  public static final String VPOLICYNAME = "vpolicyname";

  /** 退货政策名称 2 */
  public static final String VPOLICYNAME2 = "vpolicyname2";

  /** 退货政策名称3 */
  public static final String VPOLICYNAME3 = "vpolicyname3";

  /** 退货政策名称4 */
  public static final String VPOLICYNAME4 = "vpolicyname4";

  /** 退货政策名称5 */
  public static final String VPOLICYNAME5 = "vpolicyname5";

  /** 退货政策名称6 */
  public static final String VPOLICYNAME6 = "vpolicyname6";

  private static final long serialVersionUID = 1L;

  /**
   * ReturnPolicyVO 的构造子
   */
  public ReturnPolicyVO() {
    super();
  }

  public UFDate getDenddate() {
    return (UFDate) this.getAttributeValue(ReturnPolicyVO.DENDDATE);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(ReturnPolicyVO.DR);
  }

  public UFDate getDstartdate() {
    return (UFDate) this.getAttributeValue(ReturnPolicyVO.DSTARTDATE);
  }

  /**
   * 父类方法重写 声明Meta类
   * 
   * @see nc.vo.pub.ISuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.returnpolicy");
    return meta;
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(ReturnPolicyVO.PK_GROUP);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(ReturnPolicyVO.PK_ORG);
  }

  public String getPk_returnpolicy() {
    return (String) this.getAttributeValue(ReturnPolicyVO.PK_RETURNPOLICY);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(ReturnPolicyVO.TS);
  }

  public String getVexpresscode() {
    return (String) this.getAttributeValue(ReturnPolicyVO.VEXPRESSCODE);
  }

  public String getVexpressname() {
    return (String) this.getAttributeValue(ReturnPolicyVO.VEXPRESSNAME);
  }

  public String getVpolicycode() {
    return (String) this.getAttributeValue(ReturnPolicyVO.VPOLICYCODE);
  }

  public String getVpolicydetail() {
    return (String) this.getAttributeValue(ReturnPolicyVO.VPOLICYDETAIL);
  }

  public String getVpolicyname() {
    return (String) this.getAttributeValue(ReturnPolicyVO.VPOLICYNAME);
  }

  public String getVpolicyname2() {
    return (String) this.getAttributeValue(ReturnPolicyVO.VPOLICYNAME2);
  }

  public String getVpolicyname3() {
    return (String) this.getAttributeValue(ReturnPolicyVO.VPOLICYNAME3);
  }

  public String getVpolicyname4() {
    return (String) this.getAttributeValue(ReturnPolicyVO.VPOLICYNAME4);
  }

  public String getVpolicyname5() {
    return (String) this.getAttributeValue(ReturnPolicyVO.VPOLICYNAME5);
  }

  public String getVpolicyname6() {
    return (String) this.getAttributeValue(ReturnPolicyVO.VPOLICYNAME6);
  }

  public void setDenddate(UFDate denddate) {
    this.setAttributeValue(ReturnPolicyVO.DENDDATE, denddate);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(ReturnPolicyVO.DR, dr);
  }

  public void setDstartdate(UFDate dstartdate) {
    this.setAttributeValue(ReturnPolicyVO.DSTARTDATE, dstartdate);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(ReturnPolicyVO.PK_GROUP, pk_group);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(ReturnPolicyVO.PK_ORG, pk_org);
  }

  public void setPk_returnpolicy(String pk_returnpolicy) {
    this.setAttributeValue(ReturnPolicyVO.PK_RETURNPOLICY, pk_returnpolicy);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(ReturnPolicyVO.TS, ts);
  }

  public void setVexpresscode(String vexpresscode) {
    this.setAttributeValue(ReturnPolicyVO.VEXPRESSCODE, vexpresscode);
  }

  public void setVexpressname(String vexpressname) {
    this.setAttributeValue(ReturnPolicyVO.VEXPRESSNAME, vexpressname);
  }

  public void setVpolicycode(String vpolicycode) {
    this.setAttributeValue(ReturnPolicyVO.VPOLICYCODE, vpolicycode);
  }

  public void setVpolicydetail(String vpolicydetail) {
    this.setAttributeValue(ReturnPolicyVO.VPOLICYDETAIL, vpolicydetail);
  }

  public void setVpolicyname(String vpolicyname) {
    this.setAttributeValue(ReturnPolicyVO.VPOLICYNAME, vpolicyname);
  }

  public void setVpolicyname2(String vpolicyname2) {
    this.setAttributeValue(ReturnPolicyVO.VPOLICYNAME2, vpolicyname2);
  }

  public void setVpolicyname3(String vpolicyname3) {
    this.setAttributeValue(ReturnPolicyVO.VPOLICYNAME3, vpolicyname3);
  }

  public void setVpolicyname4(String vpolicyname4) {
    this.setAttributeValue(ReturnPolicyVO.VPOLICYNAME4, vpolicyname4);
  }

  public void setVpolicyname5(String vpolicyname5) {
    this.setAttributeValue(ReturnPolicyVO.VPOLICYNAME5, vpolicyname5);
  }

  public void setVpolicyname6(String vpolicyname6) {
    this.setAttributeValue(ReturnPolicyVO.VPOLICYNAME6, vpolicyname6);
  }

}
