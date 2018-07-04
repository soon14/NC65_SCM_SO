package nc.pubitf.so.m30.so.m4331;

import nc.vo.pub.BusinessException;

/**
 * 发货单回写销售订单服务接口。
 * 
 * @author 刘志伟
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public interface IRewrite30For4331 {

  /**
   * 发货单回写销售订单累计发货数量
   * 
   * @param paras Rewrite4331Para[]
   * @throws BusinessException
   */
  void rewrite30SendNumFor4331(Rewrite4331Para[] paras)
      throws BusinessException;
}
