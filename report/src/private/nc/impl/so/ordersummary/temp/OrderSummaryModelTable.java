package nc.impl.so.ordersummary.temp;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.IColumnMeta;
import nc.vo.pubapp.pattern.model.meta.table.Table;
import nc.vo.so.report.ordersummary.OrderSummaryViewMeta;

/**
 * 销售订单中间表字段
 * 
 * @since 6.3
 * @version 2012-10-18 13:57:38
 * @author 梁吉明
 */
public class OrderSummaryModelTable extends Table {

  /**
   * 构造方法
   * 
   * @param name
   */
  public OrderSummaryModelTable(String name) {
    super(name);
  }

  /**
   * 临时表中要增加的序号字段
   */
  protected static final String SEQUEENCE = "seq";

  @Override
  public IColumnMeta[] getColumns() {
    List<IColumnMeta> columns = new ArrayList<IColumnMeta>();
    for (String key : OrderSummaryViewMeta.SALEORDER_HKEYS) {
      columns.add(this.getColumn(key));
    }
    for (String key : OrderSummaryViewMeta.SALEORDER_BKEYS) {
      columns.add(this.getColumn(key));
    }
    for (String key : OrderSummaryViewMeta.SALEORDER_EXEKEYS) {
      columns.add(this.getColumn(key));
    }
    for (String key : OrderSummaryViewMeta.EXTEND_STRKEYS) {
      columns.add(this.getColumn(key));
    }
    return columns.toArray(new IColumnMeta[columns.size()]);
  }
}
