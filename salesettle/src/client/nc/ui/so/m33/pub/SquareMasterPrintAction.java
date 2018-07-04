package nc.ui.so.m33.pub;

import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

public class SquareMasterPrintAction extends AbstractSquarePrintAction {
  private static final long serialVersionUID = 1L;

  private BillManageModel model;

  @Override
  public BillManageModel getModel() {
    return this.model;
  }

  @Override
  public void setModel(BillManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  @Override
  protected Object[] getBillVO() {
    // 销售结算打印数据 都从界面取得
    // if ((this.getModel().getData() == null)
    // || (this.getModel().getData().size() == 0)) {
    // return null;
    // }
    if (this.getListView().getBillListPanel().getBodyBillModel()
        .getBodySelectedVOs(SquareOutViewVO.class.getName()) == null
        || this.getListView().getBillListPanel().getBodyBillModel()
            .getBodySelectedVOs(SquareOutViewVO.class.getName()).length == 0) {
      Object[] vos = new Object[1];
      vos[0] = this.getModel().getSelectedData();
      return vos;
    }
    Object[] selectedData = this.getListView().getBillListPanel().getBodyBillModel()
      .getBodySelectedVOs(SquareOutViewVO.class.getName());
    // 设置已结算主数量
    SquareOutVOUtils.getInstance().setNtotalsquarenum(
        (SquareOutViewVO[]) selectedData);
    return selectedData;
  }

  @Override
  protected boolean isActionEnable() {
    if (this.noModelSelected() && this.noVOSelected()) {
      return false;
    }
    return this.model.getUiState() != UIState.EDIT;
  }

  /**
   * 方法功能描述：判断model是否有选中
   * 
   * @author buxh
   * @time 2011-7-26 15:48
   */
  private boolean noModelSelected() {
    if (this.getModel().getData() == null
        || this.getModel().getData().size() == 0
        || this.getModel().getSelectedData() == null) {
      return true;
    }
    return false;
  }

  /**
   * 方法功能描述：判断界面是否有选中
   * 
   * @author buxh
   * @time 2011-7-26 15:48
   */
  private boolean noVOSelected() {
    if (this.getListView().getBillListPanel().getBodyBillModel()
        .getBodySelectedVOs(SquareOutViewVO.class.getName()) == null
        || this.getListView().getBillListPanel().getBodyBillModel()
            .getBodySelectedVOs(SquareOutViewVO.class.getName()).length == 0) {
      return true;
    }

    return false;

  }
}
