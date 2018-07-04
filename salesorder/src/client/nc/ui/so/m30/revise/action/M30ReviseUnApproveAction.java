package nc.ui.so.m30.revise.action;

import java.awt.event.ActionEvent;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.pub.bill.fixblob.ReQuery2FixBlob;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.ui.scmpub.util.ResumeExceptionUIProcessUtils;
import nc.vo.pf.change.PfUtilBaseTools;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 销售订单修订节点取消审批按钮
 * 
 * @since 6.36
 * @version 2014-12-26 下午1:52:04
 * @author wangshu6
 */
@SuppressWarnings("serial")
public class M30ReviseUnApproveAction extends UNApproveScriptAction {

	@Override
	public void doBeforAction() {
		// 继续动作时，不做检查
		this.getFlowContext().getEParam()
				.put(PfUtilBaseTools.PARAM_NO_LOCK, UFBoolean.TRUE);
		super.doBeforAction();
	}

	@Override
	protected boolean isActionEnable() {
		Object[] seldatas = this.model.getSelectedOperaDatas();

		if (this.model.getAppUiState() == AppUiState.NOT_EDIT
				&& null != seldatas && seldatas.length > 1) {
			return true;
		}
		Object selectedData = this.model.getSelectedData();
		Integer status = null;
		Integer pfstatus = null;
		String approver = null;
		String saleorderid = null;
		String orderhistoryid = null;
		if (null != selectedData && selectedData instanceof SaleOrderHistoryVO) {
			SaleOrderHistoryVO selorder = (SaleOrderHistoryVO) selectedData;
			status = selorder.getParentVO().getFstatusflag();
			pfstatus = selorder.getParentVO().getFpfstatusflag();
			approver = selorder.getParentVO().getApprover();
			saleorderid = selorder.getParentVO().getCsaleorderid();
			orderhistoryid = selorder.getParentVO().getCorderhistoryid();
		}
		boolean isEnable = (this.model.getAppUiState() == AppUiState.NOT_EDIT)
				&& (selectedData != null)
				&& (BillStatus.AUDIT.equalsValue(status) || BillStatus.AUDITING
						.equalsValue(status) && null != approver);
		// 如果销售订单修订主键和销售订单主键相同，说明当前单据就是销售订单最新版本，取消审批不可点
		isEnable = (selectedData != null)
				&& (!saleorderid.equals(orderhistoryid));
		// 审批 点击不通过，取消审批按钮可点
		if (isEnable == false) {
			if (pfstatus != null) {
				if (pfstatus == BillStatusEnum.NOPASS.toIntValue()) {
					isEnable = true;
				}
			}
		}

		return isEnable;
	}

	@Override
	protected Object[] processBefore(Object[] vos) {
		return ReviseVOFiltrateUtls.getIsPFlowActionBillVO(vos);
	}

	@Override
	protected boolean isResume(IResumeException resumeInfo) {
		return ResumeExceptionUIProcessUtils.isResume(resumeInfo,
				getFlowContext());
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
