package nc.vo.so.m32.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.vo.so.m32.scale.SaleInvoiceScaleProcessor;


/**
 * @description
 * 销售发票保存前校验精度
 * @scene
 * 销售发票新增、修改保存前
 * @param
 * 无
 */
public class SaleInvoiceScaleCheckRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    new SaleInvoiceScaleProcessor().checkBillPrecision(vos);
  }

}
