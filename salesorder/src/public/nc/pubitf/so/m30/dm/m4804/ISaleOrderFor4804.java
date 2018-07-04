package nc.pubitf.so.m30.dm.m4804;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * 销售订单提供给运输单服务接口
 * 
 * @since 6.0
 * @version 2011-6-7 上午11:20:56
 * @author 刘志伟
 */
public interface ISaleOrderFor4804 {

  /**
   * 根据销售订单BIDs查询表体财务组织ID
   * 
   * @param bids 销售订单BIDs
   * @return Map<String, String> Map<销售订单BID, 结算财务组织OID>
   * @throws BusinessException
   */
  Map<String, String> querySettleOrgsByBIDs(String[] bids)
      throws BusinessException;

}
