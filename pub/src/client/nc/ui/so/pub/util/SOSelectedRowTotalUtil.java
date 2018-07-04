package nc.ui.so.pub.util;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillTotalListener;
import nc.ui.so.pub.listener.SOSelectedRowTotalListener;

/**
 * 单表界面（列表界面）合计处理，只针对选择的行进行合并，所谓选择的行是指第一列打钩的那些
 * 
 * @since 6.36
 * @version 2015-1-16 下午4:08:39
 * @author 纪录
 */
public class SOSelectedRowTotalUtil {

  /**
   * 单表界面（列表界面）合计处理，只针对选择的行进行合并，所谓选择的行是指第一列打钩的那些
   * 
   * @param listPanel 列表界面
   * @param viewClassName 视图VO类名
   */
  public static void selectedRowTotalProcess(BillListPanel listPanel,
      String viewClassName) {
    listPanel.getChildListPanel().setTotalRowShow(true);
    BillTotalListener totallis =
        new SOSelectedRowTotalListener(listPanel, viewClassName);
    listPanel.getBodyBillModel().addTotalListener(totallis);
  }
}
