package nc.impl.so.invoicesummary.temp;

import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.IRowSetMap;
import nc.vo.so.report.invoicesummary.InvSummaryViewMeta;
import nc.vo.so.report.invoicesummary.InvSummaryViewVO;

/**
 * 将RowSet加工为Java对象的接口实现
 * 
 * @since 6.3
 * @version 2012-10-18 13:58:04
 * @author 梁吉明
 */
public class InvSummaryInRowSetMap implements IRowSetMap<InvSummaryViewVO> {

  @Override
  public InvSummaryViewVO[] convert(IRowSet rowset) {
    int size = rowset.size();
    InvSummaryViewVO[] views = new InvSummaryViewVO[size];
    int i = 0;
    while (rowset.next()) {
      views[i] = new InvSummaryViewVO();
      int j = 0;
      for (String selkey : InvSummaryViewMeta.SALEINV_HKEYS) {
        views[i].setAttributeValue(selkey, rowset.getObject(j));
        j++;
      }
      for (String selkey : InvSummaryViewMeta.SALEINV_BKEYS) {
        views[i].setAttributeValue(selkey, rowset.getObject(j));
        j++;
      }
      for (String selkey : InvSummaryViewMeta.EXTEND_STRKEYS) {
        views[i].setAttributeValue(selkey, rowset.getObject(j));
        j++;
      }
      i++;
    }
    return views;
  }

}
