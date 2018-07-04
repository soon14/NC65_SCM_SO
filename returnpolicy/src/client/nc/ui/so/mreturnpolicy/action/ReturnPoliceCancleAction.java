package nc.ui.so.mreturnpolicy.action;

import java.awt.event.ActionEvent;

import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.actions.batch.BatchCancelAction;

public class ReturnPoliceCancleAction extends BatchCancelAction {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0001")/*@res "È¡Ïû²Ù×÷"*/, this.getModel().getContext());
  }
}