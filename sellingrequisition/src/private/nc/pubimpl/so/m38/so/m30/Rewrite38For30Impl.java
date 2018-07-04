package nc.pubimpl.so.m38.so.m30;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m38.plugin.ServicePlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m38.so.m30.rule.RewriteRowStateRule;
import nc.pubimpl.so.m38.so.m30.rule.RewriteSetArrInfoRule;
import nc.pubimpl.so.m38.so.m30.rule.RewriteToleranceCheck;
import nc.pubitf.so.m38.so.m30.IRewrite38For30;
import nc.pubitf.so.m38.so.m30.Rewrite30Para;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderViewVO;

/**
 * 销售订单回写预订单服务接口实现类。
 * 
 * @author 刘志伟
 * @since 6.0
 * @time 2010-04-08 上午09:27:07
 */
public class Rewrite38For30Impl implements IRewrite38For30 {

  @Override
  public void rewrite38NarrnumFor30(Rewrite30Para[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private void addRule(AroundProcesser<PreOrderViewVO> processer,
      PreOrderViewVO[] views) throws BusinessException {
    // 最先检查超预订单安排数量
    new RewriteToleranceCheck().process(views);

    // 执行前规则
    IRule<PreOrderViewVO> rule = new RewriteSetArrInfoRule();
    processer.addBeforeRule(rule);

    // 执行后规则
    rule = new RewriteRowStateRule();
    processer.addAfterRule(rule);
  }

  private String[] lockBills(Map<String, Rewrite30Para> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0",
            "04006012-0030")/*@res "销售订单回写预订单累计安排数量，锁预订单表体失败"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, Rewrite30Para> prepareParams(Rewrite30Para[] paras) {
    Map<String, Rewrite30Para> index = new HashMap<String, Rewrite30Para>();
    for (Rewrite30Para para : paras) {
      index.put(para.getCpreorderbid(), para);
    }
    return index;
  }

  private PreOrderViewVO[] query(Map<String, Rewrite30Para> index) {
    String[] ids = this.lockBills(index);
    ViewQuery<PreOrderViewVO> bo =
        new ViewQuery<PreOrderViewVO>(PreOrderViewVO.class);
    bo.setSharedHead(true);

    PreOrderViewVO[] views = bo.query(ids);
    if (views.length != index.size()) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0",
              "04006012-0031")/*@res "出现并发，请重新查询预订单"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }

  private void rewrite(Rewrite30Para[] paras) throws BusinessException {
    TimeLog.logStart();
    Map<String, Rewrite30Para> index = this.prepareParams(paras);
    // 此处设置session变量，以避免程序到处传递
    BSContext.getInstance().setSession(Rewrite30Para.class.getName(), index);
    TimeLog.info("并处理参数"); /*-=notranslate=-*/

    TimeLog.logStart();
    PreOrderViewVO[] views = this.query(index);
    TimeLog.info("查询预订单表体"); /*-=notranslate=-*/

    AroundProcesser<PreOrderViewVO> processer =
        new AroundProcesser<PreOrderViewVO>(
            ServicePlugInPoint.rewrite38NarrnumFor30);
    this.addRule(processer, views);

    TimeLog.logStart();
    processer.before(views);
    TimeLog.info("写数据库前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      PreOrderBVO.NARRNUM, PreOrderBVO.CARRANGEID, PreOrderBVO.DARRDATE
    };
    ViewUpdate<PreOrderViewVO> bo = new ViewUpdate<PreOrderViewVO>();
    views = bo.update(views, PreOrderBVO.class, names);
    TimeLog.info("更新数据库"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("写数据库后执行业务规则"); /*-=notranslate=-*/

    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(Rewrite30Para.class.getName());
  }
}
