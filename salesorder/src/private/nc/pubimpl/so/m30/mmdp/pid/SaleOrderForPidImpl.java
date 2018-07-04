package nc.pubimpl.so.m30.mmdp.pid;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.BusinessException;
import nc.vo.pub.JavaType;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.m30.mmdp.pid.ISaleOrderForPid;
import nc.pubitf.so.m30.mmdp.pid.ParaMMVO;
import nc.pubitf.so.m30.mmdp.pid.ParaVO;
import nc.pubitf.so.m30.mmdp.pid.ParaVO.ParaVOKeyEnum;
import nc.pubitf.so.m30.mmdp.pid.ResultVO;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.TempTable;

/**
 * 销售订单提供给计划独立需求的接口实现
 * 
 * @since 6.0
 * @version 2011-12-5 下午04:21:51
 * @author 么贵敬
 */
public class SaleOrderForPidImpl implements ISaleOrderForPid {

  private static final String[] COLUMNS = new String[] {
    SaleOrderBVO.CSENDSTOCKORGID, SaleOrderBVO.CMATERIALID,
    SaleOrderBVO.CVENDORID, SaleOrderBVO.CPRODUCTORID, SaleOrderBVO.CPROJECTID,
    SaleOrderHVO.CCUSTOMERID, SaleOrderBVO.VFREE1, SaleOrderBVO.VFREE2,
    SaleOrderBVO.VFREE3, SaleOrderBVO.VFREE4, SaleOrderBVO.VFREE5,
    SaleOrderBVO.VFREE6, SaleOrderBVO.VFREE7, SaleOrderBVO.VFREE8,
    SaleOrderBVO.VFREE9, SaleOrderBVO.VFREE10, SaleOrderBVO.CMFFILEID
  };

  private static final String[] COLUMNTYPES = new String[] {
    "varchar(20)", "varchar(20)", "varchar(20)", "varchar(20)", "varchar(20)",
    "varchar(20)", "varchar(101)", "varchar(101)", "varchar(101)",
    "varchar(101)", "varchar(101)", "varchar(101)", "varchar(101)",
    "varchar(101)", "varchar(101)", "varchar(101)", "varchar(20)"
  };

  private static final JavaType[] JAVATYPES = new JavaType[] {
    JavaType.String, JavaType.String, JavaType.String, JavaType.String,
    JavaType.String, JavaType.String, JavaType.String, JavaType.String,
    JavaType.String, JavaType.String, JavaType.String, JavaType.String,
    JavaType.String, JavaType.String, JavaType.String, JavaType.String, 
    JavaType.String
  };

  @Override
  public List<ResultVO> queryOrderNnum(ParaMMVO parammvo)
      throws BusinessException {
    TempTable temp = new TempTable();
    List<List<Object>> datas = new ArrayList<List<Object>>();
    this.PrepareDatas(datas, parammvo);

    String tablename =
        temp.getTempTable(SOTable.TMP_SO_PID.getName(),
            SaleOrderForPidImpl.COLUMNS, SaleOrderForPidImpl.COLUMNTYPES,
            SaleOrderForPidImpl.JAVATYPES, datas);
    SqlBuilder sql = new SqlBuilder();
    this.appendSQL(sql, parammvo, tablename);
    String groupsql = this.getGroupSQL(parammvo);
    DataAccessUtils utils = new DataAccessUtils();
    List<ResultVO> results = new ArrayList<ResultVO>();
    try {
      IRowSet rowset = utils.query(sql.toString() + groupsql.toString());

      while (rowset.next()) {
        ResultVO ret = new ResultVO();
        int i = 0;
        for (ParaVOKeyEnum key : parammvo.getKeys()) {
          ret.setAttributeValue(key.getValue(), rowset.getString(i));
          i++;
        }
        ret.setAttributeValue(SaleOrderBVO.DSENDDATE,
            new UFDate(rowset.getString(i++)));
        ret.setAttributeValue(SaleOrderBVO.NNUM, rowset.getUFDouble(i++));
        results.add(ret);
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }

    return results;
  }

  private void appendFixCondition(SqlBuilder sql) {
    sql.append(" and ");
    sql.append("orderb." + SaleOrderBVO.PK_GROUP, AppContext.getInstance()
        .getPkGroup());

    sql.append(" and ");
    sql.append("orderb." + SaleOrderBVO.NNUM + ">0");
  }

  private void appendSQL(SqlBuilder sql, ParaMMVO parammvo, String tablename) {
    sql.append("select ");
    for (ParaVOKeyEnum key : parammvo.getKeys()) {
      if (key.getValue().equals(SaleOrderHVO.CCUSTOMERID)) {
        sql.append("h." + key.getValue() + " as " + key.getValue());
        sql.append(",");
        continue;
      }
      sql.append("orderb." + key.getValue() + " as " + key.getValue());
      sql.append(",");
    }
    sql.append(SaleOrderBVO.DSENDDATE);
    sql.append(",");
    sql.append("sum(isnull(nnum,0)) as nnum");

    sql.append(" from so_saleorder_b orderb ");
    boolean iscontcustomerid = this.isConcust(parammvo);
    if (iscontcustomerid) {
      sql.append(" inner join so_saleorder h ");
      sql.append(" on ");

      sql.append("orderb." + SaleOrderHVO.CSALEORDERID + "=h."
          + SaleOrderHVO.CSALEORDERID);

      sql.append(" and ");
      sql.append("h.dr=0");

    }
    sql.append(" inner join " + tablename);
    sql.append(" on ");
    for (ParaVOKeyEnum key : parammvo.getKeys()) {
      if (key.getValue().equals(SaleOrderHVO.CCUSTOMERID)) {
        sql.append(tablename + "." + SaleOrderHVO.CCUSTOMERID + "=h."
            + SaleOrderHVO.CCUSTOMERID);
        sql.append(" and ");
        continue;
      }
      sql.append("orderb." + key.getValue() + "=" + tablename + "."
          + key.getValue());
      sql.append(" and ");
    }
    sql.append(" orderb.dr =0");

    sql.append(" where ");
    sql.append(SaleOrderBVO.DSENDDATE, ">=", parammvo.getCbegindate()
        .toString());
    sql.append(" and ");
    sql.append(SaleOrderBVO.DSENDDATE, "<=", parammvo.getCenddate().toString());

    this.appendFixCondition(sql);
  }

  private String getGroupSQL(ParaMMVO parammvo) {
    SqlBuilder groupsql = new SqlBuilder();
    groupsql.append(" group by ");
    for (ParaVOKeyEnum key : parammvo.getKeys()) {
      if (key.getValue().equals(SaleOrderHVO.CCUSTOMERID)) {
        groupsql.append("h." + key.getValue());
        groupsql.append(",");
        continue;
      }
      groupsql.append("orderb." + key.getValue());
      groupsql.append(",");
    }
    groupsql.append(SaleOrderBVO.DSENDDATE);
    return groupsql.toString();
  }

  private boolean isConcust(ParaMMVO parammvo) {
    if (parammvo.getKeys().contains(ParaVOKeyEnum.CCUSTOMERID)) {
      return true;
    }
    return false;
  }

  private void PrepareDatas(List<List<Object>> datas, ParaMMVO parammvo) {

    List<ParaVO> paras = parammvo.getParas();
    for (ParaVO para : paras) {
      List<Object> data = new ArrayList<Object>();
      for (String key : SaleOrderForPidImpl.COLUMNS) {
        data.add(para.getAttributeValue(key));
      }
      datas.add(data);
    }
  }

}
