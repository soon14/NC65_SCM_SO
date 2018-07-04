package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.scale.ScaleUtils;

public class NumPriceMnyEditHandler {
  public void afterEdit(CardBodyAfterEditEvent e) {
    // 1.创建数据映射实例 获得数据项之间的映射关系
    IRelationForItems item = new RelationItemForCal();
    // 2.创建数据集实例 初始化数据关系计算用的数据集
    IDataSetForCal data =
        new DeliveryCardDataSet(e.getBillCardPanel(), e.getRow(), item);

    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    Calculator tool = new Calculator(data, scale);
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = new Condition();
    // 设置是否进行本币换算
    cond.setIsCalLocalCurr(false);
    // 设置调单价方式调折扣
    cond.setIsChgPriceOrDiscount(false);
    // 设置是否固定单位换算率
    cond.setIsFixNchangerate(false);
    // 设置含税优先还是无税优先
    cond.setIsTaxOrNet(false);
    // 两个参数 cond 为计算时的参数条件
    tool.calculate(cond, e.getKey());
  }
}
