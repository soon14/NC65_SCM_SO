/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年5月29日 下午5:14:36 
 * @version: V6.5   
 */
package nc.ui.so.m30.revise.action;

import java.awt.event.ActionEvent;

import nc.md.persist.framework.MDPersistenceService;
import nc.ui.pub.Action.SaveScriptAction;
import nc.ui.pub.bill.fixblob.ReQuery2FixBlob;
import nc.ui.pubapp.uif2app.actions.pflow.ScriptPFlowAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * @Description: 走流程平台：虽然暂时还用不到，为了易于扩展
 * @author: wangzy
 * @date: 2018年5月29日 下午5:14:36
 */
public class M30RSaveForEditAction extends ScriptPFlowAction {

	public M30RSaveForEditAction() {
		this.setBtnName("修改保存");
		this.setCode("SAVEBASE");
		this.setActionName("SAVEBASE");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		super.doAction(e);
		// 为了重新刷新下最新数据
		ReQuery2FixBlob.reFreshDate(model);
	}

	@Override
	public boolean isActionEnable() {
		// 如果是修订就不可以修改保存
		boolean iseidtable = (this.model.getUiState() == UIState.EDIT)
				&& (this.model.getSelectedData() != null);
		if (iseidtable) {

			SaleOrderHistoryVO vo = (SaleOrderHistoryVO) this.editor.getValue();
			Object value = vo.getParentVO().getAttributeValue("agdef1");
			// 为修改做falg
			if (value != null && "修订".equals(value)) {
				return false;
			}
		}
		return super.isActionEnable();
	}

}
