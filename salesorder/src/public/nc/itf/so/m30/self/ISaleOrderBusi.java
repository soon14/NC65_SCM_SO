package nc.itf.so.m30.self;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.bind.OrderBindMatchPara;
import nc.vo.so.m30.bind.OrderBindMatchResult;
import nc.vo.so.m30.entity.SaleOrderVO;

public interface ISaleOrderBusi {

  /**
   * 销售订单批量捆绑匹配
   * 
   * @param bindparas
   * @return
   */
  OrderBindMatchResult[] matchBind(OrderBindMatchPara[] bindparas)
      throws BusinessException;

  /**
   * 订单收款准备
   * 
   * @param bill 销售订单VO
   * @return AggregatedValueObject[] 收款单VOs
   */
  AggregatedValueObject[] prepareOrderGathering(SaleOrderVO bill)
      throws BusinessException;
}
