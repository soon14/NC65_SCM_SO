package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.itf.so.m4331.IDeliveryScriptMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.uap.pf.PFBusinessException;

public class N_4331_WRITE extends AbstractCompiler2 {
  /**
   * N_32_WRITE 的构造子
   */
  public N_4331_WRITE() {
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
      DeliveryVO[] originBills =
          (DeliveryVO[]) vo
              .getCustomProperty(PfParameterUtil.ORIGIN_VO_PARAMETER);
      DeliveryVO[] vos = (DeliveryVO[]) this.getVos();
      PfUserObject userObj = (PfUserObject) this.getUserObj();
      if (vos != null && vos.length > 0) {
        vo.m_preValueVo = vos[0];
        vo.m_preValueVos = vos;
      }
      IDeliveryScriptMaintain maintainsrv =
          NCLocator.getInstance().lookup(IDeliveryScriptMaintain.class);
      return maintainsrv.deliveryWrite(vos, userObj, originBills);
    }
    catch (Exception e) {
      if (e instanceof BusinessException) {
        throw (BusinessException) e;
      }
      throw new PFBusinessException(e.getMessage(), e);
    }
  }
}
