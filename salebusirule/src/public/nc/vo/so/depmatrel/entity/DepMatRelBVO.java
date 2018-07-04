package nc.vo.so.depmatrel.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class DepMatRelBVO extends SuperVO {

  // 业务员
  public static final String CEMPLOYEEID = "cemployeeid";

  // 优先码
  public static final String CPRIORITYCODE = "cprioritycode";

  // dr
  public static final String DR = "dr";

  public static final String ENTITYNAME = "so.so_depmatrel_b";

  // 不包含
  public static final String EXCLUDE = "exclude";

  // 部门物料关系主实体_主键
  public static final String PK_DEPMATREL = "pk_depmatrel";

  // 子实体主键
  public static final String PK_DEPMATREL_B = "pk_depmatrel_b";

  // 部门
  public static final String PK_DEPT = "pk_dept";

  // 部门版本
  public static final String PK_DEPT_V = "pk_dept_v";

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

  private static final long serialVersionUID = 8946950534221538165L;

  public String getCemployeeid() {
    return (String) this.getAttributeValue(DepMatRelBVO.CEMPLOYEEID);
  }

  public String getCprioritycode() {
    return (String) this.getAttributeValue(DepMatRelBVO.CPRIORITYCODE);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(DepMatRelBVO.DR);
  }

  public UFBoolean getExclude() {
    return (UFBoolean) this.getAttributeValue(DepMatRelBVO.EXCLUDE);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(DepMatRelBVO.ENTITYNAME);
    return meta;
  }

  public String getPk_depmatrel() {
    return (String) this.getAttributeValue(DepMatRelBVO.PK_DEPMATREL);
  }

  public String getPk_depmatrel_b() {
    return (String) this.getAttributeValue(DepMatRelBVO.PK_DEPMATREL_B);
  }

  public String getPk_dept() {
    return (String) this.getAttributeValue(DepMatRelBVO.PK_DEPT);
  }

  public String getPk_dept_v() {
    return (String) this.getAttributeValue(DepMatRelBVO.PK_DEPT_V);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(DepMatRelBVO.PK_GROUP);
  }

  public String getPk_material() {
    return (String) this.getAttributeValue(DepMatRelBVO.PK_MATERIAL);
  }

  public String getPk_material_v() {
    return (String) this.getAttributeValue(DepMatRelBVO.PK_MATERIAL_V);
  }

  public String getPk_materialbaseclass() {
    return (String) this.getAttributeValue(DepMatRelBVO.PK_MATERIALBASECLASS);
  }

  public String getPk_materialsaleclass() {
    return (String) this.getAttributeValue(DepMatRelBVO.PK_MATERIALSALECLASS);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(DepMatRelBVO.PK_ORG);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(DepMatRelBVO.TS);
  }

  public String getVnote() {
    return (String) this.getAttributeValue(DepMatRelBVO.VNOTE);
  }

  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(DepMatRelBVO.CEMPLOYEEID, cemployeeid);
  }

  public void setCprioritycode(String cprioritycode) {
    this.setAttributeValue(DepMatRelBVO.CPRIORITYCODE, cprioritycode);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(DepMatRelBVO.DR, dr);
  }

  public void setExclude(UFBoolean exclude) {
    this.setAttributeValue(DepMatRelBVO.EXCLUDE, exclude);
  }

  public void setPk_depmatrel(String pk_depmatrel) {
    this.setAttributeValue(DepMatRelBVO.PK_DEPMATREL, pk_depmatrel);
  }

  public void setPk_depmatrel_b(String pk_depmatrel_b) {
    this.setAttributeValue(DepMatRelBVO.PK_DEPMATREL_B, pk_depmatrel_b);
  }

  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(DepMatRelBVO.PK_DEPT, pk_dept);
  }

  public void setPk_dept_v(String pk_dept_v) {
    this.setAttributeValue(DepMatRelBVO.PK_DEPT_V, pk_dept_v);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(DepMatRelBVO.PK_GROUP, pk_group);
  }

  public void setPk_material(String pk_material) {
    this.setAttributeValue(DepMatRelBVO.PK_MATERIAL, pk_material);
  }

  public void setPk_material_v(String pk_material_v) {
    this.setAttributeValue(DepMatRelBVO.PK_MATERIAL_V, pk_material_v);
  }

  public void setPk_materialbaseclass(String pk_materialbaseclass) {
    this.setAttributeValue(DepMatRelBVO.PK_MATERIALBASECLASS,
        pk_materialbaseclass);
  }

  public void setPk_materialsaleclass(String pk_materialsaleclass) {
    this.setAttributeValue(DepMatRelBVO.PK_MATERIALSALECLASS,
        pk_materialsaleclass);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(DepMatRelBVO.PK_ORG, pk_org);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(DepMatRelBVO.TS, ts);
  }

  public void setVnote(String vnote) {
    this.setAttributeValue(DepMatRelBVO.VNOTE, vnote);
  }

}
