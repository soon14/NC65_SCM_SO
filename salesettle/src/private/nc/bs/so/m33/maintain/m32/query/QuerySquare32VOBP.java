package nc.bs.so.m33.maintain.m32.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvHVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.trade.checkrule.VOChecker;

import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 销售出库结算单查询服务BP
 * 
 * @author zhangcheng
 * 
 */
public class QuerySquare32VOBP {

  /**
   * 查询销售发票上游的来源于发货单的出库单
   * 
   * @param minv
   * @return <4cbid,4331bid>
   */
  public Map<String, String> query4C4331bid(Map<String, SquareInvViewVO> minv) {
    SquareInvViewVO[] views =
        minv.values().toArray(new SquareInvViewVO[minv.size()]);
    Set<String> setoutbid = new HashSet<String>();
    for (SquareInvViewVO view : views) {
      String vsrctype = view.getItem().getVsrctype();
      if (ICBillType.SaleOut.getCode().equals(vsrctype)) {
        setoutbid.add(view.getItem().getCsrcbid());
      }
    }

    Map<String, String> map = new HashMap<String, String>();
    // 销售发票来源于销售出库单
    int size = setoutbid.size();
    if (size > 0) {
      map = this.query4C4331bid(setoutbid.toArray(new String[size]));
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
  public String[] queryIDsFromSql(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    String[] cbillids = rowset.toOneDimensionStringArray();
    return cbillids;
  }

  /**
   * 根据销售发票待结算单表体ID查询待结算单VO
   * 
   * @param bids
   * @return <bid,SquareOutViewVO>
   */
  public Map<String, SquareInvViewVO> queryMapSquareInvViewVOByBID(String[] bids) {
    // 根据id查询VO
    ViewQuery<SquareInvViewVO> query =
        new ViewQuery<SquareInvViewVO>(SquareInvViewVO.class);
    SquareInvViewVO[] bills = query.query(bids);

    Map<String, SquareInvViewVO> map = new HashMap<String, SquareInvViewVO>();
    for (SquareInvViewVO view : bills) {
      map.put(view.getItem().getCsalesquarebid(), view);
    }
    return map;
  }

  /**
   * 根据销售结算明细ID查询销售结算单明细VO
   * 
   * @param hids
   * @return 销售结算单明细VO
   */
  public SquareInvDetailVO[] querySquareInvDetailVOByPK(String[] hids) {

    if (hids == null || hids.length == 0) {
      return null;
    }

    // 根据id查询VO
    VOQuery<SquareInvDetailVO> query =
        new VOQuery<SquareInvDetailVO>(SquareInvDetailVO.class);
    SquareInvDetailVO[] bills = query.query(hids);
    return bills;
  }

  /**
   * 根据销售出库待结算单表头ID查询结算单明细VO
   * 
   * @param hids -- 销售结算单表头ID
   * @return 结算单明细VO
   */
  public SquareInvDetailVO[] querySquareInvDetailVOBySQHID(String[] hids) {
    // 查询表头id
    StringBuilder hsql =
        new StringBuilder("select csalesquaredid from so_SquareInv_d ");
    hsql.append("where so_SquareInv_d.dr = 0 and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = idqb.buildSQL(SquareInvDetailVO.CSALESQUAREID, hids);
    hsql.append(where);
    String[] newhids = this.queryIDsFromSql(hsql.toString());
    return this.querySquareInvDetailVOByPK(newhids);
  }

  /**
   * 方法功能描述：查询视图vo
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param sql -- 查询子表id的sql的语句
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-6-10 下午02:56:20
   */
  public SquareInvViewVO[] querySquareInvViewVO(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    // 1.查询表体
    IRowSet rowset = utils.query(sql.toString());
    if (rowset.size() == 0) {
      return null;
    }
    String[] bids = rowset.toOneDimensionStringArray();

    // 2.查询视图vo
    ViewQuery<SquareInvViewVO> bquery =
        new ViewQuery<SquareInvViewVO>(SquareInvViewVO.class);
    SquareInvViewVO[] vos = bquery.query(bids);

    return vos;
  }

  /**
   * 根据待结算单出库单id查询待结算单视图VO
   * 
   * @param invBidValues
   * @return 待结算单视图VO
   */
  public SquareInvViewVO[] querySquareInvViewVOBy32BID(String[] invBidValues) {
    if (VOChecker.isEmpty(invBidValues)) {
      return null;
    }
    String[] bids = this.querySquareInvVOPKsBy32BID(invBidValues);
    return this.querySquareInvViewVOByBID(bids);
  }

  /**
   * 根据待结算单出库单id查询待结算单视图VO
   * 
   * @param hidValues
   * @return 待结算单视图VO
   */
  public SquareInvViewVO[] querySquareInvViewVOBy32ID(String[] hidValues) {
    String[] hids = this.querySquareInvVOPKsBy32ID(hidValues);
    return this.querySquareInvViewVOByPK(hids);
  }

  /**
   * 根据待结算单来源表体ID，来源表头ID查询待结算单视图VO
   * 
   * @param outbids
   * @param outhids
   * @param querykeys
   * @return SquareInvViewVO[]
   */
  public SquareInvViewVO[] querySquareInvViewVOBy4CBIDHID(String[] outbids,
      String[] outhids, String[] querykeys) {
    if (SOVOChecker.isEmpty(outbids) || SOVOChecker.isEmpty(outhids)) {
      return null;
    }
    StringBuilder sqlwhere = new StringBuilder(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String bidwhere = iq.buildSQL(SquareInvBVO.CSRCBID, outbids);
    sqlwhere.append(bidwhere);
    sqlwhere.append(" and ");

    iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
    String hidwhere = iq.buildSQL(SquareInvBVO.CSRCID, outhids);
    sqlwhere.append(hidwhere);

    EfficientViewQuery<SquareInvViewVO> viewQuery =
        new EfficientViewQuery<SquareInvViewVO>(SquareInvViewVO.class,
            querykeys);
    return viewQuery.query(sqlwhere.toString());
  }

  /**
   * 根据销售发票待结算单表体ID查询待结算单VO
   * 
   * @param bids
   * @return 待结算单ViewVO
   */
  public SquareInvViewVO[] querySquareInvViewVOByBID(String[] bids) {
    // 根据id查询VO
    ViewQuery<SquareInvViewVO> query =
        new ViewQuery<SquareInvViewVO>(SquareInvViewVO.class);
    SquareInvViewVO[] bills = query.query(bids);
    return bills;
  }

  /**
   * 根据销售出库待结算单表头ID查询待结算单VO
   * 
   * @param hids
   * @return 待结算单ViewVO
   */
  public SquareInvViewVO[] querySquareInvViewVOByPK(String[] hids) {
    // 根据id查询VO
    ViewQuery<SquareInvViewVO> query =
        new ViewQuery<SquareInvViewVO>(SquareInvViewVO.class);
    SquareInvViewVO[] bills = query.query(hids);
    return bills;
  }

  /**
   * 根据待结算单出库单id查询待结算单视图VO
   * 
   * @param hidValues
   * @return 待结算单视图VO
   */
  public SquareInvVO[] querySquareInvVOBy32ID(String[] hidValues) {
    String[] hids = this.querySquareInvVOPKsBy32ID(hidValues);
    return this.querySquareInvVOByPK(hids);
  }

  /**
   * 根据销售出库待结算单表头ID查询待结算单VO
   * 
   * @param hids
   * @return 待结算单VO
   */
  public SquareInvVO[] querySquareInvVOByPK(String[] hids) {
    // 根据id查询VO
    BillQuery<SquareInvVO> query =
        new BillQuery<SquareInvVO>(SquareInvVO.class);
    SquareInvVO[] bills = query.query(hids);
    return bills;
  }

  /**
   * 查询销售发票上游的来源于发货单的出库单
   * 
   * @param outbids -- 销售出库单表体id
   * @return <4cbid,4331bid>
   */
  private Map<String, String> query4C4331bid(String[] outbids) {
    Map<String, String> map = new HashMap<String, String>();
    // 查询销售出库待结算单
    SquareOutViewVO[] outviews =
        new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(outbids);

    // tianft 2013-05-28 对于退库的时候，应考虑这部分来源发货单的情况
    List<SquareOutViewVO> redOutFromSelf = new ArrayList<SquareOutViewVO>();
    Set<String> redoutidFromSelf = new HashSet<String>();
    for (SquareOutViewVO view : outviews) {
      String vsrctype = view.getItem().getVsrctype();
      if (SOBillType.Delivery.getCode().equals(vsrctype)) {
        map.put(view.getItem().getCsquarebillbid(), view.getItem().getCsrcbid());
      }
      // tianft 2013-05-28 出库单来源自己，这里先记录下来，随后看一下是不是来源发货单
      else if (ICBillType.SaleOut.getCode().equals(vsrctype)) {
        redOutFromSelf.add(view);
        redoutidFromSelf.add(view.getItem().getCsrcbid());
      }
    }
    // 处理红字出库对应的兰自出库来源发货单的情况
    if (redOutFromSelf.size() > 0) {
      SquareOutViewVO[] blueviews =
          new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(redoutidFromSelf
              .toArray(new String[redoutidFromSelf.size()]));
      Map<String, SquareOutViewVO> blueViewMap =
          new HashMap<String, SquareOutViewVO>();
      if (ArrayUtils.isEmpty(blueviews)) {
        return map;
      }
      for (SquareOutViewVO view : blueviews) {
        blueViewMap.put(view.getItem().getCsquarebillbid(), view);
      }
      for (SquareOutViewVO redView : redOutFromSelf) {
        if (blueViewMap.containsKey(redView.getItem().getCsrcbid())) {
          SquareOutViewVO blueView =
              blueViewMap.get(redView.getItem().getCsrcbid());
          String vsrctype = blueView.getItem().getVsrctype();
          // 如果兰字出库单来源发货单，则应建立红字出库单和该发货单的联系
          if (SOBillType.Delivery.getCode().equals(vsrctype)) {
            map.put(redView.getItem().getCsquarebillbid(), blueView.getItem()
                .getCsrcbid());
          }
        }
      }
    }
    return map;
  }

  /**
   * 根据待结算单销售发票Bid查询待结算单BID数组
   * 
   * @param ibills
   * @return
   */
  private String[] querySquareInvVOPKsBy32BID(String[] invBidValues) {
    // 查询表头id
    StringBuilder hsql =
        new StringBuilder("select csalesquarebid from so_squareinv_b ");
    hsql.append("where so_squareinv_b.dr = 0 and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = idqb.buildSQL(SquareInvBVO.CSQUAREBILLBID, invBidValues);
    hsql.append(where);
    String[] bids = this.queryIDsFromSql(hsql.toString());
    return bids;
  }

  /**
   * 根据待结算单出库单id查询待结算单PK数组
   * 
   * @param ibills
   * @return
   */
  private String[] querySquareInvVOPKsBy32ID(String[] hidValues) {
    // 查询表头id
    StringBuilder hsql =
        new StringBuilder("select csalesquareid from so_SquareInv ");
    hsql.append("where so_SquareInv.dr = 0 and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = idqb.buildSQL(SquareInvHVO.CSQUAREBILLID, hidValues);
    hsql.append(where);
    String[] hids = this.queryIDsFromSql(hsql.toString());
    return hids;
  }

}
