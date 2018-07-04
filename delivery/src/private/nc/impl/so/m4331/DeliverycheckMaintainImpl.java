package nc.impl.so.m4331;

import nc.bs.so.m4331.quality.QueryDeliveryCheckBP;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.so.m4331.action.quality.DeliverycheckDeleteAction;
import nc.impl.so.m4331.action.quality.DeliverycheckInsertAction;
import nc.itf.so.m4331.IDeliverycheckMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

/**
 * 发货单质检
 * 
 * @since 6.0
 * @version 2010-12-21 上午09:40:34
 * @author 祝会征
 */
public class DeliverycheckMaintainImpl implements IDeliverycheckMaintain {

  /**
   * 删除质检结果
   */
  @Override
  public void deleteDeliverycheck(DeliveryCheckVO[] vos, boolean isCheck)
      throws BusinessException {
    try {
      DeliverycheckDeleteAction action = new DeliverycheckDeleteAction();
      action.delete(vos, isCheck);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 新增质检结果
   */
  @Override
  public DeliveryCheckVO[] insertDeliverycheck(DeliveryCheckVO[] vos)
      throws BusinessException {
    DeliveryCheckVO[] ret = null;
    try {
      DeliverycheckInsertAction action = new DeliverycheckInsertAction();
      ret = action.insert(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

  @Override
  public DeliveryCheckVO[] queryDeliveryCheckVO(String sql) {
    QueryDeliveryCheckBP query = new QueryDeliveryCheckBP();
    return query.queryDeliveryCheckVO(sql);
  }

  @Override
  public DeliveryCheckVO[] updateDeliverycheck(DeliveryCheckVO[] vos) {
	if(vos!=null && vos.length>0){
		VOUpdate<DeliveryCheckVO> update = new VOUpdate<DeliveryCheckVO>();
		update.update(vos);
	  }
    return null;
  }
}
