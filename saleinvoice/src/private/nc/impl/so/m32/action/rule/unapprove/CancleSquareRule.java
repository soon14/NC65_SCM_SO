package nc.impl.so.m32.action.rule.unapprove;

import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.RemoteFormSOUtil;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * 销售发票弃审后销售发票取消结算规则
 * @scene
 * 销售发票弃审后
 * @param
 * 无
 * @since 6.1
 * @version 2012-12-21 上午09:21:23
 * @author yaogj
 */
public class CancleSquareRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {

    RemoteFormSOUtil.cancelSquareSrv(vos);

  }

}
