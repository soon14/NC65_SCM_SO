package nc.vo.so.custmatrel.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class CustMatRelHVO extends SuperVO {

  /**
   * 
   */
  public static final String ENTITYNAME = "so.so_custmatrel";

  // 允许销售
  public static final String ALLOWSALE = "allowsale";

  // dr
  public static final String DR = "dr";

  // 主实体主键
  public static final String PK_CUSTMATREL = "pk_custmatrel";

  // 集团
  public static final String PK_GROUP = "pk_group";

  // 销售组织
  public static final String PK_ORG = "pk_org";

  // 销售组织版本
  public static final String PK_ORG_V = "pk_org_v";

  // 客户基本分类
  public static final String PK_CUSTBASECLASS = "pk_custbaseclass";

  // 客户销售分类
  public static final String PK_CUSTSALECLASS = "pk_custsaleclass";
  
  // 客户编码
  public static final String PK_CUSTOMER = "pk_customer";
  
  // 物料基本分类
  public static final String PK_MATERIALBASECLASS = "pk_materialbaseclass";
  
  // 物料销售分类
  public static final String PK_MATERIALSALECLASS = "pk_materialsaleclass";
  
  // 物料
  public static final String PK_MATERIAL = "pk_material";
  
  
  private String pk_custbaseclass;
  
  private String pk_custsaleclass;
  
  private String pk_customer;
  
  private String pk_materialbaseclass;
  
  private String pk_materialsaleclass;
  
  private String pk_material;


  // 时间戳
  public static final String TS = "ts";

  private static final long serialVersionUID = -5398312439222182926L;

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(CustMatRelHVO.ENTITYNAME);
    return meta;
  }

  public Integer getAllowsale() {
    return (Integer) this.getAttributeValue(CustMatRelHVO.ALLOWSALE);
  }

  public void setAllowsale(Integer allowsale) {
    this.setAttributeValue(CustMatRelHVO.ALLOWSALE, allowsale);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(CustMatRelHVO.DR);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(CustMatRelHVO.DR, dr);
  }

  public String getPk_custmatrel() {
    return (String) this.getAttributeValue(CustMatRelHVO.PK_CUSTMATREL);
  }

  public void setPk_custmatrel(String pk_custmatrel) {
    this.setAttributeValue(CustMatRelHVO.PK_CUSTMATREL, pk_custmatrel);
  }

  public String getPk_custbaseclass() {
    return this.pk_custbaseclass;
  }

  
  public void setPk_custbaseclass(String pk_custbaseclass) {
    this.pk_custbaseclass = pk_custbaseclass;
  }

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
  
  public String getPk_custsaleclass() {
    return this.pk_custsaleclass;
  }

  
  public void setPk_custsaleclass(String pk_custsaleclass) {
    this.pk_custsaleclass = pk_custsaleclass;
  }

  
	public String getPk_customer() {
		return this.pk_customer;
	}

	public void setPk_customer(String pk_customer) {
		this.pk_customer = pk_customer;
	}
	  
	public String getPk_materialbaseclass() {
		return this.pk_materialbaseclass;
	}

	public void setPk_materialbaseclass(String pk_materialbaseclass) {
		this.pk_materialbaseclass = pk_materialbaseclass;
	}

	public String getPk_materialsaleclass() {
		return this.pk_materialsaleclass;
	}
	
	public void setPk_materialsaleclass(String pk_materialsaleclass) {
		this.pk_materialsaleclass = pk_materialsaleclass;
	}

	public String getPk_material() {
		return this.pk_material;
	}

	public void setPk_material(String pk_material) {
		this.pk_material = pk_material;
	}

	public String getPk_group() {
		return (String) this.getAttributeValue(CustMatRelHVO.PK_GROUP);
	}

  public void setPk_group(String pk_group) {
    this.setAttributeValue(CustMatRelHVO.PK_GROUP, pk_group);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(CustMatRelHVO.PK_ORG);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(CustMatRelHVO.PK_ORG, pk_org);
  }

  public String getPk_org_v() {
    return (String) this.getAttributeValue(CustMatRelHVO.PK_ORG_V);
  }

  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(CustMatRelHVO.PK_ORG_V, pk_org_v);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(CustMatRelHVO.TS);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(CustMatRelHVO.TS, ts);
  }

}
