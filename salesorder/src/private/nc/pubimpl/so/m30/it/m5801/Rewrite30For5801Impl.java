package nc.pubimpl.so.m30.it.m5801;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.pubitf.so.m30.it.m5801.IRewrite30For5801;
import nc.pubitf.so.m30.it.m5801.Rewrite5801Para;

import nc.bs.so.m30.plugin.ServicePlugInPoint;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

import nc.pubimpl.so.m30.it.m5801.rule.RewriteCheckArrangeNumRule;
import nc.pubimpl.so.m30.it.m5801.rule.RewriteSetNumRule;

/**
 * 进口合同拉销售订单回写接口的实现类
 * 
 * @since JCK 6.31
 * @version 2014-03-19 14:32:15
 * @author zhangyfr
 */
public class Rewrite30For5801Impl implements IRewrite30For5801 {

  @Override
  public void rewriteNarrangeItcNumFor5801(Rewrite5801Para[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }

  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer,
      SaleOrderViewVO[] views) {
    // -------- 执行前规则 ----------------

    // 最先检查发货容差
    new RewriteCheckArrangeNumRule().process(views);

    // 设置累计发货数量
    processer.addBeforeRule(new RewriteSetNumRule());

    // -------- 执行后规则 ----------------

  }

  private String[] lockBills(Map<String, Rewrite5801Para> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0483")/*@res "进口合同回写销售订单累计安排进口合同数量，锁销售订单表体失败"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, Rewrite5801Para> prepareParams(Rewrite5801Para[] paras) {
    Map<String, Rewrite5801Para> index = new HashMap<String, Rewrite5801Para>();
    for (Rewrite5801Para para : paras) {
      String key = para.getCsaleorderbid();
      if (index.containsKey(key)) {
        UFDouble num = this.GetNoNullDouble(para.getNchangenum());
        num = num.add(this.GetNoNullDouble(index.get(key).getNchangenum()));
        Rewrite5801Para newpara = new Rewrite5801Para(key, num);
        index.remove(key);
        index.put(key, newpara);
      }
      else {
        index.put(para.getCsaleorderbid(), para);
      }
    }
    return index;
  }

  private UFDouble GetNoNullDouble(UFDouble value) {
    if (value == null) {
      return UFDouble.ZERO_DBL;
    }
    return value;

  }

  private SaleOrderViewVO[] query(Map<String, Rewrite5801Para> index) {
    String[] ids = this.lockBills(index);
    ViewQuery<SaleOrderViewVO> bo =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    bo.setSharedHead(true);

    SaleOrderViewVO[] views = bo.query(ids);
    if (views.length != index.size()) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
              "04006011-0171")/*@res "出现并发，请重新查询销售订单"*/;
      //可能销售订单修订已经审批通过已经回写完成，查出来的就是空直接返回views就行了，后续有问题再说吧
      return views;
      //ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }

  private void rewrite(Rewrite5801Para[] paras) {

    Map<String, Rewrite5801Para> index = this.prepareParams(paras);
    // 此处设置session变量，以避免程序到处传递
    BSContext.getInstance().setSession(Rewrite5801Para.class.getName(), index);
    TimeLog.info("处理参数"); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleOrderViewVO[] views = this.query(index);
    TimeLog.info("查询销售订单表体"); /*-=notranslate=-*/

    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30ArrangeItcNumFor5801);
    this.addRule(processer, views);

    TimeLog.logStart();
    processer.before(views);
    TimeLog.info("写数据库前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names =
        new String[] {
          SaleOrderBVO.NARRANGEITCNUM, SaleOrderBVO.BARRANGEDFLAG,
          SaleOrderBVO.CARRANGEPERSONID, SaleOrderBVO.TLASTARRANGETIME
        };
    ViewUpdate<SaleOrderViewVO> bo = new ViewUpdate<SaleOrderViewVO>();
    views = bo.update(views, SaleOrderBVO.class, names);
    TimeLog.info("更新数据库"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("写数据库后执行业务规则"); /*-=notranslate=-*/

    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(Rewrite5801Para.class.getName());
  }

}
