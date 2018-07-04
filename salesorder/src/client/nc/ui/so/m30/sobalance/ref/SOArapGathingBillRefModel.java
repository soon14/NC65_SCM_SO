package nc.ui.so.m30.sobalance.ref;

import nc.ui.arap.billref.ArapGathingBillRefModel;
import nc.vo.so.m30.sobalance.util.AbstractGatheringKeyValue;
import nc.vo.so.m30.sobalance.util.GatherbillUtil;
import nc.vo.so.m30.sobalance.util.GatheringKeyValueForSoBalance;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * @author gdsjw
 * 
 */
public class SOArapGathingBillRefModel extends ArapGathingBillRefModel {
  private IKeyValue keyValue;

  public SOArapGathingBillRefModel(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public SOArapGathingBillRefModel() {
    super();
  }

  @Override
  public void setWherePart(String wherePart) {
    StringBuilder sb = new StringBuilder();
    sb.append(wherePart);
    if (this.keyValue != null) {
      AbstractGatheringKeyValue gatheringKeyValue =
          new GatheringKeyValueForSoBalance(this.keyValue);
      sb.append(GatherbillUtil.getInstance().getWherePartSqlCanVerify(
          gatheringKeyValue));
    }
    super.setWherePart(sb.toString());
  }

}
