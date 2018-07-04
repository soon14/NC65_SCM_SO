package nc.ui.so.m38.billui.action;

import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.ui.uif2.UIState;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class PreOrderDeleteAction extends DeleteScriptAction {

  /**
   * 
   */
  private static final long serialVersionUID = 7269070764420472589L;

  @Override
  protected boolean isActionEnable() {
    boolean isEnable =
        this.getModel().getUiState() == UIState.NOT_EDIT
            && null != this.getModel().getSelectedData();
    if (isEnable) {
      PreOrderVO selectedData = (PreOrderVO) this.getModel().getSelectedData();
      String cauditorid = selectedData.getParentVO().getApprover();

      if (!PubAppTool.isNull(cauditorid)) {
        // 审批中状态，审批人为空时可以删除。所以审批人非空一定不可删除。
        return false;
      }
      Integer fstatusflag = selectedData.getParentVO().getFstatusflag();
      if (!BillStatus.FREE.equalsValue(fstatusflag)) {
        return false;
      }
    }
    else {
      return false;
    }
    return isEnable;
  }
}
