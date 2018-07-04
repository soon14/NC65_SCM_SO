package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.so.m32.action.UnApproveSaleInvoiceAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 发票弃审操作
 * 
 * @since 6.0
 * @version 2011-10-28 上午09:19:05
 * @author 么贵敬
 */
public class N_32_UNAPPROVE extends AbstractCompiler2 {

  /**
   * N_32_UNAPPROVE 构造子注解。
   */
  public N_32_UNAPPROVE() {
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
      UnApproveSaleInvoiceAction action = new UnApproveSaleInvoiceAction();
      return action.unapprove(this);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
