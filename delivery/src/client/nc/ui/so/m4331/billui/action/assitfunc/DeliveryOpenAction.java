package nc.ui.so.m4331.billui.action.assitfunc;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.so.m4331.IDeliveryAssitFunc;
import nc.pubitf.credit.creditcheck.ICreditCheckMessageService;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m4331.billui.model.DeliveryManageModel;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.credit.exception.CreditCheckException;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.exp.AtpNotEnoughException;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.SOParameterVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.exeception.OrderToleranceException;
import nc.vo.to.pub.exception.M5xDeliToleranceException;

public class DeliveryOpenAction extends NCAction {

  private static final long serialVersionUID = 7259715510438781095L;

  private Map<String, Boolean> businessCheckMap =
      new HashMap<String, Boolean>();

  private DeliveryEditor editor;

  private DeliveryManageModel model;

  public DeliveryOpenAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_BILLOPEN);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    boolean isContinue = true;
    Object obj = this.model.getSelectedData();
    DeliveryVO[] bills = new DeliveryVO[] {
      (DeliveryVO) obj
    };
    SOParameterVO paravo = new SOParameterVO();
    paravo.setVos(bills);
    paravo.setBusinessCheckMap(this.businessCheckMap);
    DeliveryVO[] ret = null;
    while (isContinue) {
      isContinue = false;
      try {
        ret = this.getService().openDelivery(paravo);
        // 后台变化VO与前台合并
        new ClientBillCombinServer<DeliveryVO>().combine(bills, ret);
      }
      catch (BusinessException ex) {
        boolean isCheckException = this.isBusiCheckException(ex);
        if (isCheckException) {
          isContinue = this.dealException(ex);
        }
        else {
          isContinue = false;
          ExceptionUtils.wrappException(ex);
        }
      }
    }
    this.model.directlyUpdate(bills);
  }

  private IDeliveryAssitFunc getService() {
    return NCLocator.getInstance().lookup(IDeliveryAssitFunc.class);
  }

  public DeliveryEditor getEditor() {
    return this.editor;
  }

  public DeliveryManageModel getModel() {
    return this.model;
  }

  public void setEditor(DeliveryEditor editor) {
    this.editor = editor;
  }

  public void setModel(DeliveryManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    boolean isEnable = true;
    // 没有选中单据，置灰
    if (this.model.getSelectedData() == null
        || this.model.getUiState() == UIState.ADD
        || this.model.getUiState() == UIState.EDIT) {
      isEnable = false;
    }
    else {
      // 未整单关闭的单据，按钮不可用
      DeliveryVO vo = (DeliveryVO) this.model.getSelectedData();
      Integer fstatusflag = vo.getParentVO().getFstatusflag();
      if (!BillStatus.CLOSED.equalsValue(fstatusflag)) {
        isEnable = false;
      }
      else {
        isEnable = true;
      }
    }
    return isEnable;
  }

  private boolean dealException(BusinessException ex) {
    Throwable e = ExceptionUtils.unmarsh(ex);
    boolean expr1 = this.processATPCheck(e);
    boolean expr2 = this.processCreditCheck(e);
    boolean expr3 = this.processToleranceCheck(e);
    boolean expr4 = this.processTranOrderCheck(e);
    return expr1 || expr2 || expr3 || expr4;
  }

  private boolean isBusiCheckException(Exception ex) {
    Throwable e = ExceptionUtils.unmarsh(ex);
    if (e instanceof CreditCheckException
        || e instanceof AtpNotEnoughException
        || e instanceof OrderToleranceException
        || e instanceof M5xDeliToleranceException) {
      return true;
    }
    return false;
  }

  private boolean processATPCheck(Throwable e) {
    boolean isResume = true;
    if (e instanceof AtpNotEnoughException) {
      int back = 0;
      back =
          MessageDialog.showYesNoDlg(WorkbenchEnvironment.getInstance()
              .getWorkbench().getParent(),
              NCLangRes.getInstance().getStrByID("4006002_0", "04006002-0108")
              /* 可用量检查 */, e.getMessage());
      // 可用量不足继续
      if (UIDialog.ID_YES == back) {
        isResume = true;
        this.businessCheckMap.put(BusinessCheck.ATPCheck.getCheckCode(),
            Boolean.FALSE);
      }
      else {
        isResume = false;
      }
    }
    return isResume;
  }

  private boolean processCreditCheck(Throwable e) {
    boolean isResume = true;
    if (e instanceof CreditCheckException) {
      ICreditCheckMessageService cservice =
          NCUILocator.getInstance().lookup(ICreditCheckMessageService.class,
              NCModule.CREDIT);
      try {
        isResume =
            cservice.showMessage(WorkbenchEnvironment.getInstance()
                .getWorkbench().getParent(),
                (CreditCheckException) e.getCause());
        if (isResume) {
          this.businessCheckMap.put(BusinessCheck.CreditCheck.getCheckCode(),
              Boolean.FALSE);
        }
      }
      catch (BusinessException e1) {
        ExceptionUtils.wrappException(e1);
      }
    }
    return isResume;
  }

  private boolean processToleranceCheck(Throwable e) {
    boolean isResume = true;
    if (e instanceof OrderToleranceException) {
      int back =
          MessageDialog
              .showYesNoDlg(
                  WorkbenchEnvironment.getInstance().getWorkbench().getParent(),
                  NCLangRes.getInstance().getStrByID("4006002_0",
                      "04006002-0109")/* 发货单超订单发货检查 */, e.getMessage());

      if (UIDialog.ID_YES == back) {
        isResume = true;
        String busiCheckKey = BusinessCheck.OrderToleranceCheck.getCheckCode();
        this.businessCheckMap.put(busiCheckKey, Boolean.FALSE);
      }
      else {
        isResume = false;
      }
    }
    return isResume;
  }

  private boolean processTranOrderCheck(Throwable e) {
    boolean isResume = true;
    if (e instanceof M5xDeliToleranceException) {
      int back =
          MessageDialog
              .showYesNoDlg(
                  WorkbenchEnvironment.getInstance().getWorkbench().getParent(),
                  NCLangRes.getInstance().getStrByID("4006002_0",
                      "04006002-0110")/* 发货单超调拨订单发货检查 */, e.getMessage());

      if (UIDialog.ID_YES == back) {
        isResume = true;
        String busiCheckKey =
            BusinessCheck.TransDeliToleranceCheck.getCheckCode();
        this.businessCheckMap.put(busiCheckKey, Boolean.FALSE);
      }
      else {
        isResume = false;
      }
    }
    return isResume;
  }
}
