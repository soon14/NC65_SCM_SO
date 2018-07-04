package nc.pubimpl.so.m30.so.m4331.rule.pf;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;
import nc.impl.pubapp.bill.rewrite.BillRewriteObject;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m30.pub.SORewriteParaForPFUtil;
import nc.pubimpl.so.m30.so.m4331.rule.RewriteSendStateRule;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * 发货单保存流程回写销售订单
 * @scene
 * 发货单保存流程回写销售订单累计发货数量后业务组扩展总后规则
 * @param
 * 无
 *
 * @since 6.5
 * @version 2015-10-19 下午5:38:07
 * @author zhangby5
 */
public class PFRewrite30AfterRule implements IRule<BillRewriteObject> {

  @Override
  public void process(BillRewriteObject[] rewriteObjs) {

    if (rewriteObjs == null || rewriteObjs.length == 0) {
      return;
    }

    SaleOrderVO[] currentTargetBills =
        (SaleOrderVO[]) rewriteObjs[0].getCurrentTargetBills();

    // 初始化回写视图VO
    SaleOrderViewVO[] rewriteViewVOs =
        SORewriteParaForPFUtil.initRewriteViewVOMap(rewriteObjs[0],
            currentTargetBills);

    if (rewriteViewVOs == null || rewriteViewVOs.length == 0) {
      return;
    }
    
    if (BSContext.getInstance().getSession(Rewrite4331Para.class.getName()) == null) {
      return;
    }

    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30SendNumFor4331);
    processer.after(rewriteViewVOs);

    // 更新信用调用后(必须放在前后rule最内层，防止和状态信用调用嵌套)
    new RenovateARByBidsEndRule(M30EngrossAction.M30SendReWrite)
        .process(rewriteViewVOs);
    new RewriteSendStateRule().process(rewriteViewVOs);
    BSContext.getInstance().removeSession(Rewrite4331Para.class.getName());
  }

}
