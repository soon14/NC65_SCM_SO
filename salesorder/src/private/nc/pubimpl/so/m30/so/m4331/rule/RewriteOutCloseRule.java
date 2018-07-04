package nc.pubimpl.so.m30.so.m4331.rule;

import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.bs.so.m30.state.SaleOrderStateMachine;
import nc.bs.so.m30.state.row.OutOpenState;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 
 * @description
 * 发货单回写销售订单
 * @scene
 * 发货单回写销售订单执行后打开出库关闭
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-12-13 下午02:41:06
 * @author 衣晓龙
 */
public class RewriteOutCloseRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {
    OutOpenState state = new OutOpenState();

    // 只有出库关闭为Y是才能将出库关闭打开
    if (null != vos && vos.length > 0
        && vos[0].getBody().getBboutendflag().booleanValue()) {
      SaleOrderStateMachine bo = new SaleOrderStateMachine();
      bo.setState(state, vos);
    }
  }

}
