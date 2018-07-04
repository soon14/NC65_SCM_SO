package nc.ui.so.m38.billui.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class PreOrderUnApproveAction extends UNApproveScriptAction {

  /**
   * 
   */
  private static final long serialVersionUID = -2422714587726948187L;

  @Override
  protected void beforeCheck(Object vo) {
    super.beforeCheck(vo);
  }

  @Override
  protected boolean isActionEnable() {
    boolean isEnable =
        (this.getModel().getAppUiState() == AppUiState.NOT_EDIT)
            && (null != this.getModel().getSelectedData());

    if (isEnable) {
      Object[] selectedRows = this.getModel().getSelectedOperaDatas();
      PreOrderVO selectedData = (PreOrderVO) this.getModel().getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();

      isEnable =
          ((null != selectedRows) && (selectedRows.length > 1))
              || BillStatus.AUDIT.equalsValue(billstatus);
    }
    return isEnable;
  }

}
