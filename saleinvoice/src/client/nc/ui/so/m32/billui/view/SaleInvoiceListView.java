package nc.ui.so.m32.billui.view;

import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.so.m32.billui.pub.SaleInvoicePrecision;
import nc.ui.so.pub.listener.SOListPanelTotalRowUtil;
import nc.vo.pubapp.AppContext;

/**
 * 销售发票列表试图
 * 
 * @since 6.0
 * @version 2011-5-6 下午01:27:34
 * @author 么贵敬
 */
public class SaleInvoiceListView extends ShowUpableBillListView {

  /** Version */
  private static final long serialVersionUID = -4014700571812033159L;

  @Override
  public void initUI() {
    super.initUI();
    // 处理列表界面的合计行
    this.setListViewTotalRow();

    String pk_group = AppContext.getInstance().getPkGroup();
    SaleInvoicePrecision.getInstance().setListPrecision(pk_group,
        this.getBillListPanel());
  }

  /**
   * 设置表头及表体的合计行监听
   */
  private void setListViewTotalRow() {
    SOListPanelTotalRowUtil.setListViewTotalHeadAndBodyRow(this);
  }

}
