package nc.pubitf.so.m32.so.m33;

import nc.vo.pub.BusinessException;

public interface IRewrite32For33 {

  void reWriteBalNumMny(RewritePara32For33[] paras) throws BusinessException;

  /**
   * 财务核销的时候 回写收款金额
   * 
   * @param paras
   * @throws BusinessException
   */
  void reWritePaymnyOnVerfy(RewritePara32For33OnVerify[] paras)
      throws BusinessException;

}
