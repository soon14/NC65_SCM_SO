package nc.vo.so.report.multipleprofit;

/**
 * 综合毛利分析查询模版条件
 * 
 * @since 6.3
 * @version 2012-12-10 13:49:11
 * @author liangjm
 */
public interface MultProfitCondition {

  /**
   * 财务组织
   */
  public static final String PK_ORG = "pk_org";

  /**
   * 单据日期
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * 销售组织
   */
  public static final String CSALEORGID = "csaleorgid";

  /**
   * 订单号
   */
  public static final String VBILLCODE = "vbillcode";

  /**
   * 订单客户
   */
  public static final String CCUSTOMERID = "ccustomerid";

  /**
   * 客户基本分类
   */
  public static final String PK_CUSTCLASS = "pk_custclass";

  /**
   * 地区分类
   */
  public static final String PK_AREACL = "pk_areacl";

  /**
   * 部门
   */
  public static final String CDPTID = "cdptid";

  /**
   * 业务员
   */
  public static final String CBIZID = "cbizid";

  /**
   * 产品线
   */
  public static final String CPRODLINEID = "cprodlineid";

  /**
   * 物料编码
   */
  public static final String CMATERIALCODE = "cmaterialcode";

  /**
   * 物料名称
   */
  public static final String CMATERIALNAME = "cmaterialname";

  /**
   * 物料基本分类
   */
  public static final String PK_MARBASCLASS = "pk_marbasclass";

  /**
   * 批次号
   */
  public static final String VBATCHCODE = "vbatchcode";

  /**
   * 销售来源（汇总条件）
   */
  public static final String SOURCESYSTEM = "sourcesystem";

  /**
   * 客户销售分类（汇总条件）
   */
  public static final String PK_CUSTSALECLASS = "pk_custsaleclass";

  /**
   * 物料销售分类（汇总条件）
   */
  public static final String PK_MARSALECLASS = "pk_marsaleclass";

  /**
   * 物料（汇总条件）
   */
  public static final String CMATERIALID = "cmaterialid";

}
