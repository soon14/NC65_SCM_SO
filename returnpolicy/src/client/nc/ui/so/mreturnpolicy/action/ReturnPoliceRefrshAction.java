package nc.ui.so.mreturnpolicy.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.RefreshAllAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;

public class ReturnPoliceRefrshAction extends RefreshAllAction {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Override
  public void doAction(ActionEvent e) {
    super.doAction(e);
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0024")/*@res "Ë¢ÐÂ²Ù×÷"*/, this.getModel().getContext());
  }
}