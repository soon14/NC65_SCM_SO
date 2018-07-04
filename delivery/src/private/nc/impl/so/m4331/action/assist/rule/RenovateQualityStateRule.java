package nc.impl.so.m4331.action.assist.rule;

import nc.impl.so.m4331.action.quality.DeliverycheckCloseAction;
import nc.impl.so.m4331.action.quality.DeliverycheckOpenAction;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m4331.entity.DeliveryVO;

public class RenovateQualityStateRule {

  public void renovateState(DeliveryVO[] bills, UFBoolean isclose) {
    if (null == bills) {
      return;
    }
    if (!isclose.booleanValue()) {
      // 打开发货单时，打开发货单相应的质检信息
      DeliverycheckOpenAction open = new DeliverycheckOpenAction();
      open.openQualityInfo(bills);
      return;
    }
    // 关闭发货单，关闭发货单相应的质检信息
    DeliverycheckCloseAction close = new DeliverycheckCloseAction();
    close.closeQualityInfo(bills);
  }
}
