package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.RemoteFormSOUtil;

/**
 * 自动成本结算
 * 
 * @since 6.0
 * @version 2011-9-15 下午12:31:05
 * @author 么贵敬
 */
public class N_32_SQUARECOST extends AbstractCompiler2 {

  /**
   * N_32_SQUARECOST 的构造子
   */
  public N_32_SQUARECOST() {
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

      RemoteFormSOUtil.autoSquareCostSrv(voInvoices);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
