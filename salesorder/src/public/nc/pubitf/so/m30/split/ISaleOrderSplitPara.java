package nc.pubitf.so.m30.split;
/**
 * 销售订单给上游单据填充业务委托关系字段分单提供的参数接口,没有相应字段时直接返回NULL即可
 * 
 * @since 6.0
 * @version 2011-6-30 下午01:31:45
 * @author fengjb
 */
public interface ISaleOrderSplitPara {

  /**
   * 返回表体行数
   * 
   * @return
   */
  int getBodyCount();
  /**
   * 由于销售组织一定是分单条件，并且销售订单上游单据销售组织都在表头或统一录入，
   * 故销售组织不区分行，统一返回销售组织OID
   * 
   * @param row
   * @return
   */
  String getSaleOrgid();
  /**
   * 由于订单客户一定是分单条件，并且销售订单上游单据订单客户都在表头或统一录入，
   * 故订单客户不区分行，统一返回订单客户ID
   * 
   * @param row
   * @return
   */
  String getCustomerid();
  /**
   * 返回第row行的物料OID
   * 
   * @param row
   * @return
   */
  String getMaterialid(int row);
  /**
   * 返回第row行的库存组织OID
   * 
   * @param row
   * @return
   */
  String  getSendStockOrgid(int row);
  /**
   * 返回第row行的物流组织OID
   * 
   * @param row
   * @return
   */
  String  getTrafficOrgid(int row);
  /**
   * 返回第row行的结算财务组织OID
   * 
   * @param row
   * @return
   */
  String  getSettleOrgid(int row);
  /**
   * 返回第row行的应收财务组织OID
   * 
   * @param row
   * @return
   */
  String  getArOrgid(int row);
  
}
