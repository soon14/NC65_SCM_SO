package nc.pubimpl.so.m30.so.m4331;

import java.util.HashMap;
import java.util.Map;

import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.pubitf.so.m30.so.m4331.IRewrite30For4331;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

import nc.pubimpl.so.m30.so.m4331.rule.RewriteOutCloseRule;
import nc.pubimpl.so.m30.so.m4331.rule.RewritePriceNumRule;
import nc.pubimpl.so.m30.so.m4331.rule.RewriteSendStateRule;
import nc.pubimpl.so.m30.so.m4331.rule.RewriteSetNumRule;
import nc.pubimpl.so.m30.so.m4331.rule.RewriteToleranceCheck;

/**
 * 发货单回写销售订单服务接口实现类。
 * 
 * @author 刘志伟
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public class Rewrite30For4331Impl implements IRewrite30For4331 {

  @Override
  public void rewrite30SendNumFor4331(Rewrite4331Para[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer,
      SaleOrderViewVO[] views, UFBoolean bboutendflag) throws BusinessException {
    // -------- 执行前规则 ----------------

    // 最先检查发货容差
    new RewriteToleranceCheck().process(views);

    // 设置累计发货数量
    processer.addBeforeRule(new RewriteSetNumRule());
    
    // 设置促销价格表 jilu for 恒安限量促销
    processer.addBeforeRule(new RewritePriceNumRule());

    // 更新信用调用前(必须放在前后rule最内层，防止和状态信用调用嵌套)
    processer.addBeforeRule(new RenovateARByBidsBeginRule(
        M30EngrossAction.M30SendReWrite));

    // -------- 执行后规则 ----------------
    // 更新信用调用后(必须放在前后rule最内层，防止和状态信用调用嵌套)
    processer.addAfterRule(new RenovateARByBidsEndRule(
        M30EngrossAction.M30SendReWrite));

    // 执行后行状态规则
    processer.addAfterRule(new RewriteSendStateRule());
    // 出库打开时更新表体行出库关闭
    if (!bboutendflag.booleanValue()) {
      processer.addAfterRule(new RewriteOutCloseRule());
    }
  }

  private String[] lockBills(Map<String, Rewrite4331Para> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0189")/*@res "发货单回写销售订单累计发货数量，锁销售订单表体失败"*/;
    locker.lock(bids, message);
    return bids;
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

  private SaleOrderViewVO[] query(Map<String, Rewrite4331Para> index) {
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

  private void rewrite(Rewrite4331Para[] paras) throws BusinessException {
    UFBoolean bboutendflag = paras[0].getBboutendflag();

    Map<String, Rewrite4331Para> index = this.prepareParams(paras);
    // 此处设置session变量，以避免程序到处传递
    BSContext.getInstance().setSession(Rewrite4331Para.class.getName(), index);
    TimeLog.info("并处理参数"); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleOrderViewVO[] views = this.query(index);
    TimeLog.info("查询销售订单表体"); /*-=notranslate=-*/

    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30SendNumFor4331);
    this.addRule(processer, views, bboutendflag);

    TimeLog.logStart();
    processer.before(views);
    TimeLog.info("写数据库前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      SaleOrderBVO.NTOTALSENDNUM
    };
    ViewUpdate<SaleOrderViewVO> bo = new ViewUpdate<SaleOrderViewVO>();
    views = bo.update(views, SaleOrderBVO.class, names);
    TimeLog.info("更新数据库"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("写数据库后执行业务规则"); /*-=notranslate=-*/

    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(Rewrite4331Para.class.getName());
  }

}
