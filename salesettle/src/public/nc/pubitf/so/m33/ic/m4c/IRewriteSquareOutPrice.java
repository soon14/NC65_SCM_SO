package nc.pubitf.so.m33.ic.m4c;

import nc.vo.pub.BusinessException;

/**
 * 当销售出库单价格被修改的时候，同时修改与之对应的销售待结算单的价格
 * 同步销售出库单和销售出库待结算单
 * @since 6.0
 * @version 2011-9-26 下午07:57:29
 * @author zhangcheng
 */
public interface IRewriteSquareOutPrice {
  
  /**
   * 当销售出库单价格被修改的时候，同时修改与之对应的销售待结算单的价格
   * @param RewritePara33For4C 参数中4个原币价格、4个本币价格必须全部填写 
   * @throws BusinessException
   */
  void rewriteSquareOutPrice(RewritePara33For4C[] paras) throws BusinessException;
  
}
