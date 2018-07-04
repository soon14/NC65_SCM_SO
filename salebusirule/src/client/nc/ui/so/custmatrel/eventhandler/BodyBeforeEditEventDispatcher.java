package nc.ui.so.custmatrel.eventhandler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.custmatrel.billhandler.BillEventHandler;
//import nc.vo.so.custmatrel.entity.CustMatRelBVO;

public class BodyBeforeEditEventDispatcher implements
    IAppEventHandler<CardBodyBeforeEditEvent> {

  private BillEventHandler billhandler;

  @Override
  public void handleAppEvent(CardBodyBeforeEditEvent e) {
    this.getBillhandler().handleEditEvent(e);

    boolean iseditable = true;

//    if (e.getKey().equals(CustMatRelBVO.PK_MATERIAL_V)) {
//      //
//    }
//    else if (e.getKey().equals(CustMatRelBVO.PK_CUSTOMER)) {
//      //
//    }
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
