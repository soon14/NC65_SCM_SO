package nc.vo.so.custmatrel.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CustMatRelBVO extends SuperVO {

  // 优先码
  public static final String CPRIORITYCODE = "cprioritycode";

  // dr
  public static final String DR = "dr";

  public static final String ENTITYNAME = "so.so_custmatrel_b";

  // 不包含
  public static final String EXCLUDE = "exclude";

  // 客户基本分类
  public static final String PK_CUSTBASECLASS = "pk_custbaseclass";

  // 客户物料关系主实体_主键
  public static final String PK_CUSTMATREL = "pk_custmatrel";

  // 子实体主键
  public static final String PK_CUSTMATREL_B = "pk_custmatrel_b";

  // 客户
  public static final String PK_CUSTOMER = "pk_customer";

  // 客户销售分类
  public static final String PK_CUSTSALECLASS = "pk_custsaleclass";

  /** 集团 */
  public static final String PK_GROUP = "pk_group";

  // 物料最新版本
  public static final String PK_MATERIAL = "pk_material";

  // 物料编码
  public static final String PK_MATERIAL_V = "pk_material_v";

  // 物料基本分类
  public static final String PK_MATERIALBASECLASS = "pk_materialbaseclass";

  // 物料销售分类
  public static final String PK_MATERIALSALECLASS = "pk_materialsaleclass";

  // 销售组织
  public static final String PK_ORG = "pk_org";

  // 时间戳
  public static final String TS = "ts";

  // 行备注
  public static final String VNOTE = "vnote";

  /**
   * 
   */
  private static final long serialVersionUID = 2382524269827876072L;

  public String getCprioritycode() {
    return (String) this.getAttributeValue(CustMatRelBVO.CPRIORITYCODE);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(CustMatRelBVO.DR);
  }

  public UFBoolean getExclude() {
    return (UFBoolean) this.getAttributeValue(CustMatRelBVO.EXCLUDE);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(CustMatRelBVO.ENTITYNAME);
    return meta;
  }

  public String getPk_custbaseclass() {
    return (String) this.getAttributeValue(CustMatRelBVO.PK_CUSTBASECLASS);
  }

  public String getPk_custmatrel() {
    return (String) this.getAttributeValue(CustMatRelBVO.PK_CUSTMATREL);
  }

  public String getPk_custmatrel_b() {
    return (String) this.getAttributeValue(CustMatRelBVO.PK_CUSTMATREL_B);
  }

  public String getPk_customer() {
    return (String) this.getAttributeValue(CustMatRelBVO.PK_CUSTOMER);
  }

  public String getPk_custsaleclass() {
    return (String) this.getAttributeValue(CustMatRelBVO.PK_CUSTSALECLASS);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(CustMatRelBVO.PK_GROUP);
  }

  public String getPk_material() {
    return (String) this.getAttributeValue(CustMatRelBVO.PK_MATERIAL);
  }

  public String getPk_material_v() {
    return (String) this.getAttributeValue(CustMatRelBVO.PK_MATERIAL_V);
  }

  public String getPk_materialbaseclass() {
    return (String) this.getAttributeValue(CustMatRelBVO.PK_MATERIALBASECLASS);
  }

  public String getPk_materialsaleclass() {
    return (String) this.getAttributeValue(CustMatRelBVO.PK_MATERIALSALECLASS);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(CustMatRelBVO.PK_ORG);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(CustMatRelBVO.TS);
  }

  public String getVnote() {
    return (String) this.getAttributeValue(CustMatRelBVO.VNOTE);
  }

  public void setCprioritycode(String cprioritycode) {
    this.setAttributeValue(CustMatRelBVO.CPRIORITYCODE, cprioritycode);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(CustMatRelBVO.DR, dr);
  }

  public void setExclude(UFBoolean exclude) {
    this.setAttributeValue(CustMatRelBVO.EXCLUDE, exclude);
  }

  public void setPk_custbaseclass(String pk_custbaseclass) {
    this.setAttributeValue(CustMatRelBVO.PK_CUSTBASECLASS, pk_custbaseclass);
  }

  public void setPk_custmatrel(String pk_custmatrel) {
    this.setAttributeValue(CustMatRelBVO.PK_CUSTMATREL, pk_custmatrel);
  }

  public void setPk_custmatrel_b(String pk_custmatrel_b) {
    this.setAttributeValue(CustMatRelBVO.PK_CUSTMATREL_B, pk_custmatrel_b);
  }

  public void setPk_customer(String pk_customer) {
    this.setAttributeValue(CustMatRelBVO.PK_CUSTOMER, pk_customer);
  }

  public void setPk_custsaleclass(String pk_custsaleclass) {
    this.setAttributeValue(CustMatRelBVO.PK_CUSTSALECLASS, pk_custsaleclass);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(CustMatRelBVO.PK_GROUP, pk_group);
  }

  public void setPk_material(String pk_material) {
    this.setAttributeValue(CustMatRelBVO.PK_MATERIAL, pk_material);
  }

  public void setPk_material_v(String pk_material_v) {
    this.setAttributeValue(CustMatRelBVO.PK_MATERIAL_V, pk_material_v);
  }

  public void setPk_materialbaseclass(String pk_materialbaseclass) {
    this.setAttributeValue(CustMatRelBVO.PK_MATERIALBASECLASS,
        pk_materialbaseclass);
  }

  public void setPk_materialsaleclass(String pk_materialsaleclass) {
    this.setAttributeValue(CustMatRelBVO.PK_MATERIALSALECLASS,
        pk_materialsaleclass);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(CustMatRelBVO.PK_ORG, pk_org);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(CustMatRelBVO.TS, ts);
  }

  public void setVnote(String vnote) {
    this.setAttributeValue(CustMatRelBVO.VNOTE, vnote);
  }

}
