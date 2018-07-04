package nc.bs.so.m30.rule.rewrite.m4331;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m4331.pub.RewritePara4331;
import nc.pubitf.so.m4331.so.m30.IDeleteOrCloseFor30OutClose;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * @description
 * 销售订单手工出库关闭回写发货单规则
 * <p>
 * <b>规则处理以下两种情况：</b>
 * <ul>
 * <li>出库关闭时：自由状态的发货单需要删除
 * <li>出库关闭时：审批状态的发货单需要整单关闭
 * <li>...
 * </ul>
 * @scene
 * 销售订单手工出库关闭动作后
 * @param 
 * 
 * @version 6.0
 * @author 刘志伟
 * @time 2010-8-17 下午03:49:11
 */
public class Rewrite4331WhenOutCloseRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] views) {
    int length = views.length;
    RewritePara4331[] paras = new RewritePara4331[length];
    for (int i = 0; i < length; i++) {
      paras[i] = new RewritePara4331(views[i].getBody().getCsaleorderbid());
    }

    IDeleteOrCloseFor30OutClose api =
        NCLocator.getInstance().lookup(IDeleteOrCloseFor30OutClose.class);
    try {
      api.rewrite(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
