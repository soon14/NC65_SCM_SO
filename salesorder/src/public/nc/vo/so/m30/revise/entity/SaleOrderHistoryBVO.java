package nc.vo.so.m30.revise.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m30.entity.SaleOrderBVO;

/**
 * 销售订单修订BVO
 * 
 * @version 6.0
 * @author 刘志伟
 * @time 2010-8-11 下午02:03:24
 */
public class SaleOrderHistoryBVO extends SaleOrderBVO {
  /**
   * 客户物料编码
   */
  public static final String CCUSTMATERIALID = "ccustmaterialid";

  /**
   * 设置客户物料编码
   * 
   */
  public void setCcustmaterialid(String ccustmaterialid) {
    this.setAttributeValue(SaleOrderHistoryBVO.CCUSTMATERIALID, ccustmaterialid);
  }

  /**
   * 获取客户物料编码
   * 
   * @return 客户物料编码
   */
  public String getCcustmaterialid() {
    return (String) this.getAttributeValue(SaleOrderHistoryBVO.CCUSTMATERIALID);
  }

  /** 销售订单历史表附表ID */
  public static final String CORDERHISTORYBID = "corderhistorybid";

  /** 销售订单历史表主表ID */
  public static final String CORDERHISTORYID = "corderhistoryid";

  /**
   * 
   */
  private static final long serialVersionUID = -7548909090744819708L;

  /**
   * 属性corderhistorybid的Getter方法.
   * 创建日期:2009-06-19 08:47:30
   * 
   * @return java.lang.String
   */
  public java.lang.String getCorderhistorybid() {
    return (java.lang.String) this
        .getAttributeValue(SaleOrderHistoryBVO.CORDERHISTORYBID);
  }

  /**
   * 属性corderhistoryid的Getter方法.
   * 创建日期:2009-06-19 08:47:30
   * 
   * @return java.lang.String
   */
  public java.lang.String getCorderhistoryid() {
    return (java.lang.String) this
        .getAttributeValue(SaleOrderHistoryBVO.CORDERHISTORYID);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.orderhistory_b");
    return meta;
  }

  /**
   * 属性corderhistorybid的Setter方法.
   * 创建日期:2009-06-19 08:47:30
   * 
   * @param newCorderhistorybid java.lang.String
   */
  public void setCorderhistorybid(java.lang.String newCorderhistorybid) {
    this.setAttributeValue(SaleOrderHistoryBVO.CORDERHISTORYBID,
        newCorderhistorybid);
  }

  /**
   * 属性corderhistoryid的Setter方法.
   * 创建日期:2009-06-19 08:47:30
   * 
   * @param newCorderhistoryid java.lang.String
   */
  public void setCorderhistoryid(java.lang.String newCorderhistoryid) {
    this.setAttributeValue(SaleOrderHistoryBVO.CORDERHISTORYID,
        newCorderhistoryid);
  }
}
