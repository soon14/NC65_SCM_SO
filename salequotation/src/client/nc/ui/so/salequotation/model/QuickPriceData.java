package nc.ui.so.salequotation.model;

import nc.vo.pub.lang.UFDouble;

public class QuickPriceData {

  // 加成
  private UFDouble addValue;

  // 指数
  private UFDouble factorValue;

  public UFDouble getAddValue() {
    return this.addValue;
  }

  public UFDouble getFactorValue() {
    return this.factorValue.div(100);
  }

  public void setAddValue(UFDouble addValue) {
    this.addValue = addValue;
  }

  public void setFactorValue(UFDouble factorValue) {
    this.factorValue = factorValue;
  }
}
