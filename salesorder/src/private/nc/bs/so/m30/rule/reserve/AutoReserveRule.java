package nc.bs.so.m30.rule.reserve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.ref.ic.m4c.SoAutoReserve;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * 销售订单保存后自动预留
 * @scene 
 * 销售订单新增、修改保存后
 * @param 
 * 无
 */
public class AutoReserveRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    try {
      for (SaleOrderVO vo : vos) {
        SoAutoReserve.autoReserveForReqBill(SOBillType.Order.getCode(), vo);
      }
    }
    catch (BusinessException e) {
      
      ExceptionUtils.wrappException(e);
    }
  }
}
