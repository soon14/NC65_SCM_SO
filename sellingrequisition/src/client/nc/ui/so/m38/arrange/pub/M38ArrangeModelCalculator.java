package nc.ui.so.m38.arrange.pub;

import java.util.HashSet;
import java.util.Set;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.calculator.data.BillModelDataSet;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

/**
 * <p>
 * <b>预订单安排数量单价金额计算工具类</b>
 * 
 * <ul>
 * <li>
 * <li>
 * <li>...
 * </ul>
 * 
 * @since 6.0
 * @author 刘志伟
 * @time 2010-7-3 上午10:23:08
 */
public class M38ArrangeModelCalculator {

  private BillListPanel listPanel;

  private Set<String> hsNeedCalKey;

  public M38ArrangeModelCalculator(BillListPanel listPanel) {
    this.listPanel = listPanel;
  }

  public void calculate(int[] rows, String editkey) {
    this.calculate(rows, editkey, false);
  }

  public void calculateOnlyNumAssNumQtNum(int[] rows, String editkey) {
    this.calculate(rows, editkey, true);
  }

  public Set<String> getNeedCalKey() {
    if (null == this.hsNeedCalKey) {
      this.hsNeedCalKey = new HashSet<String>();
      for (String key : SOConstant.STRNEEDCALKEY) {
        this.hsNeedCalKey.add(key);
      }
    }
    return this.hsNeedCalKey;
  }

  private void calculate(int[] rows, String editkey, boolean isonlynum) {

    // 如果编辑字段不会影响到数量单价金额，不进行计算
    if (!this.getNeedCalKey().contains(editkey)) {
      return;
    }
    // 1.创建数据映射实例 获得数据项之间的映射关系
    IRelationForItems item = new RelationItemForCal();
    // 2.创建数据集实例 初始化数据关系计算用的数据集
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = SOCalConditionRule.getCondition();
    // 设置调单价方式调折扣
    cond.setIsChgPriceOrDiscount(false);
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    // 两个参数 cond 为计算时的参数条件
    BillModel billmodel = this.listPanel.getBodyBillModel();
    for (int row : rows) {
      IKeyValue keyValue =
          new ListKeyValue(this.listPanel, row, ListTemplateType.SUB);
      IDataSetForCal data = new BillModelDataSet(billmodel, row, item);
      // 设置是否国内销售
      SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(keyValue);
      cond.setInternational(buysellrule.isBuysellFlagOut(row));
      // 设置是否固定单位换算率
      SOUnitChangeRateRule unitrule = new SOUnitChangeRateRule(keyValue);
      cond.setIsFixNchangerate(unitrule.isAstFixedRate(row));
      cond.setIsFixNqtunitrate(unitrule.isQtFixedRate(row));
      Calculator tool = new Calculator(data, scale);
      // 两个参数 cond 为计算时的参数条件
      if (isonlynum) {
        tool.calculateOnlyNumAssNumQtNum(cond, editkey);
      }
      else {
        tool.calculate(cond, editkey);
      }
    }

  }
}
