package nc.bs.so.m30.maintain.util;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class SOStateUtil {

  /**
   * 判断销售订单视图VO是否进行过费用冲抵或者赠品兑付：
   * 1.费用冲抵且费用冲抵金额不为空或0，
   * 2.赠品兑付
   * true:没有进行过上面两种情况，不需要回写
   * false:需要回写
   * 
   * @param vo
   * @return
   */
  public static boolean isNotOffsetAndlrgcash(SaleOrderViewVO vo) {
    SaleOrderBVO bvo = vo.getBody();
    UFBoolean blrgcashflag = bvo.getBlrgcashflag();
    UFDouble norigsubmny = bvo.getNorigsubmny();
    if ((blrgcashflag != null && blrgcashflag.booleanValue())
        || (!MathTool.isZero(norigsubmny) && !blrgcashflag.booleanValue())) {
      return false;
    }
    return true;
  }

}
