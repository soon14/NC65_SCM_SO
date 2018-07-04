package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.so.m30.revise.IM30ReviseMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.m30.util.FeatureSelectUtil;

/**
 * 销售订单修订保存
 * 
 * @since 6.0
 * @version 2011-8-9 下午04:22:50
 * @author fengjb
 */
public class N_30R_REVISEWRITE extends AbstractCompiler2 {

  /**
   * N_30_REVISEWRITE 构造子注解。
   */
  public N_30R_REVISEWRITE() {
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
      PfUserObject userObj = (PfUserObject) this.getUserObj();
      /** -------------- 修订保存动作 -------------- */
      SaleOrderHistoryVO[] inCurVOs = (SaleOrderHistoryVO[]) this.getVos();
      FeatureSelectUtil.fillAggffileVO(inCurVOs,userObj);
      IM30ReviseMaintain maintainsrv =
          NCLocator.getInstance().lookup(IM30ReviseMaintain.class);
     // ReviseSaveSaleOrderAction action = new ReviseSaveSaleOrderAction();
     // 调用新的方法，传入的bills 是销售订单修订历史vo
      SaleOrderHistoryVO[] ret = maintainsrv.reviseOrderHisVOSave(inCurVOs);
      /** -------------- 修订保存动作 -------------- */

      return ret;
    }
    catch (Exception ex) {

      ExceptionUtils.marsh(ex);
    }
    return null;
  }

}
