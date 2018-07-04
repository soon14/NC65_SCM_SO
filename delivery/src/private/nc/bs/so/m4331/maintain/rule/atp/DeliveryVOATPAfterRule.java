package nc.bs.so.m4331.maintain.rule.atp;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.so.pub.ref.ic.m4c.SOATPprocess;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * @description
 *              销售发货单保存、删除后可用量检查
 * @scene
 *        开启库存管理模块下销售发货单新增保存、删除后
 * @param 无
 */
public class DeliveryVOATPAfterRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    try {
      if (SysInitGroupQuery.isICEnabled()) {
        SOATPprocess.modifyATPAfter(SOBillType.Delivery.getCode(), vos);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
