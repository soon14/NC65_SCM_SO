package nc.pubitf.so.mbuylagress.api;

import java.io.Serializable;

/**
 * @description
 * 买赠设置查询API条件构造常量类
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-12 上午10:55:51
 * @author 刘景
 */
public interface IBuyLargessVO extends Serializable {
  
  /**
   * 买赠主表id
   */
  public static final String PK_BUYLARGESS = "pk_buylargess";
  /**
   * 销售组织
   */
  public static final String PK_ORG = "pk_org";
  /**
   * 集团
   */
  public static final String PK_GROUP = "pk_group";
  /**
   * 物料编码
   */
  public static final String CBUYMARID = "cbuymarid";
  /**
   * 单位
   */
  public static final String CBUYUNITID = "cbuyunitid";
  /**
   * 物料基本分类
   */
  public static final String PK_MARBASCLASS = "pk_marbasclass";
  /**
   * 物料销售分类
   */
  public static final String PK_MARSALECLASS = "pk_marsaleclass";
  /**
   * 客户
   */
  public static final String PK_CUSTOMER = "pk_customer";
  /**
   * 客户基本分类
   */
  public static final String PK_CUSTCLASS = "pk_custclass";
  /**
   * 客户销售分类
   */
  public static final String PK_CUSTSALECLASS = "pk_custsaleclass";
  /**
   * 购买数量
   */
  public static final String NBUYNUM = "nbuynum";
  /**
   * 币种
   */
  public static final String PK_CURRINFO = "pk_currinfo";
  /**
   * 适用下级
   */
  public static final String ISLOW = "islow";
  /**
   * 优先码
   */
  public static final String CPRIORITYCODE = "cprioritycode";
  /**
   * 促销类型
   */
  public static final String CPROMOTTYPEID = "cpromottypeid";
  /**
   * 营销活动
   */
  public static final String CMARKETACTID = "cmarketactid";
  /**
   * vostatus
   */
  public static final String STATUS = "status";
  /**
   * dr
   */
  public static final String DR = "dr";
  /**
   * ts
   */
  public static final String TS = "ts";
  /**
   * 买赠子表VO.买赠子表id
   */
  public static final String PK_BUYLARGESS_B_PK_BUYLARGESS_B = "pk_buylargess_b.pk_buylargess_b";
  /**
   * 买赠子表VO.物料编码
   */
  public static final String PK_BUYLARGESS_B_PK_MATERIAL = "pk_buylargess_b.pk_material";
  /**
   * 买赠子表VO.单位
   */
  public static final String PK_BUYLARGESS_B_PK_MEASDOC = "pk_buylargess_b.pk_measdoc";
  /**
   * 买赠子表VO.赠送数量
   */
  public static final String PK_BUYLARGESS_B_NNUM = "pk_buylargess_b.nnum";
  /**
   * 买赠子表VO.单价
   */
  public static final String PK_BUYLARGESS_B_NPRICE = "pk_buylargess_b.nprice";
  /**
   * 买赠子表VO.金额
   */
  public static final String PK_BUYLARGESS_B_NMNY = "pk_buylargess_b.nmny";
  /**
   * 买赠子表VO.上限类型
   */
  public static final String PK_BUYLARGESS_B_FTOPLIMITTYPE = "pk_buylargess_b.ftoplimittype";
  /**
   * 买赠子表VO.上限值
   */
  public static final String PK_BUYLARGESS_B_NTOPLIMITVALUE = "pk_buylargess_b.ntoplimitvalue";
  /**
   * 买赠子表VO.开始日期
   */
  public static final String PK_BUYLARGESS_B_DBEGDATE = "pk_buylargess_b.dbegdate";
  /**
   * 买赠子表VO.截止日期
   */
  public static final String PK_BUYLARGESS_B_DENDDATE = "pk_buylargess_b.denddate";
  /**
   * 买赠子表VO.集团
   */
  public static final String PK_BUYLARGESS_B_PK_GROUP = "pk_buylargess_b.pk_group";
  /**
   * 买赠子表VO.vostatus
   */
  public static final String PK_BUYLARGESS_B_STATUS = "pk_buylargess_b.status";
  /**
   * 买赠子表VO.dr
   */
  public static final String PK_BUYLARGESS_B_DR = "pk_buylargess_b.dr";
  /**
   * 买赠子表VO.ts
   */
  public static final String PK_BUYLARGESS_B_TS = "pk_buylargess_b.ts";
}


