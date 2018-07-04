package nc.ui.so.m30.revise.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.fixblob.ReQuery2FixBlob;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 销售订单修订节点收回按钮
 * 
 * @since 6.36
 * @version 2014-12-26 下午1:52:04
 * @author wangshu6
 */
public class M30ReviseUnSendApproveAction extends UnCommitScriptAction {

	/** Version */
	private static final long serialVersionUID = 4549098173080532064L;

	@Override
	protected boolean isActionEnable() {
		boolean isEnable = this.getModel().getAppUiState() == AppUiState.NOT_EDIT
				&& null != this.getModel().getSelectedData();

		if (isEnable) {
			Object[] selectedRows = this.getModel().getSelectedOperaDatas();
			SaleOrderHistoryVO selectedData = (SaleOrderHistoryVO) this
					.getModel().getSelectedData();
			Integer billstatus = selectedData.getParentVO().getFstatusflag();
			String approver = selectedData.getParentVO().getApprover();
			String saleorderid = selectedData.getParentVO().getCsaleorderid();
			String orderhistoryid = selectedData.getParentVO()
					.getCorderhistoryid();
			isEnable = (null != selectedRows && selectedRows.length > 0)
					|| null == approver
					&& BillStatus.AUDITING.equalsValue(billstatus);
			// 如果销售订单修订主键和销售订单主键相同，说明当前单据就是销售订单最新版本，收回按钮不可点
			isEnable = (selectedData != null)
					&& (!saleorderid.equals(orderhistoryid));

		}
		return isEnable;
	}

	@Override
	protected Object[] processBefore(Object[] vos) {
		return ReviseVOFiltrateUtls.getIsPFlowActionBillVO(vos);
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
