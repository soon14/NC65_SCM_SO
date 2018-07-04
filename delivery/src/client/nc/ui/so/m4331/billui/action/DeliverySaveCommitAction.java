package nc.ui.so.m4331.billui.action;

import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.uif2.UIState;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class DeliverySaveCommitAction extends SaveAndCommitScriptAction {

	 public DeliverySaveCommitAction(SaveScriptAction pSaveAction,
		      CommitScriptAction pCommitAction) {
		    super(pSaveAction, pCommitAction);
		  }
	  private static final long serialVersionUID = 1L;

	  @Override
	  protected boolean isActionEnable() {
	    if (this.getModel().getUiState() == UIState.EDIT
	        || this.getModel().getUiState() == UIState.ADD) {
	      return true;
	    }
	    boolean isEnable =
	        this.getModel().getUiState() == UIState.NOT_EDIT
	            && null != this.getModel().getSelectedData();

	    if (isEnable) {
	      Object[] selectedRows = this.getModel().getSelectedOperaDatas();
	      DeliveryVO selectedData = (DeliveryVO) this.getModel().getSelectedData();
	      Integer billstatus = selectedData.getParentVO().getFstatusflag();

	      isEnable =
	          null != selectedRows && selectedRows.length > 1
	              || BillStatus.FREE.equalsValue(billstatus);
	    }
	    return isEnable;
	  }

}
