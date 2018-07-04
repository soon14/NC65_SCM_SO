package nc.ui.so.mreturnassign.editor;


import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.so.mreturnassign.entity.ReturnAssignVO;

public class BodyBeforeEditHandler implements
    IAppEventHandler<CardBodyBeforeEditEvent> {

  @Override
  public void handleAppEvent(CardBodyBeforeEditEvent e) {
    if (e.getKey().equals(ReturnAssignVO.PK_RETURNPOLICY)) {
      String pk_org =
          (String) e.getBillCardPanel().getBodyValueAt(e.getRow(),
              ReturnAssignVO.PK_SALEORG);
      BillItem item =
          e.getBillCardPanel().getBodyItem(ReturnAssignVO.PK_RETURNPOLICY);
      UIRefPane pane = (UIRefPane) item.getComponent();
      pane.setPk_org(pk_org);
    }
    
    e.setReturnValue(Boolean.TRUE);
  }
}
