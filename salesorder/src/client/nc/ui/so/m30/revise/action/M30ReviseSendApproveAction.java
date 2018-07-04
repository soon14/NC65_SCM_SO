/**
 * 
 */
package nc.ui.so.m30.revise.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.fixblob.ReQuery2FixBlob;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.vo.pf.change.PfUtilBaseTools;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 销售订单修订节点提交按钮
 * 
 * @since 6.36
 * @version 2014-12-26 下午1:52:04
 * @author wangshu6
 */
@SuppressWarnings("serial")
public class M30ReviseSendApproveAction extends CommitScriptAction {

	@Override
	public void doBeforAction() {
		// add by wangshu6 for 销售订单修订支持审批流 提交不要进行加锁，后台自己进行了加锁和校验
		this.getFlowContext().getEParam()
				.put(PfUtilBaseTools.PARAM_NO_LOCK, UFBoolean.TRUE);

		super.doBeforAction();
	}

	@Override
	protected Object[] processBefore(Object[] vos) {
		return ReviseVOFiltrateUtls.getIsPFlowActionBillVO(vos);
	}

	@Override
	protected boolean isActionEnable() {
		if (this.getModel().getAppUiState() == AppUiState.EDIT
				|| this.getModel().getAppUiState() == AppUiState.ADD
				|| this.getModel().getAppUiState() == AppUiState.COPY_ADD) {
			return true;
		}
		boolean isEnable = this.getModel().getAppUiState() == AppUiState.NOT_EDIT
				&& null != this.getModel().getSelectedData();

		if (isEnable) {
			Object[] selectedRows = this.getModel().getSelectedOperaDatas();
			SaleOrderHistoryVO selectedData = (SaleOrderHistoryVO) this
					.getModel().getSelectedData();
			Integer billstatus = selectedData.getParentVO().getFstatusflag();

			isEnable = null != selectedRows && selectedRows.length > 1
					|| BillStatus.FREE.equalsValue(billstatus);
		}
		return isEnable;
	}

	/**
	 * @author wangzy
	 * @date:2018-05-18
	 * @Description: 做一下重新查询
	 */
	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		super.doAction(e);
		ReQuery2FixBlob.reFreshDate(model);
	}

}
