package nc.impl.so.outsummary.temp;

import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.IRowSetMap;
import nc.vo.so.report.outsummary.OutSummaryViewMeta;
import nc.vo.so.report.outsummary.OutSummaryViewVO;

/**
 * 将RowSet加工为Java对象的接口实现
 * 
 * @since 6.3
 * @version 2012-10-18 13:59:06
 * @author 梁吉明
 */
public class OutSummaryIcRowSetMap implements IRowSetMap<OutSummaryViewVO> {

  /**
   * 将rowset加工为另外一组对象
   * 
   * @param rowset 要加工的rowset
   * @return 加工后的对象数组
   */
  @Override
  public OutSummaryViewVO[] convert(IRowSet rowset) {
    int size = rowset.size();
    OutSummaryViewVO[] views = new OutSummaryViewVO[size];
    int i = 0;
    while (rowset.next()) {
      views[i] = new OutSummaryViewVO();
      int j = 0;
      for (String selkey : OutSummaryViewMeta.SALEOUT_HKEYS) {
        views[i].setAttributeValue(selkey, rowset.getObject(j));
        j++;
      }
      for (String selkey : OutSummaryViewMeta.SALEOUT_BKEYS) {
        views[i].setAttributeValue(selkey, rowset.getObject(j));
        j++;
      }
      for (String selkey : OutSummaryViewMeta.SALEOUT_EXEKEYS) {
        views[i].setAttributeValue(selkey, rowset.getObject(j));
        j++;
      }
      for (String selkey : OutSummaryViewMeta.EXTEND_STRKEYS) {
        views[i].setAttributeValue(selkey, rowset.getObject(j));
        j++;
      }
      i++;
    }
    return views;
  }

}
