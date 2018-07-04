package nc.impl.so.m30.revise.action.rule;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * ReviseSaveSaleOrderAction:修订保存前填充数据规则
 * @scene
 * 销售订单修订保存前
 * @param
 * 无
 * @since 6.0
 * @version 2011-8-26 上午09:38:33
 * @author 刘志伟
 */
public class FillupDataRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] bills) {
    for (SaleOrderVO bill : bills) {
      // 填充修订信息
      this.fillUpReviseInfo(bill);
    }
  }

  private void fillUpReviseInfo(SaleOrderVO bill) {
    SaleOrderHVO head = bill.getParentVO();
    InvocationInfoProxy proxy = InvocationInfoProxy.getInstance();
    String userId = proxy.getUserId();
    head.setCreviserid(userId);
    head.setTrevisetime(TimeUtils.getsrvBaseDate());
  }

}
