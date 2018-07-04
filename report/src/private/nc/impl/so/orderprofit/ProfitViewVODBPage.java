package nc.impl.so.orderprofit;

import java.sql.Types;

import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.ITableMeta;
import nc.vo.pubapp.pattern.data.IRowSetMap;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.model.meta.table.Column;
import nc.vo.pubapp.pattern.model.meta.table.Table;
import nc.vo.so.pub.SOTable;
import nc.vo.so.report.profit.OrderPorfitViewMeta;
import nc.vo.so.report.profit.OrderProfitViewVO;
import nc.vo.so.report.profit.ProfitModelTable;

import nc.impl.pubapp.pattern.page.IPage;
import nc.impl.pubapp.pattern.page.db.DBPage;

public class ProfitViewVODBPage implements IPage<OrderProfitViewVO> {

  private IPage<OrderProfitViewVO> ds;

  public ProfitViewVODBPage(String sql, int fetchSize) {
    IRowSetMap<OrderProfitViewVO> map = new ProfitViewVORowSetMap();
    ITableMeta table = this.createTable();
    this.ds = new DBPage<OrderProfitViewVO>(sql, map, table, fetchSize);
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
  public OrderProfitViewVO[] next() {
    return this.ds.next();
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
      else {
        Column column = new Column(table, key);
        column.setLength(20);
        column.setSqlType(Types.CHAR);
        column.setPrecision(0);
        column.setLabel(key);
        table.add(column);
      }

    }
  }

  private ITableMeta createTable() {
    ProfitModelTable table =
        new ProfitModelTable(SOTable.TMP_SO_ORDERPFPAGE.getName());
    OrderProfitViewVO view = new OrderProfitViewVO();
    IDataViewMeta viewmeta = view.getMetaData();
    for (String key : OrderPorfitViewMeta.HNAMES) {
      this.addKeys(key, viewmeta, table);
    }

    for (String key : OrderPorfitViewMeta.BNAMES) {
      this.addKeys(key, viewmeta, table);
    }

    for (String key : OrderPorfitViewMeta.MATERIALNAMES) {
      this.addKeys(key, viewmeta, table);
    }

    for (String key : OrderPorfitViewMeta.MATERIALSALENAMES) {
      this.addKeys(key, viewmeta, table);
    }

    for (String key : OrderPorfitViewMeta.CUSTNAMES) {
      this.addKeys(key, viewmeta, table);
    }

    for (String key : OrderPorfitViewMeta.CUSTSALENAMES) {
      this.addKeys(key, viewmeta, table);
    }

    for (String key : OrderPorfitViewMeta.DOUSELECT) {
      this.addKeys(key, viewmeta, table);
    }

    for (String key : OrderPorfitViewMeta.DOUBLEFIELDS) {
      this.addKeys(key, viewmeta, table);
    }

    return table;

  }
}
