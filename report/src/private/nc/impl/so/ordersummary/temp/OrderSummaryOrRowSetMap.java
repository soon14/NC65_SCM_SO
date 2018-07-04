package nc.impl.so.ordersummary.temp;

import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.IRowSetMap;
import nc.vo.so.report.ordersummary.OrderSummaryViewMeta;
import nc.vo.so.report.ordersummary.OrderSummaryViewVO;

/**
 * 将RowSet加工为Java对象的接口实现
 * 
 * @since 6.3
 * @version 2012-10-18 13:58:47
 * @author 梁吉明
 */
public class OrderSummaryOrRowSetMap implements IRowSetMap<OrderSummaryViewVO> {

  @Override
  public OrderSummaryViewVO[] convert(IRowSet rowset) {
    int size = rowset.size();
    OrderSummaryViewVO[] views = new OrderSummaryViewVO[size];
    int i = 0;
    while (rowset.next()) {
      views[i] = new OrderSummaryViewVO();
      int index = 0;

      for (String key : OrderSummaryViewMeta.SALEORDER_HKEYS) {
        views[i].setAttributeValue(key, rowset.getObject(index));
        index++;
      }
      for (String key : OrderSummaryViewMeta.SALEORDER_BKEYS) {
        views[i].setAttributeValue(key, rowset.getObject(index));
        index++;
      }
      for (String key : OrderSummaryViewMeta.SALEORDER_EXEKEYS) {
        views[i].setAttributeValue(key, rowset.getObject(index));
        index++;
      }
      for (String key : OrderSummaryViewMeta.EXTEND_STRKEYS) {
        views[i].setAttributeValue(key, rowset.getObject(index));
        index++;
      }
      i++;
    }
    return views;
  }

}
