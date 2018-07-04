package nc.pubimpl.so.m33.self.pub;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.m33.self.pub.ISquare4332Query;

import nc.bs.so.m33.maintain.m32.query.QuerySquare32VOBP;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 销售结算为销售发票提供的查询服务实现类
 * 
 * @since 6.1
 * @version 2012-11-29 10:17:47
 * @author 冯加彬
 */
public class Square4332QueryImpl implements ISquare4332Query {

  private QuerySquare32VOBP queryBP;

  /**
   * 构造子
   */
  public Square4332QueryImpl() {
    this.queryBP = new QuerySquare32VOBP();
  }

  @Override
  public Map<String, UFDouble> queryARNumBy4CBID(String[] outBids) {
    SqlBuilder hsql = new SqlBuilder();
    // 因为发票传多少确认应收就会传多少回冲应收（上游出库单做暂估应收），所以此处结算动作为传应收（发票不会记单独的回冲应收标记）
    hsql.append("select csrcbid,sum(isnull(nsquarearnum,.0)) arnum ");
    hsql.append("from so_squareinv_b where dr = 0 and ");
    hsql.append(" vsrctype = '4C' and ");
    hsql.append("fpreartype", SquareType.SQUARETYPE_AR.getIntValue());
    hsql.append("and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareInvBVO.CSRCBID, outBids);
    hsql.append(where);
    hsql.append(" group by csrcbid ");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(hsql.toString());

    // 封装结果--<出库单行id,累计回冲应收数量>
    Map<String, UFDouble> mOutBidNum = null;
    int size = rowset.size();
    if (size > 0) {
      mOutBidNum = new HashMap<String, UFDouble>();
      do {
        mOutBidNum.put(rowset.getString(0), rowset.getUFDouble(1));
      }
      while (rowset.next());
    }
    return mOutBidNum;
  }

  @Override
  public SquareInvViewVO[] queryCostSquareInvVOBy32BID(String[] invBids) {
    SquareInvViewVO[] vviewvo =
        this.queryViewVOBy32BIDUseSquareType(invBids,
            SquareType.SQUARETYPE_IA.getIntegerValue());
    return vviewvo;
  }

  /**
   * 根据销售发票表头ID查询发票结算视图VO
   * 
   * @param hidValues
   * @return 发票结算视图VO
   */
  public SquareInvViewVO[] querySquareInvVOBy32ID(String[] hidValues) {
    return this.queryBP.querySquareInvViewVOBy32ID(hidValues);
  }

  private SquareInvViewVO[] queryViewVOBy32BIDUseSquareType(String[] invBids,
      Integer squareType) {
    String[] bids = null;
    SqlBuilder hsql = new SqlBuilder();
    hsql.append("select csalesquarebid from so_squareinv_b where dr = 0 and ");
    hsql.append("fpreiatype", squareType);
    hsql.append("and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareInvBVO.CSQUAREBILLBID, invBids);
    hsql.append(where);
    bids = this.queryBP.queryIDsFromSql(hsql.toString());
    return this.queryBP.querySquareInvViewVOBy32BID(bids);
  }

}
