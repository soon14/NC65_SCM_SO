package nc.ui.so.pub.listener;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillTotalListener;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.util.ArrayUtil;

/**
 * 单表界面（列表界面）合计处理，只针对选择的行进行合并，所谓选择的行是指第一列打钩的那些
 * 
 * @since 6.36
 * @version 2015-1-16 下午4:06:52
 * @author 纪录
 */
public class SOSelectedRowTotalListener implements BillTotalListener {

  private BillListPanel listPanel;

  private String viewClassName;

  /**
   * 初始化
   * 
   * @param listPanel 列表界面
   * @param viewClassName 视图VO类名
   */
  public SOSelectedRowTotalListener(BillListPanel listPanel,
      String viewClassName) {
    this.listPanel = listPanel;
    this.viewClassName = viewClassName;
  }

  @Override
  public UFDouble calcurateTotal(String key) {
    UFDouble total = UFDouble.ZERO_DBL;
    CircularlyAccessibleValueObject[] svos =
        this.listPanel.getBodyBillModel().getBodySelectedVOs(viewClassName);
    if (ArrayUtil.isEmpty(svos)) {
      return total;
    }

    for (int i = 0; i < svos.length; i++) {
      UFDouble num = (UFDouble) svos[i].getAttributeValue(key);
      total = MathTool.add(total, num);
    }
    return total;
  }
}
