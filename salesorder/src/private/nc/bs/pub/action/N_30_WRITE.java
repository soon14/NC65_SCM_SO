package nc.bs.pub.action;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.itf.so.m30.self.ISaleOrderScriptMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m30.entity.SaleOrderUserObject;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.scale.SaleOrderScaleProcessor;
import nc.vo.so.m30.util.FeatureSelectUtil;
import nc.vo.so.m4331.entity.DeliveryUserObject;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.scale.DeliveryScaleProcessor;

/**
 * 保存动作脚本 新增、修改保存都走这个脚本
 * 
 * @author 平台脚本生成
 * @since 6.0
 */
public class N_30_WRITE extends AbstractCompiler2 {
  /**
   * N_5X_APPROVE 构造子注解。
   */
  public N_30_WRITE() {
    super();
  }

  /* 备注：平台编写原始脚本 */
  @Override
  public String getCodeRemark() {
    return "\n";
  }

  /* 备注：平台编写规则类 接口执行类 */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      SaleOrderVO[] originBills =
          (SaleOrderVO[]) vo
              .getCustomProperty(PfParameterUtil.ORIGIN_VO_PARAMETER);
      super.m_tmpVo = vo;
      PfUserObject userObj = (PfUserObject) this.getUserObj();
      SaleOrderVO[] inCurVOs = (SaleOrderVO[]) this.getVos();
      FeatureSelectUtil.fillAggffileVO(inCurVOs,userObj);
      SaleOrderVO[] updatevos = this.getUpdateVO(inCurVOs);
      ISaleOrderScriptMaintain maintainsrv =
          NCLocator.getInstance().lookup(ISaleOrderScriptMaintain.class);
      

      if (updatevos != null && updatevos.length > 0) {
        vo.m_preValueVo = inCurVOs[0];
        vo.m_preValueVos = inCurVOs;
        return maintainsrv.saleOrderUpdate(inCurVOs, userObj, originBills);
      }
      SaleOrderVO[] insertvos = this.getInsertVO(inCurVOs);
      if (insertvos != null && insertvos.length > 0) {
        return maintainsrv.saleOrderInsert(inCurVOs, userObj);
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }
  


  private SaleOrderVO[] getInsertVO(SaleOrderVO[] orderVos) {
    if (orderVos == null || orderVos.length == 0) {
      return null;
    }
    List<SaleOrderVO> newVos = new ArrayList<SaleOrderVO>();
    for (SaleOrderVO vo : orderVos) {
      if (vo != null && vo.getParentVO() != null) {

        if (VOStatus.NEW == vo.getParentVO().getStatus()
            && vo.getParentVO().getCsaleorderid() != null
            || vo.getParentVO().getCsaleorderid() == null) {
          newVos.add(vo);
        }
      }
    }
    return newVos.toArray(new SaleOrderVO[newVos.size()]);
  }

  private SaleOrderVO[] getUpdateVO(SaleOrderVO[] orderVos) {

    if (orderVos == null || orderVos.length == 0) {
      return null;
    }
    List<SaleOrderVO> updateVos = new ArrayList<SaleOrderVO>();
    for (SaleOrderVO vo : orderVos) {
      if (vo != null && vo.getParentVO() != null) {

        if (VOStatus.UPDATED == vo.getParentVO().getStatus()
            || vo.getParentVO().getCsaleorderid() != null
            && VOStatus.NEW != vo.getParentVO().getStatus()) {
          updateVos.add(vo);
        }
      }
    }
    return updateVos.toArray(new SaleOrderVO[updateVos.size()]);
  }

}
