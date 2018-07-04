package nc.ui.so.m4331.billui.action.linkaction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.so.m4331.billui.model.DeliveryManageModel;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.so.m4331.entity.DeliveryVO;

public class BillLinkQueryAction extends NCAction {

  /**
     *
     */
  private static final long serialVersionUID = 2443043993311742311L;

  private DeliveryManageModel model;

  public BillLinkQueryAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // TODO 自动生成方法存根

  }

  public DeliveryManageModel getModel() {
    return this.model;
  }

  private void initializeAction() {
    this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006002_0", "04006002-0006")/*@res "联查单据"*/);
    this.setCode("LinkQuery");
    this.putValue(Action.ACCELERATOR_KEY, null);
    this.putValue(Action.SHORT_DESCRIPTION, null);
    this.putValue(Action.SMALL_ICON, null);

  }

  @Override
  protected boolean isActionEnable() {

    DeliveryVO selectVO = (DeliveryVO) this.model.getSelectedData();
    boolean isEnable =
        (this.model.getUiState() == UIState.NOT_EDIT) && (selectVO != null);

    return isEnable;
  }

  public void setModel(DeliveryManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

}
