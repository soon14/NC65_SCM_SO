package nc.vo.so.iufo;

import java.util.HashMap;
import java.util.Map;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.SuperVO;

public class UFOFuncParaVO extends SuperVO {
  private static final long serialVersionUID = -5941234142559413815L;

  // 集团
  public static final String PK_GROUP = "pk_group";

  // 主组织ID
  public static final String PK_ORG = "pk_org";

  // 组织编码
  public static final String ORGCODE = "orgcode";

  // 客户ID
  public static final String CCUSTOMERID = "ccustomerid";

  // 客户编码
  public static final String CUSTOMERCODE = "customercode";

  // 客户基本分类ID
  public static final String CCUSTBASCLID = "ccustbasclid";

  // 客户基本分类编码
  public static final String CUSTBASCLCODE = "custbasclcode";

  // 物料ID
  public static final String CMATERIALID = "cmaterialid";

  // 物料编码
  public static final String MATERIALCODE = "materialcode";

  // 物料基本分类ID
  public static final String CMARBASCLID = "cmarbasclid";

  // 物料基本分类编码
  public static final String MARBASCLCODE = "marbasclcode";

  // 部门ID
  public static final String CDEPTID = "cdeptid";

  // 部门编码
  public static final String DEPTCODE = "deptcode";

  // 开始日期
  public static final String BEGINDATE = "begindate";

  // 结束日期
  public static final String ENDDATE = "enddate";

  // 选择字段
  public static final String FUNCNAME = "funcname";

  // 模块名称
  public static final String ModuleName = NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0", "04006005-0017")/*销售管理*/;

  // 模块描述
  public static final String ModuleDesc = NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0", "04006005-0017")/*销售管理*/;

  // 函数名称
  public static final String[] FuncName = new String[] {
    "SOInvoiceSumNum", "SOInvoiceSumMny", "SOInvoiceSumTaxMny"
  };

  // 函数描述
  public static final String[] FuncDesc = new String[] {
    NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0", "04006005-0018")/*销售发票数量汇总*/, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0", "04006005-0019")/*销售发票无税金额汇总*/, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0", "04006005-0020")/*销售发票价税合计汇总*/
  };

  // 函数形式
  public static final String[] FuncForm =
      {
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0", "04006005-0021")/*销售发票数量汇总（开票组织、客户编码、客户基本分类、物料编码、物料基本分类、部门、开始日期、终止日期）\n\n\t开票组织：取要查询开票组织的编码\n\t客户编码：取查询客户的编码\n\t客户基本分类：取查询客户基本分类的编码包含下级\n\t物料编码：取查询物料的编码\n\t物料基本分类：取查询物料基本分类的编码包含下级\n\t部门：取查询部门的编码包含下级\n\t开始日期：查询日期范围起始日期\n\t终止日期：查询日期范围终点日期*/,
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0", "04006005-0022")/*销售发票无税金额汇总（开票组织、客户编码、客户基本分类、物料编码、物料基本分类、部门、开始日期、终止日期）\n\n\t开票组织：取要查询开票组织的编码\n\t客户编码：取查询客户的编码\n\t客户基本分类：取查询客户基本分类的编码包含下级\n\t物料编码：取查询物料的编码\n\t物料基本分类：取查询物料基本分类的编码包含下级\n\t部门：取查询部门的编码包含下级\n\t开始日期：查询日期范围起始日期\n\t终止日期：查询日期范围终点日期*/,
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0", "04006005-0023")/*销售发票价税合计汇总（开票组织、客户编码、客户基本分类、物料编码、物料基本分类、部门、开始日期、终止日期）\n\n\t开票组织：取要查询开票组织的编码\n\t客户编码：取查询客户的编码\n\t客户基本分类：取查询客户基本分类的编码包含下级\n\t物料编码：取查询物料的编码\n\t物料基本分类：取查询物料基本分类的编码包含下级\n\t部门：取查询部门的编码包含下级\n\t开始日期：查询日期范围起始日期\n\t终止日期：查询日期范围终点日期*/
      };

  private Map<String, String[]> mapLower = new HashMap<String, String[]>();

  private Map<String, String> maptempvalue = new HashMap<String, String>();

  public void setPk_group(String pk_group) {
    this.maptempvalue.put(UFOFuncParaVO.PK_GROUP, pk_group);
  }

  public String getPk_group() {
    return this.maptempvalue.get(UFOFuncParaVO.PK_GROUP);
  }

  public void setPk_org(String pk_org) {
    this.maptempvalue.put(UFOFuncParaVO.PK_ORG, pk_org);
  }

  public String getPk_org() {
    return this.maptempvalue.get(UFOFuncParaVO.PK_ORG);

  }

  public void setOrgcode(String orgcode) {
    this.maptempvalue.put(UFOFuncParaVO.ORGCODE, orgcode);
  }

  public String getOrgcode() {
    return this.maptempvalue.get(UFOFuncParaVO.ORGCODE);

  }

  public void setCcustomerid(String ccustomerid) {
    this.maptempvalue.put(UFOFuncParaVO.CCUSTOMERID, ccustomerid);
  }

  public String getCcustomerid() {
    return this.maptempvalue.get(UFOFuncParaVO.CCUSTOMERID);

  }

  public void setCustomercode(String customercode) {
    this.maptempvalue.put(UFOFuncParaVO.CUSTOMERCODE, customercode);
  }

  public String getCustomercode() {
    return this.maptempvalue.get(UFOFuncParaVO.CUSTOMERCODE);

  }

  public void setCcustbasclid(String ccustbasclid) {
    this.maptempvalue.put(UFOFuncParaVO.CCUSTBASCLID, ccustbasclid);
  }

  public String getCcustbasclid() {
    return this.maptempvalue.get(UFOFuncParaVO.CCUSTBASCLID);

  }

  public void setCustbasclcode(String custbasclcode) {
    this.maptempvalue.put(UFOFuncParaVO.CUSTBASCLCODE, custbasclcode);
  }

  public String getCustbasclcode() {
    return this.maptempvalue.get(UFOFuncParaVO.CUSTBASCLCODE);

  }

  public void setCmaterialid(String cmaterialid) {
    this.maptempvalue.put(UFOFuncParaVO.CMATERIALID, cmaterialid);
  }

  public String getCmaterialid() {
    return this.maptempvalue.get(UFOFuncParaVO.CMATERIALID);

  }

  public void setMaterialcode(String materialcode) {
    this.maptempvalue.put(UFOFuncParaVO.MATERIALCODE, materialcode);
  }

  public String getMaterialcode() {
    return this.maptempvalue.get(UFOFuncParaVO.MATERIALCODE);

  }

  public void setCmarbasclid(String cmarbasclid) {
    this.maptempvalue.put(UFOFuncParaVO.CMARBASCLID, cmarbasclid);
  }

  public String getCmarbasclid() {
    return this.maptempvalue.get(UFOFuncParaVO.CMARBASCLID);

  }

  public void setMarbasclcode(String marbasclcode) {
    this.maptempvalue.put(UFOFuncParaVO.MARBASCLCODE, marbasclcode);
  }

  public String getMarbasclcode() {
    return this.maptempvalue.get(UFOFuncParaVO.MARBASCLCODE);

  }

  public void setCdeptid(String cdeptid) {
    this.maptempvalue.put(UFOFuncParaVO.CDEPTID, cdeptid);
  }

  public String getCdeptid() {
    return this.maptempvalue.get(UFOFuncParaVO.CDEPTID);

  }

  public void setDeptcode(String deptcode) {
    this.maptempvalue.put(UFOFuncParaVO.DEPTCODE, deptcode);
  }

  public String getDeptcode() {
    return this.maptempvalue.get(UFOFuncParaVO.DEPTCODE);

  }

  public void setBegindate(String begindate) {
    this.maptempvalue.put(UFOFuncParaVO.BEGINDATE, begindate);
  }

  public String getBegindate() {
    return this.maptempvalue.get(UFOFuncParaVO.BEGINDATE);

  }

  public void setEnddate(String enddate) {
    this.maptempvalue.put(UFOFuncParaVO.ENDDATE, enddate);
  }

  public String getEnddate() {
    return this.maptempvalue.get(UFOFuncParaVO.ENDDATE);
  }

  public void setFuncname(String funcname) {
    this.maptempvalue.put(UFOFuncParaVO.FUNCNAME, funcname);
  }

  public String getFuncname() {
    return this.maptempvalue.get(UFOFuncParaVO.FUNCNAME);

  }

  public void addLowerArray(String key, String[] lowpks) {
    this.mapLower.put(key, lowpks);
  }

  public Map<String, String[]> getLowerMap() {
    return this.mapLower;
  }

  @Override
  public Object getAttributeValue(String key) {
    return this.maptempvalue.get(key);
  }

  @Override
  public void setAttributeValue(String key, Object value) {
    this.maptempvalue.put(key, (String) value);
  }
}
