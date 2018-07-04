package nc.pubimpl.so.m4331.to.m5x;

import nc.pubimpl.so.m4331.pub.PubDeleteOrCloseFor305XOutClose;
import nc.pubitf.so.m4331.pub.RewritePara4331;
import nc.pubitf.so.m4331.to.m5x.IDeleteOrCloseFor5XOutClose;
import nc.vo.pub.BusinessException;

/**
 * 销售订单或者调拨订单发货关闭
 * 
 * @author 祝会征
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public class DeleteOrCloseFor5XOutCloseImpl implements
    IDeleteOrCloseFor5XOutClose {

  @Override
  public void rewrite(RewritePara4331[] paras) throws BusinessException {
    new PubDeleteOrCloseFor305XOutClose().rewrite(paras);
  }
}
