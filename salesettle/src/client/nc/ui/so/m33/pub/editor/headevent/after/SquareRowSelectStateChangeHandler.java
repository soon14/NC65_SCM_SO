package nc.ui.so.m33.pub.editor.headevent.after;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadRowStateChangeEvent;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售结算手工界面列表表头行选择处理器
 * </ul>
 * 
 * @author zhangcheng
 * @time 2010-10-12 下午02:49:02
 */
public class SquareRowSelectStateChangeHandler implements
    IAppEventHandler<ListHeadRowStateChangeEvent> {

  @Override
  public void handleAppEvent(ListHeadRowStateChangeEvent e) {
    this.select(e);
  }

  private void select(ListHeadRowStateChangeEvent e) {
    int srow = e.getRow();
    int erow = e.getEndRow();
    BillListPanel blp = e.getBillListPanel();
    ListPanelValueUtils util = new ListPanelValueUtils(blp);
    BillModel bm = blp.getHeadBillModel();
    int rowcnt = bm.getRowCount();
    for (int row = srow; row <= erow; ++row) {
      Object pk = util.getHeadValueAt(row, SquareOutDetailVO.PROCESSEID);
      // 处理编号为null跳出
      if (VOChecker.isEmpty(pk)) {
        continue;
      }
      // 循环遍历每一行，如果跟外循环处理编号一致设置相同的选择状态
      for (int i = 0; i < rowcnt; ++i) {
        Object ipk = util.getHeadValueAt(i, SquareOutDetailVO.PROCESSEID);
        // 处理编号为null跳出
        if (VOChecker.isEmpty(pk)) {
          continue;
        }
        // 处理编号一致设置相同的状态
        if (pk.equals(ipk)) {
          bm.setRowState(i, e.getRowStaus());
        }
      }
    }
  }

}
