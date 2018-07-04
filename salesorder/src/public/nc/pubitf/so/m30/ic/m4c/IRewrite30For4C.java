package nc.pubitf.so.m30.ic.m4c;

import nc.vo.pub.BusinessException;

/**
 * 销售出库单回写销售订单服务接口。
 * 销售出库单回写出库单应发、实发数量，是否强制行出库关闭。
 * 
 * @author 刘志伟
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public interface IRewrite30For4C {

  /**
   * 销售出库单回写销售订单累计应发、实发
   * 
   * @param paras Rewrite4CPara[]
   * @throws BusinessException
   */
  void rewrite30NumFor4C(Rewrite4CPara[] paras) throws BusinessException;
}
