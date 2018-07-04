package nc.pubitf.so.m4331.qc.mc001;

import nc.vo.pub.BusinessException;

/**
 * 报检单回写发货单累计报检数量和是否报检标识
 * 
 * @since 6.0
 * @version 2010-12-20 下午03:46:18
 * @author 祝会征
 */
public interface IRewrite4331ForC001 {
  void rewriteForC001(RewritePara4331ForC001[] paras) throws BusinessException;
}
