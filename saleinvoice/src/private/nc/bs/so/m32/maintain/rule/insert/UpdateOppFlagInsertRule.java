package nc.bs.so.m32.maintain.rule.insert;

import nc.bs.so.m32.maintain.rule.util.UpdateSrcOppFlagUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.enumeration.OpposeFlag;

/**
 * @description
 * 销售发票保存后： <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>对冲生成发票保存后更新来源发票对冲标志字段
 * </ul>
 * <p>
 * @scene
 * 对冲生成发票保存后
 * @param
 * 无
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-1-21 下午04:59:51
 */
public class UpdateOppFlagInsertRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    UpdateSrcOppFlagUtil updateOppUtil = new UpdateSrcOppFlagUtil();
    updateOppUtil.updateSrcOppFlag(vos, OpposeFlag.FINSH);
  }

}
