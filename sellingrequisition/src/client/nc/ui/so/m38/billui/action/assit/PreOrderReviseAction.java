package nc.ui.so.m38.billui.action.assit;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class PreOrderReviseAction extends EditAction {

  /**
   * 
   */
  private static final long serialVersionUID = -330212757048615473L;

  private PreOrderEditor editor;

  private AbstractAppModel model;

  public PreOrderReviseAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_REVISE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.getModel().setUiState(UIState.EDIT);
    this.getEditor().setReviseEdit();
  }

  public PreOrderEditor getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    BillManageModel mangemodel = (BillManageModel) this.getModel();
    boolean isEnable =
        mangemodel.getUiState() == UIState.NOT_EDIT
            && null != mangemodel.getSelectedData();

    if (isEnable) {
      PreOrderVO selectedData = (PreOrderVO) mangemodel.getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();

      isEnable = BillStatus.AUDIT.equalsValue(billstatus);
    }
    return isEnable;
  }

}
