package nc.impl.so.outsummary.processer;

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
import nc.vo.so.report.outsummary.OutSummaryViewMeta;
import nc.vo.so.report.outsummary.OutSummaryViewVO;
import nc.vo.so.report.reportpub.ReportUserObject;

import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.impl.pubapp.pattern.page.IPage;
import nc.impl.pubapp.pattern.page.db.DBPage;
import nc.impl.so.outsummary.temp.OutSummaryIcRowSetMap;
import nc.impl.so.outsummary.temp.OutSummrayModelTable;

import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 分页查询出库单数据
 * 
 * @since 6.3
 * @version 2012-09-04 13:43:42
 * @author 梁吉明
 */
public class OutSummaryIcProcesser implements IPage<OutSummaryViewVO> {

  private IPage<OutSummaryViewVO> ds;

  private final static int FETCH_SIZE = 10000;

  /**
   * 构造方法
   * 
   * @param userobj
   */
  public OutSummaryIcProcesser(ReportUserObject userobj) {

    // 1.获得查询出库单的SQL
    String queryOutSql = this.getQueryOutSql(userobj);

    // 2.获得对象映射
    IRowSetMap<OutSummaryViewVO> rowsetMap = new OutSummaryIcRowSetMap();

    // 3.获得临时表
    ITableMeta table = this.createTable();

    // 4.分页查询出库单信息
    this.ds =
        new DBPage<OutSummaryViewVO>(queryOutSql, rowsetMap, table,
            OutSummaryIcProcesser.FETCH_SIZE);

  }

  private ITableMeta createTable() {
    OutSummrayModelTable table =
        new OutSummrayModelTable(SOTable.TMP_SO_OUTSUMPAGE.getName());
    OutSummaryViewVO view = new OutSummaryViewVO();
    IDataViewMeta viewmeta = view.getMetaData();
    for (String key : OutSummaryViewMeta.SALEOUT_HKEYS) {
      this.addKeys(key, viewmeta, table);
    }
    for (String key : OutSummaryViewMeta.SALEOUT_BKEYS) {
      this.addKeys(key, viewmeta, table);
    }
    for (String key : OutSummaryViewMeta.SALEOUT_EXEKEYS) {
      this.addKeys(key, viewmeta, table);
    }
    for (String key : OutSummaryViewMeta.EXTEND_STRKEYS) {
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

  private String getQueryOutSql(ReportUserObject userobj) {
    IQueryScheme queryScheme = userobj.getIQueryScheme();
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    Set<String> groupkeys = userobj.getSummaryKeys();
    SqlBuilder qrysql = new SqlBuilder();
    qrysql.append("select  distinct  ");
    this.getSelect(qrysql, qsp, groupkeys);
    this.appendWhere(qsp, queryScheme);
    this.appendFrom(qsp, queryScheme, groupkeys);
    qsp.appendCurrentGroup();
    qrysql.append(qsp.getFinalFromWhere());
    String sql = qrysql.toString();
    return sql.replace("inner join bd_custsale", "left join bd_custsale");
  }

  private void getSelect(SqlBuilder qrysql, QuerySchemeProcessor qsp,
      Set<String> groupkeys) {
    this.appendFixSelect(qrysql, qsp);
    this.appendDynaSelect(qrysql, groupkeys, qsp);
  }

  private void appendDynaSelect(SqlBuilder qrysql, Set<String> groupkeys,
      QuerySchemeProcessor qsp) {
    if (groupkeys.contains(OutSummaryViewVO.PK_CUSTCLASS)) {
      String custtable =
          qsp.getTableAliasOfAttribute(CustomerVO.class, CustomerVO.PK_CUSTOMER);
      qrysql.append(this.getTableKey(custtable, OutSummaryViewVO.PK_CUSTCLASS));
    }
    else {
      qrysql.append(" null as " + OutSummaryViewVO.PK_CUSTCLASS + ",");
    }

    if (groupkeys.contains(OutSummaryViewVO.PK_CUSTSALECLASS)) {
      String str = "ccustomerid.sales.pk_custsaleclass";
      String salecltable = qsp.getTableAliasOfAttribute(str);
      qrysql.append(this.getTableKey(salecltable,
          OutSummaryViewVO.PK_CUSTSALECLASS));
    }
    else {
      qrysql.append(" null as " + OutSummaryViewVO.PK_CUSTSALECLASS + ",");
    }

    if (groupkeys.contains(OutSummaryViewVO.PK_MARBASCLASS)) {
      String martable =
          qsp.getTableAliasOfAttribute(MaterialVO.class,
              MaterialVO.PK_MARBASCLASS);
      qrysql
          .append(this.getTableKey(martable, OutSummaryViewVO.PK_MARBASCLASS));
    }
    else {
      qrysql.append(" null as " + OutSummaryViewVO.PK_MARBASCLASS + ",");
    }

    if (groupkeys.contains(OutSummaryViewVO.PK_MARSALECLASS)) {
      String str = "cgeneralbid.cmaterialvid.materialsale.pk_marsaleclass";
      String marsaletable = qsp.getTableAliasOfAttribute(str);
      qrysql.append(this.getTableKey(marsaletable,
          OutSummaryViewVO.PK_MARSALECLASS));
    }
    else {
      qrysql.append(" null as " + OutSummaryViewVO.PK_MARSALECLASS + ",");
    }

    if (groupkeys.contains(OutSummaryViewVO.PK_AREACL)) {
      String salecltable =
          qsp.getTableAliasOfAttribute(CustomerVO.class, CustomerVO.PK_CUSTOMER);
      qrysql.append(this.getTableKey(salecltable, OutSummaryViewVO.PK_AREACL));
    }
    else {
      qrysql.append(" null as " + OutSummaryViewVO.PK_AREACL + ",");
    }
    if (groupkeys.contains(OutSummaryViewVO.CCHANNELTYPEID)) {
      String table = "so_saleorder";
      qrysql.append(this.getTableKey(table, OutSummaryViewVO.CCHANNELTYPEID));
    }
    else {
      qrysql.append(" null as " + OutSummaryViewVO.CCHANNELTYPEID + ",");
    }
    qrysql.deleteLastChar();
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
    boolean hasSaleorder = false;
    for (String key : groupkeys) {
      if (key.equals(OutSummaryViewVO.CCHANNELTYPEID)) {
        hasSaleorder = true;
        break;
      }
    }
    if (!hasSaleorder) {
      ConditionVO[] conditionVOs =
          (ConditionVO[]) queryScheme.get(IQueryScheme.KEY_LOGICAL_CONDITION);
      for (ConditionVO cond : conditionVOs) {
        if (cond.getFieldCode().equals(OutSummaryViewVO.CCHANNELTYPEID)) {
          hasSaleorder = true;
          break;
        }
      }
    }

    if (hasSaleorder) {
      // 出库单子表
      String outbt =
          qsp.getTableAliasOfAttribute(SaleOutBodyVO.class,
              OutSummaryViewVO.CGENERALBID);
      qsp.appendFrom(" inner join so_saleorder so_saleorder on ");
      qsp.appendFrom("so_saleorder.csaleorderid =" + outbt + ".cfirstbillhid");
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
    // 获取查询字段
    ConditionVO[] conditionVOs =
        (ConditionVO[]) queryScheme.get(IQueryScheme.KEY_LOGICAL_CONDITION);
    for (ConditionVO cond : conditionVOs) {
      if (cond.getFieldCode().equals(OutSummaryViewVO.CCHANNELTYPEID)) {
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
        String sqlPart = bo.buildSQL(OutSummaryViewVO.CCHANNELTYPEID, keys);

        String saleorderTableName = "so_saleorder";
        qsp.appendWhere(" and " + saleorderTableName + "." + sqlPart);
      }
    }
  }

  private Set<String> getBodyGroupKeySet() {
    String[] bodykey =
        new String[] {
          OutSummaryViewVO.CINVOICECUSTID, OutSummaryViewVO.CRECEIEVEID,
          OutSummaryViewVO.VBATCHCODE
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
          OutSummaryViewVO.CSALEORGOID, OutSummaryViewVO.PK_ORG,
          OutSummaryViewVO.CDPTID, OutSummaryViewVO.CBIZID,
          OutSummaryViewVO.CCUSTOMERID
        };
    Set<String> sethead = new HashSet<String>();
    for (String key : headkey) {
      sethead.add(key);
    }
    return sethead;
  }

  private void appendFixSelect(SqlBuilder qrysql, QuerySchemeProcessor qsp) {
    // 表头固定字段
    String outht = qsp.getMainTableAlias();
    String[] fixheads =
        new String[] {
          OutSummaryViewVO.CGENERALHID, OutSummaryViewVO.CSALEORGOID,
          OutSummaryViewVO.PK_ORG, OutSummaryViewVO.CDPTID,
          OutSummaryViewVO.CBIZID, OutSummaryViewVO.CCUSTOMERID
        };
    for (String key : fixheads) {
      qrysql.append(this.getTableKey(outht, key));
    }
    // 表体固定字段
    String outbt =
        qsp.getTableAliasOfAttribute(SaleOutBodyVO.class,
            OutSummaryViewVO.CGENERALBID);
    String[] fixbodys =
        new String[] {
          OutSummaryViewVO.CGENERALBID, OutSummaryViewVO.CINVOICECUSTID,
          OutSummaryViewVO.CRECEIEVEID, OutSummaryViewVO.CMATERIALOID,
          OutSummaryViewVO.CUNITID, OutSummaryViewVO.VBATCHCODE,
          OutSummaryViewVO.FLARGESS, OutSummaryViewVO.CORIGCURRENCYID,
          OutSummaryViewVO.NNUM, OutSummaryViewVO.NORIGTAXMNY
        };
    for (String key : fixbodys) {
      if (OutSummaryViewVO.NORIGTAXMNY.equals(key)) {
        // 出库金额
        String origmnykey = outbt + "." + OutSummaryViewVO.NORIGTAXMNY;
        String condition = outbt + "." + OutSummaryViewVO.FLARGESS + "= 'Y' ";
        qrysql.appendCaseWhen(condition, "0", origmnykey);
        qrysql.append(OutSummaryViewVO.NORIGTAXMNY + ",");
      }
      else {
        qrysql.append(this.getTableKey(outbt, key));
      }
    }
    // 执行表固定字段
    String[] exekeys =
        new String[] {
          OutSummaryViewVO.NACCUMOUTSIGNNUM, OutSummaryViewVO.NACCUMOUTBACKNUM,
          OutSummaryViewVO.NACCUMWASTNUM, OutSummaryViewVO.NSIGNNUM
        };
    qsp.appendFrom("  inner join ic_saleout_e on ");
    qsp.appendFrom(outbt + ".cgeneralbid = ic_saleout_e.cgeneralbid ");
    for (String key : exekeys) {
      qrysql.append(this.getTableKey("ic_saleout_e", key));
    }
  }

  private String getTableKey(String table, String key) {
    return table + "." + key + " " + key + " ,";
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
  public OutSummaryViewVO[] next() {
    return this.ds.next();
  }

}
