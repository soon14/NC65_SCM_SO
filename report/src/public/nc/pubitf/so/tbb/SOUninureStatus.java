package nc.pubitf.so.tbb;

import nc.itf.scmpub.tbb.ITbbUninureStatus;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.BillStatus;

public class SOUninureStatus implements ITbbUninureStatus {

  @Override
  public String getBillStatusMetaPath() {
    return SOItemKey.FSTATUSFLAG;
  }

  @Override
  public int getFressStatusValue() {
    return BillStatus.FREE.getIntValue();
  }

}
