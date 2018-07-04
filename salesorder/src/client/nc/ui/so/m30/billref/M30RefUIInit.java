package nc.ui.so.m30.billref;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.billref.src.IRefPanelInit;
import nc.ui.so.m30.pub.SaleOrderPrecision;

public class M30RefUIInit implements IRefPanelInit {

  @Override
  public void refMasterPanelInit(BillListPanel masterPanel) {
    // 主子表精度处理
    SaleOrderPrecision.getInstance().setListPrecision(
        AppUiContext.getInstance().getPkGroup(), masterPanel);
  }

  @Override
  public void refSinglePanelInit(BillListPanel singlePanel) {
    String pk_group = AppUiContext.getInstance().getPkGroup();
    // 单表精度处理
    SaleOrderPrecision.getInstance().setSingleTableScale(pk_group, singlePanel);
  }
}
