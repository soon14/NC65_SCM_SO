package nc.bs.so.m33.biz.m4c.bp.cancelsquare;

import nc.bs.so.m33.biz.pub.cancelsquare.AbstractCancelSquareDetail;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareActionFactory;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;

/**
 * 出库单取消结算BP
 */
public class CancelSquareOutDetailBP extends
    AbstractCancelSquareDetail<SquareOutDetailVO> {
  @Override
  protected ICancelSquareActionFactory<SquareOutDetailVO> 
  initCancelSquareActionFactory() {
    return new CancelSquareOutActionFactoryImpl();
  }

}
