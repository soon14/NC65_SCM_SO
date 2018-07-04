package nc.pubimpl.so.m30.ic.m4480;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m30.ic.m4480.rule.RewriteCheckRule;
import nc.pubimpl.so.m30.ic.m4480.rule.RewriteSetNumRule;
import nc.pubitf.so.m30.ic.m4480.IRewrite30For4480;
import nc.pubitf.so.m30.ic.m4480.Rewrite4480Para;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class Rewrite30For4480Impl implements IRewrite30For4480 {

  @Override
  public void rewrite30ReqrsNumFor4480(Rewrite4480Para[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer) {
    processer.addBeforeRule(new RewriteCheckRule());
    processer.addBeforeRule(new RewriteSetNumRule());
  }

  private String[] lockBills(Map<String, Rewrite4480Para> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0177")/*@res "预留回写销售订单预留数量，锁销售订单表体失败"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, Rewrite4480Para> prepareParams(Rewrite4480Para[] paras) {
    Map<String, Rewrite4480Para> index = new HashMap<String, Rewrite4480Para>();
    for (Rewrite4480Para para : paras) {
      index.put(para.getCsaleorderbid(), para);
    }
    return index;
  }

  private SaleOrderViewVO[] query(Map<String, Rewrite4480Para> index) {
    String[] ids = this.lockBills(index);
    ViewQuery<SaleOrderViewVO> bo =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    bo.setSharedHead(true);

    SaleOrderViewVO[] views = bo.query(ids);
    // if (views.length != index.size()) {
    // String message =
    // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0171")/*@res
    // "出现并发，请重新查询销售订单"*/;
    // ExceptionUtils.wrappBusinessException(message);
    // }
    return views;
  }

  private void rewrite(Rewrite4480Para[] paras) {
    TimeLog.logStart();
    Map<String, Rewrite4480Para> index = this.prepareParams(paras);
    // 此处设置session变量，以避免程序到处传递
    BSContext.getInstance().setSession(Rewrite4480Para.class.getName(), index);
    TimeLog.info("并处理参数"); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleOrderViewVO[] views = this.query(index);
    TimeLog.info("查询销售订单表体"); /*-=notranslate=-*/

    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30ReqrsNumFor4480);
    this.addRule(processer);

    TimeLog.logStart();
    processer.before(views);
    TimeLog.info("写数据库前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      SaleOrderBVO.NREQRSNUM
    };
    ViewUpdate<SaleOrderViewVO> bo = new ViewUpdate<SaleOrderViewVO>();
    views = bo.update(views, SaleOrderBVO.class, names);
    TimeLog.info("更新数据库"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("写数据库后执行业务规则"); /*-=notranslate=-*/

    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(Rewrite4480Para.class.getName());
  }

}
