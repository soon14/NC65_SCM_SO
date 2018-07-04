package nc.pubimpl.so.m4331.ic.m4c;

import nc.pubitf.so.m4331.ic.m4c.IRewrite4331For4C;
import nc.pubitf.so.m4331.ic.m4c.RewritePara4331For4C;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 销售出库单回写发货单累计出库数量
 * 
 * @author 祝会征
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public class Rewrite4331For4CImpl implements IRewrite4331For4C {

  @Override
  public void rewrite4331OutNumFor4C(RewritePara4331For4C[] paras)
      throws BusinessException {
    try {
      new Rewrite4331For4CBO().rewrite4331OutNumFor4C(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

}
