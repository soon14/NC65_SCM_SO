package nc.pubitf.so.m30.so.m4331;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * 销售订单提供给销售发货单/发货安排接口服务
 * 
 * @since 6.0
 * @version 2011-1-21 上午11:37:58
 * @author 刘志伟
 */
public interface ISaleOrderFor4331 {

  /**
   * 查询销售订单表体行是否做过预留
   * 
   * @param bids 销售订单bids
   * @return Map<String, UFBoolean> Map<销售订单bid, 是否预留>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsReserved(String[] bids)
      throws BusinessException;

  /**
   * 根据销售订单BIDs查询销售订单ViewVOs
   * 
   * @param bids 销售订单bids
   * @return SaleOrderViewVO[]
   * @throws BusinessException
   */
  Object[] querySaleOrderViewVOsFor4331Arrange(String[] bids)
      throws BusinessException;
}
