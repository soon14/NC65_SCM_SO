package nc.ui.so.mreturnassign.action;

import java.awt.event.ActionEvent;

import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.actions.RefreshAllAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;

public class ReturnAssignRefreshAction extends RefreshAllAction {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  public void doAction(ActionEvent e) {
    super.doAction(e);
    ShowStatusBarMsgUtil.showStatusBarMsg(NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0038")/*Ë¢ÐÂ²Ù×÷*/, this.getModel().getContext());
  }
}
