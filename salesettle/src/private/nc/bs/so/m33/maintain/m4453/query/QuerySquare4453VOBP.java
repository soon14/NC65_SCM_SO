package nc.bs.so.m33.maintain.m4453.query;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasHVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.trade.checkrule.VOChecker;

import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 途损单待结算单查询BP类
 * 
 * @since 6.1
 * @version 2012-11-29 09:56:35
 * @author 冯加彬
 */
public class QuerySquare4453VOBP {

  /**
   * 查询途损单上游的来源于发货单的出库单
   * 
   * @param mwas
   * @return <4cbid,4331bid>>
   */
  public Map<String, String> query4C4331bid(Map<String, SquareWasViewVO> mwas) {
    SquareWasViewVO[] views =
        mwas.values().toArray(new SquareWasViewVO[mwas.size()]);
    Set<String> setoutbid = new HashSet<String>();
    for (SquareWasViewVO view : views) {
      String vsrctype = view.getItem().getVsrctype();
      if (ICBillType.SaleOut.getCode().equals(vsrctype)) {
        setoutbid.add(view.getItem().getCsrcbid());
      }
    }

    Map<String, String> map = new HashMap<String, String>();
    // 途损单来源于销售出库单
    int size = setoutbid.size();
    if (size > 0) {
      map = this.query4C4331bid(setoutbid.toArray(new String[size]));
    }

    return map;
  }

  /**
   * 根据途损待结算单表体ID查询待结算单VO
   * 
   * @param bids
   * @return <bid,SquareOutViewVO>
   */
  public Map<String, SquareWasViewVO> queryMapSquareWasViewVOByBID(String[] bids) {
    // 根据id查询VO
    ViewQuery<SquareWasViewVO> query =
        new ViewQuery<SquareWasViewVO>(SquareWasViewVO.class);
    SquareWasViewVO[] bills = query.query(bids);

    Map<String, SquareWasViewVO> map = new HashMap<String, SquareWasViewVO>();
    for (SquareWasViewVO view : bills) {
      map.put(view.getItem().getCsalesquarebid(), view);
    }
    return map;
  }

  /**
   * 根据条件查询途损结算单
   * 
   * @param condition 查询条件 例如：and name='cc'
   * @param order 排序条件
   * @return 途损结算单明细VO
   */
  public SquareWasDetailVO[] querySquareWasDetailVOByCondition(
      String condition, String order) {
    VOQuery<SquareWasDetailVO> query =
        new VOQuery<SquareWasDetailVO>(SquareWasDetailVO.class);
    return query.query(condition, order);
  }

  /**
   * 根据销售结算明细ID查询销售结算单明细VO
   * 
   * @param hids
   * @return 途损结算单明细VO
   */
  public SquareWasDetailVO[] querySquareWasDetailVOByPK(String[] hids) {

    if (hids == null || hids.length == 0) {
      return null;
    }

    // 根据id查询VO
    VOQuery<SquareWasDetailVO> query =
        new VOQuery<SquareWasDetailVO>(SquareWasDetailVO.class);
    SquareWasDetailVO[] bills = query.query(hids);
    return bills;
  }

  /**
   * 根据销售出库待结算单表头ID查询结算单明细VO
   * 
   * @param hidValues -- 销售结算单表头ID
   * @return 途损结算单明细VO
   */
  public SquareWasDetailVO[] querySquareWasDetailVOBySQHID(String[] hidValues) {
    // 查询表头id
    StringBuilder hsql =
        new StringBuilder("select csalesquaredid from so_squarewas_d ");
    hsql.append("where so_squarewas_d.dr = 0 and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = idqb.buildSQL(SquareWasDetailVO.CSALESQUAREID, hidValues);
    hsql.append(where);
    String[] hids = this.queryIDsFromSql(hsql.toString());
    return this.querySquareWasDetailVOByPK(hids);
  }

  /**
   * 根据SQL语句查询途损结算视图VO
   * 
   * @param sql
   * @return 途损结算视图VO
   */
  public SquareWasViewVO[] querySquareWasViewVO(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    // 1.查询表体
    IRowSet rowset = utils.query(sql.toString());
    if (rowset.size() == 0) {
      return null;
    }
    String[] bids = rowset.toOneDimensionStringArray();

    // 2.查询视图vo
    SquareWasViewVO[] vos = this.querySquareWasViewVOByBID(bids);

    return vos;
  }

  /**
   * 根据销售出库单行id查询待途损结算单
   * 
   * @param outBids
   * @return 途损结算视图VO
   */
  public SquareWasViewVO[] querySquareWasViewVOBy4CBID(String[] outBids) {
    // 查询表头id
    StringBuilder hsql =
        new StringBuilder("select csalesquarebid from so_squarewas_b where ");
    hsql.append("so_squarewas_b.dr = 0 and so_squarewas_b.vsrctype='4C' and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = idqb.buildSQL(SquareWasBVO.CSRCBID, outBids);
    hsql.append(where);
    String[] bids = this.queryIDsFromSql(hsql.toString());
    SquareWasViewVO[] svvos = null;
    if (!VOChecker.isEmpty(bids)) {
      svvos = this.querySquareWasViewVOByBID(bids);
    }
    return svvos;
  }

  /**
   * 根据途损待结算单表体ID查询待结算单VO
   * 
   * @param bids
   * @return 途损结算视图VO
   */
  public SquareWasViewVO[] querySquareWasViewVOByBID(String[] bids) {
    // 根据id查询VO
    ViewQuery<SquareWasViewVO> query =
        new ViewQuery<SquareWasViewVO>(SquareWasViewVO.class);
    SquareWasViewVO[] bills = query.query(bids);
    return bills;
  }

  /**
   * 根据待结算单途损单id查询待结算单VO
   * 
   * @param hidValues
   * @return 途损结算单VO
   */
  public SquareWasVO[] querySquareWasVOBy4453ID(String[] hidValues) {
    String[] hids = this.querySquareWasVOPKsBy4453ID(hidValues);
    return this.querySquareWasVOByPK(hids);
  }

  /**
   * 根据途损待结算单表头ID查询待结算单VO
   * 
   * @param hids
   * @return 途损结算单VO
   */
  public SquareWasVO[] querySquareWasVOByPK(String[] hids) {
    // 根据id查询VO
    BillQuery<SquareWasVO> query =
        new BillQuery<SquareWasVO>(SquareWasVO.class);
    SquareWasVO[] bills = query.query(hids);
    return bills;
  }

  /**
   * 查询途损单上游的来源于发货单的出库单
   * 
   * @param outbids -- 销售出库单表体id
   * @return <4cbid,4331bid>
   */
  private Map<String, String> query4C4331bid(String[] outbids) {
    Map<String, String> map = new HashMap<String, String>();
    // 查询销售出库待结算单
    SquareOutViewVO[] outviews =
        new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(outbids);
    for (SquareOutViewVO view : outviews) {
      String vsrctype = view.getItem().getVsrctype();
      if (SOBillType.Delivery.getCode().equals(vsrctype)) {
        map.put(view.getItem().getCsquarebillbid(), view.getItem().getCsrcbid());
      }
    }
    return map;
  }

  /**
   * 方法功能描述：根据sql语句查询返回查询结果ID数组
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param sql -- select id from ...........
   * @return id数组
   *         <p>
   * @author zhangcheng
   * @time 2010-7-21 下午01:31:07
   */
  private String[] queryIDsFromSql(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    String[] cbillids = rowset.toOneDimensionStringArray();
    return cbillids;
  }

  /**
   * 根据待结算单途损单id查询待结算单PK数组
   * 
   * @param ibills
   * @return
   */
  private String[] querySquareWasVOPKsBy4453ID(String[] hidValues) {
    // 查询表头id
    StringBuilder hsql =
        new StringBuilder("select csalesquareid from so_squarewas ");
    hsql.append("where so_squarewas.dr = 0 and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = idqb.buildSQL(SquareWasHVO.CSQUAREBILLID, hidValues);
    hsql.append(where);
    String[] hids = this.queryIDsFromSql(hsql.toString());
    return hids;
  }

}
