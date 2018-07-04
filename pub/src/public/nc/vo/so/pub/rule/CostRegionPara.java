package nc.vo.so.pub.rule;


/**
 * 成本域查询参数
 * 
 * @since 6.5
 * @version 2015-6-13 下午02:36:21
 * @author 刘景
 */
public class CostRegionPara {
  
  /**
   * 发货库存组织old
   */
  private String stockorgid;

  /**
  * 仓库id
  */
  private String stordocid;

  /**
   * 订单交易类型
   */
  private String ordertantype;

  /**
   * 结算财务oid
   */
  private String cinfinanceorgid;
  
  /**
   * 物料oid
   */
  private String Cmaterialid;
  
  
  /**
   * 成本域id(此参数不用传递)
   */
  private String  ccostorgid;
  
  public String getCcostorgid() {
    return ccostorgid;
  }

  public void setCcostorgid(String ccostorgid) {
    this.ccostorgid = ccostorgid;
  }



  public String getStockorgid() {
    return stockorgid;
  }

  public void setStockorgid(String stockorgid) {
    this.stockorgid = stockorgid;
  }

  public String getStordocid() {
    return stordocid;
  }

  public void setStordocid(String stordocid) {
    this.stordocid = stordocid;
  }

  public String getOrdertantype() {
    return ordertantype;
  }

  public void setOrdertantype(String ordertantype) {
    this.ordertantype = ordertantype;
  }

  public String getCinfinanceorgid() {
    return cinfinanceorgid;
  }

  public void setCinfinanceorgid(String cinfinanceorgid) {
    this.cinfinanceorgid = cinfinanceorgid;
  }

  public String getCmaterialid() {
    return Cmaterialid;
  }

  public void setCmaterialid(String cmaterialid) {
    Cmaterialid = cmaterialid;
  }


}
