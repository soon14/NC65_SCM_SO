package nc.ui.so.m32.billref;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.src.IRefPanelInit;
import nc.ui.so.m32.billui.pub.SaleInvoicePrecision;
import nc.vo.pubapp.AppContext;

/**
 * 销售发票转单界面初始化类
 * 
 * @since 6.0
 * @version 2011-6-1 上午08:33:10
 * @author 么贵敬
 */
public class M32RefUIInit implements IRefPanelInit {

  @Override
  public void refMasterPanelInit(BillListPanel masterPanel) {
    // 主子表精度处理
    SaleInvoicePrecision.getInstance().setListPrecision(
        AppContext.getInstance().getPkGroup(), masterPanel);
  }

  @Override
  public void refSinglePanelInit(BillListPanel singlePanel) {
    String pk_group = AppContext.getInstance().getPkGroup();
    // 单表精度处理
    SaleInvoicePrecision.getInstance().setSingleTableScale(pk_group,
        singlePanel);
  }

}
