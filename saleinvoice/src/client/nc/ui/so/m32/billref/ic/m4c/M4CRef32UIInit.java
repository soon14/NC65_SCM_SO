package nc.ui.so.m32.billref.ic.m4c;

import nc.ui.pubapp.billref.src.RefUIInit;
import nc.ui.pubapp.billref.src.view.RefListPanel;
import nc.ui.so.m32.billui.pub.SaleInvoicePrecision;
import nc.vo.pubapp.AppContext;

public class M4CRef32UIInit extends RefUIInit {

  @Override
  public void initBillListPanel(RefListPanel listpane) {
    // 处理精度
    SaleInvoicePrecision.getInstance().setListPrecision(
        AppContext.getInstance().getPkGroup(), listpane);
  }

}
