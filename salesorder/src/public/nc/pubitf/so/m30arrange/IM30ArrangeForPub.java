package nc.pubitf.so.m30arrange;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * 补货/安排提供给下游单据服务接口
 * 
 * @since 6.0
 * @version 2011-8-9 上午10:54:10
 * @author 刘志伟
 */
public interface IM30ArrangeForPub {
  /**
   * 根据销售订单IDs查询销售订单VOs
   * 
   * @param ids 销售订单ids
   * @return SaleOrderViewVO[]
   * @throws BusinessException
   */
  Map<String, Object[]> querySaleOrderVOs(String[] ids)
      throws BusinessException;
}
