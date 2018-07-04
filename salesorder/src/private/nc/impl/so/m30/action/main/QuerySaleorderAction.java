package nc.impl.so.m30.action.main;

import nc.bs.so.m30.maintain.QuerySaleorderBP;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 根据sql查询销售订单
 * 
 * @since 6.0
 * @version 2011-5-7 下午03:10:01
 * @author 祝会征
 */
public class QuerySaleorderAction {
  public SaleOrderVO[] querySaleorder(String sql) {
    QuerySaleorderBP bp = new QuerySaleorderBP();
    return bp.querySaleOrder(sql);
  }
}
