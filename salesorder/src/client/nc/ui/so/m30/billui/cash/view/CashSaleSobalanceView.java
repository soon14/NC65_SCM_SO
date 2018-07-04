package nc.ui.so.m30.billui.cash.view;

import nc.vo.pubapp.scale.BillScaleProcessor;

import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.so.m30.sobalance.view.SobalancePrecision;

public class CashSaleSobalanceView extends BatchBillTable {

  private static final long serialVersionUID = 8870657479686964013L;

  private String voClassName;

  @Override
  public void initUI() {
    super.initUI();
    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(ClientContext.getInstance().getPk_group(),
            this.getBillCardPanel());
    SobalancePrecision.getInstance().setCashSalePrecision(scaleprocess);
  }

  @Override
  public Object getEditingLineVO(int rowIndex) {

    Object obj = null;
    obj =
        this.getBillCardPanel().getBillModel()
            .getBodyValueRowVO(rowIndex, this.voClassName);
    return obj;
  }

  @Override
  public String getVoClassName() {
    return this.voClassName;
  }

  @Override
  public void setVoClassName(String voClassName) {
    this.voClassName = voClassName;
  }

}
