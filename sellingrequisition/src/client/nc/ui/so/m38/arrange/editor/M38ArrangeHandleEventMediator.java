package nc.ui.so.m38.arrange.editor;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.ExAppEventConst;
import nc.ui.pubapp.billref.push.IBillPush;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.scmf.ic.batchcode.ref.ScmBatchAdaptor;
import nc.ui.so.m38.arrange.editor.eventdispatcher.M30AfterEditEventDispatcher;
import nc.ui.so.m38.arrange.editor.eventdispatcher.M30BeforeEditEventDispatcher;
import nc.ui.so.m38.arrange.editor.eventdispatcher.RowChangeEventHandler;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.AppEventListener;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;

public class M38ArrangeHandleEventMediator implements AppEventListener,
    IBillPush {
  private BillContext billcontext;

  private BillListPanel listPanel;

  private M30AfterEditEventDispatcher m30AfterEdit;

  private M30BeforeEditEventDispatcher m30BeforeEdit;

  @Override
  public BillContext getBillContext() {
    return this.billcontext;
  }

  public BillListPanel getListPanel() {
    if (this.listPanel == null) {
      this.listPanel = this.billcontext.getListPanel();
      // 初始化批编辑的字段
      this.initFillEnabled();
      if (SysInitGroupQuery.isICEnabled()) {
        ScmBatchAdaptor scmbach =
            new ScmBatchAdaptor("nc.ui.ic.batchcode.ref.BatchRefPane");
        UIRefPane uiref = scmbach.getRefPane();
        this.listPanel.getBodyItem(SaleOrderBVO.VBATCHCODE).setComponent(uiref);

      }
    }
    return this.listPanel;
  }

  private void initFillEnabled() {
    BillItem[] billitems = this.listPanel.getBodyBillModel().getBodyItems();
    for (BillItem item : billitems) {
      item.setFillEnabled(false);
    }
    String[] keys = new String[] {
      SOItemKey.CTRANTYPEID, SOItemKey.VROWNOTE
    };
    for (String key : keys) {
      BillItem bodyitem = this.listPanel.getBodyItem(key);
      bodyitem.setFillEnabled(true);
    }
    // 自定义项都可以批编辑
    for (int i = 1; i < 21; i++) {
      BillItem bodyitem = this.listPanel.getBodyItem(SOConstant.VBDEF + i);
      bodyitem.setFillEnabled(true);
    }
  }

  public M30AfterEditEventDispatcher getM30AfterEditEventDispatcher() {
    if (this.m30AfterEdit == null) {
      this.m30AfterEdit = new M30AfterEditEventDispatcher(this.getListPanel());
    }
    return this.m30AfterEdit;
  }

  public M30BeforeEditEventDispatcher getM30BeforeEditEventDispatcher() {
    if (this.m30BeforeEdit == null) {
      this.m30BeforeEdit =
          new M30BeforeEditEventDispatcher(this.getListPanel(),
              this.billcontext);
    }
    return this.m30BeforeEdit;
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
    // 目标单据编辑前事件
    if (ExAppEventConst.BILL_PUSH_BEFOREDIT.equals(e.getType())) {
      this.getM30BeforeEditEventDispatcher().handleEvent(e);
    }
    // 目标单据编辑后事件
    else if (ExAppEventConst.BILL_PUSH_AFTEREDIT.equals(e.getType())) {
      this.getM30AfterEditEventDispatcher().handleEvent(e);
    }
    // 目标行切换事件
    else if (ExAppEventConst.BILL_PUSH_ROWCHANGE.equals(e.getType())) {
      if (e.getEditEvent().getRow() > -1) {
        RowChangeEventHandler handler = new RowChangeEventHandler();
        handler.handleEvent(e, this.billcontext);
      }
    }
  }

  @Override
  public void setBillContext(BillContext newContext) {
    this.billcontext = newContext;
  }
}
