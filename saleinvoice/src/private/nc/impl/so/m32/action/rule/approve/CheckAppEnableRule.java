package nc.impl.so.m32.action.rule.approve;

import nc.vo.ml.AbstractNCLangRes;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * 销售发票审批前审批合法性校验（单据状态、单据日期校验）
 * @scene
 * 销售发票审批前
 * @param
 * 无
 * @since 6.1
 * @version 2012-12-21 上午09:20:13
 * @author yaogj
 */
public class CheckAppEnableRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    UFDate curdate = AppContext.getInstance().getBusiDate();
    AbstractNCLangRes nclangres = NCLangRes4VoTransl.getNCLangRes();
    for (SaleInvoiceVO invoicevo : vos) {
      // 自由、审批中状态单据允许审核
      Integer status = invoicevo.getParentVO().getFstatusflag();
      if (!(BillStatus.FREE.equalsValue(status) || BillStatus.AUDITING
          .equalsValue(status))) {
        ExceptionUtils.wrappBusinessException(nclangres.getStrByID("4006008_0",
            "04006008-0029")/*@res "当前发票单据状态，不可进行审批。"*/);
      }
      // 当前日期和单据日期校验  beforeDate比较的是当前日期不包含时分秒
      UFDate billdate = invoicevo.getParentVO().getDbilldate();
      if (curdate.beforeDate(billdate)) {
        ExceptionUtils.wrappBusinessException(nclangres.getStrByID("4006008_0",
            "04006008-0030", null, new String[] {
              curdate.toString(), billdate.toString()
            })/*@res "发票审核日期({0})不能早于开票日期({1})。"*/);
      }

    }
  }

}
