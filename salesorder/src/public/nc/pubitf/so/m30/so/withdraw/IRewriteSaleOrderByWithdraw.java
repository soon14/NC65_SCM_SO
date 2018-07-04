package nc.pubitf.so.m30.so.withdraw;

import nc.vo.pub.BusinessException;

/**
 * 销售退货订单回写销售订单服务接口。
 * 
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public interface IRewriteSaleOrderByWithdraw {

  void rewrite30NumForWithdraw(Rewrite30Para[] paras) throws BusinessException;

}
