package nc.bs.so.m38.maintain;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * 根据sql查询预订单信息
 * 
 * @since 6.0
 * @version 2011-5-7 上午11:31:04
 * @author 祝会征
 */
public class QueryPreOrderBP {
  public PreOrderVO[] queryPreOrder(String sql) {
    PreOrderVO[] bills = null;
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    String[] cbillids = rowset.toOneDimensionStringArray();

    // 根据id查询VO
    BillQuery<PreOrderVO> query = new BillQuery<PreOrderVO>(PreOrderVO.class);
    bills = query.query(cbillids);
    return bills;
  }
}
