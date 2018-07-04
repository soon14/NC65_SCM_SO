package nc.impl.so.m30trantype.tool;

import nc.vo.so.m30trantype.enumeration.SaleMode;

/**
 * 销售模式可以从“退货”修改成“普通+退货”
 * 
 * @since 6.36
 * @version 2015-1-15 下午2:55:03
 * @author wangshu6
 */
public class ReturnSaleModeStrategyImpl implements ISaleModeStrategy {

  /**
   * 如果交易模式为“退货”允许修改成“普通+退货”
   */
  @Override
  public SaleMode[] getParseSaleMode() {
    return new SaleMode[]{SaleMode.MODE_COMMONRETURN};

  }

}
