package nc.impl.so.m32.action.rule.commit;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.wfengine.definition.WorkflowTypeEnum;

/**
 * @description
 * 销售发票送审前： <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>检查是否允许送审的规则
 * </ul>
 * <p>
 * @scene
 * 销售发票送审前
 * @param
 * 无
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-5-21 下午03:25:37
 */
public class CheckCommitEnableRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    NCLangResOnserver resonserver = NCLangResOnserver.getInstance();
    for (SaleInvoiceVO invoicevo : vos) {
      // 自由状态、审批不通过单据允许送审
      // modify by wangshu6 for 636 销售发票走审批流审批不通过过可再次提交 20150408
      if (!BillStatus.FREE
          .equalsValue(invoicevo.getParentVO().getFstatusflag())
          && !BillStatus.NOPASS.equalsValue(invoicevo.getParentVO()
              .getFstatusflag())) {

        ExceptionUtils.wrappBusinessException(resonserver.getStrByID(
            "4006008_0", "04006008-0031")/*@res "当前发票单据状态，不可进行送审。"*/);
      }
      SaleInvoiceHVO header = invoicevo.getParentVO();
      if (!PfServiceScmUtil.isExistWorkflowDefinition(
          header.getVtrantypecode(), header.getPk_org(), header.getBillmaker(),
          WorkflowTypeEnum.Approveflow.getIntValue())) {
        ExceptionUtils.wrappBusinessException(resonserver.getStrByID(
            "4006008_0", "04006008-0103", null, new String[] {
              header.getVbillcode()
            })/*{0}单据没有找到匹配的审批流程，本单据可以直接审批通过!!*/);
      }
    }

  }

}
