package nc.ui.so.m30.billui.cash.view;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.uif2.editor.value.BillCardPanelBodyVOValueAdapter;

public class CashBillCardPanelBodyVOValueAdapter extends
    BillCardPanelBodyVOValueAdapter {

  @Override
  public void setValue(Object object) {
    super.setValue(object);
    ((BillCardPanel) this.getComponent()).getBillModel()
        .loadLoadRelationItemValue();
  }

}
