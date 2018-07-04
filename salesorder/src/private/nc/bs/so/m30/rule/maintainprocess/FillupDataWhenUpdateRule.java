package nc.bs.so.m30.rule.maintainprocess;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * UpdateSaleOrderAction：保存前填充修补数据规则
 * @scene
 * 销售订单修改保存前
 * @param
 * 无
 * @since 6.0
 * @version 2011-8-25 下午08:25:29
 * @author 刘志伟
 */
public class FillupDataWhenUpdateRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] bills) {
    for (SaleOrderVO bill : bills) {
      this.cleanUpApproveInfo(bill);
    }
  }

  /**
   * 为审批不通过的修改单据动作清空审批信息
   * 此逻辑不能放在UpdateSaleOrderBP中，因为修订调用BP时不需要清空审批信息的
   */
  private void cleanUpApproveInfo(SaleOrderVO bill) {
    SaleOrderHVO head = bill.getParentVO();
    head.setApprover(null);
    head.setTaudittime(null);
  }

}
