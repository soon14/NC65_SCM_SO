package nc.pubimpl.so.m33.self.pub;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.m33.self.pub.ISquare4353Query;

import nc.bs.so.m33.maintain.m4453.query.QuerySquare4453VOBP;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 销售结算为途损单提供的查询服务实现类
 * 
 * @since 6.1
 * @version 2012-11-29 10:34:15
 * @author 冯加彬
 */
public class Square4353QueryImpl implements ISquare4353Query {

  @Override
  public Map<String, UFDouble[]> queryARNumBy4CBID(String[] outBids) {
    Map<String, UFDouble[]> mOutBidNum =
        this.queryARNumMnyBy4CBIDAndSquareType(outBids,
            SquareType.SQUARETYPE_AR);
    return mOutBidNum;
  }

  /**
   * /**
   * 根据上游出库单行ID和待查结算类型查询下游途损待结算单相关信息
   * 
   * @param outBids
   * @param type
   * @return Map
   */
  public Map<String, UFDouble[]> queryARNumMnyBy4CBIDAndSquareType(
      String[] outBids, SquareType type) {
    SqlBuilder hsql = new SqlBuilder();
    hsql.append("select csrcbid,sum(isnull(NNUM,.0)) arnum,");
    hsql.append(" sum(isnull(norigtaxmny,.0)) artaxmny,");
    hsql.append(" sum(isnull(norigmny,.0)) armny from so_squarewas_b ");
    hsql.append(" where dr = 0 and  vsrctype = '4C' and ");
    hsql.append(" fpreartype", type.getIntValue());
    hsql.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareInvBVO.CSRCBID, outBids);
    hsql.append(where);
    hsql.append(" group by csrcbid ");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(hsql.toString());

    // 封装结果--<出库单行id,累计回冲应收数量>
    Map<String, UFDouble[]> mOutBidNum = null;
    int size = rowset.size();
    if (size > 0) {
      mOutBidNum = new HashMap<String, UFDouble[]>();
      while (rowset.next()) {
        mOutBidNum.put(rowset.getString(0), new UFDouble[] {
          rowset.getUFDouble(1), rowset.getUFDouble(2), rowset.getUFDouble(3)
        });
      }

    }
    return mOutBidNum;

  }

  @Override
  public Map<String, UFDouble[]> queryARRushNumBy4CBID(String[] outBids) {
    Map<String, UFDouble[]> mOutBidNum =
        this.queryARNumMnyBy4CBIDAndSquareType(outBids,
            SquareType.SQUARETYPE_ARRUSH);
    return mOutBidNum;

  }

  @Override
  public SquareWasDetailVO[] querySquareWasDetailVOByBIDForETRushSquare(
      String[] wasBids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and FSQUARETYPE = "
        + SquareType.SQUARETYPE_ARRUSH.getIntValue() + " and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(SquareWasDetailVO.CSQUAREBILLBID, wasBids));
    return new QuerySquare4453VOBP().querySquareWasDetailVOByCondition(
        sql.toString(), null);
  }

  @Override
  public SquareWasViewVO[] querySquareWasDetailVOByBIDForNoSquare(
      String[] wasBids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select csalesquarebid from so_squarewas_b where ");

    sql.startParentheses();
    sql.append("(isnull(so_squarewas_b.nsquareianum,.0)=0 and ");
    sql.append("so_squarewas_b.fpreartype="
        + SquareType.SQUARETYPE_IA.getIntValue() + ") ");
    sql.append(" or ");
    sql.append("(isnull(so_squarewas_b.nsquarearnum,.0)=0 and ");
    sql.append("so_squarewas_b.fpreartype="
        + SquareType.SQUARETYPE_AR.getIntValue() + ") ");
    sql.endParentheses();

    sql.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(SquareWasBVO.CSQUAREBILLBID, wasBids));
    return new QuerySquare4453VOBP().querySquareWasViewVO(sql.toString());
  }

  @Override
  public SquareWasDetailVO[] querySquareWasDetailVOByBIDForREGSquare(
      String[] wasBids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and FSQUARETYPE = "
        + SquareType.SQUARETYPE_REG_DEBIT.getIntValue() + " and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(SquareWasDetailVO.CSQUAREBILLBID, wasBids));
    return new QuerySquare4453VOBP().querySquareWasDetailVOByCondition(
        sql.toString(), null);
  }

  @Override
  public SquareWasDetailVO[] querySquareWasDetailVOByBIDForSquare(
      String[] wasBids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and ( FSQUARETYPE = " + SquareType.SQUARETYPE_IA.getIntValue()
        + " or FSQUARETYPE = " + SquareType.SQUARETYPE_IA.getIntValue()
        + ") and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(SquareWasDetailVO.CSQUAREBILLBID, wasBids));
    return new QuerySquare4453VOBP().querySquareWasDetailVOByCondition(
        sql.toString(), null);
  }

  @Override
  public SquareWasViewVO[] querySquareWasViewVOByBID(String[] bids) {
    return new QuerySquare4453VOBP().querySquareWasViewVOByBID(bids);
  }

  @Override
  public SquareWasViewVO[] querySquareWasViewVOByBIDForNoETRushSquare(
      String[] wasBids) {

    SqlBuilder sql = new SqlBuilder();
    sql.append("select csalesquarebid from so_squarewas_b where ");
    sql.append("(isnull(so_squarewas_b.narrushnum,.0)=0 ");
    sql.append("and so_squarewas_b.fpreartype=3) and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(SquareWasBVO.CSQUAREBILLBID, wasBids));

    return new QuerySquare4453VOBP().querySquareWasViewVO(sql.toString());
  }

  @Override
  public SquareWasViewVO[] querySquareWasViewVOByBIDForNoREGSquare(
      String[] wasBids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select csalesquarebid from so_squarewas_b where ");
    sql.append("(isnull(so_squarewas_b.nsquareregnum,.0)=0 and ");
    sql.append("so_squarewas_b.fpreiatype=5) and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(SquareWasBVO.CSQUAREBILLBID, wasBids));

    return new QuerySquare4453VOBP().querySquareWasViewVO(sql.toString());
  }

}
