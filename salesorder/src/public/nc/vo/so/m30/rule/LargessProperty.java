package nc.vo.so.m30.rule;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.enumeration.Largesstype;

/**
 * 
 * @since 6.0
 * @version 2010-11-11 上午11:05:16
 * @author 苏建文
 */

public class LargessProperty {

  /**
   * 判断订单行是否赠品
   * 
   * @param bvo
   * @return
   */
  public boolean isLargess(SaleOrderBVO bvo) {
    boolean result = false;
    UFBoolean largessflag = bvo.getBlargessflag();
    Integer largesstypeflag = bvo.getFlargesstypeflag();
    if ((largessflag != null) && (largessflag.booleanValue())) {
      result = true;
    }
    else {
      if ((largesstypeflag != null)
          && (largesstypeflag.equals(Largesstype.APPORTIONLARGESS.value()))) {
        result = true;
      }
    }

    return result;
  }

  /**
   * 判断订单行是否进行过赠品价格分摊
   * 
   * @param bvo
   * @return
   */
  public boolean isLargessApportion(SaleOrderBVO bvo) {
    boolean result = false;
    Integer largesstypeflag = bvo.getFlargesstypeflag();
    if (largesstypeflag == null) {
      result = false;
    }
    else if (largesstypeflag.equals(Largesstype.NOAPPORTION.value())) {
      result = false;
    }
    else {
      result = true;
    }

    return result;
  }

  /**
   * 根据表体行判断订单是否进行了赠品价格分摊
   * 
   * @param saleordervo
   * @return
   */
  public boolean isSaleOrderApportion(SaleOrderVO saleordervo) {
    boolean result = false;
    SaleOrderBVO[] bvos = saleordervo.getChildrenVO();
    for (SaleOrderBVO bvo : bvos) {
      result = this.isLargessApportion(bvo);
      if (result) {
        break;
      }
    }
    return result;
  }
}
