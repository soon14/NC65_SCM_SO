package nc.impl.so.orderprofit;

import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.IRowSetMap;
import nc.vo.so.report.profit.OrderPorfitViewMeta;
import nc.vo.so.report.profit.OrderProfitViewVO;

public class ProfitViewVORowSetMap implements IRowSetMap<OrderProfitViewVO> {

  @Override
  public OrderProfitViewVO[] convert(IRowSet rowset) {
    int size = rowset.size();
    OrderProfitViewVO[] views = new OrderProfitViewVO[size];

    // OrderProfitViewVO view = new OrderProfitViewVO();
    int cursor = 0;
    while (rowset.next()) {
      views[cursor] = new OrderProfitViewVO();
      int index = 0;

      for (String key : OrderPorfitViewMeta.HNAMES) {
        views[cursor].setAttributeValue(key, rowset.getObject(index));
        index++;
      }
      for (String key : OrderPorfitViewMeta.BNAMES) {
        views[cursor].setAttributeValue(key, rowset.getObject(index));
        index++;
      }

      for (String key : OrderPorfitViewMeta.MATERIALNAMES) {
        views[cursor].setAttributeValue(key, rowset.getObject(index));
        index++;
      }
      for (String key : OrderPorfitViewMeta.MATERIALSALENAMES) {
        views[cursor].setAttributeValue(key, rowset.getObject(index));
        index++;
      }

      for (String key : OrderPorfitViewMeta.CUSTNAMES) {
        views[cursor].setAttributeValue(key, rowset.getObject(index));
        index++;
      }
      for (String key : OrderPorfitViewMeta.CUSTSALENAMES) {
        views[cursor].setAttributeValue(key, rowset.getObject(index));
        index++;
      }

      for (String key : OrderPorfitViewMeta.DOUSELECT) {
        views[cursor].setAttributeValue(key, rowset.getObject(index));
        index++;
      }
      cursor++;
    }
    return views;
  }

}
