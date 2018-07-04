package nc.pubitf.so.m30.it.m5801;

import nc.vo.pub.BusinessException;

/**
 * 进口合同拉销售订单回写接口
 * 
 * @since JCK 6.31
 * @version 2014-03-18 10:18:39
 * @author zhangyfr
 */
public interface IRewrite30For5801 {

  /**
   * 进口合同新增，修改，删除，修订回写销售订单累计安排进口合同数量
   * 
   * @param paras
   * @throws BusinessException
   */
  void rewriteNarrangeItcNumFor5801(Rewrite5801Para[] paras)
      throws BusinessException;
}
