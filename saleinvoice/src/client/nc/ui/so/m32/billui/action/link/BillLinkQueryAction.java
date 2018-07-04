package nc.ui.so.m32.billui.action.link;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.ml.NCLangRes;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.so.m32.entity.SaleInvoiceVO;

public class BillLinkQueryAction extends NCAction {

  /**
     * 
     */
  private static final long serialVersionUID = 2443043993311742311L;

  private AbstractAppModel model;

  public BillLinkQueryAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // TODO 自动生成方法存根

  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {

    SaleInvoiceVO selectVO = (SaleInvoiceVO) this.model.getSelectedData();
    boolean isEnable =
        this.model.getUiState() == UIState.NOT_EDIT && selectVO != null;

    return isEnable;
  }

  private void initializeAction() {

    this.setBtnName(NCLangRes.getInstance().getStrByID("4006008_0",
        "04006008-0078")/*联查单据*/);
    this.setCode("LinkQuery");
    this.putValue(Action.ACCELERATOR_KEY, null);
    this.putValue(Action.SHORT_DESCRIPTION, null);
    this.putValue(Action.SMALL_ICON, null);

  }

}
