package nc.vo.so.report.profit;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.IColumnMeta;
import nc.vo.pubapp.pattern.model.meta.table.Table;

public class ProfitModelTable extends Table {

  /**
   * 临时表中要增加的序号字段
   */
  protected static final String SEQUEENCE = "seq";

  public ProfitModelTable(String name) {
    super(name);
    // TODO Auto-generated constructor stub
  }

  @Override
  public IColumnMeta[] getColumns() {
    List<IColumnMeta> columns = new ArrayList<IColumnMeta>();
    for (String key : OrderPorfitViewMeta.HNAMES) {
      columns.add(this.getColumn(key));
    }

    for (String key : OrderPorfitViewMeta.BNAMES) {
      columns.add(this.getColumn(key));
    }

    for (String key : OrderPorfitViewMeta.MATERIALNAMES) {
      columns.add(this.getColumn(key));
    }
    for (String key : OrderPorfitViewMeta.MATERIALSALENAMES) {
      columns.add(this.getColumn(key));
    }
    for (String key : OrderPorfitViewMeta.CUSTNAMES) {
      columns.add(this.getColumn(key));
    }
    for (String key : OrderPorfitViewMeta.CUSTSALENAMES) {
      columns.add(this.getColumn(key));
    }

    for (String key : OrderPorfitViewMeta.DOUSELECT) {
      columns.add(this.getColumn(key));
    }
    for (String key : OrderPorfitViewMeta.DOUBLEFIELDS) {
      columns.add(this.getColumn(key));
    }
    return columns.toArray(new IColumnMeta[columns.size()]);
  }
}
