package nc.ui.so.m4331.billui.view;

import java.util.Map;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.so.m4331.billui.model.DeliveryManageModel;
import nc.ui.so.m4331.billui.util.DeliveryPrecision;
import nc.ui.so.pub.listener.SOListPanelTotalRowUtil;
import nc.vo.pubapp.AppContext;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.pub.SOConstant;

public class DeliveryListView extends ShowUpableBillListView {

  private static final long serialVersionUID = -4014700571812033159L;

  @Override
  public void initUI() {
    super.initUI();
    this.setItemName();
    
    // 处理列表界面的合计行
    this.setListViewTotalRow();
    
    String pk_group = AppContext.getInstance().getPkGroup();
    DeliveryPrecision.getInstance().setListPrecision(pk_group,
        this.getBillListPanel());
    this.getBillListPanel().getParentListPanel()
        .refreshTableCol(DeliveryHVO.NTOTALWEIGHT);
    this.getBillListPanel().getParentListPanel()
        .refreshTableCol(DeliveryHVO.NTOTALVOLUME);
    // this.getBillListPanel().getParentListPanel().getTable()
    // .sizeColumnsToFitTitle();
    // UITable table = this.getBillListPanel().getHeadTable();
    // table.getTableHeader().invalidate();
    // BillModel model = (BillModel) table.getModel();
    // this.getBillListPanel().setListData(
    // this.getBillListPanel().getBillListData());
    // model.fireTableStructureChanged();
    // // this.getBillListPanel().updateUI();
    // // this.getBillListPanel().getParentListPanel().showTableCol("");
    // this.getBillListPanel().setMultiSelect(true);
    this.getBillListPanel().updateUI();
  }

  private void setItemName() {
    DeliveryManageModel manageModel = (DeliveryManageModel) this.getModel();
    Map<String, String> map = manageModel.getWeightAndVolName();
    String name = map.get(SOConstant.BD305);
    if (null != name && !"".equals(name)) {
      BillItem weightItem =
          this.getBillListPanel().getHeadItem(DeliveryHVO.NTOTALWEIGHT);
      weightItem.setName(NCLangRes.getInstance().getStrByID("4006002_0",
          "04006002-0115", null, new String[] {
            name
          })/*总重量({0})*/);
    }
    name = map.get(SOConstant.BD304);
    if (null != name && !"".equals(name)) {
      BillItem volItem =
          this.getBillListPanel().getHeadItem(DeliveryHVO.NTOTALVOLUME);
      volItem.setName(NCLangRes.getInstance().getStrByID("4006002_0",
          "04006002-0116", null, new String[] {
            name
          })/*总体积({0})*/);
    }
  }
  
  /**
   * 设置表头及表体的合计行监听
   */
  private void setListViewTotalRow() {
    SOListPanelTotalRowUtil.setListViewTotalHeadAndBodyRow(this);
  }
}
