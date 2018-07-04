package nc.bs.so.salequotation.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * @description
 * 销售报价单提交前设置单据状态
 * @scene
 * 销售报价单提交前
 * @param
 * 无
 */
public class SalequoCommitStateChgRule implements IRule<AggSalequotationHVO> {

  @Override
  public void process(AggSalequotationHVO[] vos) {
    // TODO Auto-generated method stub
    if (vos == null) {
      return;
    }
    for (AggSalequotationHVO vo : vos) {
      vo.getParentVO().setStatus(VOStatus.UPDATED);
      ((SalequotationHVO) vo.getParentVO()).setFstatusflag(Integer
          .valueOf(BillStatusEnum.C_AUDITING));
    }
  }

}
