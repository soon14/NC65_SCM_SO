package nc.ui.so.depmatrel.eventhandler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.depmatrel.billhandler.BillEventHandler;

public class HeadBeforeEditEventDispatcher implements
    IAppEventHandler<CardHeadTailBeforeEditEvent> {

  private BillEventHandler billhandler;

  @Override
  public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
    this.getBillhandler().handleEditEvent(e);

    boolean iseditable = true;
    
    e.setReturnValue(Boolean.valueOf(iseditable));
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
