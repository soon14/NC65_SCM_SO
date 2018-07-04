package nc.pubimpl.so.m4310.crm;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

import nc.pubitf.so.m4310.crm.CRMQueryPara;
import nc.pubitf.so.m4310.crm.ISaleQuotationQueryForCRM;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;

/**
 * 为CRM提供报价单查询接口实现类
 * 
 * @since 6.3.1
 * @version 2013-08-06 20:04:29
 * @author 张云枫
 */
public class SaleQuotationQueryForCRMImpl implements ISaleQuotationQueryForCRM {

  @Override
  public SalequotationHVO[] querySaleQuotationVOs(CRMQueryPara queryPara)
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
      VOQuery<SalequotationHVO> query =
          new VOQuery<SalequotationHVO>(SalequotationHVO.class);
      String[] ids = list.toArray(new String[list.size()]);
      SalequotationHVO[] rets = query.query(ids);
      return rets;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SalequotationBVO[] querySaleQuotationVOsById(String id)
      throws BusinessException {
    SalequotationBVO[] rets = null;
    try {
      VOQuery<SalequotationBVO> query =
          new VOQuery<SalequotationBVO>(SalequotationBVO.class);
      SqlBuilder condition = new SqlBuilder();
      condition.append("and pk_salequotation = '" + id + "'");
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
   * @param CRM参数对象
   * @return SQL语句
   */
  private String getQuerySql(CRMQueryPara queryPara) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select sq.pk_salequotation ");
    sql.append("from so_salequotation sq where sq.dr = 0 ");
    if (!PubAppTool.isNull(queryPara.getBusid())) {
      sql.append("and ");
      sql.append("sq.cbillsrcid", queryPara.getBusid());
    }
    sql.append(" and ");
    sql.append("sq.pk_customer", queryPara.getCustomerid());
    sql.append(" and sq.dquotedate");
    sql.append(" >= '" + queryPara.getDfromdate() + "'");
    sql.append(" and sq.dquotedate");
    sql.append(" <= '" + queryPara.getDtodate() + "'");
    sql.append("order by sq.dquotedate desc ");

    return sql.toString();
  }

}
