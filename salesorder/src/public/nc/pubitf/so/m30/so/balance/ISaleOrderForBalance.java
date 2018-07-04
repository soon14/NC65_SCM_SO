package nc.pubitf.so.m30.so.balance;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 销售订单提供给订单核销服务接口
 * 
 * @since 6.0
 * @version 2011-8-18 上午11:35:39
 * @author 刘志伟
 */
public interface ISaleOrderForBalance {

  /**
   * 根据销售订单表头HIDs查寻所有Views
   * 
   * @param ids
   * @return 订单视图VO
   * @throws BusinessException
   */
  SaleOrderViewVO[] querySaleOrderViewVOByHIDs(String[] ids)
      throws BusinessException;
}
