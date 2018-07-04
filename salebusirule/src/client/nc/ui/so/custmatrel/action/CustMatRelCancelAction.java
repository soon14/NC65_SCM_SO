/**
 * 
 */
package nc.ui.so.custmatrel.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.CancelAction;
import nc.ui.so.custmatrel.view.CardForm;

/**
 * @author gdsjw
 *
 */
@SuppressWarnings("serial")
public class CustMatRelCancelAction extends CancelAction {
	private CardForm view;
	public CardForm getView() {
		return this.view;
	}

	public void setView(CardForm view) {
		this.view = view;
	}
	@Override
	public void doAction(ActionEvent e) throws Exception {
		super.doAction(e);
		// 设置“销售组织”为不可编辑 by quyt 20141022
		view.getOrgPanel().setEnabled(false);
		// end
	}

}
