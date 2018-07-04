package nc.impl.so.outsummary.temp;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.IColumnMeta;
import nc.vo.pubapp.pattern.model.meta.table.Table;
import nc.vo.so.report.outsummary.OutSummaryViewMeta;

/**
 * 出库单中间表字段
 * 
 * @since 6.3
 * @version 2012-10-18 13:56:58
 * @author 梁吉明
 */
public class OutSummrayModelTable extends Table {

  /**
   * 构造方法
   * 
   * @param name
   */
  public OutSummrayModelTable(String name) {
    super(name);

  }

  /**
   * 临时表中要增加的序号字段
   */
  protected static final String SEQUEENCE = "seq";

  @Override
  public IColumnMeta[] getColumns() {
    List<IColumnMeta> columns = new ArrayList<IColumnMeta>();
    for (String key : OutSummaryViewMeta.SALEOUT_HKEYS) {
      columns.add(this.getColumn(key));
    }
    for (String key : OutSummaryViewMeta.SALEOUT_BKEYS) {
      columns.add(this.getColumn(key));
    }
    for (String key : OutSummaryViewMeta.SALEOUT_EXEKEYS) {
      columns.add(this.getColumn(key));
    }
    for (String key : OutSummaryViewMeta.EXTEND_STRKEYS) {
      columns.add(this.getColumn(key));
    }
    return columns.toArray(new IColumnMeta[columns.size()]);
  }
}
