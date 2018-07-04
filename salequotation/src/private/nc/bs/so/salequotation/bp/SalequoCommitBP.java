package nc.bs.so.salequotation.bp;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.bs.so.salequotation.rule.ApproveFlowCheckRule;
import nc.bs.so.salequotation.rule.SalequoCommitStateChgRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoCommitBP {

  public AggSalequotationHVO[] commit(AggSalequotationHVO[] vos,
      AbstractCompiler2 script) {

    PfParameterUtil<AggSalequotationHVO> util =
        new PfParameterUtil<AggSalequotationHVO>(script.getPfParameterVO(), vos);
    AggSalequotationHVO[] originBills = util.getOrginBills();
    AggSalequotationHVO[] clientBills = util.getClientFullInfoBill();
    AroundProcesser<AggSalequotationHVO> aroundProcesser =
        new AroundProcesser<AggSalequotationHVO>(null);
    aroundProcesser.addBeforeFinalRule(new ApproveFlowCheckRule());
    aroundProcesser.addBeforeFinalRule(new SalequoCommitStateChgRule());
    aroundProcesser.before(clientBills);
    this.checkBillStatus(originBills);
    // 数据持久化
    AggSalequotationHVO[] returnVos =
        new BillUpdate<AggSalequotationHVO>().update(clientBills, originBills);

    return returnVos;

  }

  private void checkBillStatus(AggSalequotationHVO[] originBills) {
    for (AggSalequotationHVO bill : originBills) {
      SalequotationHVO hvo = (SalequotationHVO) bill.getParent();
      if (hvo == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0024")/*@res "无效单据"*/);
        continue;
      }
      int billStatus = hvo.getFstatusflag().intValue();
      if (billStatus != BillStatusEnum.C_FREE) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0025")/*@res "只有自由态的单据可以提交"*/);
      }
    }
  }
}