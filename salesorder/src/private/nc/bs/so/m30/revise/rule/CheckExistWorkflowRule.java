package nc.bs.so.m30.revise.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryHVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.wfengine.definition.WorkflowTypeEnum;

/**
 * @description
 *              销售订单修订提交前审批流检查，校验销售订单修订是否（有审批流）允许提交
 * @scene
 *        销售订单修订提交前
 * @param 无
 * @since 6.36
 * @version 2014-12-26 下午3:34:22
 * @author wangshu6
 */
public class CheckExistWorkflowRule implements IRule<SaleOrderHistoryVO> {

  @Override
  public void process(SaleOrderHistoryVO[] vos) {
    for (SaleOrderHistoryVO vo : vos) {
      SaleOrderHistoryHVO header = vo.getParentVO();
      if (header.getFstatusflag().intValue() != BillStatus.FREE.getIntValue()) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006011_0", "04006011-0305", null, new String[] {
              header.getVbillcode()
            })/*{0}单据为非自由态不允许做提交动作!*/);
      }
      if (!PfServiceScmUtil.isExistWorkflowDefinition(
          header.getVhistrantypecode(), header.getPk_org(), header.getCreviserid(),
          WorkflowTypeEnum.Approveflow.getIntValue())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0306", null, new String[]{header.getVbillcode()})/*{0}单据没有找到匹配的审批流程，本单据可以直接审批通过!*/);
      }
    }
  }
}
