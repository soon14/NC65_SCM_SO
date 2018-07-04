/**
 * $文件说明$
 * 
 * @author 么贵敬
 * @version 6.0
 * @see
 * @since
 * @time 2010-11-5 上午11:12:47
 */
package nc.pubimpl.so.m30.pu.m21;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.pubitf.so.m30.pu.m21.ISaleorderQryFor21;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 销售订单提供给采购订单的查询接口实现类
 * 
 * @since 6.0
 * @version 2010-12-15 上午09:03:41
 * @author 么贵敬
 */
public class SaleorderQryFor21Impl implements ISaleorderQryFor21 {

  @Override
  public Map<String, UFDouble> getSaleOrderNumber(String[] cmaterialid,
      UFDate queryDate, Integer queryDay, String pk_group, String pk_org) {
    String sql =
        this.getWhereSQL(cmaterialid, queryDate, queryDay, pk_group, pk_org);

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    Map<String, UFDouble> map = new HashMap<String, UFDouble>();
    while (rowset.next()) {
      map.put(rowset.getString(1), rowset.getUFDouble(0));
    }
    return map;
  }

  private String getWhereSQL(String[] cmaterialid, UFDate queryDate,
      Integer queryDay, String pk_group, String pk_org) {
    UFDate startDate = queryDate.getDateBefore(queryDay.intValue());
    UFDate endDate = queryDate;
    SqlBuilder sql = new SqlBuilder();
    sql.append("select sum(isnull(so_saleorder_b.nnum,0)) as totalnum,"
        + "so_saleorder_b.cmaterialvid"
        + " from so_saleorder_b inner join so_saleorder on"
        + " so_saleorder.csaleorderid = so_saleorder_b.csaleorderid");
    sql.append(" where ");
    sql.append("so_saleorder.pk_org", pk_org);
    sql.append(" and ");
    sql.append("so_saleorder_b.pk_org", pk_org);
    sql.append(" and ");
    sql.append("so_saleorder.dbilldate", "<=", endDate.toString());
    sql.append(" and ");
    sql.append("so_saleorder.dbilldate", ">=", startDate.toString());
    sql.append(" and ");
    sql.append("so_saleorder_b.dbilldate", "<=", endDate.toString());
    sql.append(" and ");
    sql.append("so_saleorder_b.dbilldate", ">=", startDate.toString());
    sql.append(" and ");
    sql.startParentheses();
    sql.append("so_saleorder.fstatusflag", (Integer) BillStatus.AUDIT.value());
    sql.append(" or ");
    sql.append("so_saleorder.fstatusflag", (Integer) BillStatus.CLOSED.value());
    sql.endParentheses();
    sql.append(" and ");
    sql.append("so_saleorder.dr", 0);
    sql.append(" and ");
    sql.append("so_saleorder_b.dr", 0);
    sql.append(" and ");
    sql.append("so_saleorder.pk_group", pk_group);
    // 拼写INsql语句
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String insql = iq.buildSQL("so_saleorder_b.cmaterialid", cmaterialid);
    sql.append(" and ");
    sql.append(insql);
    sql.append(" group by so_saleorder_b.cmaterialvid");
    return sql.toString();
  }

}
