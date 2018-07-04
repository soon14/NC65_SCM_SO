package nc.pubitf.so.m30.so.balance;

import nc.vo.pub.BusinessException;

/**
 * 财务核销监听回写销售订单服务接口
 * 
 * @since 6.0
 * @version 2011-5-28 下午01:01:26
 * @author 刘志伟
 */
public interface IRewrite30ForVerify {
  /**
   * 核销监听时回写销售订单表体行收款金额(累计收款核销金额)
   * 
   * @param paras RewriteBalancePara[]
   * @throws BusinessException
   */
  void rewrite30TotalPayMnyVerifyListener(RewriteVerifyPara[] paras)
      throws BusinessException;
}
