package nc.bs.so.m4331.maintain.rule.send;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * 销售发货单收回操作前收回时设置相关字段值
 * @scene
 * 销售发货单收回操作前
 * @param
 * 无
 * @since 6.0
 * @version 2011-2-22 上午11:01:45
 * @author 么贵敬
 */
public class SetUnSendValueRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    for (DeliveryVO vo : vos) {
      vo.getParentVO().setStatus(VOStatus.UPDATED);
      vo.getParentVO().setFstatusflag((Integer) BillStatus.FREE.value());
    }
  }

}
