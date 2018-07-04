package nc.pubitf.so.m38.so.m30;

import nc.vo.pub.BusinessException;

/**
 * 预订单提供给销售订单服务接口
 * 
 * @since 6.0
 * @version 2011-4-2 下午02:51:55
 * @author 刘志伟
 */
public interface IPreOrderFor30 {

  /**
   * 根据预订单bids关闭相应预订单行
   * 
   * @throws BusinessException
   * @since 6.0
   */
  void closeRowFor38Arrange(String[] bids) throws BusinessException;
}
