package nc.bs.so.m33.biz.m4c.bp.cancelsquare;

import nc.bs.so.m33.biz.pub.cancelsquare.AbstractCancelSquareDetail;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.trade.checkrule.VOChecker;

public class CancelSquareFor4CPubBP {

  public void cancelSquare(SquareOutDetailVO[] sqdvos, SquareOutVO[] sqvos) {

    if (!VOChecker.isEmpty(sqdvos)) {
      // 加锁
      new VOConcurrentTool().lock(sqdvos);

      // 设置本次取消结算数量
      SquareOutVO[] nsqvos =
          SquareOutVOUtils.getInstance().setNthisnumForCancelSquare(sqdvos,
              sqvos);

      // 将销售出库待结算单缓存
      BSContext.getInstance().setSession(SquareOutVO.class.getName(), nsqvos);

      AbstractCancelSquareDetail<SquareOutDetailVO> caction =
          new CancelSquareOutDetailBP();
      caction.cancelSquare(sqdvos, SquareOutDetailVO.FSQUARETYPE);

      // 释放缓存
      BSContext.getInstance().removeSession(SquareOutVO.class.getName());

      SquareOutVOUtils.getInstance().setNewTS(nsqvos, sqvos);
    }
  }

}
