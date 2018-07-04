package nc.pubitf.so.m30.mmpps.lotreg;

import java.io.Serializable;

import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

import nc.pubitf.mmpub.sdmanage.IBillTraceResult;

/**
 * 销售订单提供给"供需预订维护"节点的单据追溯返回信息
 * 
 * @since 6.3.1
 * @version 2013-08-20 19:47:32
 * @author 张云枫
 * 
 */
public class SaleOrderTraceResult implements IBillTraceResult, Serializable {

  private static final long serialVersionUID = -4075742684603094254L;

  /**
   * 来源表from串
   */
  private String fromSql;

  /**
   * 查询来源条件查询语句
   */
  private String whereSql;

  /**
   * 设置来源表from串
   * 
   * @param fromSql from串，无from关键字
   */
  public void setFromSql(String fromSql) {
    this.fromSql = fromSql;
  }

  /**
   * 设置查询条件
   * 
   * @param whereSql where串，无where关键字
   */
  public void setWhereSql(String whereSql) {
    this.whereSql = whereSql;
  }

  @Override
  public String getFrom() {
    return this.fromSql;
  }

  @Override
  public String getWhere() {
    return this.whereSql;
  }

  @Override
  public String getSrcBid() {
    return "so_saleorder_b." + SaleOrderBVO.CSRCBID;
  }

  @Override
  public String getSrcId() {
    return "so_saleorder_b." + SaleOrderBVO.CSRCID;
  }

  @Override
  public String getSrcType() {
    return "so_saleorder_b." + SaleOrderBVO.VSRCTYPE;
  }

  @Override
  public String getBillBid() {
    return "so_saleorder_b." + SaleOrderBVO.CSALEORDERBID;
  }

  @Override
  public String getBillId() {
    return "so_saleorder." + SaleOrderHVO.CSALEORDERID;
  }

  @Override
  public String getBillType() {
    return SOBillType.Order.getCode();
  }

}
