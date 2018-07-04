package nc.ui.so.m38.billui.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class PreOrderApproveAction extends ApproveScriptAction {

  /**
   * 
   */
  private static final long serialVersionUID = -6548001236571988013L;

  @Override
  protected boolean isActionEnable() {
    boolean isEnable =
        (this.getModel().getAppUiState() == AppUiState.NOT_EDIT)
            && (null != this.getModel().getSelectedData());

    if (isEnable) {
      // 批选可用
      Object[] selectedRows = this.getModel().getSelectedOperaDatas();
      // 单选时单据状态为自由和审批中的可用
      PreOrderVO selectedData = (PreOrderVO) this.getModel().getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();

      isEnable =
          ((null != selectedRows) && (selectedRows.length > 1))
              || BillStatus.FREE.equalsValue(billstatus)
              || BillStatus.AUDITING.equalsValue(billstatus);

    }
    return isEnable;
  }
}
