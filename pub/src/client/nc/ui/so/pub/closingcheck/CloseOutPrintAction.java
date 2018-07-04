package nc.ui.so.pub.closingcheck;

import nc.ui.uif2.actions.AbstractDirectPrintAction;
import nc.ui.uif2.actions.DirectOutputAction;
import nc.ui.uif2.model.AbstractUIAppModel;

public class CloseOutPrintAction extends DirectOutputAction{
  /**
   * Ä£ÐÍ
   */
  protected AbstractUIAppModel model;
  /**
   * 
   * @param pDirectPrintAction
   */
  public CloseOutPrintAction(AbstractDirectPrintAction pDirectPrintAction) {
    super(pDirectPrintAction);
    this.printAction = pDirectPrintAction;
  }
  @Override
  protected boolean isActionEnable() {
    return this.printAction.isEnabled();
  }
  /**
   * 
   * 
   * @param model
   */
  public void setModel(AbstractUIAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }
  /**
   * 
   */
  AbstractDirectPrintAction printAction = null;
}
