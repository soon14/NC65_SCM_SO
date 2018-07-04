package nc.pubitf.so.deptmatrel;

import java.util.HashMap;
import java.util.Map;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

public class DeptMatRelParaVO extends CircularlyAccessibleValueObject {
  // 允许销售
  public static final String ALLOWSALE = "allowsale";

  // 业务员
  public static final String CEMPLOYEEID = "cemployeeid";

  /** 销售订单表体行号 */
  public static final String CROWNO = "crowno";

  // 不包含
  public static final String EXCLUDE = "exclude";

  public static final String PARAINDEX = "paraindex";
  
  //优先码
  public static final String CPRIORITYCODE = "cprioritycode";

  // 主实体主键
  public static final String PK_DEPMATREL = "pk_depmatrel";

  // 子实体主键
  public static final String PK_DEPMATREL_B = "pk_depmatrel_b";

  // 部门
  public static final String PK_DEPT = "pk_dept";

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

  public DeptMatRelParaVO() {
    super();
    this.mapTemp = new HashMap<String, Object>();
  }

  public Integer getAllowsale() {
    return (Integer) this.getAttributeValue(DeptMatRelParaVO.ALLOWSALE);
  }

  @Override
  public String[] getAttributeNames() {
    return new String[] {
      DeptMatRelParaVO.PK_ORG, DeptMatRelParaVO.PK_DEPMATREL,
      DeptMatRelParaVO.PK_MATERIAL, DeptMatRelParaVO.PK_DEPMATREL_B,
      DeptMatRelParaVO.PK_DEPT, DeptMatRelParaVO.ALLOWSALE,
      DeptMatRelParaVO.EXCLUDE, DeptMatRelParaVO.PARAINDEX,DeptMatRelParaVO.CPRIORITYCODE,
      DeptMatRelParaVO.CROWNO, DeptMatRelParaVO.CEMPLOYEEID,
      DeptMatRelParaVO.PK_MATERIALBASECLASS,
      DeptMatRelParaVO.PK_MATERIALSALECLASS
    };
  }

  @Override
  public Object getAttributeValue(String attributeName) {
    return this.mapTemp.get(attributeName);
  }

  public String getCemployeeid() {
    return (String) this.getAttributeValue(DeptMatRelParaVO.CEMPLOYEEID);
  }

  public String getCrowno() {
    return (String) this.getAttributeValue(DeptMatRelParaVO.CROWNO);
  }

  @Override
  public String getEntityName() {
    return null;
  }

  public UFBoolean getExclude() {
    return (UFBoolean) this.getAttributeValue(DeptMatRelParaVO.EXCLUDE);
  }

  public Integer getParaindex() {
    return (Integer) this.getAttributeValue(DeptMatRelParaVO.PARAINDEX);
  }

  public String getCprioritycode() {
    return (String) this.getAttributeValue(DeptMatRelParaVO.CPRIORITYCODE);
  }
  
  public String getPk_depmatrel() {
    return (String) this.getAttributeValue(DeptMatRelParaVO.PK_DEPMATREL);
  }

  public String getPk_depmatrel_b() {
    return (String) this.getAttributeValue(DeptMatRelParaVO.PK_DEPMATREL_B);
  }

  public String getPk_dept() {
    return (String) this.getAttributeValue(DeptMatRelParaVO.PK_DEPT);
  }

  public String getPk_material() {
    return (String) this.getAttributeValue(DeptMatRelParaVO.PK_MATERIAL);
  }

  public String getPk_materialbaseclass() {
    return (String) this
        .getAttributeValue(DeptMatRelParaVO.PK_MATERIALBASECLASS);
  }

  public String getPk_materialsaleclass() {
    return (String) this
        .getAttributeValue(DeptMatRelParaVO.PK_MATERIALSALECLASS);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(DeptMatRelParaVO.PK_ORG);
  }

  public void setAllowsale(Integer allowsale) {
    this.setAttributeValue(DeptMatRelParaVO.ALLOWSALE, allowsale);
  }

  @Override
  public void setAttributeValue(String attributeName, Object attributeValue) {
    this.mapTemp.put(attributeName, attributeValue);
  }

  public void setCemployeeid(String cemployeeid) {
    this.setAttributeValue(DeptMatRelParaVO.CEMPLOYEEID, cemployeeid);
  }

  public void setCrowno(String crowno) {
    this.setAttributeValue(DeptMatRelParaVO.CROWNO, crowno);
  }

  public void setExclude(UFBoolean exclude) {
    this.setAttributeValue(DeptMatRelParaVO.EXCLUDE, exclude);
  }

  public void setParaindex(Integer paraindex) {
    this.setAttributeValue(DeptMatRelParaVO.PARAINDEX, paraindex);
  }

  public void setCprioritycode(String code) {
    this.setAttributeValue(DeptMatRelParaVO.CPRIORITYCODE, code);
  }
  
  public void setPk_depmatrel(String pk_depmatrel) {
    this.setAttributeValue(DeptMatRelParaVO.PK_DEPMATREL, pk_depmatrel);
  }

  public void setPk_depmatrel_b(String pk_depmatrel_b) {
    this.setAttributeValue(DeptMatRelParaVO.PK_DEPMATREL_B, pk_depmatrel_b);
  }

  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(DeptMatRelParaVO.PK_DEPT, pk_dept);
  }

  public void setPk_material(String pk_material) {
    this.setAttributeValue(DeptMatRelParaVO.PK_MATERIAL, pk_material);
  }

  public void setPk_materialbaseclass(String pk_materialbaseclass) {
    this.setAttributeValue(DeptMatRelParaVO.PK_MATERIALBASECLASS,
        pk_materialbaseclass);
  }

  public void setPk_materialsaleclass(String pk_materialsaleclass) {
    this.setAttributeValue(DeptMatRelParaVO.PK_MATERIALSALECLASS,
        pk_materialsaleclass);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(DeptMatRelParaVO.PK_ORG, pk_org);
  }

  @Override
  public void validate() throws ValidationException {
    StringBuilder validateitem = new StringBuilder();
    if (null == this.getPk_org()) {
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0", "04006007-0024")/* 销售组织*/);
    }
    if (null == this.getPk_dept()) {
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0", "04006007-0031")/* 部门*/);
    }
    if (PubAppTool.isNull(this.getPk_material())) {
      if (validateitem.length() > 0) {
        validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0", "04006007-0025")/*、*/);
      }
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0", "04006007-0026")/* 物料 */);
    }
    if (validateitem.length() > 0) {
      validateitem.deleteCharAt(validateitem.length() - 1);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0","04006007-0032")/*@res "部门物料关系检查时，下列项目不允许为空："*/
          + validateitem.toString());
    }
  }
}