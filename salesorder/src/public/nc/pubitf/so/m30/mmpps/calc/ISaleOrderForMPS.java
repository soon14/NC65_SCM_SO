package nc.pubitf.so.m30.mmpps.calc;

import nc.vo.pub.BusinessException;

import nc.pubitf.mmpub.sdmanage.IDemandResult;

/**
 * 销售订单提供生产制造MPS运算接口
 * 
 * @since 6.1
 * @version 2011-12-05 16:30
 * @author 王天文
 */

public interface ISaleOrderForMPS {

  /**
   * 查询销售预订单的未关闭量。
   * 
   * @return 出库未关闭的销售订单需求对象
   * @throws BusinessException
   */

  IDemandResult getDemandFroSaleOrder() throws BusinessException;

}
