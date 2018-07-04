package nc.ui.so.m30.billui.view;

import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.so.m30.pub.SaleOrderPrecision;
import nc.ui.so.pub.listener.SOListPanelTotalRowUtil;

public class SaleOrderBillListView extends ShowUpableBillListView {

  /**
   * 
   */
  private static final long serialVersionUID = -3171792172183638055L;

  @Override
  public void initUI() {
    super.initUI();
    // 处理列表界面的合计行
    this.setListViewTotalRow();
    String pk_group = this.getModel().getContext().getPk_group();
    // t AppContext.getInstance().getPkGroup();
    SaleOrderPrecision.getInstance().setListPrecision(pk_group,
        this.getBillListPanel());
  }

  /**
   * 设置表头及表体的合计行监听
   */
  private void setListViewTotalRow() {
    SOListPanelTotalRowUtil.setListViewTotalHeadAndBodyRow(this);
  }
}
