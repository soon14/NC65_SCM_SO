package nc.ui.so.m4331.billui.action.addaction;

import nc.ui.pubapp.uif2app.actions.CancelAction;

public class CancelRefAction extends CancelAction {
  private static final long serialVersionUID = 1L;

  public CancelRefAction() {
    super();
    this.initializeAction();
  }

  private void initializeAction() {
    this.setCode("CancelRef");
    this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006002_0", "04006002-0001")
    /*@res "ÍË³ö×ªµ¥"*/);
  }

}
