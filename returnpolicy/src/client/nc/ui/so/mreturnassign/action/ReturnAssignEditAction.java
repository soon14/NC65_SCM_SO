package nc.ui.so.mreturnassign.action;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.util.ManageModeUtil;
import nc.ui.pubapp.uif2app.actions.batch.BatchEditAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.vo.pub.BusinessException;
import nc.vo.so.mreturnassign.entity.ReturnAssignVO;
import nc.vo.uif2.LoginContext;

public class ReturnAssignEditAction extends BatchEditAction {

  private static final long serialVersionUID = 1L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    ReturnAssignVO vo = (ReturnAssignVO) this.getModel().getSelectedData();
    LoginContext context = this.getModel().getContext();
    boolean isManageable = ManageModeUtil.manageable(vo, context);
    if (!isManageable) {
      String errMsg = ManageModeUtil.getDisManageableMsg(context.getNodeType());
      throw new BusinessException(errMsg);
    }
    this.getModel().setUiState(UIState.EDIT);
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0002")/*@res "ÐÞ¸Ä²Ù×÷"*/, this.getModel().getContext());
  }
}