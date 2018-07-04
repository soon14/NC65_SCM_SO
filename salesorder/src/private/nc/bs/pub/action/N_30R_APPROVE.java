package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.so.m30.action.main.ApproveSaleOrderReviseAction;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.credit.accountcheck.IIgnoreAccountCheck;
import nc.pubitf.credit.creditcheck.IAuditFlowFuncFlag;
import nc.pubitf.credit.creditcheck.IIgnoreCreditCheck;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * 销售订单修订审批
 * 
 * @since 6.36
 * @version 2014-12-26 下午2:49:00
 * @author wangshu6
 */
public class N_30R_APPROVE extends AbstractCompiler2 {

  public N_30R_APPROVE() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "\n";
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;

      SaleOrderHistoryVO[] inCurVOs = this.getVos();
      Object creditsrv = null;
      if (SysInitGroupQuery.isCREDITEnabled()) {
        creditsrv = NCLocator.getInstance().lookup(IAuditFlowFuncFlag.class);
        ((IAuditFlowFuncFlag) creditsrv).setAuditFlowFuncFlag();
      }
      /** -------------- 修订审批动作 -------------- */
      ApproveSaleOrderReviseAction action = new ApproveSaleOrderReviseAction();
      Object rts = action.approve(inCurVOs, this);
      /** -------------- 修订审批-------------- */

      if (SysInitGroupQuery.isCREDITEnabled() && null != creditsrv) {
        ((IAuditFlowFuncFlag) creditsrv).removeAuditFlowFuncFlag();
      }
      return rts;
    }
    catch (Exception ex) {

      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public SaleOrderHistoryVO[] getVos() {
    return (SaleOrderHistoryVO[]) super.getVos();
  }
}
