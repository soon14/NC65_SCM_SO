package nc.ui.so.m4331.arrange.action;

import nc.ui.pubapp.billref.push.DestSaveAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.scmpub.res.SCMActionCode;

public class DeliverySaveAction extends DestSaveAction {

  /**
   * 
   */
  private static final long serialVersionUID = 5448775956617954525L;

  public DeliverySaveAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_SAVE4331);
  }

}
