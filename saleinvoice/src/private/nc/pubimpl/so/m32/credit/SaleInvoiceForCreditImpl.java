package nc.pubimpl.so.m32.credit;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.m32.credit.ISaleInvoiceForCredit;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 销售发票给信用帐期的接口
 * 
 * @since 6.0
 * @version 2011-6-28 下午05:55:17
 * @author 么贵敬
 */
public class SaleInvoiceForCreditImpl implements ISaleInvoiceForCredit {

  @Override
  public Map<String, UFDate[]> getBusiDateBy32Bids(String[] bids)
      throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    String maintable = "so_saleinvoice";
    String subable = "so_saleinvoice_b";
    sql.append("select ");
    sql.append(subable + "." + SaleInvoiceBVO.CSALEINVOICEBID);
    sql.append("," + maintable + "." + SaleInvoiceHVO.DBILLDATE);
    sql.append("," + maintable + "." + SaleInvoiceHVO.TAUDITTIME);
    sql.append(" from ");
    sql.append(maintable);
    sql.append(" inner join ");
    sql.append(subable);
    sql.append(" on ");
    sql.append(maintable + "." + SaleInvoiceHVO.CSALEINVOICEID + "=" + subable
        + "." + SaleInvoiceBVO.CSALEINVOICEID);
    sql.append(" where ");

    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq
        .buildSQL(subable + "." + SaleInvoiceBVO.CSALEINVOICEBID, bids));
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    Map<String, UFDate[]> map = new HashMap<String, UFDate[]>();
    while (rowset.next()) {
      UFDate[] dates = new UFDate[2];
      dates[0] = rowset.getUFDate(1);
      dates[1] = rowset.getUFDate(2);
      map.put(rowset.getString(0), dates);
    }
    return map;
  }

  @Override
  public Map<String, UFDate[]> getBusiDateBy4CBids(String[] bids)
      throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    String maintable = "so_saleinvoice";
    String subable = "so_saleinvoice_b";
    sql.append("select ");
    sql.append(subable + "." + SaleInvoiceBVO.CSALEINVOICEBID);
    sql.append("," + maintable + "." + SaleInvoiceHVO.DBILLDATE);
    sql.append("," + maintable + "." + SaleInvoiceHVO.TAUDITTIME);
    sql.append(" from ");
    sql.append(maintable);
    sql.append(" inner join ");
    sql.append(subable);
    sql.append(" on ");
    sql.append(maintable + "." + SaleInvoiceHVO.CSALEINVOICEID + "=" + subable
        + "." + SaleInvoiceBVO.CSALEINVOICEID);
    sql.append(" where ");

    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(subable + "." + SaleInvoiceBVO.CSRCBID, bids));
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    Map<String, UFDate[]> map = new HashMap<String, UFDate[]>();
    while (rowset.next()) {
      UFDate[] dates = new UFDate[2];
      dates[0] = rowset.getUFDate(1);
      dates[1] = rowset.getUFDate(2);
      map.put(rowset.getString(0), dates);
    }
    return map;
  }
}
