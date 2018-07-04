package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.so.m32.action.ApproveSaleInvoiceAction;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.so.pub.ref.ic.m4c.SOATPprocess;
import nc.pubitf.credit.accountcheck.IIgnoreAccountCheck;
import nc.pubitf.credit.creditcheck.IIgnoreCreditCheck;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.res.BusinessCheck;

/**
 * 销售发票审批
 * 
 * @since 6.0
 * @version 2011-9-23 上午09:42:48
 * @author 么贵敬
 */
public class N_32_APPROVE extends AbstractCompiler2 {

  /**
   * N_32_APPROVE 构造子注解。
   */
  public N_32_APPROVE() {
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
    ApproveSaleInvoiceAction action = new ApproveSaleInvoiceAction();
    try {
      return action.approve(this);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
