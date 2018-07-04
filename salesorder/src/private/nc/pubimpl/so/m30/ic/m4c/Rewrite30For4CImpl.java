package nc.pubimpl.so.m30.ic.m4c;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubimpl.so.m30.ic.m4c.rule.Rewrite35WhenOutNumChange;
import nc.pubimpl.so.m30.ic.m4c.rule.RewriteExchangeOutRule;
import nc.pubimpl.so.m30.ic.m4c.rule.RewriteOPCOutNumRule;
import nc.pubimpl.so.m30.ic.m4c.rule.RewriteOutNumRule;
import nc.pubimpl.so.m30.ic.m4c.rule.RewriteOutStateRule;
import nc.pubimpl.so.m30.ic.m4c.rule.RewritePriceNumRule;
import nc.pubimpl.so.m30.ic.m4c.rule.RewriteSetNumRule;
import nc.pubimpl.so.m30.ic.m4c.rule.RewriteToleranceCheck;
import nc.pubimpl.so.m30.ic.m4c.rule.RewriteZ3ByRowStateRule;
import nc.pubitf.so.m30.ic.m4c.IRewrite30For4C;
import nc.pubitf.so.m30.ic.m4c.Rewrite4CPara;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 出库回写销售订单累计出库数量实现
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * 
 * @version 6.0
 * @author 刘志伟
 * @time 2010-7-12 下午04:50:48
 */
public class Rewrite30For4CImpl implements IRewrite30For4C {

  @Override
  public void rewrite30NumFor4C(Rewrite4CPara[] paras) throws BusinessException {
    try {
      this.rewrite(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer,
      SaleOrderViewVO[] views) throws BusinessException {
    // -------- 执行前规则 ----------------

    // 先检查出库容差范围
    new RewriteToleranceCheck().process(views);
    processer.addBeforeRule(new RewriteOutNumRule());
    processer.addBeforeRule(new RewriteExchangeOutRule());
    // 执行前最后设置累计出库数量
    processer.addBeforeRule(new RewriteSetNumRule());
    processer.addBeforeRule(new Rewrite35WhenOutNumChange());
    // 设置销售价格限量促销执行量 jilu for 恒安限量促销
    processer.addBeforeRule(new RewritePriceNumRule());
    // end
    // 更新信用调用前(必须放在前后rule最内层，防止和状态信用调用嵌套)
    processer.addBeforeRule(new RenovateARByBidsBeginRule(
        M30EngrossAction.M30OutReWrite));

    // -------- 执行后规则 ----------------

    // 更新信用调用后(必须放在前后rule最内层，防止和状态信用调用嵌套)
    processer.addAfterRule(new RenovateARByBidsEndRule(
        M30EngrossAction.M30OutReWrite));

    // 为了通知StateCalculateUtil.isAutoTransitInvoiceOpen是自动回写调用的，而非行手工打开
    // 此处非常别扭，作为临时处理，以后版本对状态机的使用重新考虑
    BSContext.getInstance().setSession(Rewrite30For4CImpl.class.getName(),
        UFBoolean.TRUE);
    // 回写电子销售:累计出库数量
    if(SysInitGroupQuery.isOPCEnabled()){
      processer.addAfterRule(new RewriteOPCOutNumRule());
    }
    processer.addAfterRule(new RewriteOutStateRule());

    // 回写销售订单数量时，如果订单行关闭还要回写上游合同
    processer.addAfterRule(new RewriteZ3ByRowStateRule());
    
  }

  private String[] lockBills(Map<String, Rewrite4CPara> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0180")/*@res "库存销售出库单回写销售订单累计出库数量，锁销售订单表体失败"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, Rewrite4CPara> prepareParams(Rewrite4CPara[] paras) {
    Map<String, Rewrite4CPara> index = new HashMap<String, Rewrite4CPara>();
    for (Rewrite4CPara para : paras) {
      index.put(para.getCsaleorderbid(), para);
    }
    return index;
  }

  private SaleOrderViewVO[] query(Map<String, Rewrite4CPara> index) {
    String[] ids = this.lockBills(index);
    ViewQuery<SaleOrderViewVO> bo =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    bo.setSharedHead(true);

    SaleOrderViewVO[] views = bo.query(ids);
    if (views.length != index.size()) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
              "04006011-0171")/*@res "出现并发，请重新查询销售订单"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }

  private void rewrite(Rewrite4CPara[] paras) throws BusinessException {
    TimeLog.logStart();
    Map<String, Rewrite4CPara> index = this.prepareParams(paras);
    // 此处设置session变量，以避免程序到处传递
    BSContext.getInstance().setSession(Rewrite4CPara.class.getName(), index);
    TimeLog.info("并处理参数"); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleOrderViewVO[] views = this.query(index);
    TimeLog.info("查询销售订单表体"); /*-=notranslate=-*/

    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30NumFor4C);
    this.addRule(processer, views);

    TimeLog.logStart();
    processer.before(views);
    TimeLog.info("写数据库前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      SaleOrderBVO.NTOTALNOTOUTNUM, SaleOrderBVO.NTOTALOUTNUM
    };
    ViewUpdate<SaleOrderViewVO> bo = new ViewUpdate<SaleOrderViewVO>();
    views = bo.update(views, SaleOrderBVO.class, names);
    TimeLog.info("更新数据库"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("写数据库后执行业务规则"); /*-=notranslate=-*/

    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(Rewrite4CPara.class.getName());
    BSContext.getInstance().removeSession(Rewrite30For4CImpl.class.getName());
  }

}
