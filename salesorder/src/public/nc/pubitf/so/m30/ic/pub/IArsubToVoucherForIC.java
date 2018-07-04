package nc.pubitf.so.m30.ic.pub;

import nc.vo.pub.BusinessException;

/**
 * 
 * 销售订单提供给库存管理的销售出库单，签收途损单审批、弃审时影响客户费用单传凭证接口
 * 
 * @since 6.3
 * @version 2014-06-30 19:00:55
 * @author 刘景
 */

public interface IArsubToVoucherForIC {

  /**
   * 调用场景：<br>
   * 
   * 销售出库单签证时调用此接口客户费用单传凭证<br>
   * 
   * @param data
   * 
   * @throws BusinessException
   * 
   */
  void onSaleOutSign(ArsubToVoucherData[] data) throws BusinessException;

  /**
   * 调用场景：<br>
   * 
   * 销售出库单取消签证时调用此接口客户费用单传凭证<br>
   * 
   * @param data
   * 
   * @throws BusinessException
   */
  void onSaleOutUnSign(ArsubToVoucherData[] data) throws BusinessException;

  /**
   * 调用场景：<br>
   * 
   * 签收途损单签证时调用此接口客户费用单传凭证<br>
   * 
   * @param data
   * 
   * @throws BusinessException
   */
  void onWastageSign(ArsubToVoucherData[] data) throws BusinessException;

  /**
   * 调用场景：<br>
   * 
   * 签收途损单取消签证时调用此接口客户费用单传凭证
   * 
   * @param data
   * @throws BusinessException
   */
  void onWastageUnSign(ArsubToVoucherData[] data) throws BusinessException;

}
