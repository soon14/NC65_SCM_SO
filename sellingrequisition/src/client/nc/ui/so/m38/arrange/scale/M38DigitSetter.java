package nc.ui.so.m38.arrange.scale;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.billref.push.IBillModelDigitSetter;
import nc.ui.so.m38.billui.pub.PreOrderPrecision;

public class M38DigitSetter implements IBillModelDigitSetter {

  @Override
  public void setDigit(BillModel billmodel) {
    String pk_group =
        WorkbenchEnvironment.getInstance().getGroupVO().getPk_group();
    PreOrderPrecision.getInstance().setModelPrecision(pk_group, billmodel);
  }
}
