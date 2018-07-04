package nc.ui.so.m4331.billui.action;

import java.awt.event.ActionEvent;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.actions.RefreshSingleAction;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.scmpub.util.ResumeExceptionUIProcessUtils;
import nc.ui.so.m4331.billui.pub.rule.DeliverySaveReverseCheckRule;
import nc.ui.uif2.UIState;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m4331.entity.DeliveryUserObject;
import nc.vo.so.m4331.entity.DeliveryVO;

public class DeliverySaveAction extends SaveScriptAction {
  private static final long serialVersionUID = 7141467834250734134L;

  private RefreshSingleAction refreshAction;
  
  @Override
  public void doBeforAction() {
    //通知后台，单据是在前台界面保存的
    PfUserObject pfUserObj = this.getFlowContext().getUserObj();
    pfUserObj = pfUserObj == null ? new PfUserObject() : pfUserObj;
    DeliveryUserObject obj=new DeliveryUserObject();
    obj.setIsclientsave(true);
    pfUserObj.setUserObject(obj);
    this.getFlowContext().setUserObj(pfUserObj);
    super.doBeforAction();
  }
  
  @Override
  public void doAction(ActionEvent e) throws Exception {
    String errMsg = null;
    if (this.getModel().getUiState() == UIState.EDIT) {
      Object obj = this.editor.getValue();
      int index = this.getModel().findBusinessData(obj);
      if (index == -1) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006002_0", "04006002-0015")
        /*@res "修改保存时，获取前台差异VO出错。"*/);
      }
      DeliveryVO oldVO = (DeliveryVO) this.getModel().getData().get(index);
      DeliveryVO newVO = (DeliveryVO) obj;
      DeliverySaveReverseCheckRule check = new DeliverySaveReverseCheckRule();
      errMsg = check.checkChangeItems(oldVO, newVO);
    }
    if (null != errMsg && errMsg.trim().length() > 0) {
      this.save(errMsg, e);
      return;
    }
    super.doAction(e);
  }

  public RefreshSingleAction getreFreshAction() {
    return this.refreshAction;
  }

  public void setRefreshAction(RefreshSingleAction refreshAction1) {
    this.refreshAction = refreshAction1;
  }

  @Override
  protected DeliveryVO[] getFlowData() {
    DeliveryVO[] deliveryvo = (DeliveryVO[]) super.getFlowData();
    return deliveryvo;
  }

  @Override
  protected boolean isResume(IResumeException resumeInfo) {
    return ResumeExceptionUIProcessUtils.isResume(resumeInfo, getFlowContext());
  }

  private void save(String errMsg, ActionEvent e) {
    int back =
        MessageDialog
            .showYesNoDlg(WorkbenchEnvironment.getInstance().getWorkbench()
                .getParent(),
                NCLangRes.getInstance()
                    .getStrByID("4006002_0", "04006002-0113")/*预留校验*/, errMsg);
    if (UIDialog.ID_YES == back) {
      try {
        super.doAction(e);
        this.refreshAction.doAction(e);
      }
      catch (Exception e1) {
        ExceptionUtils.wrappException(e1);
      }
    }
  }
}
