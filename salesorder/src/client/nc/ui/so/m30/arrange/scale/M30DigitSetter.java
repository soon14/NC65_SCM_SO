package nc.ui.so.m30.arrange.scale;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.IBillListDigitSetter;
import nc.ui.so.m30.pub.SaleOrderPrecision;

public class M30DigitSetter implements IBillListDigitSetter {

  @Override
  public void setDigit(BillListPanel billlist) {

    String pk_group =
        WorkbenchEnvironment.getInstance().getGroupVO().getPk_group();
    SaleOrderPrecision.getInstance().setListPrecision(pk_group, billlist);
  }
}
