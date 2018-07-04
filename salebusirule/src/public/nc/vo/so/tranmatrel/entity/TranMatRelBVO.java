package nc.vo.so.tranmatrel.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class TranMatRelBVO extends SuperVO {

  // 优先码
  public static final String CPRIORITYCODE = "cprioritycode";

  // dr
  public static final String DR = "dr";

  public static final String ENTITYNAME = "so.so_tranmatrel_b";

  // 不包含
  public static final String EXCLUDE = "exclude";

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

  // 订单类型物料关系主实体_主键
  public static final String PK_TRANMATREL = "pk_tranmatrel";

  // 子实体主键
  public static final String PK_TRANMATREL_B = "pk_tranmatrel_b";

  // 订单类型
  public static final String TRANTYPE = "trantype";

  // 时间戳
  public static final String TS = "ts";

  // 行备注
  public static final String VNOTE = "vnote";

  private static final long serialVersionUID = -1112148828109118702L;

  public String getCprioritycode() {
    return (String) this.getAttributeValue(TranMatRelBVO.CPRIORITYCODE);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(TranMatRelBVO.DR);
  }

  public UFBoolean getExclude() {
    return (UFBoolean) this.getAttributeValue(TranMatRelBVO.EXCLUDE);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(TranMatRelBVO.ENTITYNAME);
    return meta;
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(TranMatRelBVO.PK_GROUP);
  }

  public String getPk_material() {
    return (String) this.getAttributeValue(TranMatRelBVO.PK_MATERIAL);
  }

  public String getPk_material_v() {
    return (String) this.getAttributeValue(TranMatRelBVO.PK_MATERIAL_V);
  }

  public String getPk_materialbaseclass() {
    return (String) this.getAttributeValue(TranMatRelBVO.PK_MATERIALBASECLASS);
  }

  public String getPk_materialsaleclass() {
    return (String) this.getAttributeValue(TranMatRelBVO.PK_MATERIALSALECLASS);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(TranMatRelBVO.PK_ORG);
  }

  public String getPk_tranmatrel() {
    return (String) this.getAttributeValue(TranMatRelBVO.PK_TRANMATREL);
  }

  public String getPk_tranmatrel_b() {
    return (String) this.getAttributeValue(TranMatRelBVO.PK_TRANMATREL_B);
  }

  public String getTrantype() {
    return (String) this.getAttributeValue(TranMatRelBVO.TRANTYPE);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(TranMatRelBVO.TS);
  }

  public String getVnote() {
    return (String) this.getAttributeValue(TranMatRelBVO.VNOTE);
  }

  public void setCprioritycode(String cprioritycode) {
    this.setAttributeValue(TranMatRelBVO.CPRIORITYCODE, cprioritycode);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(TranMatRelBVO.DR, dr);
  }

  public void setExclude(UFBoolean exclude) {
    this.setAttributeValue(TranMatRelBVO.EXCLUDE, exclude);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(TranMatRelBVO.PK_GROUP, pk_group);
  }

  public void setPk_material(String pk_material) {
    this.setAttributeValue(TranMatRelBVO.PK_MATERIAL, pk_material);
  }

  public void setPk_material_v(String pk_material_v) {
    this.setAttributeValue(TranMatRelBVO.PK_MATERIAL_V, pk_material_v);
  }

  public void setPk_materialbaseclass(String pk_materialbaseclass) {
    this.setAttributeValue(TranMatRelBVO.PK_MATERIALBASECLASS,
        pk_materialbaseclass);
  }

  public void setPk_materialsaleclass(String pk_materialsaleclass) {
    this.setAttributeValue(TranMatRelBVO.PK_MATERIALSALECLASS,
        pk_materialsaleclass);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(TranMatRelBVO.PK_ORG, pk_org);
  }

  public void setPk_tranmatrel(String pk_tranmatrel) {
    this.setAttributeValue(TranMatRelBVO.PK_TRANMATREL, pk_tranmatrel);
  }

  public void setPk_tranmatrel_b(String pk_tranmatrel_b) {
    this.setAttributeValue(TranMatRelBVO.PK_TRANMATREL_B, pk_tranmatrel_b);
  }

  public void setTrantype(String trantype) {
    this.setAttributeValue(TranMatRelBVO.TRANTYPE, trantype);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(TranMatRelBVO.TS, ts);
  }

  public void setVnote(String vnote) {
    this.setAttributeValue(TranMatRelBVO.VNOTE, vnote);
  }

}
