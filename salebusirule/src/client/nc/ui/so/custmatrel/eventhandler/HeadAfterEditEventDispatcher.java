package nc.ui.so.custmatrel.eventhandler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.custmatrel.billhandler.BillEventHandler;

public class HeadAfterEditEventDispatcher implements
    IAppEventHandler<CardHeadTailAfterEditEvent> {

  private BillEventHandler billhandler;

  @Override
  public void handleAppEvent(CardHeadTailAfterEditEvent e) {
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
