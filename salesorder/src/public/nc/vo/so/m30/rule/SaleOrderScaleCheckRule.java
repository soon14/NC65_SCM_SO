package nc.vo.so.m30.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.scale.SaleOrderScaleProcessor;

/**
 * @description
 * 销售订单保存前精度检查
 * @scene
 * 销售订单新增、修改保存前
 * @param 
 * 无
 * @since 6.3
 * @version 2013-6-14 上午10:49:23
 * @author tianft
 */
public class SaleOrderScaleCheckRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    new SaleOrderScaleProcessor().checkBillPrecision(vos);
  }
}
