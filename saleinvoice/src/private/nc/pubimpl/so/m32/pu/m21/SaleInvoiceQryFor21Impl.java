package nc.pubimpl.so.m32.pu.m21;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.pubitf.so.m32.pu.m21.ISaleInvoiceQryFor21;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 销售发票提供给采购订单的查询功能接口实现类
 * 
 * @since 6.0
 * @version 2010-12-15 上午09:05:53
 * @author 么贵敬
 */
public class SaleInvoiceQryFor21Impl implements ISaleInvoiceQryFor21 {

  @Override
  public Map<String, UFDouble> getInvInvoiceNumber(String[] cmaterialid,
      UFDate queryDate, Integer queryDay, String pk_group, String saleorg)
      throws BusinessException {

    UFDate startDate = queryDate.getDateBefore(queryDay.intValue());
    UFDate endDate = queryDate;
    SqlBuilder sql = new SqlBuilder();
    sql.append("select sum(isnull(so_saleinvoice_b.nnum,0)) as totalnum,"
        + "so_saleinvoice_b.cmaterialvid"
        + " from so_saleinvoice_b inner join so_saleinvoice on"
        + " so_saleinvoice.csaleinvoiceid = so_saleinvoice_b.csaleinvoiceid");
    sql.append(" where ");
    sql.append("so_saleinvoice.dbilldate", "<=", endDate.toString());
    sql.append(" and ");
    sql.append("so_saleinvoice.dbilldate", ">=", startDate.toString());
    sql.append(" and ");
    sql.append("so_saleinvoice_b.dbilldate", "<=", endDate.toString());
    sql.append(" and ");
    sql.append("so_saleinvoice_b.dbilldate", ">=", startDate.toString());
    sql.append(" and ");
    sql.append("so_saleinvoice_b.csaleorgid", saleorg);
    sql.append(" and ");
    sql.append("so_saleinvoice.fstatusflag", (Integer) BillStatus.AUDIT.value());
    sql.append(" and ");
    sql.append("so_saleinvoice.dr", 0);
    sql.append(" and ");
    sql.append("so_saleinvoice_b.dr", 0);
    sql.append(" and ");
    sql.append("so_saleinvoice.pk_group", pk_group);

    // 拼写INsql语句
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String insql = iq.buildSQL("so_saleinvoice_b.cmaterialvid", cmaterialid);
    sql.append(" and ");
    sql.append(insql);
    sql.append(" group by so_saleinvoice_b.cmaterialvid");

    DataAccessUtils utils = new DataAccessUtils();
    Map<String, UFDouble> map = new HashMap<String, UFDouble>();
    try {
      IRowSet rowset = utils.query(sql.toString());
      while (rowset.next()) {
        map.put(rowset.getString(1), rowset.getUFDouble(0));
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return map;
  }

}
