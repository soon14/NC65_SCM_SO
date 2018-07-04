package nc.ui.so.m4331.billui.action;

import java.util.HashSet;
import java.util.Set;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.pubitf.credit.creditcheck.ICreditCheckMessageService;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.ui.scmpub.util.ResumeExceptionUIProcessUtils;
import nc.ui.uif2.UIState;
import nc.vo.credit.exception.CreditCheckException;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.exp.AtpNotEnoughException;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.exeception.OrderToleranceException;

/**
 * 发货单删除操作
 * 
 * @since 6.0
 * @version 2011-1-21 下午04:02:35
 * @author 祝会征
 */
public class DeliveryDeleteAction extends DeleteScriptAction {
	private static final long serialVersionUID = -9001790797638052546L;

	public DeliveryDeleteAction() {
		super();
	}

	@Override
	protected boolean isActionEnable() {
		boolean isEnable = this.getModel().getUiState() == UIState.NOT_EDIT
				&& null != this.getModel().getSelectedData();

		if (isEnable) {
			Object[] selectedRows = this.getModel().getSelectedOperaDatas();
			DeliveryVO selectedData = (DeliveryVO) this.getModel()
					.getSelectedData();
			Integer billstatus = selectedData.getParentVO().getFstatusflag();
			isEnable = null != selectedRows && selectedRows.length > 1
					|| BillStatus.FREE.equalsValue(billstatus)
					|| BillStatus.NOPASS.equalsValue(billstatus);
		}
		return isEnable;
	}

	@Override
	protected boolean isResume(IResumeException resumeInfo) {
		return ResumeExceptionUIProcessUtils.isResume(resumeInfo, getFlowContext());
	}
}
