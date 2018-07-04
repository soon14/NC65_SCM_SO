package nc.pubimpl.so.m38.mmpps.calc;

import nc.vo.pub.BusinessException;

import nc.pubitf.mmpub.sdmanage.IDemandResult;
import nc.pubitf.so.m38.mmpps.calc.IPreOrderForMPS;
import nc.pubitf.so.m38.mmpps.calc.PreOrderDemandMapVO;

/**
 * 销售预订单提供生产制造MPS运算接口实现类
 * 
 * @since 6.3.1
 * @version 2013-08-21 09:11:00
 * @author 张云枫
 * 
 */
public class PreOrderForMPSImpl implements IPreOrderForMPS {

  @Override
  public IDemandResult getDemandForPreOrder() throws BusinessException {
    PreOrderDemandMapVO mapVo = new PreOrderDemandMapVO();
    return mapVo;
  }

}
