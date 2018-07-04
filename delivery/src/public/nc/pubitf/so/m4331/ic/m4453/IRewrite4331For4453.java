package nc.pubitf.so.m4331.ic.m4453;

import nc.vo.pub.BusinessException;

public interface IRewrite4331For4453 {
  /**
   * 方法功能描述：回写发货单上的累计途损数量。
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras
   * @throws BusinessException
   *           <p>
   * @author 祝会征
   * @time 2010-3-23 下午06:38:40
   */
  void rewrite(RewritePara4331For4453[] paras) throws BusinessException;
}
