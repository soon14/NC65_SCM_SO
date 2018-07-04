package nc.bs.so.salequotation.bp;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pub.VOStatus;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoUnCommitBP {
  public AggSalequotationHVO[] unCommit(AggSalequotationHVO[] vos,
      AbstractCompiler2 script) {

    // 调用平台脚本进行审批
    if (null != script) {
      try {
        PfParameterVO pfParaVO = script.getPfParameterVO();
        script.procRecallFlow(pfParaVO);
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }
    else {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0021")/*@res "系统错误"*/);
      return null;
    }
    PfParameterUtil<AggSalequotationHVO> util =
        new PfParameterUtil<AggSalequotationHVO>(script.getPfParameterVO(), vos);
    AggSalequotationHVO[] originBills = util.getOrginBills();
    AggSalequotationHVO[] clientBills = util.getClientFullInfoBill();
    this.checkBillStatus(originBills);
    for (AggSalequotationHVO bill : clientBills) {
      SalequotationHVO hvo = (SalequotationHVO) bill.getParent();
      if (hvo == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0020")/*@res "无效单据！"*/);
        continue;
      }
      hvo.setStatus(VOStatus.UPDATED);
    }
    AroundProcesser<AggSalequotationHVO> aroundProcesser =
        new AroundProcesser<AggSalequotationHVO>(null);
    aroundProcesser.before(clientBills);
    BillUpdate<AggSalequotationHVO> update =
        new BillUpdate<AggSalequotationHVO>();
    AggSalequotationHVO[] returnVos = update.update(clientBills, originBills);
    return returnVos;
  }

  private void checkBillStatus(AggSalequotationHVO[] clientBills) {
    for (AggSalequotationHVO bill : clientBills) {
      SalequotationHVO hvo = (SalequotationHVO) bill.getParent();
      if (hvo == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0020")/*@res "无效单据！"*/);
        continue;
      }
      int billStatus = hvo.getFstatusflag().intValue();
      if (billStatus != BillStatusEnum.C_AUDITING) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0022")/*@res "只有状态是自由态和审批中的单据才可以被审批"*/);
      }
    }
  }
}