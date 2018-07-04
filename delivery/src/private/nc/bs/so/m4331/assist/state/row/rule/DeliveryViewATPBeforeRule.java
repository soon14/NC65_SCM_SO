package nc.bs.so.m4331.assist.state.row.rule;

import nc.impl.pubapp.bill.convertor.ViewToBillConvertor;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.ref.ic.m4c.SOATPprocess;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * @description
 * 销售发货单行关闭、打开前可用量变更操作
 * @scene
 * 发货单行关闭、打开动作执行前
 * @param
 * 无
 */
public class DeliveryViewATPBeforeRule implements IRule<DeliveryViewVO> {

  @Override
  public void process(DeliveryViewVO[] viewvos) {
    try {
      IObjectConvert<DeliveryViewVO, DeliveryVO> billconvert =
          new ViewToBillConvertor<DeliveryViewVO, DeliveryVO>(DeliveryVO.class);
      DeliveryVO[] vos = billconvert.convert(viewvos);
      SOATPprocess.modifyATPBefore(SOBillType.Delivery.getCode(), vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
