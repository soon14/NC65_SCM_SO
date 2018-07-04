package nc.bs.so.m30.maintain;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 根据sql查询销售订单vo
 * 
 * @since 6.0
 * @version 2011-5-7 下午03:08:20
 * @author 祝会征
 */
public class QuerySaleorderBP {
  public SaleOrderVO[] querySaleOrder(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    String[] cbillids = rowset.toOneDimensionStringArray();

    // 根据id查询VO
    BillQuery<SaleOrderVO> query =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] bills = query.query(cbillids);

    return bills;
  }
}
