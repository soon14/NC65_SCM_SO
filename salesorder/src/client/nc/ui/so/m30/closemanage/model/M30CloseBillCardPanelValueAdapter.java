package nc.ui.so.m30.closemanage.model;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.uif2.editor.value.BillCardPanelBodyVOValueAdapter;

public class M30CloseBillCardPanelValueAdapter extends
    BillCardPanelBodyVOValueAdapter {

  @Override
  public void setValue(Object object) {
    super.setValue(object);
    ((BillCardPanel) this.getComponent()).getBillModel()
        .loadLoadRelationItemValue();
  }
}
