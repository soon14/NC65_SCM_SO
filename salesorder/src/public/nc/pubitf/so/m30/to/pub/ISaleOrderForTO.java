package nc.pubitf.so.m30.to.pub;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 销售订单提供给内部交易公共接口服务
 * 
 * @since 6.0
 * @version 2011-5-26 上午09:54:27
 * @author 刘志伟
 */
public interface ISaleOrderForTO {

  /**
   * 查询销售订单原币单价
   * 
   * @param bids 销售订单附表ids
   * @return SaleOrderVO{子表主键、原币无税单价、原币含税单价、原币币种}
   * @throws BusinessException
   */
  SaleOrderVO[] queryOrigPrice(String[] bids) throws BusinessException;

  /**
   * 交易类型是否直运调拨
   * 
   * @param ctrantypeids 交易类型ID
   * @return Map<String, UFBoolean> Map<ctrantypeid, 是否直运调拨>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsDirectTO(String[] ctrantypeids)
      throws BusinessException;
}
