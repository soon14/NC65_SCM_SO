package nc.bs.so.salequotation.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class ApproveVOValidateRule implements IRule<AggSalequotationHVO> {

  @Override
  public void process(AggSalequotationHVO[] vos) {
    // TODO Auto-generated method stub
    // 单据状态合法性检查
    this.statusCheck(vos);
  }

  private void statusCheck(AggSalequotationHVO[] vos) {
    for (AggSalequotationHVO vo : vos) {
      SalequotationHVO header = (SalequotationHVO) vo.getParentVO();
      // 状态是自由或审批中才能进行审批
      if (!header.getFstatusflag().equals(
          Integer.valueOf(BillStatusEnum.C_FREE))
          && !header.getFstatusflag().equals(
              Integer.valueOf(BillStatusEnum.C_AUDITING))) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006009_0", "04006009-0066", null, new String[]{})/*单据状态不正确，不能审批！*/);
      }
    }
  }
}
