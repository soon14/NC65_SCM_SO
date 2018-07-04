package nc.ui.so.m4331.billui.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 收回按钮类
 * 
 * @since 6.0
 * @version 2011-2-22 上午10:37:57
 * @author 么贵敬
 */
public class DeliveryUnSendApproveAction extends UnCommitScriptAction {
  /** Version */
  private static final long serialVersionUID = -1349760332624780133L;

  @Override
  protected boolean isActionEnable() {
    boolean isEnable =
        this.getModel().getAppUiState() == AppUiState.NOT_EDIT
            && null != this.getModel().getSelectedData();

    if (isEnable) {
      Object[] selectedRows = this.getModel().getSelectedOperaDatas();
      DeliveryVO selectedData = (DeliveryVO) this.getModel().getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();
      String operator = selectedData.getParentVO().getApprover();

      isEnable =
          null != selectedRows && selectedRows.length > 1 || null == operator
              && BillStatus.AUDITING.equalsValue(billstatus);
    }
    return isEnable;
  }

}
