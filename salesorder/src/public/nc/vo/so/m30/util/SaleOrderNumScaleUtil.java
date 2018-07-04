package nc.vo.so.m30.util;

import nc.itf.scmpub.reference.uap.bd.measuredoc.MeasureDocService;
import nc.vo.so.m30.entity.SaleOrderBVO;

/**
 * 销售订单主辅数量换算精度处理工具类
 * @author zhangby5
 *
 */
public class SaleOrderNumScaleUtil {
  
  /**
   * 返回当前表体VO的辅单位对应的精度，若该精度为空，则取报价数量的精度
   * @param body 销售订单表体VO
   * @return
   */
  public static int getNumPower(SaleOrderBVO body) {

    Integer[] scale = MeasureDocService.getMeasPrecision(new String[] {
      body.getCastunitid()
    });
    if (scale != null && scale.length > 0) {
      return scale[0].intValue();
    }

    return body.getNqtunitnum().getPower();
  }
  
  /**
   * 获取报价单位数量的精度，若报价单位精度为空则取报价数量精度
   * @param body
   * @return
   */
  public static int getNqtunitnumPower(SaleOrderBVO body) {

    Integer[] scale = MeasureDocService.getMeasPrecision(new String[] {
      body.getCqtunitid()
    });
    if (scale != null && scale.length > 0) {
      return scale[0].intValue();
    }
    return body.getNqtunitnum().getPower();
  }
  
}
