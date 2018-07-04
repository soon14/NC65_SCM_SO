package nc.ui.so.m30.arrange.editor;

import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.ExAppEventConst;
import nc.ui.pubapp.billref.push.IBillPush;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.AppEventListener;

public class EditorEventHandler implements AppEventListener, IBillPush {

  private BillContext context;

  @Override
  public BillContext getBillContext() {
    return this.context;
  }

  @Override
  public void handleEvent(AppEvent event) {

    PushBillEvent e = null;
    if (event instanceof PushBillEvent) {
      e = (PushBillEvent) event;
    }
    else {
      return;
    }
    // 行改变触发事件
    if (e.getType() == ExAppEventConst.BILL_PUSH_BODYROWCHANGE) {
      int editrow = e.getRow();
      if (editrow > -1) {
        OnRowChangeEventHandler handler = new OnRowChangeEventHandler();
        handler.afterEdit(e, this.context);
      }
    }
  }

  @Override
  public void setBillContext(BillContext context1) {
    this.context = context1;
  }

}
