package nc.impl.so.m4331.action.maintain.rule.send;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.wfengine.definition.WorkflowTypeEnum;

/**
 * @description
 * 销售发货单提交前审批流检查（当前操作员是否有可用的审批流）
 * @scene
 * 销售发货单提交前
 * @param
 * 无
 * @since 6.0
 * @version 2011-3-22 下午06:35:50
 * @author 么贵敬
 */
public class CheckExistWorkflowRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    for (DeliveryVO vo : vos) {
      DeliveryHVO header = vo.getParentVO();
      if (header.getFstatusflag().intValue() != BillStatus.FREE.getIntValue()) {
        ExceptionUtils.wrappBusinessException(header.getVbillcode()
            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0064")/*@res "单据为非自由态不允许做提交动作!"*/);
      }
      if (!PfServiceScmUtil.isExistWorkflowDefinition(
          header.getVtrantypecode(), header.getPk_org(), header.getBillmaker(),
          WorkflowTypeEnum.Approveflow.getIntValue())) {

        ExceptionUtils.wrappBusinessException(header.getVbillcode()
            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0065")/*@res "单据没有找到匹配的审批流程，本单据可以直接审批通过!"*/);
      }
    }
  }

}