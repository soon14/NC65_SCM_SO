package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.so.m4331.action.maintain.DeliveryApproveAction;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.credit.accountcheck.IIgnoreAccountCheck;
import nc.pubitf.credit.creditcheck.IAuditFlowFuncFlag;
import nc.pubitf.credit.creditcheck.IIgnoreCreditCheck;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.res.BusinessCheck;

public class N_4331_APPROVE extends AbstractCompiler2 {

  /**
   * N_32_APPROVE 构造子注解。
   */
  public N_4331_APPROVE() {
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
  public Object runComClass(PfParameterVO vo) {
    super.m_tmpVo = vo;
    Object rets = null;
    try {
      if (SysInitGroupQuery.isCREDITEnabled()) {
        IAuditFlowFuncFlag service =
            NCLocator.getInstance().lookup(IAuditFlowFuncFlag.class);
        service.setAuditFlowFuncFlag();
        DeliveryApproveAction action = new DeliveryApproveAction();
        rets = action.approve(this);
        service.removeAuditFlowFuncFlag();
      }
      else {
        DeliveryApproveAction action = new DeliveryApproveAction();
        rets = action.approve(this);
      }

    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return rets;

  }
}
