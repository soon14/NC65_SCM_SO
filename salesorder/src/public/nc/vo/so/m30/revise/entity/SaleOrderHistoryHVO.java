package nc.vo.so.m30.revise.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * 销售订单修订HVO
 * 
 * @version 6.0
 * @author 刘志伟
 * @time 2010-8-11 下午02:03:24
 */
public class SaleOrderHistoryHVO extends SaleOrderHVO {

  /** 销售订单历史表主表ID */
  public static final String CORDERHISTORYID = "corderhistoryid";

  /** 修订交易类型 */
  public static final String CHISTRANTYPEID = "chistrantypeid";

  /** 修订交易类型编码 */
  public static final String VHISTRANTYPECODE = "vhistrantypecode";

  /**
   * 获取修订交易类型
   * 
   * @return 修订交易类型
   */
  public String getChistrantypeid() {
    return (String) this.getAttributeValue(SaleOrderHistoryHVO.CHISTRANTYPEID);
  }

  /**
   * 修订交易类型
   * 
   * @param chistrantypeid 修订交易类型
   */
  public void setChistrantypeid(String chistrantypeid) {
    this.setAttributeValue(SaleOrderHistoryHVO.CHISTRANTYPEID, chistrantypeid);
  }

  /**
   * 获取修订交易类型编码
   * 
   * @return 修订交易类型编码
   */
  public String getVhistrantypecode() {
    return (String) this
        .getAttributeValue(SaleOrderHistoryHVO.VHISTRANTYPECODE);
  }

  /**
   * 设置修订交易类型编码
   * 
   * @param vhistrantypecode 修订交易类型编码
   */
  public void setVhistrantypecode(String vhistrantypecode) {
    this.setAttributeValue(SaleOrderHistoryHVO.VHISTRANTYPECODE,
        vhistrantypecode);
  }

  /**
   * 
   */
  private static final long serialVersionUID = -1317873021890748807L;

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.orderhistory");
    return meta;
  }

  /**
   * 属性corderhistoryid的Getter方法.
   * 创建日期:2009-06-19 08:47:30
   * 
   * @return java.lang.String
   */
  public java.lang.String getCorderhistoryid() {
    return (java.lang.String) this
        .getAttributeValue(SaleOrderHistoryHVO.CORDERHISTORYID);
  }

  /**
   * 属性corderhistoryid的Setter方法.
   * 创建日期:2009-06-19 08:47:30
   * 
   * @param newCorderhistoryid java.lang.String
   */
  public void setCorderhistoryid(java.lang.String newCorderhistoryid) {
    this.setAttributeValue(SaleOrderHistoryHVO.CORDERHISTORYID,
        newCorderhistoryid);
  }
}
