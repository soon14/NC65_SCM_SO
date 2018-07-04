package nc.ui.so.m38.arrange.action;

import nc.ui.pubapp.billref.push.DestSaveAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.scmpub.res.SCMActionCode;

public class M38ArrangeSaveAction extends DestSaveAction {

  /**
   * 
   */
  private static final long serialVersionUID = -2670269628105196072L;

  public M38ArrangeSaveAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_SAVE30);
  }
}
