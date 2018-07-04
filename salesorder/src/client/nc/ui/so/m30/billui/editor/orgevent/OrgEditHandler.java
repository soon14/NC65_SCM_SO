package nc.ui.so.m30.billui.editor.orgevent;

import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.uif2.LoginContext;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderPrecision;
import nc.ui.so.pub.keyvalue.CardKeyValue;

public class OrgEditHandler implements IAppEventHandler<OrgChangedEvent> {

  private BillCardPanel billCardPanel;

  private SaleOrderBillForm billfrom;

  private LoginContext context;

  public OrgEditHandler(SaleOrderBillForm bill, LoginContext context) {
    this.billfrom = bill;
    this.billCardPanel = bill.getBillCardPanel();
    this.context = context;
  }

  @Override
  public void handleAppEvent(OrgChangedEvent e) {

    // 设置界面精度
    SaleOrderPrecision.getInstance().setCardPrecision(
        this.context.getPk_group(), this.billCardPanel);

    if (this.billfrom.isEditable()) {
      // 在编辑状态下
      this.billfrom.addNew();
    }

    // 设置参照约束条件
    BillPanelUtils.setOrgForAllRef(this.billfrom.getBillCardPanel(),
        this.billfrom.getModel().getContext());
  }
}
