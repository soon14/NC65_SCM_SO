package nc.ui.so.mbuylargess.editor.orgevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.ui.so.mbuylargess.view.BuyLargessPrecision;
import nc.vo.uif2.LoginContext;

public class OrgEditHandler implements IAppEventHandler<OrgChangedEvent> {

  private BillForm billForm;

  private BillCardPanel card;

  private LoginContext context;

  public OrgEditHandler(BillForm bill, LoginContext context) {
    this.billForm = bill;
    this.card = this.billForm.getBillCardPanel();
    this.context = context;
  }

  @Override
  public void handleAppEvent(OrgChangedEvent e) {

    if (this.billForm.isEditable()) {
      // 在编辑状态下，主组织切换时，清空界面数据，自动表体增行，并设置行号，songhy
      this.billForm.addNew();
    }

    // 设置界面精度
    BuyLargessPrecision.getInstance().setCardPrecision(
        this.context.getPk_group(), this.card);

    BillPanelUtils.setOrgForAllRef(this.billForm.getBillCardPanel(),
        this.billForm.getModel().getContext());
  }
}
