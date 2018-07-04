package nc.vo.so.depmatrel.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class DepMatRelHVO extends SuperVO {

  /**
   * 
   */
  public static final String ENTITYNAME = "so.so_depmatrel";

  // 允许销售
  public static final String ALLOWSALE = "allowsale";

  // dr
  public static final String DR = "dr";

  // 主实体主键
  public static final String PK_DEPMATREL = "pk_depmatrel";

  // 集团
  public static final String PK_GROUP = "pk_group";

  // 销售组织
  public static final String PK_ORG = "pk_org";

  // 销售组织版本
  public static final String PK_ORG_V = "pk_org_v";

  // 时间戳
  public static final String TS = "ts";

  private static final long serialVersionUID = 7120671732607706910L;

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(DepMatRelHVO.ENTITYNAME);
    return meta;
  }

  public Integer getAllowsale() {
    return (Integer) this.getAttributeValue(DepMatRelHVO.ALLOWSALE);
  }

  public void setAllowsale(Integer allowsale) {
    this.setAttributeValue(DepMatRelHVO.ALLOWSALE, allowsale);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(DepMatRelHVO.DR);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(DepMatRelHVO.DR, dr);
  }

  public String getPk_depmatrel() {
    return (String) this.getAttributeValue(DepMatRelHVO.PK_DEPMATREL);
  }

  public void setPk_depmatrel(String pk_depmatrel) {
    this.setAttributeValue(DepMatRelHVO.PK_DEPMATREL, pk_depmatrel);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(DepMatRelHVO.PK_GROUP);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(DepMatRelHVO.PK_GROUP, pk_group);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(DepMatRelHVO.PK_ORG);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(DepMatRelHVO.PK_ORG, pk_org);
  }

  public String getPk_org_v() {
    return (String) this.getAttributeValue(DepMatRelHVO.PK_ORG_V);
  }

  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(DepMatRelHVO.PK_ORG_V, pk_org_v);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(DepMatRelHVO.TS);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(DepMatRelHVO.TS, ts);
  }

}
