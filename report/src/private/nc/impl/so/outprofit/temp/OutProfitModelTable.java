package nc.impl.so.outprofit.temp;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.IColumnMeta;
import nc.vo.pubapp.pattern.model.meta.table.Table;
import nc.vo.so.report.outprofit.OutProfitViewMeta;

/**
 * 出库单中间表字段
 * 
 * @since 6.3
 * @version 2012-09-07 16:46:01
 * @author 梁吉明
 */
public class OutProfitModelTable extends Table {

  /**
   * 构造方法
   * 
   * @param name
   */
  public OutProfitModelTable(String name) {
    super(name);

  }

  /**
   * 临时表中要增加的序号字段
   */
  protected static final String SEQUEENCE = "seq";

  @Override
  public IColumnMeta[] getColumns() {
    List<IColumnMeta> columns = new ArrayList<IColumnMeta>();
    for (String key : OutProfitViewMeta.SALEOUT_HKEYS) {
      columns.add(this.getColumn(key));
    }
    for (String key : OutProfitViewMeta.SALEOUT_BKEYS) {
      columns.add(this.getColumn(key));
    }
    for (String key : OutProfitViewMeta.EXTEND_STRKEYS) {
      columns.add(this.getColumn(key));
    }
    return columns.toArray(new IColumnMeta[columns.size()]);
  }
}
