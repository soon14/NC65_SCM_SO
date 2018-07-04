package nc.pubitf.so.m30.ic.m4453;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * 销售订单提供给途损单接口服务
 * 
 * @since 6.0
 * @version 2011-1-17 上午09:29:44
 * @author 刘志伟
 */
public interface ISaleOrderFor4453 {

  /**
   * 根据销售订单主表ID查询销售组织
   * 
   * @param hids 销售订单HIDs
   * @return Map<String, String> Map<销售订单HID, 销售组织ID>
   * @throws BusinessException
   */
  Map<String, String> querySaleOrgsByIDs(String[] hids)
      throws BusinessException;

}
