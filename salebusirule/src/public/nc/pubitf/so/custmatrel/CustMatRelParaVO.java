package nc.pubitf.so.custmatrel;

import java.util.HashMap;
import java.util.Map;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class CustMatRelParaVO extends CircularlyAccessibleValueObject {
  // 允许销售
  public static final String ALLOWSALE = "allowsale";

  // 优先码
  public static final String CPRIORITYCODE = "cprioritycode";

  /** 销售订单表体行号 */
  public static final String CROWNO = "crowno";

  // 不包含
  public static final String EXCLUDE = "exclude";

  public static final String PARAINDEX = "paraindex";

  // 客户基本分类
  public static final String PK_CUSTBASECLASS = "pk_custbaseclass";

  // 子实体主键
  public static final String PK_CUSTMATREL_B = "pk_custmatrel_b";

  // 客户
  public static final String PK_CUSTOMER = "pk_customer";

  // 客户销售分类
  public static final String PK_CUSTSALECLASS = "pk_custsaleclass";

  // 物料
  public static final String PK_MATERIAL = "pk_material";

  // 物料基本分类
  public static final String PK_MATERIALBASECLASS = "pk_materialbaseclass";

  // 物料销售分类
  public static final String PK_MATERIALSALECLASS = "pk_materialsaleclass";

  // 销售组织
  public static final String PK_ORG = "pk_org";

  private static final long serialVersionUID = -8279083368487381072L;

  private Map<String, Object> mapTemp;

  public CustMatRelParaVO() {
    super();
    this.mapTemp = new HashMap<String, Object>();
  }

  public Integer getAllowsale() {
    return (Integer) this.getAttributeValue(CustMatRelParaVO.ALLOWSALE);
  }

  @Override
  public String[] getAttributeNames() {
    return new String[] {
      CustMatRelParaVO.PK_ORG, CustMatRelParaVO.PK_MATERIAL,
      CustMatRelParaVO.PK_CUSTMATREL_B, CustMatRelParaVO.PK_CUSTOMER,
      CustMatRelParaVO.ALLOWSALE, CustMatRelParaVO.EXCLUDE,
      CustMatRelParaVO.PARAINDEX, CustMatRelParaVO.CROWNO,
      CustMatRelParaVO.CPRIORITYCODE, CustMatRelParaVO.PK_CUSTBASECLASS,
      CustMatRelParaVO.PK_CUSTSALECLASS, CustMatRelParaVO.PK_MATERIALBASECLASS,
      CustMatRelParaVO.PK_MATERIALSALECLASS
    };
  }

  @Override
  public Object getAttributeValue(String attributeName) {
    return this.mapTemp.get(attributeName);
  }

  public String getCprioritycode() {
    return (String) this.getAttributeValue(CustMatRelParaVO.CPRIORITYCODE);
  }

  public String getCrowno() {
    return (String) this.getAttributeValue(CustMatRelParaVO.CROWNO);
  }

  @Override
  public String getEntityName() {
    return null;
  }

  public UFBoolean getExclude() {
    return (UFBoolean) this.getAttributeValue(CustMatRelParaVO.EXCLUDE);
  }

  public Integer getParaindex() {
    return (Integer) this.getAttributeValue(CustMatRelParaVO.PARAINDEX);
  }

  public String getPk_custbaseclass() {
    return (String) this.getAttributeValue(CustMatRelParaVO.PK_CUSTBASECLASS);
  }

  public String getPk_custmatrel_b() {
    return (String) this.getAttributeValue(CustMatRelParaVO.PK_CUSTMATREL_B);
  }

  public String getPk_customer() {
    return (String) this.getAttributeValue(CustMatRelParaVO.PK_CUSTOMER);
  }

  public String getPk_custsaleclass() {
    return (String) this.getAttributeValue(CustMatRelParaVO.PK_CUSTSALECLASS);
  }

  public String getPk_material() {
    return (String) this.getAttributeValue(CustMatRelParaVO.PK_MATERIAL);
  }

  public String getPk_materialbaseclass() {
    return (String) this
        .getAttributeValue(CustMatRelParaVO.PK_MATERIALBASECLASS);
  }

  public String getPk_materialsaleclass() {
    return (String) this
        .getAttributeValue(CustMatRelParaVO.PK_MATERIALSALECLASS);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(CustMatRelParaVO.PK_ORG);
  }

  public void setAllowsale(Integer allowsale) {
    this.setAttributeValue(CustMatRelParaVO.ALLOWSALE, allowsale);
  }

  @Override
  public void setAttributeValue(String attributeName, Object attributeValue) {
    this.mapTemp.put(attributeName, attributeValue);
  }

  public void setCprioritycode(String code) {
    this.setAttributeValue(CustMatRelParaVO.CPRIORITYCODE, code);
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(CustMatRelParaVO.CROWNO, crowno);
  }

  public void setExclude(UFBoolean exclude) {
    this.setAttributeValue(CustMatRelParaVO.EXCLUDE, exclude);
  }

  public void setParaindex(Integer paraindex) {
    this.setAttributeValue(CustMatRelParaVO.PARAINDEX, paraindex);
  }

  public void setPk_custbaseclass(String pk_custbaseclass) {
    this.setAttributeValue(CustMatRelParaVO.PK_CUSTBASECLASS, pk_custbaseclass);
  }

  public void setPk_custmatrel_b(String pk_custmatrel_b) {
    this.setAttributeValue(CustMatRelParaVO.PK_CUSTMATREL_B, pk_custmatrel_b);
  }

  public void setPk_customer(String pk_customer) {
    this.setAttributeValue(CustMatRelParaVO.PK_CUSTOMER, pk_customer);
  }

  public void setPk_custsaleclass(String pk_custsaleclass) {
    this.setAttributeValue(CustMatRelParaVO.PK_CUSTSALECLASS, pk_custsaleclass);
  }

  public void setPk_material(String pk_material) {
    this.setAttributeValue(CustMatRelParaVO.PK_MATERIAL, pk_material);
  }

  public void setPk_materialbaseclass(String pk_materialbaseclass) {
    this.setAttributeValue(CustMatRelParaVO.PK_MATERIALBASECLASS,
        pk_materialbaseclass);
  }

  public void setPk_materialsaleclass(String pk_materialsaleclass) {
    this.setAttributeValue(CustMatRelParaVO.PK_MATERIALSALECLASS,
        pk_materialsaleclass);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(CustMatRelParaVO.PK_ORG, pk_org);
  }

  @Override
  public void validate() throws ValidationException {
    StringBuilder validateitem = new StringBuilder();
    if (null == this.getPk_org()) {
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006007_0", "04006007-0024")/* 销售组织*/);
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0", "04006007-0025")/*、*/);
    }
    if (PubAppTool.isNull(this.getPk_material())) {
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006007_0", "04006007-0026")/* 物料 */);
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0", "04006007-0025")/*、*/);
    }
    if (PubAppTool.isNull(this.getPk_customer())) {
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006007_0", "04006007-0023")/* 客户*/);
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0", "04006007-0025")/*、*/);
    }
    if (validateitem.length() > 0) {
      validateitem.deleteCharAt(validateitem.length() - 1);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006007_0", "04006007-0008")/*@res "客户物料关系检查时，下列项目不允许为空："*/
          + validateitem.toString());
    }
  }
}
