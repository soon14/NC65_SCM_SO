package nc.bs.so.m30.rule.reserve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.ref.ic.m4c.SoReserveAdjust;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.util.SaleOrderVOUtil;

/**
 * @description
 * 销售订单出库关闭后可用量变更
 * @scene
 * 销售订单出库关闭后
 * @param
 * 无
 */
public class ReserveCloseRule implements IRule<SaleOrderViewVO> {
  @Override
  public void process(SaleOrderViewVO[] viewvos) {
    try {
      SaleOrderVO[] vos = new SaleOrderVOUtil().chgViewToBill(viewvos);
      for (SaleOrderVO vo : vos) {
        SoReserveAdjust.adjustReserveWhenClose(SOBillType.Order.getCode(), vo);
      }
    }
    catch (BusinessException e) {
      
      ExceptionUtils.wrappException(e);
    }
  }
}
