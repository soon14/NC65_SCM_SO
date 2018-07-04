package nc.vo.so.pub.rule;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 退货换标记判断规则(询促销价、订单后台保存等地方使用)
 * 
 * @since 6.36
 * @version 2015-5-26 下午2:56:22
 * @author 刘景
 */
public class FretexchangeRule {

  private IKeyValue keyValue;

  /**
   * 构造方法
   * 
   * @param keyValue
   */
  public FretexchangeRule(
      IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 获取退换货标记
   * 
   * @param row 表体行
   * @return
   */
  public Integer getFretexchange(int row) {
    Integer ret = null;
    UFBoolean bdiscountflag =
        keyValue.getBodyUFBooleanValue(row, SOItemKey.BDISCOUNTFLAG);
    UFDouble nastnum = keyValue.getBodyUFDoubleValue(row, SOItemKey.NASTNUM);

    //前台已确认销售模式后台不干预
    Integer fretexchange = keyValue.getBodyIntegerValue(row, "fretexchange");
    if (fretexchange != null) {
      return fretexchange;
    }

    // 数量为负数
    if (MathTool.greaterThan(UFDouble.ZERO_DBL, nastnum)) {
      if (bdiscountflag != null && bdiscountflag.booleanValue()) {
        // 对于折扣类物料而言，如果是负数，则认为其是正常，636增加
        ret = Fretexchange.COMMON.getIntegerValue();
      }
      else {
        ret = Fretexchange.WITHDRAW.getIntegerValue();
      }
    }
    // 数量为正数
    else if (MathTool.lessThan(UFDouble.ZERO_DBL, nastnum)) {
      if (bdiscountflag != null && bdiscountflag.booleanValue()) {
        // 对于折扣类物料而言，如果是正数，则认为其是退货，636增加
        ret = Fretexchange.WITHDRAW.getIntegerValue();
      }
      else {
        // 正数不是换货 就是非退换货
        if (Fretexchange.EXCHANGE.equalsValue(fretexchange)) {
          ret = fretexchange;
        }
        else {
          ret = Fretexchange.COMMON.getIntegerValue();
        }
      }
    }
    // 数量为零标记非退换货
    else {
      ret = Fretexchange.COMMON.getIntegerValue();
    }
    return ret;
  }
}
