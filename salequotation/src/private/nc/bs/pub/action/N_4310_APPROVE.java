package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.so.salequotation.bp.SalequoApproveBP;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：报价单的审核 单据动作执行中的动态执行类的动态执行类。
 * 
 * 创建日期：(2010-7-7)
 * 
 * @author 平台脚本生成
 */
public class N_4310_APPROVE extends AbstractCompiler2 {
  /**
   * N_4310_APPROVE 构造子注解。
   */
  public N_4310_APPROVE() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "  nc.vo.so.salequotation.entity.AggSalequotationHVO[] inObject"
        + "  =(nc.vo.so.salequotation.entity.AggSalequotationHVO[])getVos ();"
        + "\nObject retValue=nc.bs.framework.common.NCLocator.getInstance()"
        + ".lookup(nc.itf.so.salequotation.ISalequotationService.class)."
        + "approve(inObject, this);\nreturn retValue;\n";
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      nc.vo.so.salequotation.entity.AggSalequotationHVO[] inObject =
          (nc.vo.so.salequotation.entity.AggSalequotationHVO[]) this.getVos();
      Object retValue = new SalequoApproveBP().approve(inObject, this);
      return retValue;
    }
    catch (Exception ex) {
      throw new PFBusinessException(ex.getMessage(), ex);
    }
  }
}
