package nc.vo.so.mbuylargess.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * 买赠设置表体VO
 * 
 * @since 6.3
 * @version 2014-04-09 10:47:26
 * @author 刘景
 */
public class BuyLargessHVO extends SuperVO {

  /**
   * 营销活动
   */
  public static final String CMARKETACTID = "cmarketactid";

  /** 适用下级 */
  public static final String ISLOW = "islow";

  /** 购买数量 */
  public static final String NBUYNUM = "nbuynum";

  /** 买赠主表id */
  public static final String PK_BUYLARGESS = "pk_buylargess";

  /** 币种 */
  public static final String PK_CURRINFO = "pk_currinfo";

  /** 客户基本分类 */
  public static final String PK_CUSTCLASS = "pk_custclass";

  /** 客户类 */
  public static final String PK_CUSTOMER = "pk_customer";

  /** 客户销售分类 */
  public static final String PK_CUSTSALECLASS = "pk_custsaleclass";

  /** 集团 */
  public static final String PK_GROUP = "pk_group";

  /** 物料基本分类 */
  public static final String PK_MARBASCLASS = "pk_marbasclass";

  /** 物料销售分类 */
  public static final String PK_MARSALECLASS = "pk_marsaleclass";

  /** 物料编码 */
  public static final String CBUYMARID = "cbuymarid";

  /** 单位 */
  public static final String CBUYUNITID = "cbuyunitid";

  /** 销售组织 */
  public static final String PK_ORG = "pk_org";

  /** 优先码 */
  public static final String CPRIORITYCODE = "cprioritycode";

  /** 促销类型 */
  public static final String CPROMOTTYPEID = "cpromottypeid";

  /**
   * 时间戳
   */
  public static final String TS = "ts";

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public BuyLargessHVO() {
    super();
  }

  public UFBoolean getIslow() {
    return (UFBoolean) this.getAttributeValue(BuyLargessHVO.ISLOW);
  }

  @Override
  public IVOMeta getMetaData() {
    // 得到元数据的实体属性，其中”
    // to.to_apply”,to表示的组件命名空间，对于调入申请来说，其组件命名空间就是内部交易to，to_apply表示调入申请主实体的数据库表名。
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.so_buylargess");
    return meta;
  }

  public String getCprioritycode() {
    return (String) this.getAttributeValue(BuyLargessHVO.CPRIORITYCODE);
  }

  public UFDouble getNbuynum() {
    return (UFDouble) this.getAttributeValue(BuyLargessHVO.NBUYNUM);
  }

  public String getPk_buylargess() {
    return (String) this.getAttributeValue(BuyLargessHVO.PK_BUYLARGESS);
  }

  public String getPk_currinfo() {
    return (String) this.getAttributeValue(BuyLargessHVO.PK_CURRINFO);
  }

  public String getPk_custclass() {
    return (String) this.getAttributeValue(BuyLargessHVO.PK_CUSTCLASS);
  }

  public String getPk_customer() {
    return (String) this.getAttributeValue(BuyLargessHVO.PK_CUSTOMER);
  }

  public String getPk_custsaleclass() {
    return (String) this.getAttributeValue(BuyLargessHVO.PK_CUSTSALECLASS);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(BuyLargessHVO.PK_GROUP);
  }

  public String getPk_marbasclass() {
    return (String) this.getAttributeValue(BuyLargessHVO.PK_MARBASCLASS);
  }

  public String getPk_marsaleclass() {
    return (String) this.getAttributeValue(BuyLargessHVO.PK_MARSALECLASS);
  }

  public String getCbuymarid() {
    return (String) this.getAttributeValue(BuyLargessHVO.CBUYMARID);
  }

  public String getCbuyunitid() {
    return (String) this.getAttributeValue(BuyLargessHVO.CBUYUNITID);
  }

  /**
   * 获得促销类型
   * 
   * @return 促销类型
   */
  public String getCpromottypeid() {
    return (String) this.getAttributeValue(BuyLargessHVO.CPROMOTTYPEID);
  }

  /**
   * 获取营销活动
   * 
   * @return 营销活动
   */
  public String getCmarketactid() {
    return (String) this.getAttributeValue(BuyLargessHVO.CMARKETACTID);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(BuyLargessHVO.PK_ORG);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(BuyLargessHVO.TS);
  }

  public void setCprioritycode(String cprioritycode) {
    this.setAttributeValue(BuyLargessHVO.CPRIORITYCODE, cprioritycode);
  }

  public void setIslow(UFBoolean islow) {
    this.setAttributeValue(BuyLargessHVO.ISLOW, islow);
  }

  public void setNbuynum(UFDouble nbuynum) {
    this.setAttributeValue(BuyLargessHVO.NBUYNUM, nbuynum);
  }

  public void setPk_buylargess(String pk_buylargess) {
    this.setAttributeValue(BuyLargessHVO.PK_BUYLARGESS, pk_buylargess);
  }

  public void setPk_currinfo(String pk_currinfo) {
    this.setAttributeValue(BuyLargessHVO.PK_CURRINFO, pk_currinfo);
  }

  public void setPk_custclass(String pk_custclass) {
    this.setAttributeValue(BuyLargessHVO.PK_CUSTCLASS, pk_custclass);
  }

  public void setPk_customer(String pk_customer) {
    this.setAttributeValue(BuyLargessHVO.PK_CUSTOMER, pk_customer);
  }

  public void setPk_custsaleclass(String pk_custsaleclass) {
    this.setAttributeValue(BuyLargessHVO.PK_CUSTSALECLASS, pk_custsaleclass);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(BuyLargessHVO.PK_GROUP, pk_group);
  }

  public void setPk_marbasclass(String pk_marbasclass) {
    this.setAttributeValue(BuyLargessHVO.PK_MARBASCLASS, pk_marbasclass);
  }

  public void setPk_marsaleclass(String pk_marsaleclass) {
    this.setAttributeValue(BuyLargessHVO.PK_MARSALECLASS, pk_marsaleclass);
  }

  public void setCbuymarid(String cbuymarid) {
    this.setAttributeValue(BuyLargessHVO.CBUYMARID, cbuymarid);
  }

  public void setCbuyunitid(String cbuyunitid) {
    this.setAttributeValue(BuyLargessHVO.CBUYUNITID, cbuyunitid);
  }

  /**
   * 设置促销类型
   * 
   * @param cpromottypeid
   */
  public void setCpromottypeid(String cpromottypeid) {
    this.setAttributeValue(BuyLargessHVO.CPROMOTTYPEID, cpromottypeid);
  }

  /**
   * 设置买赠设置
   * 
   * @param cmarketactid
   */
  public void setCmarketactid(String cmarketactid) {
    this.setAttributeValue(BuyLargessHVO.CMARKETACTID, cmarketactid);
  }

  public void setPk_org(String pk_saleorg) {
    this.setAttributeValue(BuyLargessHVO.PK_ORG, pk_saleorg);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(BuyLargessHVO.TS, ts);
  }
}
