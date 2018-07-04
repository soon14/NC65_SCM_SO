package nc.vo.so.pub.calculator;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.util.CirVOKeyValue;
import nc.vo.trade.checkrule.VOChecker;

/**
 * <p>
 * <b>vo计算工具类</b>
 * <p>
 * 
 * @param <T>
 * @author zhangcheng
 * @time 2010-9-2 下午02:39:48
 */
public class PriceNumMnyCalculatorForVO {

  private Condition cond;

  private IRelationForItems item;

  public <T extends CircularlyAccessibleValueObject> void calculate(T[] bvos,
      String editKey) {
    if (VOChecker.isEmpty(editKey) || VOChecker.isEmpty(bvos)) {
      return;
    }
    IRelationForItems item1 = this.getIRelationForItems();
    PriceNumMnyCalculator calutil =
        new PriceNumMnyCalculator(new CirVOKeyValue<T>(bvos));
    calutil.setCondition(this.getCondition());
    int len = bvos.length;
    IDataSetForCal[] datas = new SOVODataSetForCal[len];
    int[] rows = new int[len];
    for (int i = 0; i < len; i++) {
      datas[i] = new SOVODataSetForCal(null, bvos[i], item1);
      rows[i] = i;
    }
    calutil.calculate(rows, editKey, datas);
  }

  public <T extends AbstractBill> void calculate(T[] bills, String editKey) {
    if (VOChecker.isEmpty(editKey) || VOChecker.isEmpty(bills)) {
      return;
    }
    IRelationForItems item1 = this.getIRelationForItems();
    for (T bill : bills) {
      PriceNumMnyCalculator calutil =
          new PriceNumMnyCalculator(new VOKeyValue<T>(bill));
      calutil.setCondition(this.getCondition());
      CircularlyAccessibleValueObject[] bvos = bill.getChildrenVO();
      int len = bvos.length;
      int[] rows = new int[len];
      IDataSetForCal[] datas = new SOVODataSetForCal[len];
      for (int i = 0; i < len; i++) {
        datas[i] = new SOVODataSetForCal(bill.getParentVO(), bvos[i], item1);
        rows[i] = i;
      }
      calutil.calculate(rows, editKey, datas);
    }
  }

  public <T extends CircularlyAccessibleValueObject> void calculateLocal(
      T[] bvos) {
    if (VOChecker.isEmpty(bvos)) {
      return;
    }
    IRelationForItems item1 = this.getIRelationForItems();
    PriceNumMnyCalculator calutil =
        new PriceNumMnyCalculator(new CirVOKeyValue<T>(bvos));
    calutil.setCondition(this.getCondition());
    int len = bvos.length;
    IDataSetForCal[] datas = new SOVODataSetForCal[len];
    int[] rows = new int[len];
    for (int i = 0; i < len; i++) {
      datas[i] = new SOVODataSetForCal(null, bvos[i], item1);
      rows[i] = i;
    }
    calutil.calculateLocal(rows, datas);
  }

  public <T extends CircularlyAccessibleValueObject> void calculateByTax(
      T[] bvos) {
    if (VOChecker.isEmpty(bvos)) {
      return;
    }
    IRelationForItems item1 = this.getIRelationForItems();
    PriceNumMnyCalculator calutil =
        new PriceNumMnyCalculator(new CirVOKeyValue<T>(bvos));
    calutil.setCondition(this.getCondition());
    int len = bvos.length;
    IDataSetForCal[] datas = new SOVODataSetForCal[len];
    int[] rows = new int[len];
    for (int i = 0; i < len; i++) {
      datas[i] = new SOVODataSetForCal(null, bvos[i], item1);
      rows[i] = i;
    }
    calutil.calculate(rows, SOItemKey.NTAX, datas);
  }

  /**
   * TODO tianft 这个类需要重构！
   * 
   * @param <T>
   * @param bills
   */
  public <T extends AbstractBill> void calculateLocal(T[] bills) {
    if (VOChecker.isEmpty(bills)) {
      return;
    }
    IRelationForItems item1 = this.getIRelationForItems();
    for (T bill : bills) {
      PriceNumMnyCalculator calutil =
          new PriceNumMnyCalculator(new VOKeyValue<T>(bill));
      Condition con = this.getCondition();
      con.setChangeKey(SOItemKey.NEXCHANGERATE);
      calutil.setCondition(con);
      CircularlyAccessibleValueObject[] bvos = bill.getChildrenVO();
      int len = bvos.length;
      int[] rows = new int[len];
      IDataSetForCal[] datas = new SOVODataSetForCal[len];
      for (int i = 0; i < len; i++) {
        datas[i] = new SOVODataSetForCal(bill.getParentVO(), bvos[i], item1);
        rows[i] = i;
      }
      calutil.calculateLocal(rows, datas);
    }
  }

  public void setCondition(Condition cond1) {
    this.cond = cond1;
  }

  public void setIRelationForItems(IRelationForItems item1) {
    this.item = item1;
  }

  private Condition getCondition() {
    if (this.cond == null) {
      this.cond = new Condition();
    }
    return this.cond;
  }

  private IRelationForItems getIRelationForItems() {
    if (this.item == null) {
      this.item = new RelationItemForCal();
    }
    return this.item;
  }

}
