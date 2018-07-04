package nc.ui.so.m4331.billui.action.addaction;

import java.awt.event.ActionEvent;

import nc.ui.scmpub.action.ReferenceAction;
import nc.ui.so.m4331.billui.model.DeliveryManageModel;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.ui.uif2.UIState;

public class DeliveryAddAction extends ReferenceAction {
  private static final long serialVersionUID = -3295795974423488926L;

  private DeliveryEditor editor;

  private DeliveryManageModel model;

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
  protected boolean isActionEnable() {
    return this.model.getUiState() == UIState.NOT_EDIT;
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
