package nc.pubitf.so.m4331.ic.m4480;

import nc.vo.pub.BusinessException;

/**
 * 预留回写发货单
 * 
 * @since 6.0
 * @version 2011-3-24 下午03:43:20
 * @author 祝会征
 */
public interface IRewrite4331For4480 {
  void rewrite4331ReqrsNum(RewritePara4331For4480[] paras)
      throws BusinessException;
}
