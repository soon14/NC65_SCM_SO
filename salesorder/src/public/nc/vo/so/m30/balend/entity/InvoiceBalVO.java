package nc.vo.so.m30.balend.entity;

import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.enumeration.SquareType;

public class InvoiceBalVO extends AbstractBalVO {

  @Override
  public String getBalbilltype() {
    return SOBillType.Invoice.getCode();
  }

  @Override
  public boolean isCostbal() {
    if (SquareType.SQUARETYPE_IA.equalsValue(this.preiatype)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isIncomebal() {
    if (SquareType.SQUARETYPE_AR.equalsValue(this.preartype)
        || SquareType.SQUARETYPE_BALANCEAR.equalsValue(this.preartype)
        || SquareType.SQUARETYPE_ARRUSH.equalsValue(this.preartype)) {
      return true;
    }
    return false;
  }
}
