package nc.vo.so.mreturnassign.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class ReturnAssignVO extends SuperVO {

  /** 优先码 */
  public static final String CPRIORITYCODE = "cprioritycode";

  /** dr */
  public static final String DR = "dr";

  /** 客户基本分类编码 */
  public static final String PK_CUSTCLASS = "pk_custclass";

  /** 客户编码 */
  public static final String PK_CUSTOMER = "pk_customer";

  /** 客户销售分类编码 */
  public static final String PK_CUSTSALECLASS = "pk_custsaleclass";

  /** 集团 */
  public static final String PK_GROUP = "pk_group";

  /** 物料基本分类编码 */
  public static final String PK_MARBASCLASS = "pk_marbasclass";

  /** 物料销售分类编码 */
  public static final String PK_MARSALECLASS = "pk_marsaleclass";

  /** 物料编码 */
  public static final String PK_MATERIAL = "pk_material";

  /** 产品线编码 */
  public static final String PK_PRODUCTLINE = "pk_productline";

  /** 退货政策分配主键 */
  public static final String PK_RETURNASSIGN = "pk_returnassign";

  /** 退货政策编码 */
  public static final String PK_RETURNPOLICY = "pk_returnpolicy";

  /** 销售组织 */
  public static final String PK_SALEORG = "pk_saleorg";

  /** 时间戳 */
  public static final String TS = "ts";

  private static final long serialVersionUID = 1L;

  public ReturnAssignVO() {
    super();
  }

  public String getCprioritycode() {
    return (String) this.getAttributeValue(ReturnAssignVO.CPRIORITYCODE);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(ReturnAssignVO.DR);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.returnassign");
    return meta;
  }

  public String getPk_custclass() {
    return (String) this.getAttributeValue(ReturnAssignVO.PK_CUSTCLASS);
  }

  public String getPk_customer() {
    return (String) this.getAttributeValue(ReturnAssignVO.PK_CUSTOMER);
  }

  public String getPk_custsaleclass() {
    return (String) this.getAttributeValue(ReturnAssignVO.PK_CUSTSALECLASS);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(ReturnAssignVO.PK_GROUP);
  }

  public String getPk_marbasclass() {
    return (String) this.getAttributeValue(ReturnAssignVO.PK_MARBASCLASS);
  }

  public String getPk_marsaleclass() {
    return (String) this.getAttributeValue(ReturnAssignVO.PK_MARSALECLASS);
  }

  public String getPk_material() {
    return (String) this.getAttributeValue(ReturnAssignVO.PK_MATERIAL);
  }

  public String getPk_productline() {
    return (String) this.getAttributeValue(ReturnAssignVO.PK_PRODUCTLINE);
  }

  public String getPk_returnassign() {
    return (String) this.getAttributeValue(ReturnAssignVO.PK_RETURNASSIGN);
  }

  public String getPk_returnpolicy() {
    return (String) this.getAttributeValue(ReturnAssignVO.PK_RETURNPOLICY);
  }

  public String getPk_saleorg() {
    return (String) this.getAttributeValue(ReturnAssignVO.PK_SALEORG);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(ReturnAssignVO.TS);
  }

  public void setCprioritycode(String cprioritycode) {
    this.setAttributeValue(ReturnAssignVO.CPRIORITYCODE, cprioritycode);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(ReturnAssignVO.DR, dr);
  }

  public void setPk_custclass(String pk_custclass) {
    this.setAttributeValue(ReturnAssignVO.PK_CUSTCLASS, pk_custclass);
  }

  public void setPk_customer(String pk_customer) {
    this.setAttributeValue(ReturnAssignVO.PK_CUSTOMER, pk_customer);
  }

  public void setPk_custsaleclass(String pk_custsaleclass) {
    this.setAttributeValue(ReturnAssignVO.PK_CUSTSALECLASS, pk_custsaleclass);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(ReturnAssignVO.PK_GROUP, pk_group);
  }

  public void setPk_marbasclass(String pk_marbasclass) {
    this.setAttributeValue(ReturnAssignVO.PK_MARBASCLASS, pk_marbasclass);
  }

  public void setPk_marsaleclass(String pk_marsaleclass) {
    this.setAttributeValue(ReturnAssignVO.PK_MARSALECLASS, pk_marsaleclass);
  }

  public void setPk_material(String pk_material) {
    this.setAttributeValue(ReturnAssignVO.PK_MATERIAL, pk_material);
  }

  public void setPk_productline(String pk_productline) {
    this.setAttributeValue(ReturnAssignVO.PK_PRODUCTLINE, pk_productline);
  }

  public void setPk_returnassign(String pk_returnassign) {
    this.setAttributeValue(ReturnAssignVO.PK_RETURNASSIGN, pk_returnassign);
  }

  public void setPk_returnpolicy(String pk_returnpolicy) {
    this.setAttributeValue(ReturnAssignVO.PK_RETURNPOLICY, pk_returnpolicy);
  }

  public void setPk_saleorg(String pk_saleorg) {
    this.setAttributeValue(ReturnAssignVO.PK_SALEORG, pk_saleorg);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(ReturnAssignVO.TS, ts);
  }
}
