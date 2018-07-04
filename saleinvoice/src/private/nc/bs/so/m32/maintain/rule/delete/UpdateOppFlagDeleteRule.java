package nc.bs.so.m32.maintain.rule.delete;

import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.enumeration.OpposeFlag;

import nc.bs.so.m32.maintain.rule.util.UpdateSrcOppFlagUtil;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * 对冲生成发票删除时更新来源发票对冲标志
 * @scene
 * 销售对冲生成发票删除保存后
 * @param
 * 无
 * @since 6.3
 * @version 2012-12-21 上午09:05:15
 * @author yaogj
 */
public class UpdateOppFlagDeleteRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    UpdateSrcOppFlagUtil updateOppUtil = new UpdateSrcOppFlagUtil();
    updateOppUtil.updateSrcOppFlag(vos, OpposeFlag.NORMAL);

  }

}
