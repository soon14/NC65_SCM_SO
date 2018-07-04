package nc.bs.so.m4331.maintain;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * 查询发货单聚合vo规则
 * 
 * @since 6.0
 * @version 2011-1-12 上午09:53:12
 * @author 祝会征
 */
public class QueryDeliveryBP {
  public DeliveryVO[] query(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    String[] cbillids = rowset.toOneDimensionStringArray();

    // 根据id查询VO
    BillQuery<DeliveryVO> query = new BillQuery<DeliveryVO>(DeliveryVO.class);
    DeliveryVO[] vos = query.query(cbillids);
    return vos;
  }
}
