package nc.bs.so.m33.maintain.m4c.query;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.constant.QueryFlag;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

import nc.bs.so.m33.biz.m4c.bp.pub.ProcessWC;

import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 出库结算单查询BP类
 * 
 * @since 6.1
 * @version 2012-11-29 10:00:20
 * @author 冯加彬
 */
public class QuerySquare4CVOBizBP {

  private QuerySquare4CVOBP queryBP;

  /**
   * 构造子
   */
  public QuerySquare4CVOBizBP() {
    this.queryBP = new QuerySquare4CVOBP();
  }

  /**
   * 根据销售出库单BID查询出库暂估结算单
   * 
   * @param outbids
   * @return Map
   */
  public Map<String, SquareOutDetailVO> queryETSquareOutDetailVOBy4CBID(
      String[] outbids) {
    Map<String, SquareOutDetailVO> map =
        new HashMap<String, SquareOutDetailVO>();
    VOQuery<SquareOutDetailVO> qry =
        new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class);
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String outbidWhere =
        idqb.buildSQL(SquareOutDetailVO.CSQUAREBILLBID, outbids);
    SqlBuilder where = new SqlBuilder();
    // 4cbid
    where.append(" and ");
    where.append(outbidWhere);
    // 暂估类型的结算
    where.append(" and ");
    where.append(SquareOutDetailVO.FSQUARETYPE,
        SquareType.SQUARETYPE_ET.getIntValue());
    SquareOutDetailVO[] retVOs = qry.query(where.toString(), null);
    if (!VOChecker.isEmpty(retVOs)) {
      for (SquareOutDetailVO retVO : retVOs) {
        map.put(retVO.getCsquarebillbid(), retVO);
      }
    }
    return map;
  }

  /**
   * 根据销售出库待结算单BID查询出库暂估结算单
   * 
   * @param sqbids
   * @return 出库结算单明细VO
   */
  public SquareOutDetailVO[] queryETSquareOutDetailVOBySQBID(String[] sqbids) {
    SquareOutDetailVO[] retVOs =
        this.querySquareOutDetailVOBySQBIDAndSquareType(sqbids,
            SquareType.SQUARETYPE_ET);
    return retVOs;
  }

  /**
   * 根据销售出库待结算单表体id和结算类型查询手工结算数据
   * 
   * @param bids 销售出库待结算单表体id
   * @param type 结算类型
   * @return 出库结算单明细VO
   */
  public SquareOutDetailVO[] queryManualDetailVOBySQBIDandSQType(String[] bids,
      SquareType[] type) {
    SquareOutDetailVO[] sdvos = null;
    StringBuilder where = new StringBuilder();
    where.append(" and BAUTOSQUAREFLAG = 'N' ");
    where.append(" and dr = 0 ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    where.append(" and " + iq.buildSQL(SquareOutDetailVO.CSALESQUAREBID, bids));

    int len = type.length;
    where.append(" and (");
    for (int i = 0; i < len; i++) {
      if (i > 0) {
        where.append(" or fsquaretype = " + type[i].getIntValue());
      }
      else {
        where.append("fsquaretype = " + type[i].getIntValue());
      }
    }
    where.append(") ");

    sdvos =
        new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class).query(
            where.toString(), null);

    return sdvos;
  }

  /**
   * 根据销售出库结算明细表id和结算类型查询手工结算数据
   * 
   * @param bids 销售出库待结算单表体id
   * @param processeids 结算批次号
   * @param type 结算类型
   * @return 出库结算单明细VO
   */
  public SquareOutDetailVO[] queryManualDetailVOBySQType(String[] processeids,
      String[] bids, SquareType[] type) {
    SquareOutDetailVO[] sdvos = null;
    StringBuilder where = new StringBuilder();
    where.append(" and BAUTOSQUAREFLAG = 'N' ");
    where.append(" and dr = 0 ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    where.append(" and "
        + iq.buildSQL(SquareOutDetailVO.PROCESSEID, processeids));

    iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
    where.append(" and " + iq.buildSQL(SquareOutDetailVO.CSALESQUAREBID, bids));

    int len = type.length;
    where.append(" and (");
    for (int i = 0; i < len; i++) {
      if (i > 0) {
        where.append(" or fsquaretype = " + type[i].getIntValue());
      }
      else {
        where.append("fsquaretype = " + type[i].getIntValue());
      }
    }
    where.append(") ");

    sdvos =
        new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class).query(
            where.toString(), null);

    return sdvos;
  }

  /**
   * 根据出库单BID查询出库对冲明细记录,为联查出库单对冲信息服务
   * 
   * @param bidValues
   * @return 出库结算单明细VO
   */
  public SquareOutDetailVO[] queryOutRushInfoForLinkQuery(String[] bidValues) {
    // 查询表头id
    StringBuilder hsql =
        new StringBuilder("select csalesquaredid from so_squareout_d ");
    hsql.append(" where so_squareout_d.dr = 0 ");
    hsql.append(" and so_squareout_d.boutrushflag = 'Y' and so_squareout_d.FSQUARETYPE = "
        + SquareType.SQUARETYPE_OUTRUSH.getIntValue());
    hsql.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CSQUAREBILLBID, bidValues);
    hsql.append(where);

    String[] hids = this.queryBP.queryIDsFromSql(hsql.toString());

    return this.queryBP.querySquareOutDetailVOByPK(hids);
  }

  /**
   * 根据出库单BID查询出库对冲明细记录
   * 
   * @param bidValues
   * @param rushbatchid
   * @return 出库结算单明细VO
   */
  public SquareOutDetailVO[] queryOutRushSquareOutDetailVOBy4CBID(
      String[] bidValues, String[] rushbatchid) {
    // 查询表头id
    StringBuilder hsql =
        new StringBuilder("select csalesquaredid from so_squareout_d ");
    hsql.append("where so_squareout_d.dr = 0 and ");
    hsql.append("so_squareout_d.boutrushflag = 'Y' ");

    hsql.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CRUSHOUTBATCHID, rushbatchid);
    hsql.append(where);

    hsql.append(" and ");
    where = iq.buildSQL(SquareOutDetailVO.CSQUAREBILLBID, bidValues);
    hsql.append(where);

    String[] hids = this.queryBP.queryIDsFromSql(hsql.toString());

    return this.queryBP.querySquareOutDetailVOByPK(hids);
  }

  /**
   * 根据对冲出库单BID查询出库对冲明细记录
   * 
   * @param bidValues
   * @param rushbatchid
   * @return 出库结算明细VO
   */
  public SquareOutDetailVO[] queryOutRushSquareOutDetailVOByRushOutBID(
      String[] bidValues, String[] rushbatchid) {
    // 查询表头id
    StringBuilder hsql =
        new StringBuilder("select csalesquaredid from so_squareout_d ");
    hsql.append("where so_squareout_d.dr = 0 and ");
    hsql.append("so_squareout_d.boutrushflag = 'Y' and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareOutDetailVO.CRUSHOUTBATCHID, rushbatchid);
    hsql.append(where);
    hsql.append(" and ");

    iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
    where = iq.buildSQL(SquareOutDetailVO.CRUSHGENERALBID, bidValues);
    hsql.append(where);
    String[] hids = this.queryBP.queryIDsFromSql(hsql.toString());
    return this.queryBP.querySquareOutDetailVOByPK(hids);
  }

  /**
   * 根据销售出库待结算单BID查询出库发出商品贷方结算单
   * 
   * @param sqbids
   * @return 出库结算明细VO
   */
  public SquareOutDetailVO[] queryREGCreditSquareOutDetailVOBySQBID(
      String[] sqbids) {
    SquareOutDetailVO[] retVOs =
        this.querySquareOutDetailVOBySQBIDAndSquareType(sqbids,
            SquareType.SQUARETYPE_REG_CREDIT);
    return retVOs;
  }

  /**
   * 根据销售出库待结算单BID查询出库发出商品借方结算单
   * 
   * @param sqbids
   * @return 出库结算明细VO
   */
  public SquareOutDetailVO[] queryREGDebitSquareOutDetailVOBySQBID(
      String[] sqbids) {
    SquareOutDetailVO[] retVOs =
        this.querySquareOutDetailVOBySQBIDAndSquareType(sqbids,
            SquareType.SQUARETYPE_REG_DEBIT);
    return retVOs;
  }

  /**
   * 查询满足条件的收入成本结算过的销售出库待结算单
   * 
   * @param queryScheme
   * @return 出库结算视图VO
   */
  public SquareOutViewVO[] querySquareOutFor4CSquare(IQueryScheme queryScheme) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);

    // 得到主表、子表别名
    String mainTable = qsp.getMainTableAlias();
    String subTable =
        qsp.getTableAliasOfAttribute(SquareOutBVO.MAINMETAPATH
            + SquareOutBVO.BCOST);

    // 拼接固定过滤条件
    qsp.appendCurrentGroup();
    qsp.appendFuncPermissionOrgSql();
    qsp.appendWhere(" and (" + mainTable + ".dr=0 and " + subTable + ".dr=0)");

    QueryCondition qc = qsp.getQueryCondition(QueryFlag.SQUAREFLAG);
    Object[] obj = qc.getValues();
    if (null == obj || obj.length == 0 || null == obj[0]) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0041")/*@res "结算状态必须选择!"*/);
    }
    else {
      int flag = Integer.parseInt(obj[0].toString());
      // 非出库对冲
      if (flag != QueryFlag.UNOUTRUSHBLUE && flag != QueryFlag.UNOUTRUSHRED) {
        qsp.appendWhere(" and (" + mainTable + ".bautosquarecost = 'N' or "
            + mainTable + ".bautosquareincome = 'N')");
      }
      // 查询
      return this.queryForAllUISquare(qsp, subTable, queryScheme);
    }

    return null;
  }

  private void buildNoETWhere(SqlBuilder squareWhere, String mainTable,
      String subTable) {
    // 过滤基于签收开票退回的销售出库单待结算单
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(mainTable + ".breturnoutstock = 'N' ");
    squareWhere.endParentheses();

    // 已结算数量不等于结算单数量
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" abs(isnull(" + subTable
        + ".nsquareestnum,.0) + isnull(" + subTable
        + ".ndownarnum,.0) + isnull(" + subTable + ".nrushnum,.0)) < " + "abs("
        + subTable + ".nnum)");
    squareWhere.endParentheses();

    // 累计下游发票确认应收数量不等于出库待结算数量
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".ndownarnum,.0) != " + subTable
        + ".nnum");
    squareWhere.endParentheses();

    // 出库待结算数量不等于累计出库对冲数量
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".nnum,.0) != isnull("
        + subTable + ".nrushnum,.0) ");
    squareWhere.endParentheses();

    // 可以传应收的数据
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(subTable + ".bincome = 'Y' ");
    squareWhere.endParentheses();

    // 结算方式为：暂估应收
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(subTable + ".fpreartype",
        SquareType.SQUARETYPE_ET.getIntValue());
    squareWhere.append(" and ");
    squareWhere.append(mainTable + ".bautosquareincome = 'N'");
    squareWhere.endParentheses();
  }

  private void buildNoREGWhere(SqlBuilder squareWhere, String mainTable,
      String subTable) {
    // 过滤基于签收开票退回的销售出库单待结算单
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(mainTable + ".breturnoutstock = 'N' ");
    squareWhere.endParentheses();

    // 已结算数量不等于结算单数量
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" abs(isnull(" + subTable
        + ".nsquareregnum,.0) + isnull(" + subTable
        + ".ndownianum,.0) + isnull(" + subTable + ".nrushnum,.0)) < " + "abs("
        + subTable + ".nnum)");
    squareWhere.endParentheses();

    // 累计下游发票成本结算数量不等于出库待结算数量
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".ndownianum,.0) != " + subTable
        + ".nnum");
    squareWhere.endParentheses();

    // 出库待结算数量不等于累计出库对冲数量
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".nnum,.0) != isnull("
        + subTable + ".nrushnum,.0) ");
    squareWhere.endParentheses();

    // 行可以传存货核算
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(subTable + ".bcost = 'Y' ");
    squareWhere.endParentheses();

    // 结算方式为：发出商品
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(subTable + ".fpreiatype",
        SquareType.SQUARETYPE_REG_DEBIT.getIntValue());
    squareWhere.append(" and ");
    squareWhere.append(mainTable + ".bautosquarecost = 'N'");
    squareWhere.endParentheses();
  }

  private void buildNoSquareWhere(SqlBuilder squareWhere, String mainTable,
      String subTable) {
    // 已结算数量不等于 结算单数量
    squareWhere.append(" and ");
    squareWhere.startParentheses();

    squareWhere.startParentheses();
    squareWhere.append(" abs(isnull(" + subTable + ".nsquarearnum,.0)) < "
        + " abs(isnull(" + subTable + ".nnum,.0)) ");
    squareWhere.append(" and ");
    squareWhere.append(subTable + ".fpreartype",
        SquareType.SQUARETYPE_AR.getIntValue());
    squareWhere.append(" and ");
    squareWhere.append(subTable + ".bincome = 'Y' ");
    squareWhere.append(" and ");
    squareWhere.append(mainTable + ".bautosquareincome = 'N'");
    squareWhere.endParentheses();

    squareWhere.append(" or ");
    squareWhere.startParentheses();
    squareWhere.append(" abs(isnull(" + subTable + ".nsquareianum,.0)) <"
        + " abs(isnull(" + subTable + ".nnum,0)) ");
    squareWhere.append(" and ");
    squareWhere.append(subTable + ".fpreiatype",
        SquareType.SQUARETYPE_IA.getIntValue());
    squareWhere.append(" and ");
    squareWhere.append(subTable + ".bcost = 'Y' ");
    squareWhere.append(" and ");
    squareWhere.append(mainTable + ".bautosquarecost = 'N'");
    squareWhere.endParentheses();

    squareWhere.endParentheses();
  }

  private void buildYesETWhere(SqlBuilder squareWhere, String mainTable,
      String subTable) {
    // 过滤基于签收开票退回的销售出库单待结算单
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(mainTable + ".breturnoutstock = 'N' ");
    squareWhere.endParentheses();

    // 结算数量不等于0
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".nsquareestnum,.0)", "!=", 0);
    squareWhere.endParentheses();

    // 结算方式为：暂估应收
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(subTable + ".fpreartype",
        SquareType.SQUARETYPE_ET.getIntValue());
    squareWhere.append(" and ");
    squareWhere.append(mainTable + ".bautosquareincome = 'N'");
    squareWhere.endParentheses();
  }

  private void buildYesREGWhere(SqlBuilder squareWhere, String mainTable,
      String subTable) {
    // 过滤基于签收开票退回的销售出库单待结算单
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(mainTable + ".breturnoutstock = 'N' ");
    squareWhere.endParentheses();

    // 结算数量不等于0
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".nsquareregnum,.0)", "!=", 0);
    squareWhere.endParentheses();

    // 结算方式为：发出商品
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(subTable + ".fpreiatype",
        SquareType.SQUARETYPE_REG_DEBIT.getIntValue());
    squareWhere.append(" and ");
    squareWhere.append(mainTable + ".bautosquarecost = 'N'");
    squareWhere.endParentheses();
  }

  private void buildYesRushBlueWhere(SqlBuilder squareWhere, String subTable) {
    // 已出库对冲数量不等于0
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".nrushnum,.0)!=0");
    squareWhere.endParentheses();

    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(subTable + ".nnum > 0 ");
    squareWhere.endParentheses();
  }

  private void buildYesRushRedWhere(SqlBuilder squareWhere, String subTable) {
    // 已出库对冲数量不等于0
    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".nrushnum,.0)!=0");
    squareWhere.endParentheses();

    squareWhere.append(" and ");
    squareWhere.startParentheses();
    squareWhere.append(subTable + ".nnum < 0 ");
    squareWhere.endParentheses();

  }

  private void buildYesSquareWhere(SqlBuilder squareWhere, String mainTable,
      String subTable) {
    // 已结算数量不等于 < 结算单数量
    squareWhere.append(" and ");
    squareWhere.startParentheses();

    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".nsquarearnum,.0)", "!=", 0);
    squareWhere.append(" and ");
    squareWhere.append(subTable + ".fpreartype",
        SquareType.SQUARETYPE_AR.getIntValue());
    squareWhere.append(" and ");
    squareWhere.append(mainTable + ".bautosquareincome = 'N'");
    squareWhere.endParentheses();

    squareWhere.append(" or ");
    squareWhere.startParentheses();
    squareWhere.append(" isnull(" + subTable + ".nsquareianum,.0)", "!=", 0);
    squareWhere.append(" and ");
    squareWhere.append(subTable + ".fpreiatype",
        SquareType.SQUARETYPE_IA.getIntValue());
    squareWhere.append(" and ");
    squareWhere.append(mainTable + ".bautosquarecost = 'N'");
    squareWhere.endParentheses();

    squareWhere.endParentheses();
  }

  /**
   * 拼接排序sql 默认方法（单据号）
   * 
   * @param queryScheme
   * @return
   */
  private String createOrderSql(IQueryScheme queryScheme) {
    // 根据单据号、行号排序
    SqlBuilder order = new SqlBuilder();
    QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
    order.append(" order by ");
    String tableName =
        processor.getTableAliasOfAttribute(SquareOutHVO.class,
            SquareOutHVO.VBILLCODE);
    order.append(tableName);
    order.append(".");
    order.append(SaleOrderHVO.VBILLCODE);

    return order.toString();
  }

  private SquareOutViewVO[] queryBySchemeViewQuery(IQueryScheme scheme) {
    SchemeViewQuery<SquareOutViewVO> query =
        new SchemeViewQuery<SquareOutViewVO>(SquareOutViewVO.class);
    String order = this.createOrderSql(scheme);
    SquareOutViewVO[] views = query.query(scheme, order);
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }
    for (SquareOutViewVO view : views) {
      SquareOutHVO headvo = view.getHead();
      SquareOutBVO bodyvo = view.getItem();
      headvo.setPk_group(bodyvo.getPk_group());
      headvo.setPk_org(bodyvo.getPk_org());
      headvo.setDbilldate(bodyvo.getDbilldate());
    }
    return views;
  }

  private SquareOutViewVO[] queryForAllUISquare(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    QueryCondition qc = qsp.getQueryCondition(QueryFlag.SQUAREFLAG);

    Object[] obj = qc.getValues();
    int flag = Integer.parseInt(obj[0].toString());
    SquareOutViewVO[] svos = null;
    // 查询待结算数据
    if (flag == QueryFlag.SQUARE) {
      svos = this.queryViewForNoSquare(qsp, subTable, queryScheme);
    }
    // 查询已结算数据
    else if (flag == QueryFlag.SQUARED) {
      svos = this.queryViewForYesSquare(qsp, subTable, queryScheme);
    }
    // 查询待暂估应收数据
    else if (flag == QueryFlag.ET) {
      svos = this.queryViewForNoET(qsp, subTable, queryScheme);
    }
    // 查询已暂估应收数据
    else if (flag == QueryFlag.ETD) {
      svos = this.queryViewForYesET(qsp, subTable, queryScheme);
    }
    // 查询待计入发出商品数据
    else if (flag == QueryFlag.REG) {
      svos = this.queryViewForNoREG(qsp, subTable, queryScheme);
    }
    // 查询已计入发出商品数据
    else if (flag == QueryFlag.REGD) {
      svos = this.queryViewForYesREG(qsp, subTable, queryScheme);
    }
    // 查询已出库对冲蓝字出库单数据
    else if (flag == QueryFlag.UNOUTRUSHBLUE) {
      svos = this.queryViewForYesRushBlue(qsp, subTable, queryScheme);
    }
    // 查询已出库对冲红字出库单数据
    else if (flag == QueryFlag.UNOUTRUSHRED) {
      svos = this.queryViewForYesRushRed(qsp, subTable, queryScheme);
    }
    return svos;
  }

  private SquareOutDetailVO[] querySquareOutDetailVOBySQBIDAndSquareType(
      String[] sqbids, SquareType type) {
    VOQuery<SquareOutDetailVO> qry =
        new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class);
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String outbidWhere = iq.buildSQL(SquareOutDetailVO.CSALESQUAREBID, sqbids);
    SqlBuilder where = new SqlBuilder();
    where.append(" and ");
    where.append(outbidWhere);
    where.append(" and ");
    where.append(SquareOutDetailVO.FSQUARETYPE, type.getIntValue());
    SquareOutDetailVO[] retVOs = qry.query(where.toString(), null);
    return retVOs;
  }

  /**
   * 查询未暂估应收的销售出库待结算单
   * 
   * @param qsp
   * @param mainTable
   * @param subTable
   * @return
   */
  private SquareOutViewVO[] queryViewForNoET(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    SqlBuilder squareWhere = new SqlBuilder();
    String mainTable = qsp.getMainTableAlias();

    // 待手工暂估应收条件
    this.buildNoETWhere(squareWhere, mainTable, subTable);

    // 增加上述where条件
    qsp.appendWhere(squareWhere.toString());

    // 增加销售订单上的开票关闭字段
    this.appendOrderInvEndWhere(qsp, subTable);
    // 查询
    SquareOutViewVO[] svos = this.queryBySchemeViewQuery(queryScheme);

    // 设置本次可暂估应收数量
    if (svos != null && svos.length > 0) {
      for (SquareOutViewVO svo : svos) {
        svo.setNthisnumForET();
      }
      new ProcessWC().reCalNumMny(svos);
    }
    return svos;
  }

  private void appendOrderInvEndWhere(QuerySchemeProcessor qsp, String subTable) {
    qsp.appendFrom(" inner join so_saleorder_b so_saleorder_b on ");
    qsp.appendFrom(subTable + ".cfirstbid = so_saleorder_b.csaleorderbid ");
    qsp.appendWhere(" and isnull(so_saleorder_b.bbinvoicendflag,'N') = 'N' ");
  }

  /**
   * 查询未手工计入发出商品的销售出库待结算单
   * 
   * @param qsp
   * @param mainTable
   * @param subTable
   * @return
   */
  private SquareOutViewVO[] queryViewForNoREG(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    SqlBuilder squareWhere = new SqlBuilder();
    String mainTable = qsp.getMainTableAlias();

    // 待手工计入发出商品条件
    this.buildNoREGWhere(squareWhere, mainTable, subTable);

    // 增加上述where条件
    qsp.appendWhere(squareWhere.toString());

    // 增加销售订单上的开票关闭字段
    this.appendOrderInvEndWhere(qsp, subTable);
    // 查询
    SquareOutViewVO[] svos = this.queryBySchemeViewQuery(queryScheme);

    // 设置本次可手工计入发出商品数量
    if (svos != null && svos.length > 0) {
      for (SquareOutViewVO svo : svos) {
        svo.setNthisnumForREG();
      }
      new ProcessWC().reCalNumMny(svos);
    }
    return svos;
  }

  /**
   * 查询未收入成本结算过的销售出库待结算单
   * 
   * @param qsp
   * @param mainTable
   * @param subTable
   * @return
   */
  private SquareOutViewVO[] queryViewForNoSquare(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    SqlBuilder squareWhere = new SqlBuilder();
    String mainTable = qsp.getMainTableAlias();

    // 待手工收入成本结算条件
    this.buildNoSquareWhere(squareWhere, mainTable, subTable);

    // 增加上述where条件
    qsp.appendWhere(squareWhere.toString());

    // 查询
    SquareOutViewVO[] svos = this.queryBySchemeViewQuery(queryScheme);

    // 设置本次可结算数量
    if (svos != null && svos.length > 0) {
      for (SquareOutViewVO svo : svos) {
        svo.setNthisnumForManualSquare();
      }
      // 本次结算数量不等于行数量的需要重新计算金额
      ProcessWC wc = new ProcessWC();
      wc.reCalNumMnyAndWCForManualSquareQuery(svos);
    }
    return svos;
  }

  private SquareOutViewVO[] queryViewForYesET(IQueryScheme queryScheme) {
    SquareOutViewVO[] vos = this.queryBySchemeViewQuery(queryScheme);
    if (!SOVOChecker.isEmpty(vos)) {
      String[] bids =
          SoVoTools.getVOsOnlyValues(vos, SquareOutBVO.CSALESQUAREBID);

      // 手工暂估应收明细表vo
      SquareOutDetailVO[] sdvos =
          this.queryManualDetailVOBySQBIDandSQType(bids, new SquareType[] {
            SquareType.SQUARETYPE_ET
          });

      // 组织返回vo
      vos = SquareOutVOUtils.getInstance().buildSquareOutdVO(vos, sdvos);
    }
    return vos;
  }

  /**
   * 查询已经暂估应收过的销售出库待结算单
   */
  private SquareOutViewVO[] queryViewForYesET(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    SqlBuilder squareWhere = new SqlBuilder();
    String mainTable = qsp.getMainTableAlias();

    // 已经暂估应收过条件
    this.buildYesETWhere(squareWhere, mainTable, subTable);

    // 增加上述where条件
    qsp.appendWhere(squareWhere.toString());

    SquareOutViewVO[] vos = this.queryViewForYesET(queryScheme);

    return vos;
  }

  private SquareOutViewVO[] queryViewForYesREG(IQueryScheme queryScheme) {
    SquareOutViewVO[] vos = this.queryBySchemeViewQuery(queryScheme);
    if (!SOVOChecker.isEmpty(vos)) {
      String[] bids =
          SoVoTools.getVOsOnlyValues(vos, SquareOutBVO.CSALESQUAREBID);

      // 手工计入发出商品明细表vo
      SquareOutDetailVO[] sdvos =
          this.queryManualDetailVOBySQBIDandSQType(bids, new SquareType[] {
            SquareType.SQUARETYPE_REG_DEBIT
          });

      // 组织返回vo
      vos = SquareOutVOUtils.getInstance().buildSquareOutdVO(vos, sdvos);
    }
    return vos;
  }

  /**
   * 查询已经手工计入发出商品的销售出库待结算单
   */
  private SquareOutViewVO[] queryViewForYesREG(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    SqlBuilder squareWhere = new SqlBuilder();
    String mainTable = qsp.getMainTableAlias();

    // 已经手工计入发出商品过条件
    this.buildYesREGWhere(squareWhere, mainTable, subTable);

    // 增加上述where条件
    qsp.appendWhere(squareWhere.toString());

    SquareOutViewVO[] vos = this.queryViewForYesREG(queryScheme);

    return vos;
  }

  private SquareOutViewVO[] queryViewForYesRush(IQueryScheme queryScheme) {
    SquareOutViewVO[] vos = this.queryBySchemeViewQuery(queryScheme);
    if (!SOVOChecker.isEmpty(vos)) {
      String[] bids =
          SoVoTools.getVOsOnlyValues(vos, SquareOutBVO.CSALESQUAREBID);

      // 手工收入成本结算明细表vo
      SquareOutDetailVO[] sdvos = null;
      StringBuilder where = new StringBuilder();
      where.append(" and boutrushflag = 'Y' ");
      where.append(" and dr = 0 ");
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
      where.append(" and "
          + iq.buildSQL(SquareOutDetailVO.CSALESQUAREBID, bids));
      where.append(" and fsquaretype = "
          + SquareType.SQUARETYPE_OUTRUSH.getIntValue());
      sdvos =
          new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class).query(
              where.toString(), null);

      // 组织返回vo
      vos = SquareOutVOUtils.getInstance().buildSquareOutdVO(vos, sdvos);
    }

    return vos;
  }

  /**
   * 查询已经做过出库对冲的蓝字销售出库待结算单
   */
  private SquareOutViewVO[] queryViewForYesRushBlue(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    SqlBuilder squareWhere = new SqlBuilder();

    // 已经出库对冲过蓝字出库单条件
    this.buildYesRushBlueWhere(squareWhere, subTable);

    // 增加上述where条件
    qsp.appendWhere(squareWhere.toString());

    SquareOutViewVO[] vos = this.queryViewForYesRush(queryScheme);

    return vos;
  }

  /**
   * 查询已经做过出库对冲的红字销售出库待结算单
   */
  private SquareOutViewVO[] queryViewForYesRushRed(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    SqlBuilder squareWhere = new SqlBuilder();

    // 已经出库对冲过红字出库单条件
    this.buildYesRushRedWhere(squareWhere, subTable);

    // 增加上述where条件
    qsp.appendWhere(squareWhere.toString());

    SquareOutViewVO[] vos = this.queryViewForYesRush(queryScheme);

    return vos;
  }

  private SquareOutViewVO[] queryViewForYesSquare(IQueryScheme queryScheme) {
    SquareOutViewVO[] vos = this.queryBySchemeViewQuery(queryScheme);
    if (!SOVOChecker.isEmpty(vos)) {
      String[] bids =
          SoVoTools.getVOsOnlyValues(vos, SquareOutBVO.CSALESQUAREBID);

      // 手工收入成本结算明细表vo
      SquareOutDetailVO[] sdvos =
          this.queryManualDetailVOBySQBIDandSQType(bids, new SquareType[] {
            SquareType.SQUARETYPE_AR, SquareType.SQUARETYPE_IA
          });

      // 组织返回vo
      if (VOChecker.isEmpty(sdvos)) {
        vos = null;
      }
      else {
        vos = SquareOutVOUtils.getInstance().buildSquareOutdVO(vos, sdvos);
      }
    }

    return vos;
  }

  /**
   * 查询已经收入成本结算过的销售出库待结算单
   */
  private SquareOutViewVO[] queryViewForYesSquare(QuerySchemeProcessor qsp,
      String subTable, IQueryScheme queryScheme) {
    SqlBuilder squareWhere = new SqlBuilder();
    String mainTable = qsp.getMainTableAlias();

    // 待手工收入成本结算条件
    this.buildYesSquareWhere(squareWhere, mainTable, subTable);

    // 增加上述where条件
    qsp.appendWhere(squareWhere.toString());

    SquareOutViewVO[] vos = this.queryViewForYesSquare(queryScheme);

    return vos;
  }

}
