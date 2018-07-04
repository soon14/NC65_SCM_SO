package nc.ui.so.m30.billui.cash.view;

import nc.vo.pubapp.scale.BillScaleProcessor;

import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.so.m35.pub.ArSubPrecision;
import nc.ui.uif2.UIState;

public class CashSaleArsubView extends BatchBillTable {

  private static final long serialVersionUID = 7102963271566712273L;

  private String voClassName;

  @Override
  public void initUI() {
    super.initUI();

    this.getModel().setUiState(UIState.EDIT);
    this.getBillCardPanel();

    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(ClientContext.getInstance().getPk_group(),
            this.getBillCardPanel());
    ArSubPrecision.getInstance().setCashSalePrecision(scaleprocess);
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
