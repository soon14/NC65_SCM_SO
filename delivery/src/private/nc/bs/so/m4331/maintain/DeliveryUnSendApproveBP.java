package nc.bs.so.m4331.maintain;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * 
 * @since 6.0
 * @version 2011-3-10 下午02:28:06
 * @author 么贵敬
 */
public class DeliveryUnSendApproveBP {

  public DeliveryVO[] unSend(DeliveryVO[] clientBills, DeliveryVO[] originBills) {
    // 把VO持久化到数据库中
    BillUpdate<DeliveryVO> update = new BillUpdate<DeliveryVO>();
    DeliveryVO[] returnVos = update.update(clientBills, originBills);
    return returnVos;
  }
}
