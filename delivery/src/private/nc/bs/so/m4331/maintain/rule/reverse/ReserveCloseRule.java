package nc.bs.so.m4331.maintain.rule.reverse;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.ref.ic.m4c.SoReserveAdjust;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.util.CombineViewToAggUtil;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * @description
 * 销售发货单行关闭后预留调整
 * @scene
 * 发货单行关闭动作执行后
 * @param
 * 无
 */
public class ReserveCloseRule implements IRule<DeliveryViewVO> {
  @Override
  public void process(DeliveryViewVO[] views) {
    try {
      // IObjectConvert<DeliveryViewVO, DeliveryVO> billconvert =
      // new ViewToBillConvertor<DeliveryViewVO, DeliveryVO>(DeliveryVO.class);
      // DeliveryVO[] vos = billconvert.convert(views);

      DeliveryVO[] vos =
          new CombineViewToAggUtil<DeliveryVO>(DeliveryVO.class,
              DeliveryHVO.class, DeliveryBVO.class).combineViewToAgg(views,
              DeliveryHVO.CDELIVERYID);
      for (DeliveryVO vo : vos) {
        SoReserveAdjust.adjustReserveWhenClose(SOBillType.Delivery.getCode(),
            vo);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
