package nc.pubimpl.so.m33.self.pub;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.AggVOUtil;

import nc.pubitf.so.m33.self.pub.ISquare434CQuery;

import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 销售结算为销售出库单提供的查询服务实现类
 * 
 * @since 6.1
 * @version 2012-11-29 10:19:13
 * @author 冯加彬
 */
public class Square434CQueryImpl implements ISquare434CQuery {

  private QuerySquare4CVOBP queryBP;

  /**
   * 构造子
   */
  public Square434CQueryImpl() {
    this.queryBP = new QuerySquare4CVOBP();
  }

  @Override
  public Map<String, UFDouble[]> queryARNumBy4CBID(String[] outBids) {
    Map<String, UFDouble[]> mOutBidNum =
        this.queryARNumMnyBy4CBIDAndSquareType(outBids,
            SquareType.SQUARETYPE_AR);
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
  public String[] queryETIncomeBidBy4CBID(String[] outBids) {
    SquareOutDetailVO[] sdvos =
        this.querySquareOutDetailVOBy4CBIDUseSquareType(outBids,
            SquareType.SQUARETYPE_ET.getIntegerValue());
    String[] etOutBids =
        AggVOUtil.getDistinctFieldArray(sdvos,
            SquareOutDetailVO.CSQUAREBILLBID, String.class);
    return etOutBids;
  }

  @Override
  public Map<String, SquareOutDetailVO> queryETIncomeDvosBy4CBID(
      String[] outBids) {
	Map<String, SquareOutDetailVO> map = new HashMap<String, SquareOutDetailVO>();
	if(outBids==null||outBids.length==0){
		return map;
	}
    SquareOutDetailVO[] sdvos =
        this.querySquareOutDetailVOBy4CBIDUseSquareType(outBids,
            SquareType.SQUARETYPE_ET.getIntegerValue());

    if (sdvos != null) {
      for (SquareOutDetailVO dvo : sdvos) {
        map.put(dvo.getCsquarebillbid(), dvo);
      }
    }
    return map;
  }

  @Override
  public SquareOutViewVO[] queryETIncomeREGCostBidBy4CBID(String[] outBids) {
    SqlBuilder hsql = new SqlBuilder();
    hsql.append("select csalesquarebid from so_squareout_b where dr = 0 and ");
    hsql.append("(");
    hsql.append("(fpreartype", SquareType.SQUARETYPE_ET.getIntValue());
    hsql.append(" and isnull(nsquareestnum,.0)!=0) ");
    hsql.append(" or ");
    hsql.append("(fpreiatype", SquareType.SQUARETYPE_REG_DEBIT.getIntValue());
    hsql.append(" and isnull(nsquareregnum,.0)!=0) ");
    hsql.append(") and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CSQUAREBILLBID, outBids);
    hsql.append(where);
    String[] bids = this.queryBP.queryIDsFromSql(hsql.toString());
    return this.queryBP.querySquareOutViewVOByBID(bids);
  }

  /**
   * 根据出库单表体DI查询暂估应收的出库单结算明细VO
   * 
   * @param outBids
   * @return 出库单结算明细VO
   */
  public SquareOutDetailVO[] queryETIncomeSquareOutDetailVOBy4CBID(
      String[] outBids) {
    SquareOutDetailVO[] sdvos =
        this.querySquareOutDetailVOBy4CBIDUseSquareType(outBids,
            SquareType.SQUARETYPE_ET.getIntegerValue());
    return sdvos;
  }

  @Override
  public String[] queryREGCostBidBy4CBID(String[] outBids) {
    SquareOutDetailVO[] sdvos =
        this.querySquareOutDetailVOBy4CBIDUseSquareType(outBids,
            SquareType.SQUARETYPE_REG_DEBIT.getIntegerValue());
    String[] etOutBids =
        AggVOUtil.getDistinctFieldArray(sdvos,
            SquareOutDetailVO.CSQUAREBILLBID, String.class);
    return etOutBids;
  }

  /**
   * 根据出库单表体DI查询计入发出商品的出库单结算明细VO
   * 
   * @param outBids
   * @return 出库结算明细VO
   */
  public SquareOutDetailVO[] queryREGCostSquareOutDetailVOBy4CBID(
      String[] outBids) {
    SquareOutDetailVO[] sdvos =
        this.querySquareOutDetailVOBy4CBIDUseSquareType(outBids,
            SquareType.SQUARETYPE_REG_DEBIT.getIntegerValue());
    return sdvos;
  }

  /**
   * 非出库对冲销售出库单传发出商品贷方明细数据
   * 
   * @param bids
   * @return 出库结算明细VO
   */
  public SquareOutDetailVO[] queryREGCreditSquareOutDetailVOByBID(String[] bids) {
    String[] dids = null;
    SqlBuilder hsql = new SqlBuilder();
    hsql.append("select csalesquaredid from so_squareout_d where dr = 0 and ");
    hsql.append(" boutrushflag = 'N' and ");
    hsql.append("fsquaretype", SquareType.SQUARETYPE_REG_CREDIT.getIntValue());
    hsql.append("and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CSALESQUAREBID, bids);
    hsql.append(where);
    QuerySquare4CVOBP qry = new QuerySquare4CVOBP();
    dids = qry.queryIDsFromSql(hsql.toString());
    return qry.querySquareOutDetailVOByPK(dids);
  }

  /**
   * 根据出库单表体DI查询回冲应收的出库结算明细VO
   * 
   * @param outBids
   * @return 出库结算明细VO
   */
  public SquareOutDetailVO[] queryRushIncomeSquareOutDetailVOBy4CBID(
      String[] outBids) {
    SquareOutDetailVO[] sdvos =
        this.querySquareOutDetailVOBy4CBIDUseSquareType(outBids,
            SquareType.SQUARETYPE_ARRUSH.getIntegerValue());
    return sdvos;
  }

  /**
   * 非出库对冲销售出库单传回冲应收明细数据
   * 
   * @param bids
   * @return 出库结算明细VO
   */
  public SquareOutDetailVO[] queryRushIncomeSquareOutDetailVOByBID(String[] bids) {
    String[] dids = null;
    SqlBuilder hsql = new SqlBuilder();
    hsql.append("select csalesquaredid from so_squareout_d where dr = 0 and ");
    hsql.append(" boutrushflag = 'N' and ");
    hsql.append("fsquaretype", SquareType.SQUARETYPE_ARRUSH.getIntValue());
    hsql.append("and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CSALESQUAREBID, bids);
    hsql.append(where);
    QuerySquare4CVOBP qry = new QuerySquare4CVOBP();
    dids = qry.queryIDsFromSql(hsql.toString());
    return qry.querySquareOutDetailVOByPK(dids);
  }

  @Override
  public SquareOutDetailVO[] querySquareOutDetailVOBy4CBID(String[] bidValues) {
    // 查询表头id
    StringBuilder hsql =
        new StringBuilder("select csalesquaredid from so_squareout_d ");
    hsql.append("where so_squareout_d.dr = 0 and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CSQUAREBILLBID, bidValues);
    hsql.append(where);
    String[] hids = this.queryBP.queryIDsFromSql(hsql.toString());
    return this.queryBP.querySquareOutDetailVOByPK(hids);
  }

  @Override
  public SquareOutDetailVO[] querySquareOutDetailVOByPK(String[] outDetailPKs) {
    return this.queryBP.querySquareOutDetailVOByPK(outDetailPKs);
  }

  @Override
  public SquareOutViewVO[] querySquareOutViewVOBy4CBID(String[] outbids) {
    return this.queryBP.querySquareOutViewVOBy4CBID(outbids);
  }

  /**
   * 根据出库单BID查询未回冲应收的出库单结算视图VO
   * 
   * @param bids
   * @return 出库结算视图VO
   */
  public SquareOutViewVO[] querySquareOutViewVOByBIDForNoETRushSquare(
      String[] bids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select csalesquarebid from so_squareout_b where ");
    sql.append("(isnull(so_squareout_b.narrushnum,.0)=0 ");
    sql.append("and so_squareout_b.fpreartype="
        + SquareType.SQUARETYPE_ET.getIntValue() + ") and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(SquareWasBVO.CSQUAREBILLBID, bids));

    return new QuerySquare4CVOBP().querySquareOutViewVO(sql.toString());
  }

  /**
   * 根据出库单BID查询未计入发出商品的出库结算视图VO
   * 
   * @param bids
   * @return 出库结算视图VO
   */
  public SquareOutViewVO[] querySquareOutViewVOByBIDForNoREGSquare(String[] bids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select csalesquarebid from so_squareout_b where ");
    sql.append("(isnull(so_squareout_b.nsquareregnum,.0)=0 and ");
    sql.append("so_squareout_b.fpreiatype="
        + SquareType.SQUARETYPE_REG_DEBIT.getIntValue() + ") and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(SquareWasBVO.CSQUAREBILLBID, bids));
    return new QuerySquare4CVOBP().querySquareOutViewVO(sql.toString());
  }

  /**
   * 根据上游出库单行ID和待查结算类型查询下游基于签收开票退回的出库单相关信息
   * 
   * @param outBids
   * @param type
   * @return
   */
  private Map<String, UFDouble[]> queryARNumMnyBy4CBIDAndSquareType(
      String[] outBids, SquareType type) {
    SqlBuilder hsql = new SqlBuilder();
    hsql.append("select sob.csrcbid ,sum(isnull(sob.NNUM,0)) arnum, ");
    hsql.append("sum(isnull(norigtaxmny,.0)) artaxmny ");
    hsql.append(",sum(isnull(norigmny,.0)) armny ");
    hsql.append("from so_squareout so inner join SO_SQUAREOUT_B sob ");
    hsql.append("on so.CSALESQUAREID = sob.CSALESQUAREID ");
    hsql.append("and so.BRETURNOUTSTOCK = 'Y' and so.dr = 0 AND sob.dr = 0 ");
    hsql.append("where sob.vsrctype = '4C' and ");
    hsql.append("fpreartype", type.getIntValue());
    hsql.append("and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareInvBVO.CSRCBID, outBids);
    hsql.append(where);
    hsql.append(" group by sob.csrcbid ");
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

  private SquareOutDetailVO[] querySquareOutDetailVOBy4CBIDUseSquareType(
      String[] outBids, Integer squareType) {
    String[] dids = null;
    SqlBuilder hsql = new SqlBuilder();
    hsql.append("select csalesquaredid from so_squareout_d where dr = 0 and ");
    hsql.append("fsquaretype", squareType);
    hsql.append("and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CSQUAREBILLBID, outBids);
    hsql.append(where);
    dids = this.queryBP.queryIDsFromSql(hsql.toString());
    return this.queryBP.querySquareOutDetailVOByPK(dids);
  }

}
