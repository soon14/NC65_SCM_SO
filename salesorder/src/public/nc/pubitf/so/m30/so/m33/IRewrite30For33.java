package nc.pubitf.so.m30.so.m33;

import nc.vo.pub.BusinessException;

/**
 * 销售应收结算回写销售订单服务接口
 * 
 * @since 6.0
 * @version 2011-5-6 上午09:32:37
 * @author 刘志伟
 */
public interface IRewrite30For33 {

  /**
   * 销售应收结算回写销售订单累计应收结算数量、累计应收结算金额
   * 
   * @paras paras Rewrite33Para[]
   * @throws BusinessException
   */
  void rewrite30ARFor33(Rewrite33Para[] paras) throws BusinessException;

  /**
   * 销售应收结算回写销售订单累计应收结算数量、累计应收结算金额
   * 
   * @paras paras Rewrite33Para[]
   * @throws BusinessException
   */
  void rewrite30ETFor33(Rewrite33Para[] paras) throws BusinessException;

  /**
   * 销售应收结算回写销售订单累计应收结算数量、累计应收结算金额
   * 
   * @paras paras Rewrite33Para[]
   * @throws BusinessException
   */
  void rewrite30IAFor33(Rewrite33Para[] paras) throws BusinessException;

  /**
   * 销售应收结算回写销售订单累计应收结算数量、累计应收结算金额
   * 
   * @paras paras Rewrite33Para[]
   * @throws BusinessException
   */
  void rewrite30OutRushFor33(Rewrite33Para[] paras) throws BusinessException;
}
