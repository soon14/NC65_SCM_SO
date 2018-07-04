package nc.bs.so.salequotation.rule;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.vo.scmpub.res.billtype.SOBillType;

import nc.vo.pubapp.pub.power.BillPowerChecker;

import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * 销售报价单弃审检查该用户是否具有审批者权限
 * @scene
 * 销售报价单弃审前
 * @param
 * 无
 * @since 6.0
 * @version 2011-11-7 下午04:00:11
 * @author 王天文
 */
public class UnApproveHasApproPermisRule implements IRule<AggSalequotationHVO> {

  @Override
  public void process(AggSalequotationHVO[] vos) {
    for(AggSalequotationHVO vo : vos){
      this.validateApproPermis(vo);
    }
  }
  
  private void validateApproPermis(AggSalequotationHVO vo){
    
    boolean approPermis = 
      BillPowerChecker.hasApproverPermission(vo, SOBillType.SaleQuotation.getCode());
    if(!approPermis){
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().
          getStrByID("4006002_0", "04006002-0176")/*@res "不满足审核者权限。"*/);
    }    
  }
  
  
  

}
