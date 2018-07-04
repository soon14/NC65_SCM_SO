package nc.ui.so.custmatrel.eventhandler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.custmatrel.billhandler.BillEventHandler;

public class BodyAfterEditEventDispatcher implements
    IAppEventHandler<CardBodyAfterEditEvent> {

  private BillEventHandler billhandler;

  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {

    this.getBillhandler().handleEditEvent(e);

  }

  public BillEventHandler getBillhandler() {
    if (this.billhandler == null) {
      this.billhandler = new BillEventHandler();
    }
    return this.billhandler;
  }

  public void setBillhandler(BillEventHandler billhandler) {
    this.billhandler = billhandler;
  }

}
