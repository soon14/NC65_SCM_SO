package nc.impl.so.mbuylargess.action;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

public class QueryBuyLargessAction {
  public BuyLargessVO[] queryBuylargess(String whereSql) {
    if (whereSql != null) {
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(whereSql.toString());
      String[] cbillids = rowset.toOneDimensionStringArray();

      BillQuery<BuyLargessVO> billquery =
          new BillQuery<BuyLargessVO>(BuyLargessVO.class);
      BuyLargessVO[] bills = billquery.query(cbillids);
      return bills;
    }
    return null;
  }
}
