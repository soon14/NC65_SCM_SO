package nc.pubimpl.so.m30.mmdp.sop;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.util.TimeUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOTable;
import nc.vo.to.m5p.enumeration.BillStatus;

import nc.pubitf.so.m30.mmdp.sop.ISaleOrderForSop;
import nc.pubitf.so.m30.mmdp.sop.ParaMMVO;
import nc.pubitf.so.m30.mmdp.sop.ParaVO;
import nc.pubitf.so.m30.mmdp.sop.ResultVO;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 
 * @since 6.0
 * @version 2011-12-5 ÏÂÎç04:21:51
 * @author Ã´¹ó¾´
 */
public class SaleOrderForSopImpl implements ISaleOrderForSop {

  @Override
  public List<ResultVO> queryOrderNnum(ParaMMVO parammvo)
      throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    this.appendSQL(sql, parammvo);
    SqlBuilder groupsql = new SqlBuilder();
    groupsql.append(" group by ");
    groupsql.append(SaleOrderBVO.CSENDSTOCKORGID);
    groupsql.append(",");
    groupsql.append(SaleOrderBVO.CMATERIALID);
    groupsql.append(",");
    groupsql.append(SaleOrderBVO.DSENDDATE);
    List<ParaVO> paras = parammvo.getParas();
    List<ResultVO> results = new ArrayList<ResultVO>();
    DataAccessUtils utils = new DataAccessUtils();
    try {
      for (ParaVO para : paras) {
        SqlBuilder dsql = new SqlBuilder();
        dsql.append(" and ");
        dsql.append(SaleOrderBVO.CSENDSTOCKORGID, para.getCsendstockorgid());
        dsql.append(" and ");
        IDExQueryBuilder iq =
            new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
        dsql.append(iq.buildSQL(SaleOrderBVO.CMATERIALID,
            para.getCmaterialids()));
        String sqls = sql.toString() + dsql.toString() + groupsql.toString();
        IRowSet rowset = utils.query(sqls);
        while (rowset.next()) {
          ResultVO ret = new ResultVO();
          ret.setCsendstockorgid(rowset.getString(0));
          ret.setCmaterialid(rowset.getString(1));
          ret.setDsenddate(rowset.getUFDate(2));
          ret.setNnum(rowset.getUFDouble(3));
          results.add(ret);
        }
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }

    return results;
  }

  private void appendFixCondition(SqlBuilder sql) {
    sql.append(" and ");
    sql.append(SaleOrderBVO.METAPATH + SaleOrderBVO.PK_GROUP, AppContext
        .getInstance().getPkGroup());
    sql.append(" and ");
    sql.append(SaleOrderBVO.METAPATH + "dr=0");

    sql.append(" and ");
    sql.append("so_saleorder." + SaleOrderBVO.PK_GROUP, AppContext
        .getInstance().getPkGroup());
    sql.append(" and ");
    sql.append("so_saleorder.dr=0");

    sql.append(" and ");
    sql.append("so_saleorder." + SaleOrderHVO.FSTATUSFLAG, BillStatus.AUDIT);
  }

  private void appendSQL(SqlBuilder sql, ParaMMVO parammvo) {
    sql.append("select ");
    sql.append(SaleOrderBVO.CSENDSTOCKORGID);
    sql.append(",");
    sql.append(SaleOrderBVO.CMATERIALID);
    sql.append(",");
    sql.append("substr(" + SaleOrderBVO.DSENDDATE + ",0,10)");
    sql.append(",");
    sql.append("sum(isnull(nnum,0)) as nnum");

    sql.append(" from so_saleorder_b so_saleorder_b");
    sql.append(" inner join so_saleorder_exe so_saleorder_exe");
    sql.append(" on so_saleorder_b.csaleorderbid=so_saleorder_exe.csaleorderbid");

    sql.append(" inner join so_saleorder so_saleorder");
    sql.append(" on so_saleorder_b.csaleorderid=so_saleorder.csaleorderid");
    sql.append(" where ");
    sql.append(SaleOrderBVO.DSENDDATE, ">=", parammvo.getCbegindate()
        .toString());
    sql.append(" and ");
    sql.append(SaleOrderBVO.DSENDDATE, "<=",
        TimeUtils.getEndDate(parammvo.getCenddate()).toString());
    this.appendFixCondition(sql);
  }

}
