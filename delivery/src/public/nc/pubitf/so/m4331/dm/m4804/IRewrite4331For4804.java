package nc.pubitf.so.m4331.dm.m4804;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * 运输单回写发货单
 * 
 * @since 6.0
 * @version 2011-3-17 下午01:51:26
 * @author 祝会征
 */
public interface IRewrite4331For4804 {
  /**
   * 运输单手动关闭、打开发货单运输状态
   * 
   * @param stateMap key发货单表体id UFBoolean 运输状态
   * @throws BusinessException
   */
  void renovateState(String[] bids, UFBoolean state) throws BusinessException;

  /**
   * 运输单新增 修改 删除 回写发货单累计运输数量
   * 
   * @param paras
   * @throws BusinessException
   */
  void rewriteTransnum(RewritePara4331For4804[] paras) throws BusinessException;
}
