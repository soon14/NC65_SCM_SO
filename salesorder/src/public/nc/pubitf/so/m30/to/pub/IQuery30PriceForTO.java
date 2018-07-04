package nc.pubitf.so.m30.to.pub;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 销售订单提供TO查询价格接口
 * 
 * @since 6.0
 * @version 2010-11-6 上午09:47:22
 * @author 刘志伟
 */

public interface IQuery30PriceForTO {

  /**
   * 查询销售订单原币单价
   * 
   * @param bids 销售订单附表ids
   * @return SaleOrderVO{子表主键、原币无税单价、原币含税单价、原币币种}
   * @throws BusinessException
   */
  SaleOrderVO[] queryOrigPrice(String[] bids) throws BusinessException;
}
