package nc.pubitf.so.m33.ia;

import nc.vo.pub.BusinessException;
import nc.vo.so.m33.para.ReWrite4CParaForIA;

/**
 * 提供给存货核算回写销售出库单成本金额和成本单价的接口
 * 
 * @since 6.0
 * @version 2011-6-1 下午01:20:29
 * @author 么贵敬
 */
public interface IRewriteCostPriceSOForIA {
  /**
   * 回写销售出库单成本
   * 
   * @param paras
   */
  void set4CCostPrice(ReWrite4CParaForIA[] paras) throws BusinessException;

}
