package nc.vo.so.m33.pub.util;

import java.util.HashSet;
import java.util.Set;

import nc.vo.pub.SuperVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.calculator.SOVODataSetForCal;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.util.CirVOKeyValue;

public class SquareCalculatorForVO {

  private Set<String> hsNeedCalKey;

  public <T extends SuperVO> void calculate(T[] bvos, String calkey) {
    this.calculate(bvos, calkey, false);
  }

  public <T extends SuperVO> void calculateOnlyNumAssNumQtNum(T[] bvos,
      String calkey) {
    this.calculate(bvos, calkey, true);
  }

  public Set<String> getNeedCalKey() {
    if (null == this.hsNeedCalKey) {
      this.hsNeedCalKey = new HashSet<String>();
      for (String key : SOConstant.STRNEEDCALKEY) {
        this.hsNeedCalKey.add(key);
      }
      this.hsNeedCalKey.add(SquareOutBVO.NTHISNUM);
    }
    return this.hsNeedCalKey;
  }

  private <T extends SuperVO> void calculate(T[] bvos, String editkey,
      boolean isonlynum) {

    // 如果编辑字段不会影响到数量单价金额，不进行计算
    if (!this.getNeedCalKey().contains(editkey)) {
      return;
    }
    // 1.创建数据映射实例 获得数据项之间的映射关系
    IRelationForItems item = new RelationItemForCal();
    item.setnumKey(SquareOutBVO.NTHISNUM);
    // 2.创建数据集实例 初始化数据关系计算用的数据集
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = SOCalConditionRule.getCondition();
    // 设置计算主单位优先
    cond.setUnitPriorType(Condition.MAIN_PRIOR);
    // 设置调单价方式调折扣
    cond.setIsChgPriceOrDiscount(false);
    String pk_group = AppContext.getInstance().getPkGroup();
    ScaleUtils scale = new ScaleUtils(pk_group);
    IKeyValue keyValue = new CirVOKeyValue<T>(bvos);
    int row = 0;
    for (T bvo : bvos) {

      IDataSetForCal data = new SOVODataSetForCal(null, bvo, item);
      // 设置是否国内销售
      SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(keyValue);
      cond.setInternational(buysellrule.isBuysellFlagOut(row));
      // 设置是否固定单位换算率
      cond.setIsFixNchangerate(true);
      cond.setIsFixNqtunitrate(true);
      Calculator tool = new Calculator(data, scale);
      // 两个参数 cond 为计算时的参数条件
      if (isonlynum) {
        tool.calculateOnlyNumAssNumQtNum(cond, editkey);
      }
      else {
        tool.calculate(cond, editkey);
      }
      row++;
    }
  }
}
