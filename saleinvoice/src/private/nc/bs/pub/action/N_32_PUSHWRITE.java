package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 销售发票推式保存实现
 * 
 * @since 6.0
 * @version 2011-1-26 上午09:18:22
 * @author 冯加滨
 */
public class N_32_PUSHWRITE extends AbstractCompiler2 {

  /**
   * N_32_PUSHWRITE 的构造子
   */
  public N_32_PUSHWRITE() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "  \n";
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    super.m_tmpVo = vo;
    try {
      Object[] inCurObjects = this.getPfParameterVO().m_preValueVos;
      // if (null == inCurObjects) {
      // ExceptionUtils
      // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
      // .getStrByID("4006008_0", "04006008-0011")/*@res
      // "错误：您希望推式保存的销售发票没有数据"*/);
      // return null;
      // }
      // if (!(inCurObjects instanceof SaleInvoiceVO[])) {
      //
      // ExceptionUtils
      // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
      // .getStrByID("4006008_0", "04006008-0012")/*@res
      // "错误：您希望推式保存的销售发票类型不匹配"*/);
      //
      // }

      int ilength = inCurObjects.length;
      SaleInvoiceVO[] voInvoices = new SaleInvoiceVO[ilength];
      for (int i = 0; i < ilength; i++) {
        voInvoices[i] = (SaleInvoiceVO) inCurObjects[i];
      }
      return PfServiceScmUtil.processBatch("WRITE",
          SOBillType.Invoice.getCode(), voInvoices, null, null);
    }
    catch (Exception e) {
      if (e instanceof BusinessException) {
        throw (BusinessException) e;
      }
      throw new PFBusinessException(e.getMessage(), e);
    }
  }
}
