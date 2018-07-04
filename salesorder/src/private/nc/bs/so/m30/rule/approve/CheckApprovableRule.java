package nc.bs.so.m30.rule.approve;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 
 * @description
 * 根据销售订单状态验证是否可以审批
 * @scene
 * 销售订单审批前
 * @param
 * 无
 */
public class CheckApprovableRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] bills) {
    for (SaleOrderVO bill : bills) {
      SaleOrderHVO header = bill.getParentVO();
      if (!BillStatus.FREE.equalsValue(header.getFstatusflag())
          && !BillStatus.AUDITING.equalsValue(header.getFstatusflag())) {

        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0304")/*单据为非自由状态或审批中状态，不可进行审批！*/);
      }
    }
  }

}
