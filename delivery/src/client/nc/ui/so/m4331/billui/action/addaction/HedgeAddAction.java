package nc.ui.so.m4331.billui.action.addaction;

import java.awt.event.ActionEvent;

import nc.ui.scmpub.action.ReferenceAction;
import nc.ui.so.m4331.billui.model.DeliveryManageModel;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.ui.uif2.UIState;

public class HedgeAddAction extends ReferenceAction {

  /**
     * 
     */
  private static final long serialVersionUID = 4935922265324881330L;

  private DeliveryEditor editor;

  private DeliveryManageModel model;

  public HedgeAddAction() {
    super();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.model.setUiState(UIState.ADD);
  }

  public DeliveryEditor getEditor() {
    return this.editor;
  }

  public DeliveryManageModel getModel() {
    return this.model;
  }

  @Override
  protected boolean isManual() {
    return true;
  }

  public void setEditor(DeliveryEditor editor) {
    this.editor = editor;
  }

  public void setModel(DeliveryManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }
}
