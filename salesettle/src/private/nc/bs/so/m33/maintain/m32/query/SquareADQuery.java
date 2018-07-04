package nc.bs.so.m33.maintain.m32.query;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.votools.SoVoTools;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>差额传应收相关查询
 * <li>...
 * </ul>
 * 
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhangcheng
 * @time 2010-7-6 下午04:41:13
 */
public class SquareADQuery {

  /**
   * 查询出库待结算单金额汇总信息
   * 
   * @param outBids
   * @return Map
   */
  public Map<String, UFDouble[]> queryTotalSquareADMnyBy4C(String[] outBids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select so_squareinv_b.csrcbid ");
    sql.append(",sum(isnull(so_squareinv_d.norigtaxmny,.0)) ");
    sql.append(",sum(isnull(so_squareinv_d.norigmny,.0)) ");
    sql.append(" from so_squareinv_d,so_squareinv_b ");
    sql.append(" where so_squareinv_d.csalesquarebid = so_squareinv_b.csalesquarebid ");
    sql.append(" and so_squareinv_b.dr=0 and so_squareinv_d.dr=0 and ");
    sql.append(" so_squareinv_b.fpreartype",
        SquareType.SQUARETYPE_BALANCEAR.getIntValue());
    sql.append(" and ");
    sql.append(" so_squareinv_d.fsquaretype",
        SquareType.SQUARETYPE_AR.getIntValue());
    sql.append(" and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(idqb.buildSQL("so_squareinv_b.CSRCBID", outBids));
    sql.append(" group by so_squareinv_b.CSRCBID ");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());

    Map<String, UFDouble[]> mInvADMny = new HashMap<String, UFDouble[]>();
    while (rowset.next()) {
      mInvADMny.put(rowset.getString(0), new UFDouble[] {
        rowset.getUFDouble(1), rowset.getUFDouble(2)
      });
    }
    return mInvADMny;
  }

  /**
   * 方法功能描述：查询同一行上游出库单下游发票累计结算信息
   * <p>
   * <出库单行id, [0]发票累计结算数量 [1]发票累计结算含税金额 [2]发票累计结算无税金额
   * <p>
   * 
   * @param vos
   * @return <出库单行id,
   *         [0]发票累计结算数量
   *         [1]发票累计结算含税金额
   *         [2]发票累计结算无税金额
   *         <p>
   * @author zhangcheng
   * @time 2010-7-1 下午07:12:51
   */
  public Map<String, UFDouble[]> queryTotalSquareNumBy4C(SquareInvVO[] vos) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select so_squareinv_b.CSRCBID,");
    sql.append("sum(isnull(so_squareinv_b.nnum,.0))");
    sql.append(",sum(isnull(so_squareinv_b.norigtaxmny,.0)) ");
    sql.append(",sum(isnull(so_squareinv_b.norigmny,.0)) ");
    sql.append("from so_squareinv_b where so_squareinv_b.dr=0 and ");
    SquareInvBVO[] bvos = SquareInvVOUtils.getInstance().getSquareInvBVO(vos);
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(idqb.buildSQL("so_squareinv_b.CSRCBID",
        SoVoTools.getVOsOnlyValues(bvos, SquareInvBVO.CSRCBID)));
    sql.append(" group by so_squareinv_b.CSRCBID ");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());

    Map<String, UFDouble[]> m4Cbid_num = new HashMap<String, UFDouble[]>();
    while (rowset.next()) {
      m4Cbid_num.put(rowset.getString(0), new UFDouble[] {
        rowset.getUFDouble(1), rowset.getUFDouble(2), rowset.getUFDouble(3)
      });
    }
    return m4Cbid_num;
  }
}
