package nc.bs.so.m33.maintain.m4c.query;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.votools.SoVoTools;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
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
public class QuerySquare4CVOBP {

  /**
   * 查询签收退回的销售出库单上游的来源于发货单的蓝字销售出库单
   * 
   * @param mout
   * @return <蓝字4cbid,4331bid>
   */
  public Map<String, String> query4C4331bid(Map<String, SquareOutViewVO> mout) {
    SquareOutViewVO[] views =
        mout.values().toArray(new SquareOutViewVO[mout.size()]);
    // 上游蓝字出库待结算单bid
    Set<String> setoutbid = new HashSet<String>();
    for (SquareOutViewVO view : views) {
      UFBoolean flag =
          ValueUtils.getUFBoolean(view.getHead().getBreturnoutstock());
      if (flag.booleanValue()) {
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
   * 根据销售出库待结算单表体ID查询待结算单VO
   * 
   * @param bids
   * @return <bid,SquareOutViewVO>
   */
  public Map<String, SquareOutViewVO> queryMapSquareOutViewVOByBID(String[] bids) {
    // 根据id查询VO
    ViewQuery<SquareOutViewVO> query =
        new ViewQuery<SquareOutViewVO>(SquareOutViewVO.class);
    SquareOutViewVO[] bills = query.query(bids);

    Map<String, SquareOutViewVO> map = new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO view : bills) {
      map.put(view.getItem().getCsalesquarebid(), view);
    }
    return map;
  }

  /**
   * 根据销售出库单表体ID查询结算单明细VO
   * 
   * @param bidValues -- 销售出库单表体ID
   * @return 出库结算明细VO
   */
  public SquareOutDetailVO[] querySquareOutDetailVOBy4CBID(String[] bidValues) {
    // 查询表头id
    StringBuilder hsql =
        new StringBuilder("select csalesquaredid from so_squareout_d ");
    hsql.append("where so_squareout_d.dr = 0 and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CSQUAREBILLBID, bidValues);
    hsql.append(where);
    String[] hids = this.queryIDsFromSql(hsql.toString());
    return this.querySquareOutDetailVOByPK(hids);
  }

  /**
   * 根据销售结算明细ID查询销售结算单明细VO
   * 
   * @param hids
   * @return 出库结算明细VO
   */
  public SquareOutDetailVO[] querySquareOutDetailVOByPK(String[] hids) {

    if (hids == null || hids.length == 0) {
      return null;
    }

    // 根据id查询VO
    VOQuery<SquareOutDetailVO> query =
        new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class);
    SquareOutDetailVO[] bills = query.query(hids);
    return bills;
  }

  /**
   * 根据销售出库待结算单表体ID查询结算单明细VO
   * 
   * @param bidValues -- 销售结算单表体ID
   * @return 出库结算明细VO
   */
  public SquareOutDetailVO[] querySquareOutDetailVOBySQBID(String[] bidValues) {
    // 查询表头id
    StringBuilder hsql =
        new StringBuilder("select csalesquaredid from so_squareout_d ");
    hsql.append("where so_squareout_d.dr = 0 and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CSALESQUAREBID, bidValues);
    hsql.append(where);
    String[] hids = this.queryIDsFromSql(hsql.toString());
    return this.querySquareOutDetailVOByPK(hids);
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
  public SquareOutViewVO[] querySquareOutViewVO(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    // 1.查询表体
    IRowSet rowset = utils.query(sql.toString());
    if (rowset.size() == 0) {
      return null;
    }
    String[] bids = rowset.toOneDimensionStringArray();

    // 2.查询视图vo
    SquareOutViewVO[] vos = this.querySquareOutViewVOByBID(bids);

    return vos;
  }

  /**
   * 根据待结算单出库单行id查询待结算单视图VO
   * 
   * @param outbids
   * @return 出库结算明细ViewVO
   */
  public SquareOutViewVO[] querySquareOutViewVOBy4CBID(String[] outbids) {
    String[] bids = this.querySquareOutVOPKsBy4CBID(outbids);
    return this.querySquareOutViewVOByBID(bids);
  }

  /**
   * 根据销售出库待结算单表体ID查询待结算单VO
   * 
   * @param bids
   * @return 出库结算视图VO
   */
  public SquareOutViewVO[] querySquareOutViewVOByBID(String[] bids) {
    // 根据id查询VO
    ViewQuery<SquareOutViewVO> query =
        new ViewQuery<SquareOutViewVO>(SquareOutViewVO.class);
    SquareOutViewVO[] bills = query.query(bids);
    return bills;
  }

  /**
   * 根据销售出库待结算单表头ID查询待结算单VO
   * 
   * @param hids
   * @return 出库结算视图VO
   */
  public SquareOutViewVO[] querySquareOutViewVOByPK(String[] hids) {
    // 根据id查询VO
    ViewQuery<SquareOutViewVO> query =
        new ViewQuery<SquareOutViewVO>(SquareOutViewVO.class);
    SquareOutViewVO[] bills = query.query(hids);
    return bills;
  }

  /**
   * 根据待结算单出库单id查询待结算单视图VO
   * 
   * @param hidValues
   * @return 出库结算VO
   */
  public SquareOutVO[] querySquareOutVOBy4CID(String[] hidValues) {
    String[] hids = this.querySquareOutVOPKsBy4CID(hidValues);
    return this.querySquareOutVOByPK(hids);
  }

  /**
   * 根据销售出库待结算单表头ID查询待结算单VO
   * 
   * @param hids
   * @return 出库结算VO
   */
  public SquareOutVO[] querySquareOutVOByPK(String[] hids) {
    // 根据id查询VO
    BillQuery<SquareOutVO> query =
        new BillQuery<SquareOutVO>(SquareOutVO.class);
    SquareOutVO[] bills = query.query(hids);
    return bills;
  }

  /**
   * 根据销售订单行id查询待结算单行id
   * 
   * @param ordbids
   * @return 出库结算子表ID
   */
  public String[] querySquareOutVOPKsBy30BID(String[] ordbids) {
    // 查询表头id
    StringBuilder hsql =
        new StringBuilder("select csalesquarebid from so_squareout_b ");
    hsql.append("where so_squareout_b.dr = 0 and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutBVO.CFIRSTBID, ordbids);
    hsql.append(where);
    String[] bids = this.queryIDsFromSql(hsql.toString());
    return bids;
  }

  /**
   * 根据旧的出库结算视图刷新数据
   * 
   * @param views
   * @return 新的出库结算视图VO
   */
  public SquareOutViewVO[] refushView(SquareOutViewVO[] views) {
    String[] bids =
        SoVoTools.getVOsOnlyValues(views, SquareOutBVO.CSALESQUAREBID);

    Map<String, SquareOutViewVO> oldmap =
        new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO view : views) {
      oldmap.put(view.getItem().getCsalesquarebid(), view);
    }

    SquareOutViewVO[] newview = this.querySquareOutViewVOByBID(bids);

    this.setValueForrefushVO(oldmap, newview);

    return newview;
  }

  /**
   * 根据旧的出库结算VO数据刷新数据
   * 
   * @param svos
   * @return 新的出库结算VO
   */
  public SquareOutVO[] refushVO(SquareOutVO[] svos) {
    SquareOutViewVO[] oldviews =
        SquareOutVOUtils.getInstance().changeToSaleSquareViewVO(svos);
    Map<String, SquareOutViewVO> oldmap =
        new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO view : oldviews) {
      oldmap.put(view.getItem().getCsalesquarebid(), view);
    }
    String[] bids =
        AggVOUtil.getDistinctItemFieldArray(svos, SquareOutBVO.CSALESQUAREBID,
            String.class);
    SquareOutViewVO[] newview = this.querySquareOutViewVOByBID(bids);

    this.setValueForrefushVO(oldmap, newview);

    SquareOutVO[] newsvos = SquareOutVOUtils.getInstance().combineBill(newview);
    return newsvos;
  }

  /**
   * 查询签收退回的销售出库单上游的来源于发货单的蓝字销售出库单
   * 
   * @param outbids -- 销售出库单表体id
   * @return <4cbid,4331bid>
   */
  public Map<String, String> query4C4331bid(String[] outbids) {
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
   * 根据待结算单出库单行id查询待结算单行id
   * 
   * @param ibills
   * @return
   */
  private String[] querySquareOutVOPKsBy4CBID(String[] bidValues) {
    // 查询表头id
    StringBuilder hsql =
        new StringBuilder("select csalesquarebid from so_squareout_b ");
    hsql.append("where so_squareout_b.dr = 0 and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutBVO.CSQUAREBILLBID, bidValues);
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
  private String[] querySquareOutVOPKsBy4CID(String[] hidValues) {
    // 查询表头id
    StringBuilder hsql =
        new StringBuilder("select csalesquareid from so_squareout ");
    hsql.append("where so_squareout.dr = 0 and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutHVO.CSQUAREBILLID, hidValues);
    hsql.append(where);
    String[] hids = this.queryIDsFromSql(hsql.toString());
    return hids;
  }

  /**
   * 用于从DB中刷新vo后，将老vo中的计算属性赋值到新vo中
   * 
   * @param oldmap -- 老vo map
   * @param newview -- 新 vo
   */
  private void setValueForrefushVO(Map<String, SquareOutViewVO> oldmap,
      SquareOutViewVO[] newview) {
    for (SquareOutViewVO view : newview) {
      String bid = view.getItem().getCsalesquarebid();
      SquareOutViewVO oldview = oldmap.get(bid);
      view.getItem().setCsalesquaredid(oldview.getItem().getCsalesquaredid());
    }
  }

  /**
   * 根据待结算单出库单行id查询待结算单行来源单bid（来源于发货单的)
   * 
   * @param ibills
   * @return
   */
  public Map<String, String> queryOutSrcidBy4CBID(String[] bidValues) {
    VOQuery<SquareOutBVO> quey =
        new VOQuery<SquareOutBVO>(SquareOutBVO.class, new String[] {
          SquareOutBVO.CSQUAREBILLBID, SquareOutBVO.CSRCBID,
          SquareOutBVO.VSRCTYPE
        });
    // 查询表头id
    SqlBuilder hsql = new SqlBuilder();
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutBVO.CSQUAREBILLBID, bidValues);
    hsql.append(" and ");
    hsql.append(where);
    SquareOutBVO[] outbvos = quey.query(hsql.toString(), null);
    Map<String, String> outsrcmap = new HashMap<String, String>();
    for (SquareOutBVO outbvo : outbvos) {
      String vsrctype = outbvo.getVsrctype();
      if (!PubAppTool.isNull(vsrctype)
          && SOBillType.Delivery.getCode().equals(vsrctype))
        outsrcmap.put(outbvo.getCsquarebillbid(), outbvo.getCsrcbid());
    }
    return outsrcmap;
  }
}
