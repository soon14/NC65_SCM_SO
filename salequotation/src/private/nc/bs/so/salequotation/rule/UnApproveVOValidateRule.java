package nc.bs.so.salequotation.rule;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 报价单弃审状态校验
 * 
 * @since 6.1
 * @version 2013-06-04 10:34:54
 * @author yixl
 */
public class UnApproveVOValidateRule implements IRule<AggSalequotationHVO> {

  @Override
  public void process(AggSalequotationHVO[] vos) {
    // TODO Auto-generated method stub
    this.billStatusCheck(vos);
  }

  private void billStatusCheck(AggSalequotationHVO[] vos) {
    for (AggSalequotationHVO vo : vos) {
      // 状态是自由态的单据不能取消审批也不能收回
      SalequotationHVO header = vo.getParentVO();
      if (header.getFstatusflag()
          .equals(Integer.valueOf(BillStatusEnum.C_FREE))) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006009_0", "04006009-0039")/*@res "单据状态不正确，不能操作！"*/);
      }
    }
  }
}
