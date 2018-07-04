package nc.pubitf.so.m30.so.balance;

import nc.vo.pub.BusinessException;

/**
 * 订单核销回写销售订单服务接口
 * 
 * @since 6.0
 * @version 2011-5-28 下午01:01:26
 * @author 刘志伟
 */
public interface IRewrite30ForBalance {

  /**
   * 收款核销回写销售订单实际收款金额
   * 
   * @param paras RewriteBalancePara[]
   * @throws BusinessException
   */
  void rewrite30ReceivedMnyForBalance(RewriteBalancePara[] paras)
      throws BusinessException;
}
