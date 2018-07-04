package nc.bs.so.m33.biz.m32.bp.cancelsquare;

import nc.bs.so.m33.biz.pub.cancelsquare.AbstractCancelSquareDetail;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareActionFactory;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;

public class CancelSquareInvDetailBP extends
    AbstractCancelSquareDetail<SquareInvDetailVO> {
  @Override
  protected ICancelSquareActionFactory<SquareInvDetailVO> 
  initCancelSquareActionFactory() {
    return new CancelSquareInvActionFactoryImpl();
  }

}
