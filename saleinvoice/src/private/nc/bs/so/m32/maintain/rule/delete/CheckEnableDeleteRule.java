package nc.bs.so.m32.maintain.rule.delete;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.trade.checkrule.VOChecker;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * 销售发票删除操作前生删除校验规则(单据状态)
 * @scene
 * 销售发票删除保存前
 * @param
 * 无
 * @since 6.3
 * @version 2012-12-21 上午09:04:01
 * @author yaogj
 */
public class CheckEnableDeleteRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {

    // 检查销售发票状态是否可被删除。
    for (SaleInvoiceVO invoicevo : vos) {
      Integer fstatusflag = invoicevo.getParentVO().getFstatusflag();
      String cauditorid = invoicevo.getParentVO().getApprover();
      if (!BillStatus.FREE.equalsValue(fstatusflag)
          && !(BillStatus.AUDITING.equalsValue(fstatusflag) && VOChecker
              .isEmpty(cauditorid))) {

        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006008_0", "04006008-0013")/*@res "当前发票单据状态不可删除。"*/);
      }
    }

  }

}
