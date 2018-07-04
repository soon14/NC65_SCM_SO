package nc.impl.so.m32.action.rule.unapprove;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ic.m4c.I4CQueryPubService;
import nc.vo.ml.AbstractNCLangRes;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.enumeration.OpposeFlag;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * 销售发票弃审前检查发票是否可弃审（单据状态、权限、生成下游单据校验）
 * @scene
 * 销售发票弃审前
 * @param
 * 无
 * @since 6.0
 * @version 2011-10-27 下午12:45:28
 * @author 么贵敬
 */
public class CheckUnAppEnableRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    AbstractNCLangRes nclangres = NCLangRes4VoTransl.getNCLangRes();
    int length = vos.length;
    String[] invoicehids = new String[length];
    // 检查单据状态
    for (int i = 0; i < length; i++) {
      SaleInvoiceHVO voHead = vos[i].getParentVO();
      Integer fstatusflag = voHead.getFstatusflag();
      if (!BillStatus.AUDIT.equalsValue(fstatusflag)
          && !BillStatus.AUDITING.equalsValue(fstatusflag)
              && !BillStatus.NOPASS.equalsValue(fstatusflag)) {
        ExceptionUtils.wrappBusinessException(nclangres.getStrByID("4006008_0",
            "04006008-0033")/*@res "当前发票单据状态，不可进行弃审。"*/);
      }
      if (OpposeFlag.FINSH.equalsValue(voHead.getFopposeflag())) {
        ExceptionUtils.wrappBusinessException(nclangres.getStrByID("4006008_0",
            "04006008-0034")/*@res "当前发票已被对冲，不可进行弃审。"*/);
      }

      if (!nc.vo.pubapp.pub.power.BillPowerChecker.hasApproverPermission(
          vos[i], SOBillType.Invoice.getCode())) {
        /*不满足审核者权限。*/
        String msg = nclangres.getStrByID("40060501", "1400605010035");
        ExceptionUtils.wrappBusinessException(msg);
      }

      invoicehids[i] = voHead.getCsaleinvoiceid();

    }
    // 检查是否存在下游单据
    Map<String, UFBoolean> hmExit = null;

    try {
      if (SysInitGroupQuery.isICEnabled()) {
        I4CQueryPubService icquerysrv =
            NCLocator.getInstance().lookup(I4CQueryPubService.class);
        hmExit =
            icquerysrv.existBill(invoicehids, true,
                SOBillType.Invoice.getCode());
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    if (null == hmExit || hmExit.size() == 0) {
      return;
    }

    for (String key : invoicehids) {
      if (hmExit.get(key).booleanValue()) {
        ExceptionUtils.wrappBusinessException(nclangres.getStrByID("4006008_0",
            "04006008-0035")/*@res "当前发票已生成下游单据，不可进行弃审。"*/);
      }

    }
  }

}
