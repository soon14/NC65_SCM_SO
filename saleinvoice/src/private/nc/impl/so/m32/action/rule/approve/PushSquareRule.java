package nc.impl.so.m32.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.RemoteFormSOUtil;

/**
 * @description
 * 销售发票审批后自动推式生成销售结算单
 * @scene
 * 销售发票审批后
 * @param
 * 无
 * @since 6.0
 * @version 2011-11-9 上午10:47:02
 * @author 么贵敬
 */
public class PushSquareRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    RemoteFormSOUtil.pushSquareSrv(vos);
  }

}
