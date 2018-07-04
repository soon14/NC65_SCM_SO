package nc.ui.so.m4331.billui.action;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.pubitf.credit.accountcheck.IAccountCheckMessageService;
import nc.pubitf.credit.creditcheck.ICreditCheckMessageService;
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
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class DeliveryApproveAction extends ApproveScriptAction {

  /**
     * 
     */
  private static final long serialVersionUID = 6224810222371029731L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    boolean isEnable =
        (this.getModel().getAppUiState() == AppUiState.NOT_EDIT)
            && (null != this.getModel().getSelectedData());

    if (isEnable) {
      // 批选可用
      Object[] selectedRows = this.getModel().getSelectedOperaDatas();
      // 单选时单据状态为自由和审批中的可用
      DeliveryVO selectedData = (DeliveryVO) this.getModel().getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();

      isEnable =
          ((null != selectedRows) && (selectedRows.length > 1))
              || BillStatus.FREE.equalsValue(billstatus)
              || BillStatus.AUDITING.equalsValue(billstatus);
    }
    return isEnable;
  }

  @Override
  protected boolean isResume(IResumeException resumeInfo) {
    return ResumeExceptionUIProcessUtils.isResume(resumeInfo, getFlowContext());
  }
}
