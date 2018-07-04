package nc.bs.so.m33.biz.m4453.bp.cancelsquare;

import nc.bs.so.m33.biz.pub.cancelsquare.AbstractCancelSquareDetail;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;

public class CancelSquareWasDetailPubFor4453BP {

  /**
   * 方法功能描述：途损单取消结算公共BP
   * 
   * @param sqdvos
   *          -- 取消结算的途损结算单
   * @param vos
   *          ----- 取消结算的途损待结算单
   *          <p>
   * @author zhangcheng
   * @time 2010-9-28 上午10:13:59
   */
  public void cancelSquare(SquareWasDetailVO[] sqdvos, SquareWasVO[] vos) {
    // 存在结算单(明细数据)
    if (sqdvos != null) {
      // 加锁
      new VOConcurrentTool().lock(sqdvos);

      // 设置本次取消结算数量
      SquareWasVOUtils.getInstance().setNthisnumForCancelSquare(sqdvos, vos);

      // 将途损单结算单缓存
      BSContext.getInstance().setSession(SquareWasVO.class.getName(), vos);

      AbstractCancelSquareDetail<SquareWasDetailVO> caction =
          new CancelSquareWasDetailBP();
      caction.cancelSquare(sqdvos, SquareWasDetailVO.FSQUARETYPE);

      // 释放缓存
      BSContext.getInstance().removeSession(SquareWasVO.class.getName());
    }

  }

}
