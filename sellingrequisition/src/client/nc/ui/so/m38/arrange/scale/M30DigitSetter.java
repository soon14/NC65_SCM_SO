package nc.ui.so.m38.arrange.scale;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.billref.push.IBillModelDigitSetter;
import nc.ui.so.m30.pub.SaleOrderPrecision;

public class M30DigitSetter implements IBillModelDigitSetter {

  @Override
  public void setDigit(BillModel billmodel) {
    String pk_group =
        WorkbenchEnvironment.getInstance().getGroupVO().getPk_group();
    SaleOrderPrecision.getInstance().setModelPrecision(pk_group, billmodel);
  }
}
