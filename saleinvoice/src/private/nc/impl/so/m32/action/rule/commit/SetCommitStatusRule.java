package nc.impl.so.m32.action.rule.commit;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * @description
 * 销售发票送审前设置VO状态
 * @scene
 * 销售发票送审前
 * @param
 * 无
 * @since 6.0
 * @version 2011-7-13 下午07:18:56
 * @author 么贵敬
 */
public class SetCommitStatusRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    for (SaleInvoiceVO voInvoice : vos) {
      voInvoice.getParentVO().setStatus(VOStatus.UPDATED);
    }
  }

}
