package nc.bs.so.m30.rule.approve;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.wfengine.definition.WorkflowTypeEnum;

/**
 * @description
 * 销售订单提交前审批流检查（当前操作员是否有可用的审批流），校验销售订单是否（有审批流）允许提交
 * @scene
 * 销售订单提交前
 * @param
 * @author gdsjw
 */
public class CheckExistWorkflowRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO vo : vos) {
      SaleOrderHVO header = vo.getParentVO();
      if (header.getFstatusflag().intValue() != BillStatus.FREE.getIntValue()) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0305", null, new String[]{header.getVbillcode()})/*{0}单据为非自由态不允许做提交动作!*/);
      }
      if (!PfServiceScmUtil.isExistWorkflowDefinition(
          header.getVtrantypecode(), header.getPk_org(), header.getBillmaker(),
          WorkflowTypeEnum.Approveflow.getIntValue())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0306", null, new String[]{header.getVbillcode()})/*{0}单据没有找到匹配的审批流程，本单据可以直接审批通过!*/);
      }
    }
  }
}
