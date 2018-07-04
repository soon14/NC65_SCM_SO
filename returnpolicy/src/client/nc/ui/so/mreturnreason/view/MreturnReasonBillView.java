package nc.ui.so.mreturnreason.view;

import nc.ui.pubapp.uif2app.view.BatchBillTable;

public class MreturnReasonBillView extends BatchBillTable {

  private static final long serialVersionUID = 1L;

  @Override
  public void initUI() {
    super.initUI();
    this.getBillCardPanel().getBodyItem("pk_org").setEdit(false);
  }

  @Override
  protected void onModelInit() {
    super.onModelInit();
    this.getBillCardPanel().getBillModel().loadLoadRelationItemValue();
  }

  @Override
  protected void onNotEdit() {
    super.onNotEdit();
    this.getBillCardPanel().getBillModel().loadLoadRelationItemValue();
  }
}
