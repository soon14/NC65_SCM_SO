package nc.impl.so.m4331.action.maintain.rule.send;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * @description
 * 销售发货单提交前设置VO状态
 * @scene
 * 销售发货单提交前
 * @param
 * 无
 * @since 6.0
 * @version 2011-7-13 下午07:18:56
 * @author 么贵敬
 */
public class SetCommitStatusRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    for (DeliveryVO vo : vos) {
      vo.getParentVO().setStatus(VOStatus.UPDATED);
    }
  }

}
