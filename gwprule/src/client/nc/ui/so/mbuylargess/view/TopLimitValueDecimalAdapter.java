package nc.ui.so.mbuylargess.view;

import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.IBillModelDecimalListener;
import nc.ui.pubapp.AppUiContext;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.scale.ScaleObjectFactory;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.mbuylargess.entity.Toplimittype;

public class TopLimitValueDecimalAdapter implements IBillModelDecimalListener {

  private BillModel billmodel;

  private BillItem curitem;

  public TopLimitValueDecimalAdapter(BillModel billmodel, BillItem curitem) {
    this.billmodel = billmodel;
    this.curitem = curitem;
  }

  @Override
  public String getSource() {
    return BuyLargessBVO.FTOPLIMITTYPE;
  }

  @Override
  public int getDecimalFromSource(int row, Object okValue) {

    int toplimittype = ValueUtils.getInt(okValue);

    String pk_group = AppUiContext.getInstance().getPkGroup();
    ScaleObjectFactory scaleutil = new ScaleObjectFactory(pk_group);
    if (Toplimittype.LIMIT_NUM.equalsValue(toplimittype)) {
      String unit =
          (String) this.billmodel.getValueAt(row, BuyLargessBVO.PK_MEASDOC
              + "_ID");
      return scaleutil.getNumVarScaleObject().getDigit(unit);
    }
    else if (Toplimittype.LIMIT_MNY.equalsValue(toplimittype)) {
      String curr = (String) curitem.getValueObject();
      return scaleutil.getMnyScaleObject().getDigit(curr);
    }
    return 0;
  }

  @Override
  public boolean isTarget(BillItem item) {
    String key = item.getKey();
    return BuyLargessBVO.NTOPLIMITVALUE.equals(key);
  }

}
