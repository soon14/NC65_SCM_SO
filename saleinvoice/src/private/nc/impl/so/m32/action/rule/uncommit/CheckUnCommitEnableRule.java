package nc.impl.so.m32.action.rule.uncommit;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.AbstractNCLangRes;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * 销售发票检查单据是否可收回
 * @scene
 * 销售发票收回前
 * @param
 * 无
 * @since 6.0
 * @version 2011-2-22 上午10:58:10
 * @author 么贵敬
 */
public class CheckUnCommitEnableRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    AbstractNCLangRes nclangres = NCLangRes4VoTransl.getNCLangRes();
    for (SaleInvoiceVO invoicevo : vos) {
      SaleInvoiceHVO header = invoicevo.getParentVO();
      // 审批中状态并且审批人为空的单据允许收回
      if (!BillStatus.AUDITING.equalsValue(header.getFstatusflag())
          || null != header.getApprover()) {
        ExceptionUtils.wrappBusinessException(nclangres.getStrByID("4006008_0",
            "04006008-0036")/*@res "当前发票单据状态，不可进行收回。"*/);
      }

    }
  }

}
