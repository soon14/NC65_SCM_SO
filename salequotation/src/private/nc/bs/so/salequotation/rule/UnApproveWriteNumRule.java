package nc.bs.so.salequotation.rule;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;

import nc.pubitf.so.m30.so.m4310.IQuery30For4310;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * 销售报价单弃审检查表体行生成合同或订单的次数，如果已经生成合同或订单，则单据应该不能被弃审
 * @scene
 * 销售报价单弃审前
 * @param
 * 无
 * @author chenyyb
 */
public class UnApproveWriteNumRule implements IRule<AggSalequotationHVO> {

  @Override
  public void process(AggSalequotationHVO[] vos) {
    for (AggSalequotationHVO vo : vos) {
      this.validateWriteNum(vo);
    }
  }

  /**
   * 
   * @param vo
   */
  private void validateWriteNum(Object vo) {
    AggSalequotationHVO aggVO = (AggSalequotationHVO) vo;
    if (aggVO != null) {
      SalequotationBVO[] bvos = aggVO.getChildrenVO();
      if (bvos != null) {
        this.validateWriteNum(bvos);
      }
      String salequotation = aggVO.getParentVO().getPk_salequotation();
      this.validateIsHasNextOrder(new String[] {
        salequotation
      });
    }
  }

  /**
   * 校验是否有下游订单
   */
  private void validateIsHasNextOrder(String[] quotationhids) {
    IQuery30For4310 querySerivice =
        NCLocator.getInstance().lookup(IQuery30For4310.class);
    try {
      Map<String, UFBoolean> map30exit =
          querySerivice.isExistNextOrder(quotationhids);
      UFBoolean isnextorder = map30exit.get(quotationhids[0]);

      if (isnextorder.booleanValue()) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006009_0", "04006009-0079")/*@res "报价单已经生成下游订单，不能弃审!"*/);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 校验生成合同和生成订单的回写次数
   * 
   * @param bvos
   */
  private void validateWriteNum(SalequotationBVO[] bvos) {
    for (int i = 0; i < bvos.length; i++) {
      SalequotationBVO bvo = bvos[i];
      if (bvo.getNordernum() != null && bvo.getNordernum().doubleValue() > 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006009_0", "04006009-0010")/*@res "累计生成订单数大于零的单据不能弃审!"*/);
      }
      if (bvo.getNcontractnum() != null
          && bvo.getNcontractnum().doubleValue() > 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006009_0", "04006009-0011")/*@res "累计生成合同数大于零的单据不能弃审!"*/);
      }
    }
  }
}
