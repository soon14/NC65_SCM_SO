package nc.pubitf.so.m30.ic.m4453;

import nc.vo.pub.BusinessException;

/**
 * 途损单回写销售订单服务接口
 * 
 * @since 6.0
 * @version 2011-5-6 上午09:10:39
 * @author 刘志伟
 */
public interface IRewrite30For4453 {

  /**
   * 途损单回写销售订单主数量
   * 
   * @param paras Rewrite4453Para
   * @throws BusinessException
   */
  void rewrite30NumFor4453(Rewrite4453Para[] paras) throws BusinessException;
}
