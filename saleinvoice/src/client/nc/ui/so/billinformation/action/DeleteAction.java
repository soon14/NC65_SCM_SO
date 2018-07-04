package nc.ui.so.billinformation.action;

import java.awt.event.ActionEvent;

import nc.md.data.access.NCObject;
import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.ui.uif2.UIState;
import nc.ui.pubapp.uif2app.model.BillManageModel;

public class DeleteAction extends DeleteScriptAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -656378907381181336L;
	
	 @Override
	public void doAction(ActionEvent e) throws Exception {
		 super.doAction(e);
	 }
	

	@Override
	protected boolean isActionEnable() {
		if ((getModel() instanceof BillManageModel)) {
			Object[] objs = ((BillManageModel) getModel())
					.getSelectedOperaDatas();
			if ((objs != null) && (objs.length > 0)) {
				return true;
			}
		}

		boolean isEnable = (this.model.getUiState() == UIState.NOT_EDIT)
				&& (this.model.getSelectedData() != null);

		if ((isEnable) && (getModel().getSelectedData() != null)) {
			NCObject obj = NCObject.newInstance(getModel().getSelectedData());

			if (obj != null) {
				/*Integer fstatusflag = ApproveFlowUtil.getBillStatus(obj);
				if (tryMakeFlow(fstatusflag)) {
					return true;
				}*/
				return true;
			}
		}
		return false;
	}

}
