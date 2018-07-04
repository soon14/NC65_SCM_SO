package nc.impl.so.m4331.action.maintain;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * 送审动作
 * 
 * @author gdsjw
 */
public class CommitDeliveryAction {

  public DeliveryVO[] sendApprove(DeliveryVO[] clientBills,
      DeliveryVO[] originBills) {
    // 把VO持久化到数据库中
    BillUpdate<DeliveryVO> update = new BillUpdate<DeliveryVO>();
    DeliveryVO[] returnVos = update.update(clientBills, originBills);
    return returnVos;
  }

}
