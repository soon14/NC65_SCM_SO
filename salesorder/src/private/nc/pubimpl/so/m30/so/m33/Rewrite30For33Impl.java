package nc.pubimpl.so.m30.so.m33;

import java.util.HashMap;
import java.util.Map;

import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.pubitf.so.m30.so.m33.IRewrite30For33;
import nc.pubitf.so.m30.so.m33.Rewrite33Para;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

import nc.pubimpl.so.m30.so.m33.rule.RewriteInvoiceStateRule;

/**
 * 回写
 * 
 * @since 6.1
 * @version 2013-03-27 11:03:36
 * @author yixl
 */
public class Rewrite30For33Impl implements IRewrite30For33 {

  @Override
  public void rewrite30ARFor33(Rewrite33Para[] paras) throws BusinessException {
    try {

      Map<String, Rewrite33Para> index = this.prepareParams(paras);
      BSContext.getInstance().setSession(Rewrite33Para.class.getName(), index);
      // 查询销售订单表体
      SaleOrderViewVO[] views = this.query(index);

      AroundProcesser<SaleOrderViewVO> processer =
          new AroundProcesser<SaleOrderViewVO>(
              ServicePlugInPoint.rewrite30ARFor33);
      this.addRule(processer, M30EngrossAction.M30ArReWrite);
      // 执行前规则
      processer.before(views);

      // 设置应收结算数量、金额
      this.setARNumMny(views, index);
      String[] names = new String[] {
        SaleOrderBVO.NTOTALARNUM, SaleOrderBVO.NTOTALARMNY
      };
      this.rewrite(views, names);

      // 执行后规则
      processer.after(views);
      // 此处释放session变量，以免浪费内存
      BSContext.getInstance().removeSession(Rewrite33Para.class.getName());
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public void rewrite30ETFor33(Rewrite33Para[] paras) throws BusinessException {
    Map<String, Rewrite33Para> index = this.prepareParams(paras);
    BSContext.getInstance().setSession(Rewrite33Para.class.getName(), index);
    // 查询销售订单表体
    SaleOrderViewVO[] views = this.query(index);

    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30ETFor33);
    this.addRule(processer, M30EngrossAction.M30EstArReWrite);
    // 执行前规则
    processer.before(views);

    // 设置应收结算数量、金额
    this.setETNumMny(views, index);
    String[] names = new String[] {
      SaleOrderBVO.NTOTALESTARMNY, SaleOrderBVO.NTOTALESTARNUM
    };
    this.rewrite(views, names);

    // 执行后规则
    processer.after(views);
    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(Rewrite33Para.class.getName());
  }

  @Override
  public void rewrite30IAFor33(Rewrite33Para[] paras) throws BusinessException {
    Map<String, Rewrite33Para> index = this.prepareParams(paras);
    BSContext.getInstance().setSession(Rewrite33Para.class.getName(), index);
    // 查询销售订单表体
    SaleOrderViewVO[] views = this.query(index);

    // 设置应收结算数量
    this.setCostNum(views, index);

    String[] names = new String[] {
      SaleOrderBVO.NTOTALCOSTNUM
    };
    this.rewrite(views, names);

    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(Rewrite33Para.class.getName());
  }

  @Override
  public void rewrite30OutRushFor33(Rewrite33Para[] paras)
      throws BusinessException {

    Map<String, Rewrite33Para> index = this.prepareParams(paras);
    BSContext.getInstance().setSession(Rewrite33Para.class.getName(), index);
    // 查询销售订单表体
    SaleOrderViewVO[] views = this.query(index);

    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30OutRushFor33);
    this.addRuleForOutRush(processer, M30EngrossAction.M30RushReWrite);
    // 执行前规则
    processer.before(views);

    // 设置应收结算数量
    this.setOutRushNum(views, index);

    String[] names = new String[] {
      SaleOrderBVO.NTOTALRUSHNUM
    };
    this.rewrite(views, names);

    // 执行后规则
    processer.after(views);

    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(Rewrite33Para.class.getName());
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer,
      M30EngrossAction engrossAction) {
    // 更新信用调用前
    processer.addBeforeRule(new RenovateARByBidsBeginRule(engrossAction));

    // --------------------------------------
    // 更新信用调用后
    processer.addAfterRule(new RenovateARByBidsEndRule(engrossAction));
  }

  private void addRuleForOutRush(AroundProcesser<SaleOrderViewVO> processer,
      M30EngrossAction engrossAction) {
    // 更新信用调用前
    processer.addBeforeRule(new RenovateARByBidsBeginRule(engrossAction));

    // --------------------------------------
    // 更新信用调用后
    processer.addAfterRule(new RenovateARByBidsEndRule(engrossAction));

    // 出库对冲影响发票关闭状态，注意要在信用调用之后调用此方法
    processer.addAfterRule(new RewriteInvoiceStateRule());

  }

  private String[] lockBills(Map<String, Rewrite33Para> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0187")/*@res "销售结算回写销售订单累计结算数量，锁销售订单表体失败"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, Rewrite33Para> prepareParams(Rewrite33Para[] paras) {
    Map<String, Rewrite33Para> index = new HashMap<String, Rewrite33Para>();
    for (Rewrite33Para para : paras) {
      index.put(para.getCsaleorderbid(), para);
    }
    return index;
  }

  private SaleOrderViewVO[] query(Map<String, Rewrite33Para> index) {
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

  private void rewrite(SaleOrderViewVO[] views, String[] names) {
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            ServicePlugInPoint.rewrite30NumFor33);

    TimeLog.logStart();
    processer.before(views);
    TimeLog.info("写数据库前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    ViewUpdate<SaleOrderViewVO> bo = new ViewUpdate<SaleOrderViewVO>();
    SaleOrderViewVO[] retviews = bo.update(views, SaleOrderBVO.class, names);
    TimeLog.info("更新数据库"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(retviews);
    TimeLog.info("写数据库后执行业务规则"); /*-=notranslate=-*/

  }

  /**
   * 方法功能描述：设置累计应收结算数量、金额
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param index
   *          <p>
   * @author zhangcheng
   * @time 2010-7-16 下午12:00:15
   */
  private void setARNumMny(SaleOrderViewVO[] vos,
      Map<String, Rewrite33Para> index) {
    UFDouble ntotalarnum = null;
    UFDouble ntotalarmny = null;
    SaleOrderBVO body = null;
    for (SaleOrderViewVO vo : vos) {
      body = vo.getBody();
      Rewrite33Para para = index.get(body.getCsaleorderbid());
      ntotalarnum = body.getNtotalarnum();
      ntotalarmny = body.getNtotalarmny();
      body.setNtotalarnum(MathTool.add(ntotalarnum, para.getNarnum()));
      body.setNtotalarmny(MathTool.add(ntotalarmny, para.getNarmny()));
    }
  }

  private void setCostNum(SaleOrderViewVO[] vos,
      Map<String, Rewrite33Para> index) {
    UFDouble ntotaletcostnum;
    SaleOrderBVO body = null;
    for (SaleOrderViewVO vo : vos) {
      body = vo.getBody();
      Rewrite33Para para = index.get(body.getCsaleorderbid());
      ntotaletcostnum = body.getNtotalcostnum();
      body.setNtotalcostnum(MathTool.add(ntotaletcostnum, para.getNarnum()));
      // dongli2--2013.8.29 因超容差开票，发票审批报错：累计成本结算数量不能大于订单主数量！
      // 先注掉 以后考虑
      // if (MathTool.greaterThan(MathTool.abs(body.getNtotalcostnum()),
      // MathTool.abs(body.getNnum()))) {
      // String message =
      // nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
      // "04006011-0475")/*@res "累计成本结算数量不能大于订单主数量！"*/;
      // ExceptionUtils.wrappBusinessException(message);
      // }
    }
  }

  private void setETNumMny(SaleOrderViewVO[] vos,
      Map<String, Rewrite33Para> index) {
    UFDouble ntotaletarnum = null;
    UFDouble ntotaletarmny = null;
    SaleOrderBVO body = null;
    for (SaleOrderViewVO vo : vos) {
      body = vo.getBody();
      Rewrite33Para para = index.get(body.getCsaleorderbid());
      ntotaletarnum = body.getNtotalestarnum();
      ntotaletarmny = body.getNtotalestarmny();
      body.setNtotalestarnum(MathTool.add(ntotaletarnum, para.getNarnum()));
      body.setNtotalestarmny(MathTool.add(ntotaletarmny, para.getNarmny()));
    }
  }

  private void setOutRushNum(SaleOrderViewVO[] vos,
      Map<String, Rewrite33Para> index) {
    UFDouble ntotaloutrushnum;
    SaleOrderBVO body = null;
    for (SaleOrderViewVO vo : vos) {
      body = vo.getBody();
      Rewrite33Para para = index.get(body.getCsaleorderbid());
      ntotaloutrushnum = body.getNtotalrushnum();
      body.setNtotalrushnum(MathTool.add(ntotaloutrushnum, para.getNarnum()));
    }
  }

}
