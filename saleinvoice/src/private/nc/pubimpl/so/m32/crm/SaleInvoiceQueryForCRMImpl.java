package nc.pubimpl.so.m32.crm;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

import nc.pubitf.so.m32.crm.ISaleInvoiceQueryForCRM;
import nc.pubitf.so.m4310.crm.CRMQueryPara;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;

/**
 * 为CRM提供销售发票查询接口的实现类
 * 
 * @since 6.3.1
 * @version 2013-08-08 09:16:39
 * @author 张云枫
 */
public class SaleInvoiceQueryForCRMImpl implements ISaleInvoiceQueryForCRM {

  @Override
  public SaleInvoiceHVO[] querySaleInvoice(CRMQueryPara queryPara)
      throws BusinessException {

    try {
      String querysql = this.getQuerySql(queryPara);

      DataAccessUtils dao = new DataAccessUtils();
      IRowSet rowset = dao.query(querysql);

      int size = rowset.size();
      int start = queryPara.getNstartcount() - 1;
      int length = queryPara.getNendcount() - start;
      // 开始位置大于总结果数
      if (size <= 0 || start > size) {
        return null;
      }

      for (int i = 0; i < start; i++) {
        rowset.next();
      }
      int cursor = 0;
      List<String> list = new ArrayList<String>();
      while (cursor < length && rowset.next()) {
        list.add(rowset.getString(0));
        cursor++;
      }
      VOQuery<SaleInvoiceHVO> query =
          new VOQuery<SaleInvoiceHVO>(SaleInvoiceHVO.class);
      String[] ids = list.toArray(new String[list.size()]);
      SaleInvoiceHVO[] rets = query.query(ids);
      return rets;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;

  }

  @Override
  public SaleInvoiceBVO[] querySaleInvoiceById(String id)
      throws BusinessException {

    SaleInvoiceBVO[] rets = null;
    try {
      VOQuery<SaleInvoiceBVO> query =
          new VOQuery<SaleInvoiceBVO>(SaleInvoiceBVO.class);
      SqlBuilder condition = new SqlBuilder();
      condition.append(" and csaleinvoiceid = '" + id + "'");
      rets = query.query(condition.toString(), null);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return rets;
  }

  /**
   * 拼接sql语句
   * 
   * @param queryPara
   * @return
   */
  private String getQuerySql(CRMQueryPara queryPara) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct si.csaleinvoiceid pk,si.dbilldate ");
    sql.append("from so_saleinvoice si ,so_saleinvoice_b si_b where ");
    sql.append("si.csaleinvoiceid = si_b.csaleinvoiceid and ");
    sql.append("si.cinvoicecustid", queryPara.getCustomerid());
    sql.append(" and si.dbilldate");
    sql.append(" >= '" + queryPara.getDfromdate() + "'");
    sql.append(" and si.dbilldate");
    sql.append(" <= '" + queryPara.getDtodate() + "'");
    sql.append(" and si.dr = 0 ");
    sql.append("order by si.dbilldate desc ");

    return sql.toString();
  }

}
