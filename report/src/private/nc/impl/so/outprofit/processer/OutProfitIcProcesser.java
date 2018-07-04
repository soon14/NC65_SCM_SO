package nc.impl.so.outprofit.processer;

import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

import nc.vo.bd.cust.CustomerVO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.ITableMeta;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.data.IRowSetMap;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.model.meta.table.Column;
import nc.vo.pubapp.pattern.model.meta.table.Table;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.pub.SOTable;
import nc.vo.so.report.outprofit.OutProfitViewMeta;
import nc.vo.so.report.outprofit.OutProfitViewVO;
import nc.vo.so.report.reportpub.ReportUserObject;

import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.impl.pubapp.pattern.page.IPage;
import nc.impl.pubapp.pattern.page.db.DBPage;
import nc.impl.so.outprofit.temp.OutProfitIcRowSetMap;
import nc.impl.so.outprofit.temp.OutProfitModelTable;

import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 分页查询出库单数据
 * 
 * @since 6.3
 * @version 2012-09-04 13:43:42
 * @author 梁吉明
 */
public class OutProfitIcProcesser implements IPage<OutProfitViewVO> {

  private IPage<OutProfitViewVO> ds;

  private final static int FETCH_SIZE = 10000;

  /**
   * 
   * @param userobj 用户前台设置信息
   */
  public OutProfitIcProcesser(ReportUserObject userobj) {

    // 1.获得查询出库单的SQL
    String queryOutSql = this.getQueryOutSql(userobj);

    // 2.获得对象映射
    IRowSetMap<OutProfitViewVO> rowsetMap = new OutProfitIcRowSetMap();

    // 3.获得临时表
    ITableMeta table = this.createTable();

    // 4.分页查询出库单信息
    this.ds =
        new DBPage<OutProfitViewVO>(queryOutSql, rowsetMap, table,
            OutProfitIcProcesser.FETCH_SIZE);

  }

  @Override
  public int getMaxRowsInPage() {
    return this.ds.getMaxRowsInPage();
  }

  @Override
  public boolean hasNext() {
    return this.ds.hasNext();
  }

  @Override
  public OutProfitViewVO[] next() {
    return this.ds.next();
  }

  private String getQueryOutSql(ReportUserObject userobj) {
    IQueryScheme queryScheme = userobj.getIQueryScheme();
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    Set<String> groupkeys = userobj.getSummaryKeys();
    SqlBuilder qrysql = new SqlBuilder();
    qrysql.append("select  distinct  ");
    this.getSelect(qrysql, qsp, groupkeys);
    this.appendFrom(qsp, queryScheme, groupkeys);
    this.appendWhere(qsp, queryScheme);

    qsp.appendCurrentGroup();
    qrysql.append(qsp.getFinalFromWhere());
    String sql = qrysql.toString();
    return sql.replace("inner join bd_custsale", "left join bd_custsale");
  }

  /**
   * 拼接销售订单表
   * 
   * @param qsp
   * @param queryScheme
   * @param groupkeys
   */
  private void appendFrom(QuerySchemeProcessor qsp, IQueryScheme queryScheme,
      Set<String> groupkeys) {
    if (qsp == null || queryScheme == null) {
      return;
    }
    boolean hasSaleorder = false;
    for (String key : groupkeys) {
      if (key.equals(OutProfitViewVO.CCHANNELTYPEID)) {
        hasSaleorder = true;
        break;
      }
    }
    if (!hasSaleorder) {
      ConditionVO[] conditionVOs =
          (ConditionVO[]) queryScheme.get(IQueryScheme.KEY_LOGICAL_CONDITION);
      for (ConditionVO cond : conditionVOs) {
        if (cond.getFieldCode().equals(OutProfitViewVO.CCHANNELTYPEID)) {
          hasSaleorder = true;
          break;
        }
      }
    }

    if (hasSaleorder) {
      // 出库单子表固定字段
      String outbt =
          qsp.getTableAliasOfAttribute(SaleOutBodyVO.class,
              OutProfitViewVO.CGENERALBID);
      qsp.appendFrom("inner join so_saleorder so_saleorder on ");
      qsp.appendFrom("so_saleorder.csaleorderid = " + outbt + ".cfirstbillhid");
    }
  }

  /**
   * 拼写where条件
   * 
   * @param qsp
   * @param queryScheme
   * @param groupkeys
   */
  private void appendWhere(QuerySchemeProcessor qsp, IQueryScheme queryScheme) {
    if (qsp == null || queryScheme == null) {
      return;
    }
    // 获取查询字段
    ConditionVO[] conditionVOs =
        (ConditionVO[]) queryScheme.get(IQueryScheme.KEY_LOGICAL_CONDITION);
    for (ConditionVO cond : conditionVOs) {
      if (cond.getFieldCode().equals(OutProfitViewVO.CCHANNELTYPEID)) {
        String channels = cond.getValue();
        if (channels == null) {
          return;
        }
        channels = channels.replace("(", "");
        channels = channels.replace(")", "");
        channels = channels.replace("'", "");
        String[] keys = channels.substring(0, channels.length()).split(",");
        if (keys.length < 1) {
          return;
        }
        IDQueryBuilder bo = new IDQueryBuilder();
        String sqlPart = bo.buildSQL(OutProfitViewVO.CCHANNELTYPEID, keys);

        String martable = "so_saleorder";
        // String saleorderTableNa =
        // qsp.getTableAliasOfAttribute(SaleOrderHVO.class,
        // SaleOrderHVO.CSALEORDERID); // 获得表名
        qsp.appendWhere(" and " + martable + "." + sqlPart);
      }
    }
  }

  private void getSelect(SqlBuilder qrysql, QuerySchemeProcessor qsp,
      Set<String> groupkeys) {
    this.appendFixSelect(qrysql, qsp);
    this.appendDynaSelect(qrysql, groupkeys, qsp);
  }

  private void appendFixSelect(SqlBuilder sql, QuerySchemeProcessor qsp) {
    // 出库单主表固定字段
    String outht = qsp.getMainTableAlias();
    String[] fixheads =
        new String[] {
          OutProfitViewVO.CGENERALHID, OutProfitViewVO.CSALEORGOID,
          OutProfitViewVO.CDPTID, OutProfitViewVO.CBIZID,
          OutProfitViewVO.CCUSTOMERID, OutProfitViewVO.VBILLCODE,
          OutProfitViewVO.CTRANTYPEID, OutProfitViewVO.VDEF1,
          OutProfitViewVO.VDEF2, OutProfitViewVO.VDEF3, OutProfitViewVO.VDEF4,
          OutProfitViewVO.VDEF5, OutProfitViewVO.VDEF6, OutProfitViewVO.VDEF7,
          OutProfitViewVO.VDEF8, OutProfitViewVO.VDEF9, OutProfitViewVO.VDEF10,
          OutProfitViewVO.VDEF11, OutProfitViewVO.VDEF12,
          OutProfitViewVO.VDEF13, OutProfitViewVO.VDEF14,
          OutProfitViewVO.VDEF15, OutProfitViewVO.VDEF16,
          OutProfitViewVO.VDEF17, OutProfitViewVO.VDEF18,
          OutProfitViewVO.VDEF19, OutProfitViewVO.VDEF20

        };
    for (String key : fixheads) {
      sql.append(this.getTableKey(outht, key));

    }
    // 出库单子表固定字段
    String outbt =
        qsp.getTableAliasOfAttribute(SaleOutBodyVO.class,
            OutProfitViewVO.CGENERALBID);
    String[] fixbodys =
        new String[] {
          OutProfitViewVO.CGENERALBID, OutProfitViewVO.CMATERIALOID,
          OutProfitViewVO.CMATERIALVID, OutProfitViewVO.CUNITID,
          OutProfitViewVO.VBATCHCODE, OutProfitViewVO.CORIGCURRENCYID,
          OutProfitViewVO.NNUM, "nnetprice", OutProfitViewVO.PK_ORG,
          OutProfitViewVO.CBODYWAREHOUSEID, OutProfitViewVO.CFANACEORGOID,
          OutProfitViewVO.VBDEF1, OutProfitViewVO.VBDEF2,
          OutProfitViewVO.VBDEF3, OutProfitViewVO.VBDEF4,
          OutProfitViewVO.VBDEF5, OutProfitViewVO.VBDEF6,
          OutProfitViewVO.VBDEF7, OutProfitViewVO.VBDEF8,
          OutProfitViewVO.VBDEF9, OutProfitViewVO.VBDEF10,
          OutProfitViewVO.VBDEF11, OutProfitViewVO.VBDEF12,
          OutProfitViewVO.VBDEF13, OutProfitViewVO.VBDEF14,
          OutProfitViewVO.VBDEF15, OutProfitViewVO.VBDEF16,
          OutProfitViewVO.VBDEF17, OutProfitViewVO.VBDEF18,
          OutProfitViewVO.VBDEF19, OutProfitViewVO.VBDEF20,
          OutProfitViewVO.FLARGESS
        };
    for (String key : fixbodys) {
      sql.append(this.getTableKey(outbt, key));

    }
  }

  private void appendDynaSelect(SqlBuilder qrysql, Set<String> groupkeys,
      QuerySchemeProcessor qsp) {
    if (groupkeys.contains(OutProfitViewVO.PK_CUSTCLASS)) {
      String custtable =
          qsp.getTableAliasOfAttribute(CustomerVO.class, CustomerVO.PK_CUSTOMER);
      qrysql.append(this.getTableKey(custtable, OutProfitViewVO.PK_CUSTCLASS));
    }
    else {
      qrysql.append(" null as " + OutProfitViewVO.PK_CUSTCLASS + ",");
    }

    if (groupkeys.contains(OutProfitViewVO.PK_CUSTSALECLASS)) {
      String str = "ccustomerid.sales.pk_custsaleclass";
      String salecltable = qsp.getTableAliasOfAttribute(str);
      qrysql.append(this.getTableKey(salecltable,
          OutProfitViewVO.PK_CUSTSALECLASS));
    }
    else {
      qrysql.append(" null as " + OutProfitViewVO.PK_CUSTSALECLASS + ",");
    }

    if (groupkeys.contains(OutProfitViewVO.PK_AREACL)) {
      String salecltable =
          qsp.getTableAliasOfAttribute(CustomerVO.class, CustomerVO.PK_CUSTOMER);
      qrysql.append(this.getTableKey(salecltable, OutProfitViewVO.PK_AREACL));
    }
    else {
      qrysql.append(" null as " + OutProfitViewVO.PK_AREACL + ",");
    }

    if (groupkeys.contains(OutProfitViewVO.PK_MARBASCLASS)) {
      String martable =
          qsp.getTableAliasOfAttribute(MaterialVO.class,
              MaterialVO.PK_MARBASCLASS);
      qrysql.append(this.getTableKey(martable, OutProfitViewVO.PK_MARBASCLASS));
    }
    else {
      qrysql.append(" null as " + OutProfitViewVO.PK_MARBASCLASS + ",");
    }

    if (groupkeys.contains(OutProfitViewVO.PK_MARSALECLASS)) {
      String str = "cgeneralbid.cmaterialvid.materialsale.pk_marsaleclass";
      String marsaletable = qsp.getTableAliasOfAttribute(str);
      qrysql.append(this.getTableKey(marsaletable,
          OutProfitViewVO.PK_MARSALECLASS));
    }
    else {
      qrysql.append(" null as " + OutProfitViewVO.PK_MARSALECLASS + ",");
    }
    if (groupkeys.contains(OutProfitViewVO.CCHANNELTYPEID)) {
      String table = "so_saleorder";
      qrysql.append(this.getTableKey(table, OutProfitViewVO.CCHANNELTYPEID));
    }
    else {
      qrysql.append(" null as " + OutProfitViewVO.CCHANNELTYPEID + ",");
    }
    qrysql.deleteLastChar();
  }

  private String getTableKey(String table, String key) {
    return table + "." + key + " " + key + " ,";
  }

  private Set<String> getBodyGroupKeySet() {
    String[] bodykey =
        new String[] {
          OutProfitViewVO.VBDEF1, OutProfitViewVO.VBDEF2,
          OutProfitViewVO.VBDEF3, OutProfitViewVO.VBDEF4,
          OutProfitViewVO.VBDEF5, OutProfitViewVO.VBDEF6,
          OutProfitViewVO.VBDEF7, OutProfitViewVO.VBDEF8,
          OutProfitViewVO.VBDEF9, OutProfitViewVO.VBDEF10,
          OutProfitViewVO.VBDEF11, OutProfitViewVO.VBDEF12,
          OutProfitViewVO.VBDEF13, OutProfitViewVO.VBDEF14,
          OutProfitViewVO.VBDEF15, OutProfitViewVO.VBDEF16,
          OutProfitViewVO.VBDEF17, OutProfitViewVO.VBDEF18,
          OutProfitViewVO.VBDEF19, OutProfitViewVO.VBDEF20
        };
    Set<String> setbody = new HashSet<String>();
    for (String key : bodykey) {
      setbody.add(key);
    }
    return setbody;
  }

  private Set<String> getHeadGroupKeySet() {
    String[] headkey =
        new String[] {
          OutProfitViewVO.CSALEORGOID, OutProfitViewVO.CDPTID,
          OutProfitViewVO.CBIZID, OutProfitViewVO.CCUSTOMERID,
          OutProfitViewVO.VBILLCODE, OutProfitViewVO.CTRANTYPEID,
          OutProfitViewVO.VDEF1, OutProfitViewVO.VDEF2, OutProfitViewVO.VDEF3,
          OutProfitViewVO.VDEF4, OutProfitViewVO.VDEF5, OutProfitViewVO.VDEF6,
          OutProfitViewVO.VDEF7, OutProfitViewVO.VDEF8, OutProfitViewVO.VDEF9,
          OutProfitViewVO.VDEF10, OutProfitViewVO.VDEF11,
          OutProfitViewVO.VDEF12, OutProfitViewVO.VDEF13,
          OutProfitViewVO.VDEF14, OutProfitViewVO.VDEF15,
          OutProfitViewVO.VDEF16, OutProfitViewVO.VDEF17,
          OutProfitViewVO.VDEF18, OutProfitViewVO.VDEF19,
          OutProfitViewVO.VDEF20
        };
    Set<String> sethead = new HashSet<String>();
    for (String key : headkey) {
      sethead.add(key);
    }
    return sethead;
  }

  private ITableMeta createTable() {
    OutProfitModelTable table =
        new OutProfitModelTable(SOTable.TMP_SO_OUTPFPAGE.getName());
    OutProfitViewVO view = new OutProfitViewVO();
    IDataViewMeta viewmeta = view.getMetaData();
    for (String key : OutProfitViewMeta.SALEOUT_HKEYS) {
      this.addKeys(key, viewmeta, table);
    }
    for (String key : OutProfitViewMeta.SALEOUT_BKEYS) {
      this.addKeys(key, viewmeta, table);
    }
    for (String key : OutProfitViewMeta.EXTEND_STRKEYS) {
      this.addKeys(key, viewmeta, table);
    }
    return table;
  }

  private void addKeys(String key, IDataViewMeta viewmeta, Table table) {
    IAttributeMeta attrmeta = viewmeta.getAttribute(key);
    if (null != attrmeta.getColumn()) {
      table.add(attrmeta.getColumn());
    }
    else {
      if (key.startsWith("n")) {
        Column column = new Column(table, key);
        column.setLength(28);
        column.setSqlType(Types.DECIMAL);
        column.setPrecision(8);
        column.setLabel(key);
        table.add(column);
      }
      else if (key.equals("vbillcode") || key.equals("vbatchcode")) {
        Column column = new Column(table, key);
        column.setLength(40);
        column.setSqlType(Types.VARCHAR);
        column.setPrecision(0);
        column.setLabel(key);
        table.add(column);
      }
      else {
        Column column = new Column(table, key);
        column.setLength(20);
        column.setSqlType(Types.VARCHAR);
        column.setPrecision(0);
        column.setLabel(key);
        table.add(column);
      }
    }
  }

}
