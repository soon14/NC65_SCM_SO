package nc.ui.so.m4331.billui.action.lineaction;

import nc.ui.pubapp.uif2app.actions.BodyDelLineAction;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m4331.rule.DeliveryHeadTotalCalculateRule;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 发货单删行动作
 * 
 * @since 6.0
 * @version 2011-9-13
 * @author buxh
 */

public class DeliveryDelLineAction extends BodyDelLineAction {

  private static final long serialVersionUID = 5401065450762303924L;

  @Override
  public void doAction() {
    super.doAction();

    // 计算表头数量合计
    IKeyValue keyValue = new CardKeyValue(this.getCardPanel());
    DeliveryHeadTotalCalculateRule headtotalrule =
        new DeliveryHeadTotalCalculateRule(keyValue);
    headtotalrule.calculateHeadTotal();

  }

}
