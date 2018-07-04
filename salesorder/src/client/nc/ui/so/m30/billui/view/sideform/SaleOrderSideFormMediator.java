package nc.ui.so.m30.billui.view.sideform;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.uif2.model.AbstractAppModel;

public class SaleOrderSideFormMediator implements
    IAppEventHandler<CardBodyRowChangedEvent> {

  private AbstractAppModel model;

  private CreditSideForm creditSideForm;

  private PaySideForm paySideForm;

  private ATPSideForm atpSideForm;

  @Override
  public void handleAppEvent(CardBodyRowChangedEvent e) {
    this.creditSideForm.resetUI();
    this.paySideForm.resetUI();
    this.atpSideForm.resetUI();
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    ((IAppModelEx) this.getModel()).addAppEventListener(
        CardBodyRowChangedEvent.class, this);
  }

  public CreditSideForm getCreditSideForm() {
    return this.creditSideForm;
  }

  public void setCreditSideForm(CreditSideForm creditSideForm) {
    this.creditSideForm = creditSideForm;
  }

  public PaySideForm getPaySideForm() {
    return this.paySideForm;
  }

  public void setPaySideForm(PaySideForm paySideForm) {
    this.paySideForm = paySideForm;
  }

  public ATPSideForm getAtpSideForm() {
    return this.atpSideForm;
  }

  public void setAtpSideForm(ATPSideForm atpSideForm) {
    this.atpSideForm = atpSideForm;
  }
}
