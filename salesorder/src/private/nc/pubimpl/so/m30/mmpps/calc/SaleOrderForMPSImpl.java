package nc.pubimpl.so.m30.mmpps.calc;

import nc.vo.pub.BusinessException;

import nc.pubitf.mmpub.sdmanage.IDemandResult;
import nc.pubitf.so.m30.mmpps.calc.ISaleOrderForMPS;
import nc.pubitf.so.m30.mmpps.calc.SaleOrderDemandMapVO;

/**
 * 销售订单提供生产制造MPS运算接口实现类
 * 
 * @since 6.3.1
 * @version 2013-08-21 09:07:49
 * @author 张云枫
 * 
 */
public class SaleOrderForMPSImpl implements ISaleOrderForMPS {

  @Override
  public IDemandResult getDemandFroSaleOrder() throws BusinessException {
    SaleOrderDemandMapVO mapVo = new SaleOrderDemandMapVO();
    return mapVo;
  }

}
