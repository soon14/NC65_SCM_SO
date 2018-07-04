package nc.pubimpl.so.m30.so.m4331.rule.pf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.impl.pubapp.bill.rewrite.BillRewriteObject;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m30.pub.SORewriteParaForPFUtil;
import nc.pubimpl.so.m30.so.m4331.rule.RewritePriceNumRule;
import nc.pubimpl.so.m30.so.m4331.rule.RewriteToleranceCheck;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * 发货单保存流程回写销售订单累计发货数量前业务组扩展总前规则
 * 
 * @author zhangby5
 * 
 */
public class PFRewrite30BeforeRule implements IRule<BillRewriteObject> {

  @Override
  public void process(BillRewriteObject[] rewriteObjs) {

    if (rewriteObjs == null || rewriteObjs.length == 0) {
      return;
    }

    SaleOrderVO[] originTargetBills =
        (SaleOrderVO[]) rewriteObjs[0].getOriginTargetBills();

    // 初始化回写视图VO
    SaleOrderViewVO[] rewriteViewVOs =
        SORewriteParaForPFUtil.initRewriteViewVOMap(rewriteObjs[0],
            originTargetBills);

    if (rewriteViewVOs == null || rewriteViewVOs.length == 0) {
      return;
    }

    // 设置回写参数到session中
    this.setRewrite4331ParaSession(rewriteObjs);

    if (BSContext.getInstance().getSession(Rewrite4331Para.class.getName()) == null) {
      return;
    }

    try {
      AroundProcesser<SaleOrderViewVO> processer =
          new AroundProcesser<SaleOrderViewVO>(
              ServicePlugInPoint.rewrite30SendNumFor4331);
      processer.before(rewriteViewVOs);
      // 最先检查发货容差
      new RewriteToleranceCheck().process(rewriteViewVOs);
      // 设置促销价格表 jilu for 恒安限量促销
      new RewritePriceNumRule().process(rewriteViewVOs);
      // 更新信用调用前(必须放在前后rule最内层，防止和状态信用调用嵌套)
      new RenovateARByBidsBeginRule(M30EngrossAction.M30SendReWrite)
          .process(rewriteViewVOs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

  private void setRewrite4331ParaSession(BillRewriteObject[] rewriteObjs) {

    DeliveryVO[] deliveryVOs =
        (DeliveryVO[]) rewriteObjs[0].getCurrentSrcBills();
    // 当删除动作时，流程平台传入的动作后的原始VO为空，需要区动作前的原始VO
    if (deliveryVOs == null || deliveryVOs.length == 0) {
      deliveryVOs = (DeliveryVO[]) rewriteObjs[0].getOriginSrcBills();
    }

    RewritePara[] rewriteParas =
        SORewriteParaForPFUtil.builderRewritePara(rewriteObjs[0]);

    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    for (DeliveryVO vo : deliveryVOs) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        map.put(bvo.getCsrcbid(), bvo.getBclosesrcflag());
      }
    }

    int size = rewriteParas.length;
    List<Rewrite4331Para> parasList = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      // 流程平台设置来源单据子表ID为来源行号
      String bid = rewriteParas[i].getCsrcbid();
      if (PubAppTool.isNull(bid)) {
        continue;
      }
      UFDouble nnum = rewriteParas[i].getNnum();
      UFBoolean closeflag = map.get(bid);
      parasList.add(new Rewrite4331Para(bid, nnum, closeflag, UFBoolean.TRUE));
    }
    if (parasList.size() == 0) {
      return;
    }

    Map<String, Rewrite4331Para> index =
        this.prepareParams(parasList.toArray(new Rewrite4331Para[parasList
            .size()]));
    // 此处设置session变量，以避免程序到处传递
    BSContext.getInstance().setSession(Rewrite4331Para.class.getName(), index);
  }

  private Map<String, Rewrite4331Para> prepareParams(Rewrite4331Para[] paras) {
    Map<String, Rewrite4331Para> index = new HashMap<String, Rewrite4331Para>();
    for (Rewrite4331Para para : paras) {
      String key = para.getCsaleorderbid();
      if (index.containsKey(key)) {
        UFDouble num = this.GetNoNullDouble(para.getNchangenum());
        num = num.add(this.GetNoNullDouble(index.get(key).getNchangenum()));
        Rewrite4331Para newpara =
            new Rewrite4331Para(key, num, para.getBclosed(),
                para.getBboutendflag());
        index.remove(key);
        index.put(key, newpara);
        continue;
      }
      index.put(para.getCsaleorderbid(), para);
    }
    return index;
  }

  private UFDouble GetNoNullDouble(UFDouble value) {
    if (value == null) {
      return UFDouble.ZERO_DBL;
    }
    return value;

  }

}
