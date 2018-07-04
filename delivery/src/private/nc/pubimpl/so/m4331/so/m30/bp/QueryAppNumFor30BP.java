package nc.pubimpl.so.m4331.so.m30.bp;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 根据订单表体id查询发货单审核数量
 * 
 * @since 6.0
 * @version 2011-4-18 下午01:28:15
 * @author 祝会征
 */
public class QueryAppNumFor30BP {

  /**
   * 根据来源单据BID查询已审核发货单数量合计
   * 
   * @param srcBids
   * @return 已审核发货单数量合计
   */
  public Map<String, UFDouble> queryAppNum(String[] srcBids) {
    SqlBuilder sqlbuilder = new SqlBuilder();
    sqlbuilder.append("select sum(isnull(" + DeliveryBVO.NNUM + ",0)),"
        + DeliveryBVO.CSRCBID + " from ");
    sqlbuilder.append(" so_delivery h, so_delivery_b b where b.dr =0");
    sqlbuilder.append(" and h." + DeliveryHVO.CDELIVERYID + "= b."
        + DeliveryBVO.CDELIVERYID);
    sqlbuilder.append(" and (");
    sqlbuilder.append(DeliveryHVO.FSTATUSFLAG, BillStatus.AUDIT.getIntValue());
    sqlbuilder.append(" or ");
    sqlbuilder.append(DeliveryHVO.FSTATUSFLAG, BillStatus.CLOSED.getIntValue());
    sqlbuilder.append(") ");
    sqlbuilder.append(" and ");

    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sqlbuilder.append(iq.buildSQL(DeliveryBVO.CSRCBID, srcBids));

    sqlbuilder.append(" group by " + DeliveryBVO.CSRCBID);
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sqlbuilder.toString());
    Map<String, UFDouble> valueMap = new HashMap<String, UFDouble>();
    if (null != rowset && rowset.size() != 0) {
      while (rowset.next()) {
        valueMap.put(rowset.getString(1), rowset.getUFDouble(0));
      }
    }
    return valueMap;
  }
}
