package nc.ui.so.m30.sobalance.view;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pubapp.AppContext;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;

public class SobalanceBillForm extends ShowUpableBillForm {

  private static final long serialVersionUID = -4698846289330821247L;

  @Override
  protected void initBillCardPanel() {
    super.initBillCardPanel();
    this.initGatheringRefModel();
    // 设置精度
    SobalancePrecision.getInstance().setCardPrecision(
        AppContext.getInstance().getPkGroup(), this.getBillCardPanel());
  }

  private void initGatheringRefModel() {
    // 收款单参照
    BillCardPanel cardPanel = this.getBillCardPanel();
    BillItem paybillrowItem = cardPanel.getBodyItem(SoBalanceBVO.VARBILLCODE);

    UIRefPane paybillrowItemRef = new UIRefPane();
    paybillrowItem.setComponent(paybillrowItemRef);
  }

}
