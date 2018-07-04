package nc.itf.so.m4331;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m4331.entity.DeliveryVO;

public interface IDeliveryScriptMaintain {

  /**
   * 方法功能描述：发货单修改保存功能。
   */
  DeliveryVO[] deliveryWrite(DeliveryVO[] vos, PfUserObject userObj,
      DeliveryVO[] originBills) throws BusinessException;

  /**
   * 方法功能描述：发货单删除功能。
   */
  void deliveryDelete(DeliveryVO[] vos, PfUserObject userObj)
      throws BusinessException;

}
