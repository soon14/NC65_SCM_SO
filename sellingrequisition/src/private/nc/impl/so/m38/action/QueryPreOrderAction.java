package nc.impl.so.m38.action;

import nc.bs.so.m38.maintain.QueryPreOrderBP;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * 根据sql查询预订单信息
 * 
 * @since 6.0
 * @version 2011-5-7 上午11:32:32
 * @author 祝会征
 */
public class QueryPreOrderAction {
  public PreOrderVO[] queryPreOrder(String sql) {
    QueryPreOrderBP query = new QueryPreOrderBP();
    return query.queryPreOrder(sql);
  }
}
