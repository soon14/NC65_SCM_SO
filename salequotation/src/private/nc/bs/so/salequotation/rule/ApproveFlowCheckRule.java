package nc.bs.so.salequotation.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;
import nc.vo.wfengine.definition.WorkflowTypeEnum;

/**
 * @description
 * 销售报价单提交前单据号检查
 * @scene
 * 销售报价单提交前
 * @param
 * 无
 */
public class ApproveFlowCheckRule implements IRule<AggSalequotationHVO> {

  @Override
  public void process(AggSalequotationHVO[] vos) {
    if (vos == null) {
      return;
    }
    for (AggSalequotationHVO vo : vos) {
      SalequotationHVO header = vo.getParentVO();
      if (!PfServiceScmUtil.isExistWorkflowDefinition(header.getVtrantype(),
          header.getPk_org(), header.getBillmaker(),
          WorkflowTypeEnum.Approveflow.getIntValue())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006009_0", "04006009-0065", null, new String[]{header.getVbillcode()})/*{0}单据没有可启动的审批流!*/);
      }
    }
  }

}
