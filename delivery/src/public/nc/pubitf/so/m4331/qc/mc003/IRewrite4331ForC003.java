package nc.pubitf.so.m4331.qc.mc003;

import nc.vo.pub.BusinessException;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

/**
 * 质检单回写发货单
 * 
 * @since 6.0
 * @version 2010-12-20 下午04:27:58
 * @author 祝会征
 */
public interface IRewrite4331ForC003 {

  /**
   * 质检单审核回写发货单
   * 
   * @param vos
   * @throws BusinessException
   */
  void rewriteOnApprove(DeliveryCheckVO[] vos) throws BusinessException;

  /**
   * 质检单弃审回写发货单
   * 
   * @param vos
   * @throws BusinessException
   */
  void rewriteOnUnApprove(DeliveryCheckVO[] vos) throws BusinessException;

}
