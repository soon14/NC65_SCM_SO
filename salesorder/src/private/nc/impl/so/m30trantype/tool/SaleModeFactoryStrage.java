package nc.impl.so.m30trantype.tool;

import nc.vo.so.m30trantype.enumeration.SaleMode;

/**
 * 销售订单交易类型销售模式可修改范围
 * 如果有新的可修改范围，则只需要在条件判断中、ISaleModeStrategy的实现类分别添加即可。
 * 
 * @since 6.36
 * @version 2015-1-15 下午5:00:24
 * @author wangshu6
 */
public class SaleModeFactoryStrage {

  /**
   * 根据原销售模式进行匹配，
   * 
   * @param straregy 原销售模式
   * @return 对应的销售模式应对类（其中含有可修改范围数组）
   */
  public ISaleModeStrategy creatSaleModeStrategy(Object straregy) {
    // 如果是普通的销售模式，
    if (straregy.equals(SaleMode.MODE_COMMON.getIntegerValue())) {
      return new CommonSaleModeStrategyImpl();
    }
    // 如果是退货的销售模式，
    else if (straregy.equals(SaleMode.MODE_RETURN.getIntValue())) {
      return new ReturnSaleModeStrategyImpl();
    }
    // 如果是退换货的销售模式，
    else if (straregy.equals(SaleMode.MODE_RETURNCHANGE.getIntValue())) {
      return new ReturnExchangeSaleModeStrategyImpl();
    }
    return null;
  }

}
