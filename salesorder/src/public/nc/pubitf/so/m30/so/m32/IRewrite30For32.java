package nc.pubitf.so.m30.so.m32;

import nc.vo.pub.BusinessException;

/**
 * 销售发票回写销售订单服务接口。
 * 
 * @author 刘志伟
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public interface IRewrite30For32 {

  /**
   * 销售发票回写销售订单服务接口。
   * 
   * @param paras Rewrite32Para[]
   * @throws BusinessException
   */
  void rewrite30NumFor32(Rewrite32Para[] paras) throws BusinessException;

}
