package nc.pubimpl.so.m30.so.balance.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.sobalance.util.GatherbillUtil;

/**
 * 
 * @description
 * 收款核销回写销售订单实际收款金额
 * @scene
 * 检查是否可以订单收款
 * @param
 * 无
 *
 * @since 6.0
 * @version 2011-6-7 上午10:25:07
 * @author 刘志伟
 */
public class CheckGatheringRule implements IRule<SaleOrderHVO> {

  @Override
  public void process(SaleOrderHVO[] heads) {
    /*收款限额控制预收“打勾”时：订单表头订单收款限额=实际预收款金额时不能再次收款
            收款限额控制预收“不打勾”时：订单表头的价税合计 = 实际收款金额时不能再次收款*/
    GatherbillUtil.getInstance().checkCanGathering(heads);
  }
}
