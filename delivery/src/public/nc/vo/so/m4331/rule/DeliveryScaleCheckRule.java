package nc.vo.so.m4331.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.scale.DeliveryScaleProcessor;

/**
 * @description
 * 发货单保存前精度检查规则
 * @scene
 * 销售发货单新增、修改保存前
 * @param
 * 无
 * @since 6.3
 * @version 2013-6-18 下午03:58:28
 * @author tianft
 */
public class DeliveryScaleCheckRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    new DeliveryScaleProcessor().checkBillPrecision(vos);
  }

}
