package nc.vo.so.m30.sobalance.util;

import nc.vo.pub.lang.UFDouble;

/**
 * 收款单取值keyValue
 * 
 * @since 6.0
 * @version 2011-7-23 下午12:52:11
 * @author 刘志伟
 */
public abstract class AbstractGatheringKeyValue {

  /** 收款单主组织 */
  public abstract String getPk_org();

  /** 原币币种 */
  public abstract String getPk_currtype();

  /** 客户 */
  public abstract String getCustomer();

  /** 销售订单主组织 */
  public abstract String getSo_org();

  /** 销售订单交易类型 */
  public abstract String getSo_ordertype();

  /** 财务组织 */
  public abstract String getSett_org();

  /** 销售部门 */
  public abstract String getSo_deptid();

  /** 销售业务员 */
  public abstract String getSo_psndoc();

  /** 销售渠道类型 */
  public abstract String getSo_transtype();

  /** 销售订单客户 */
  public abstract String getOrdercubasdoc();

  /** 产品线 */
  public abstract String[] getProductlines();

  /** 原币金额 */
  public abstract UFDouble getMoney();
}
