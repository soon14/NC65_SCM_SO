package nc.pubimpl.so.m4331.so.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.so.m4331.action.assist.DeliveryRowCloseAction;
import nc.ui.pub.bill.BillStatus;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * 关闭发货单
 * 
 * @since 6.0
 * @version 2011-3-2 上午11:17:10
 * @author 祝会征
 */
public class CloseDeliveryRule {
  public void closeDelivery(DeliveryVO[] vos) {
    List<DeliveryVO> list = new ArrayList<DeliveryVO>();
    for (DeliveryVO vo : vos) {
      if (BillStatus.AUDIT == vo.getParentVO().getFstatusflag().intValue()) {
        list.add(vo);
      }
    }
    if (list.size() > 0) {
      DeliveryVO[] appVOs = new DeliveryVO[list.size()];
      appVOs = list.toArray(appVOs);
      DeliveryRowCloseAction close = new DeliveryRowCloseAction();
      close.closeRow(appVOs);
    }
  }
}
