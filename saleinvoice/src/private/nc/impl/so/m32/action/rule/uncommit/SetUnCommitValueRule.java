package nc.impl.so.m32.action.rule.uncommit;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * 销售发票收回前设置相关字段值
 * @scene
 * 销售发票收回前
 * @param
 * 无
 * @since 6.0
 * @version 2011-2-22 上午11:01:45
 * @author 么贵敬
 */
public class SetUnCommitValueRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    for (SaleInvoiceVO voInvoice : vos) {
      voInvoice.getParentVO().setStatus(VOStatus.UPDATED);
      voInvoice.getParentVO().setFstatusflag((Integer) BillStatus.FREE.value());
    }
  }

}
