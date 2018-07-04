package nc.bs.so.salequotation.bp;

import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.bs.so.salequotation.rule.UnApproveHasApproPermisRule;
import nc.bs.so.salequotation.rule.UnApproveWriteNumRule;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * 报价单弃审bp
 * 
 * @since 6.1
 * @version 2013-06-04 10:30:52
 * @author yixl
 */
public class SalequoUnApproveBP {

  /**
   * 报价单弃审
   * 
   * @param vos
   * @param script
   * @return AggSalequotationHVO
   */
  public AggSalequotationHVO[] unApprove(AggSalequotationHVO[] vos,
      AbstractCompiler2 script) {
    // 调用平台脚本进行弃审
    if (null != script) {
      try {
        script.procUnApproveFlow(script.getPfParameterVO());
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }
    else {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0021")/*@res "系统错误"*/);
      return null;
    }

    PfParameterUtil<AggSalequotationHVO> util =
        new PfParameterUtil<AggSalequotationHVO>(script.getPfParameterVO(), vos);
    AggSalequotationHVO[] originBills = util.getOrginBills();
    AggSalequotationHVO[] clientBills = util.getClientFullInfoBill();
    for (AggSalequotationHVO bill : clientBills) {
      SalequotationHVO hvo = (SalequotationHVO) bill.getParent();
      if (hvo == null) {
        continue;
      }
      hvo.setStatus(VOStatus.UPDATED);
    }
    AroundProcesser<AggSalequotationHVO> aroundProcesser =
        new AroundProcesser<AggSalequotationHVO>(null);
    aroundProcesser.addBeforeFinalRule(new UnApproveWriteNumRule());
    aroundProcesser.addBeforeFinalRule(new UnApproveHasApproPermisRule());
    aroundProcesser.before(clientBills);
    this.checkBillStatus(originBills);

    // 把VO持久化到数据库中
    BillUpdate<AggSalequotationHVO> update =
        new BillUpdate<AggSalequotationHVO>();
    AggSalequotationHVO[] returnVos = update.update(clientBills, originBills);
    return returnVos;
  }

  private void checkBillStatus(AggSalequotationHVO[] originBills) {
    for (AggSalequotationHVO bill : originBills) {
      SalequotationHVO hvo = (SalequotationHVO) bill.getParent();
      if (hvo == null) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006009_0", "04006009-0024")/*@res "无效单据"*/);
        continue;
      }
      int billStatus = hvo.getFstatusflag().intValue();
      if (billStatus != BillStatusEnum.C_AUDIT
          && billStatus != BillStatusEnum.C_AUDITING
          && billStatus != BillStatusEnum.C_NOPASS) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006009_0", "04006009-0025")/*@res "只有自由态的单据可以提交"*/);
      }
    }
  }
}
