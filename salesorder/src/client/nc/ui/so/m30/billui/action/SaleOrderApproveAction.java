package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.pubitf.credit.accountcheck.IAccountCheckMessageService;
import nc.pubitf.credit.creditcheck.ICreditCheckMessageService;
import nc.ui.pub.bill.fixblob.ReQuery2FixBlob;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction;
import nc.ui.scmpub.util.ResumeExceptionUIProcessUtils;
import nc.vo.credit.exception.CreditCheckException;
import nc.vo.credit.exception.OverPeriodDayCheckException;
import nc.vo.credit.exception.OverPeriodInnerDayCheckException;
import nc.vo.credit.exception.OverPeriodMoneyCheckException;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

@SuppressWarnings("restriction")
public class SaleOrderApproveAction extends ApproveScriptAction {

  @Override
  protected boolean isActionEnable() {

    Object[] seldatas = this.model.getSelectedOperaDatas();

    if (this.model.getAppUiState() == AppUiState.NOT_EDIT && null != seldatas
        && seldatas.length > 1) {
      return true;
    }
    Object selectedData = this.model.getSelectedData();
    Integer status = null;
    if (null != selectedData && selectedData instanceof SaleOrderVO) {
      SaleOrderVO selorder = (SaleOrderVO) selectedData;
      status = selorder.getParentVO().getFstatusflag();
    }
    boolean isEnable =
        this.model.getAppUiState() == AppUiState.NOT_EDIT
            && selectedData != null
            && (BillStatus.FREE.equalsValue(status) || BillStatus.AUDITING
                .equalsValue(status));

    return isEnable;

  }
  @Override
  protected boolean isResume(IResumeException resumeInfo) {
    return ResumeExceptionUIProcessUtils.isResume(resumeInfo, getFlowContext());
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
