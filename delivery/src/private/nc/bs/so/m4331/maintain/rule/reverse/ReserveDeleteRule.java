package nc.bs.so.m4331.maintain.rule.reverse;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.ref.ic.m4c.SoReserveAdjust;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * @description
 * 销售发货单删除前预留调整
 * @scene
 * 销售发货单删除前
 * @param
 * 无
 */
public class ReserveDeleteRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    try {
      for (DeliveryVO vo : vos) {
        SoReserveAdjust.adjustReserveWhenDelete(SOBillType.Delivery.getCode(),
            vo);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
