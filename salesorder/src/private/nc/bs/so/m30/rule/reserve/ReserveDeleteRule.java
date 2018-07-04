package nc.bs.so.m30.rule.reserve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.ref.ic.m4c.SoReserveAdjust;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * 销售订单删除前预留调整
 * @scene 
 * 销售订单删除保存前
 * @param 
 * 无
 */
public class ReserveDeleteRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    try {
      for (SaleOrderVO vo : vos) {
        SoReserveAdjust.adjustReserveWhenDelete(SOBillType.Order.getCode(), vo);
      }
    }
    catch (BusinessException e) {
      
      ExceptionUtils.wrappException(e);
    }
  }

}
