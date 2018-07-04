package nc.bs.so.m30.rule.unapprove;

import nc.bs.so.m30.maintain.util.ApproveStateUtil;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * @description
 * 销售取消审批后更新订单相应的状态(发货、出库、开票、结算)
 * @scene 
 * 销售订单取消审批后
 * @param 
 * 无
 * @since 6.0
 * @version 2012-4-23 下午01:20:49
 * @author 么贵敬
 */
public class UNApproveStateRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    // 1.SaleOrderVO[] -> SaleOrderViewVO[]
    SaleOrderViewVO[] views = this.billToViewConvertor(vos);

    ApproveStateUtil approvestate = new ApproveStateUtil();
    // 2.处理发货状态
    approvestate.processSendState(views);
    // 3.处理出库状态
    approvestate.processOutState(views);
    // 4.处理开票状态
    approvestate.processInvoiceState(views);
    // 5.订单审批赠品行自动应收结算关闭
    approvestate.processARSettleOpenState(views);
    // 6.订单审批服务费用行自动成本结算关闭
    approvestate.processCostSettleOpenState(views);
  }

  private SaleOrderViewVO[] billToViewConvertor(SaleOrderVO[] vos) {
    BillToViewConvertor<SaleOrderVO, SaleOrderViewVO> convert =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    return convert.convert(vos);
  }

}
