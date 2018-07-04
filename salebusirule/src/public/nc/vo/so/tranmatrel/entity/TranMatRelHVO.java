package nc.vo.so.tranmatrel.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class TranMatRelHVO extends SuperVO {

  /**
   * 
   */
  public static final String ENTITYNAME = "so.so_tranmatrel";

  // 允许销售
  public static final String ALLOWSALE = "allowsale";

  // 适用下级
  public static final String APPLYLOWER = "applylower";

  // dr
  public static final String DR = "dr";

  // 集团
  public static final String PK_GROUP = "pk_group";

  // 销售组织
  public static final String PK_ORG = "pk_org";

  // 销售组织版本
  public static final String PK_ORG_V = "pk_org_v";

  // 主实体主键
  public static final String PK_TRANMATREL = "pk_tranmatrel";

  // 时间戳
  public static final String TS = "ts";

  private static final long serialVersionUID = 7167765764615400960L;

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(TranMatRelHVO.ENTITYNAME);
    return meta;
  }

  public Integer getAllowsale() {
    return (Integer) this.getAttributeValue(TranMatRelHVO.ALLOWSALE);
  }

  public void setAllowsale(Integer allowsale) {
    this.setAttributeValue(TranMatRelHVO.ALLOWSALE, allowsale);
  }

  public UFBoolean getApplylower() {
    return (UFBoolean) this.getAttributeValue(TranMatRelHVO.APPLYLOWER);
  }

  public void setApplylower(UFBoolean applylower) {
    this.setAttributeValue(TranMatRelHVO.APPLYLOWER, applylower);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(TranMatRelHVO.DR);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(TranMatRelHVO.DR, dr);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(TranMatRelHVO.PK_GROUP);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(TranMatRelHVO.PK_GROUP, pk_group);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(TranMatRelHVO.PK_ORG);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(TranMatRelHVO.PK_ORG, pk_org);
  }

  public String getPk_org_v() {
    return (String) this.getAttributeValue(TranMatRelHVO.PK_ORG_V);
  }

  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(TranMatRelHVO.PK_ORG_V, pk_org_v);
  }

  public String getPk_tranmatrel() {
    return (String) this.getAttributeValue(TranMatRelHVO.PK_TRANMATREL);
  }

  public void setPk_tranmatrel(String pk_tranmatrel) {
    this.setAttributeValue(TranMatRelHVO.PK_TRANMATREL, pk_tranmatrel);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(TranMatRelHVO.TS);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(TranMatRelHVO.TS, ts);
  }

}
