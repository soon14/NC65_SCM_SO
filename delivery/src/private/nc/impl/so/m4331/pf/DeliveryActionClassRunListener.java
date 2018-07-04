package nc.impl.so.m4331.pf;

import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPAfterRule;
import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPBeforeRule;
import nc.impl.pubapp.bill.rewrite.BillActionClassRunListener;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.SOConstant;

/**
 * 
 * @description
 *              发货单流程平台回写扩展类
 * @scene
 *        发货单动作脚本调用
 * @param
 * 
 * 
 * @since 6.5
 * @version 2015-11-5 下午8:57:56
 * @author zhangby5
 */
public class DeliveryActionClassRunListener extends BillActionClassRunListener {

  @Override
  public void beforeActionExecute(PfParameterVO pfVo) throws BusinessException {
    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable && SOConstant.DELETE.equalsIgnoreCase(pfVo.m_actionName)) {
      // 删除单据时可用量更新前需要在动作前处理
      DeliveryVOATPBeforeRule beforerule = new DeliveryVOATPBeforeRule();
      beforerule.process((DeliveryVO[]) pfVo.m_preValueVos);
    }
    super.beforeActionExecute(pfVo);
  }

  @Override
  public void afterActionExecute(PfParameterVO pfVo) throws BusinessException {
    super.afterActionExecute(pfVo);
    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (!icEnable) {
      return;
    }
    if (SOConstant.WRITE.equalsIgnoreCase(pfVo.m_actionName)
        || SOConstant.DELETE.equalsIgnoreCase(pfVo.m_actionName)) {
      // 修改保存删除单据时可用量更新后需要在动作后处理
      DeliveryVOATPAfterRule rule = new DeliveryVOATPAfterRule();
      rule.process((DeliveryVO[]) pfVo.m_preValueVos);
    }
  }

}
