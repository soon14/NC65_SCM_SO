package nc.pubimpl.so.m30.so.m32;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m30.so.m32.rule.RewriteInvoiceStateRule;
import nc.pubimpl.so.m30.so.m32.rule.RewriteSetNumRule;
import nc.pubimpl.so.m30.so.m32.rule.RewriteToleranceCheck;
import nc.pubitf.so.m30.so.m32.IRewrite30For32;
import nc.pubitf.so.m30.so.m32.Rewrite32Para;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 销售发票回写销售订单服务接口实现类。
 *
 * @author 刘志伟
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public class Rewrite30For32Impl implements IRewrite30For32 {

  @Override
  public void rewrite30NumFor32(Rewrite32Para[] paras) throws BusinessException {
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

    // 最先检查开票数量
    new RewriteToleranceCheck().process(views);
    // 执行前最后设置开票数量
    processer.addBeforeRule(new RewriteSetNumRule());

    // -------- 执行后规则 ----------------

    // 执行后开票状态规则
    processer.addAfterRule(new RewriteInvoiceStateRule());

  }

  private String[] lockBills(Map<String, Rewrite32Para> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0186")/*@res "销售发票回写销售订单累计开票数量，锁销售订单表体失败"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, Rewrite32Para> prepareParams(Rewrite32Para[] paras) {
    Map<String, Rewrite32Para> index = new HashMap<String, Rewrite32Para>();
    for (Rewrite32Para para : paras) {
      index.put(para.getCsaleorderbid(), para);
    }
    return index;
  }

  private SaleOrderViewVO[] query(Map<String, Rewrite32Para> index) {
    String[] ids = this.lockBills(index);
    ViewQuery<SaleOrderViewVO> bo =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    bo.setSharedHead(true);

    SaleOrderViewVO[] views = bo.query(ids);
    if (views.length != index.size()) {
      String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0171")/*@res "出现并发，请重新查询销售订单"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }

  private void rewrite(Rewrite32Para[] paras) throws BusinessException {
    TimeLog.logStart();
    Map<String, Rewrite32Para> index = this.prepareParams(paras);
    // 此处设置session变量，以避免程序到处传递
    BSContext.getInstance().setSession(Rewrite32Para.class.getName(), index);
    TimeLog.info("并处理参数"); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleOrderViewVO[] views = this.query(index);
    TimeLog.info("查询销售订单表体"); /*-=notranslate=-*/

    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30NumFor32);
    this.addRule(processer, views);

    TimeLog.logStart();
    processer.before(views);
    TimeLog.info("写数据库前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      SaleOrderBVO.NTOTALINVOICENUM
    };
    ViewUpdate<SaleOrderViewVO> bo = new ViewUpdate<SaleOrderViewVO>();
    views = bo.update(views, SaleOrderBVO.class, names);
    TimeLog.info("更新数据库"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("写数据库后执行业务规则"); /*-=notranslate=-*/

    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(Rewrite32Para.class.getName());
  }

}