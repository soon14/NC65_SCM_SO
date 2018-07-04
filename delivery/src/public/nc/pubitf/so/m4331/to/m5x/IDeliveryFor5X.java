package nc.pubitf.so.m4331.to.m5x;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * 发货单提供给调拨订单的接口
 * 
 * @since 6.0
 * @version 2011-1-26 下午01:55:19
 * @author 祝会征
 */
public interface IDeliveryFor5X {
  /**
   * 根据调拨订单表体id 查询发货单是否做过预留标记
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryReverseFlag(String[] bids)
      throws BusinessException;
}
