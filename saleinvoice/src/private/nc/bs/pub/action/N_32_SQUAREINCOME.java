package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.RemoteFormSOUtil;

/**
 * 自动收入结算脚本
 * 
 * @since 6.0
 * @version 2011-6-21 下午01:21:34
 * @author 么贵敬
 */
public class N_32_SQUAREINCOME extends AbstractCompiler2 {

  /**
   * N_32_SQUAREINCOME 的构造子
   */
  public N_32_SQUAREINCOME() {
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
      Object[] inCurObjects = this.getVos();
      if (inCurObjects == null) {
        inCurObjects = new SaleInvoiceVO[] {
          (SaleInvoiceVO) this.getVo()
        };
      }

      int ilength = inCurObjects.length;
      SaleInvoiceVO[] voInvoices = new SaleInvoiceVO[ilength];
      for (int i = 0; i < ilength; i++) {
        voInvoices[i] = (SaleInvoiceVO) inCurObjects[i];
      }

      RemoteFormSOUtil.autoSquareIncomeSrv(voInvoices);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
