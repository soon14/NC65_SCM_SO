package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 备注：报价单的弃审 单据动作执行中的动态执行类的动态执行类。
 * 
 * 创建日期：(2010-7-7)
 * 
 * @author 平台脚本生成
 */
public class N_4310_UNAPPROVE extends AbstractCompiler2 {
  /**
   * N_4310_UNAPPROVE 构造子注解。
   */
  public N_4310_UNAPPROVE() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "  Object retValue = null;\n      /*********************** 调用到货单"/*-=notranslate=-*/
        + "的弃审操作 不可修改 ******************/\n      "/*-=notranslate=-*/
        + "nc.vo.so.salequotation.entity.AggSalequotationHVO[] inObject = "
        + "(nc.vo.so.salequotation.entity.AggSalequotationHVO[]) getVos();\n"
        + "      retValue = nc.bs.framework.common.NCLocator.getInstance()."
        + "lookup(nc.itf.so.salequotation.ISalequotationService.class)\n    "
        + "      .unApprove(inObject, this);\n      /*************************"
        + "**********************************************/\n     "
        + " return retValue;\n";
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      Object retValue = null;
      /*********************** 调用到货单的弃审操作 不可修改 ******************/
      nc.vo.so.salequotation.entity.AggSalequotationHVO[] inObject =
          (nc.vo.so.salequotation.entity.AggSalequotationHVO[]) this.getVos();
      retValue =
          nc.bs.framework.common.NCLocator.getInstance()
              .lookup(nc.itf.so.salequotation.ISalequotationService.class)
              .unApprove(inObject, this);
      /***********************************************************************/
      return retValue;
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }
      throw new PFBusinessException(ex.getMessage(), ex);
    }
  }
}
