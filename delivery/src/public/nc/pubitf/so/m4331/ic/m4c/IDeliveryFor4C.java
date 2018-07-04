package nc.pubitf.so.m4331.ic.m4c;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * 发货单提供给出库单的接口
 * 
 * @since 6.0
 * @version 2011-9-26 
 * @author 步晓慧
 */
public interface IDeliveryFor4C {
  /**
   * 根据发货单表体id 查询来源销售订单是否做过预留标记
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  Map<String, UFBoolean> getReserveInfo(String[] bids)
  throws BusinessException;

}
