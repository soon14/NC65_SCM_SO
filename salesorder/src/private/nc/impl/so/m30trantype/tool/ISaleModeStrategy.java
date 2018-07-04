package nc.impl.so.m30trantype.tool;

import nc.vo.so.m30trantype.enumeration.SaleMode;


/**
 * 销售订单交易类型属性 销售模式 可修改范围接口
 * 
 * @since 6.36
 * @version 2015-1-15 下午4:57:19
 * @author wangshu6
 */
public interface ISaleModeStrategy {
  SaleMode[] getParseSaleMode();
}
