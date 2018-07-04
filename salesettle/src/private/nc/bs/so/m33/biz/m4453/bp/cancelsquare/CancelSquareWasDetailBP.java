package nc.bs.so.m33.biz.m4453.bp.cancelsquare;

import nc.bs.so.m33.biz.pub.cancelsquare.AbstractCancelSquareDetail;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareActionFactory;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;

/**
 * 出库单取消结算BP
 */
public class CancelSquareWasDetailBP extends
    AbstractCancelSquareDetail<SquareWasDetailVO> {
  @Override
  protected ICancelSquareActionFactory<SquareWasDetailVO> 
  initCancelSquareActionFactory() {
    return new CancelSquareWasActionFactoryImpl();
  }

}
