package nc.ui.so.m38.billref.m30;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.billref.src.IRefPanelInit;
import nc.ui.so.m38.billui.pub.PreOrderPrecision;

public class M30Ref38UIInit implements IRefPanelInit {

  @Override
  public void refMasterPanelInit(BillListPanel masterPanel) {
    // 主子表精度处理
    PreOrderPrecision.getInstance().setListPrecision(
        ClientContext.getInstance().getPk_group(), masterPanel);
  }

  @Override
  public void refSinglePanelInit(BillListPanel singlePanel) {
    String pk_group = ClientContext.getInstance().getPk_group();
    // 单表精度处理
    PreOrderPrecision.getInstance().setSingleTableScale(pk_group, singlePanel);
  }
}
