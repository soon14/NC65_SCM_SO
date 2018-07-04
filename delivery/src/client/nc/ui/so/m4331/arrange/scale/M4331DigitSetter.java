package nc.ui.so.m4331.arrange.scale;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.billref.push.IBillModelDigitSetter;
import nc.ui.so.m4331.billui.util.DeliveryPrecision;

public class M4331DigitSetter implements IBillModelDigitSetter {

  @Override
  public void setDigit(BillModel billmodel) {
    String pk_group =
        WorkbenchEnvironment.getInstance().getGroupVO().getPk_group();
    DeliveryPrecision.getInstance().setModelPrecision(pk_group, billmodel);
  }

}
