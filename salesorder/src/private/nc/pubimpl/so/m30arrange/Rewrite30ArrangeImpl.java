package nc.pubimpl.so.m30arrange;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.IBillType;
import nc.vo.scmpub.res.billtype.MMBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import nc.pubitf.so.m30arrange.Rewrite30ArrangePara;
import nc.pubitf.so.m30arrange.mm.m55a2.IRewrite30For55A2;
import nc.pubitf.so.m30arrange.mmpps.plo.IRewrite30For55B4;
import nc.pubitf.so.m30arrange.po.m20.IRewrite30For20;
import nc.pubitf.so.m30arrange.po.m21.IRewrite30For21;
import nc.pubitf.so.m30arrange.sc.m61.IRewrite30For61;
import nc.pubitf.so.m30arrange.to.m5a.IRewrite30For5A;
import nc.pubitf.so.m30arrange.to.m5x.IRewrite30For5X;

import nc.bs.so.m30.plugin.ServicePlugInPoint;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

import nc.pubimpl.so.m30arrange.rule.RewriteCheckArrangeNumRule;
import nc.pubimpl.so.m30arrange.rule.RewriteCheckOutCloseRule;
import nc.pubimpl.so.m30arrange.rule.RewriteSetNumRule;

/**
 * 补货直运安排回写销售订单
 * 
 * @since 6.1
 * @version 2013-04-26 16:13:42
 * @author yixl
 */
public class Rewrite30ArrangeImpl implements IRewrite30For20, IRewrite30For21,
    IRewrite30For5A, IRewrite30For5X, IRewrite30For61, IRewrite30For55A2,
    IRewrite30For55B4 {

  private void addRule(AroundProcesser<SaleOrderViewVO> processer,
      IBillType srctype) {
    // 直运采购不需要检查
    if (!(POBillType.Order.getCode().equals(srctype.getCode()) || POBillType.PrayBill
        .getCode().equals(srctype.getCode()))) {
      processer.addBeforeFinalRule(new RewriteCheckOutCloseRule());
    }
    processer.addBeforeRule(new RewriteCheckArrangeNumRule());
    processer.addBeforeRule(new RewriteSetNumRule());
  }

  private IPluginPoint getServicePlugInPoint(Rewrite30ArrangePara para) {
    IPluginPoint pluginPoint = null;
    if (POBillType.PrayBill.isEqual(para.getBilltype())) {
      pluginPoint = ServicePlugInPoint.rewrite30ArrangeNumFor20;
    }
    else if (POBillType.Order.isEqual(para.getBilltype())) {
      pluginPoint = ServicePlugInPoint.rewrite30ArrangeNumFor21;
    }
    else if (TOBillType.TransIn.isEqual(para.getBilltype())) {
      pluginPoint = ServicePlugInPoint.rewrite30ArrangeNumFor5A;
    }
    else if (TOBillType.TransOrder.isEqual(para.getBilltype())) {
      pluginPoint = ServicePlugInPoint.rewrite30ArrangeNumFor5X;
    }
    else if (SCBillType.Order.isEqual(para.getBilltype())) {
      pluginPoint = ServicePlugInPoint.rewrite30ArrangeNumFor61;
    }
    else if (MMBillType.ProduceOrder.isEqual(para.getBilltype())) {
      pluginPoint = ServicePlugInPoint.rewrite30ArrangeNumForA2;
    }
    else if (MMBillType.PlanOrder.isEqual(para.getBilltype())) {
      pluginPoint = ServicePlugInPoint.rewrite30npolnumFor55B4;
    }
    return pluginPoint;
  }

  private String[] getUpdateNames(Rewrite30ArrangePara para) {
    String arrangeName = null;
    if (POBillType.PrayBill.isEqual(para.getBilltype())) {
      arrangeName = SaleOrderBVO.NARRANGEPOAPPNUM;
    }
    else if (POBillType.Order.isEqual(para.getBilltype())) {
      arrangeName = SaleOrderBVO.NARRANGEPONUM;
    }
    else if (TOBillType.TransIn.isEqual(para.getBilltype())) {
      arrangeName = SaleOrderBVO.NARRANGETOAPPNUM;
    }
    else if (TOBillType.TransOrder.isEqual(para.getBilltype())) {
      arrangeName = SaleOrderBVO.NARRANGETOORNUM;
    }
    else if (SCBillType.Order.isEqual(para.getBilltype())) {
      arrangeName = SaleOrderBVO.NARRANGESCORNUM;
    }
    else if (MMBillType.ProduceOrder.isEqual(para.getBilltype())) {
      arrangeName = SaleOrderBVO.NARRANGEMONUM;
    }
    else if (MMBillType.LsProduceOrder.isEqual(para.getBilltype())) {
      arrangeName = SaleOrderBVO.NARRANGEMONUM;
    }
    else if (MMBillType.PlanOrder.isEqual(para.getBilltype())) {
      arrangeName = SaleOrderBVO.NTOTALPLONUM;
    }

    String[] names =
        new String[] {
          arrangeName, SaleOrderBVO.CARRANGEPERSONID,
          SaleOrderBVO.TLASTARRANGETIME, SaleOrderBVO.BARRANGEDFLAG
        };
    return names;
  }

  private String[] lockBills(Map<String, Rewrite30ArrangePara> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0191")/*@res "补货/安排下游单据回写销售订单累计安排数量，锁销售订单表体失败"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, Rewrite30ArrangePara> prepareParams(
      Rewrite30ArrangePara[] paras) {
    Map<String, Rewrite30ArrangePara> index =
        new HashMap<String, Rewrite30ArrangePara>();
    for (Rewrite30ArrangePara para : paras) {
      String bid = para.getCsaleorderbid();
      Rewrite30ArrangePara oldpara = index.get(bid);
      if (null == oldpara) {
        index.put(bid, para);
      }
      else {
        UFDouble oldnum = oldpara.getNnum();
        oldpara.setNnum(MathTool.add(oldnum, para.getNnum()));
      }

    }
    return index;
  }

  private SaleOrderViewVO[] query(Map<String, Rewrite30ArrangePara> index) {
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

  private void rewrite(Rewrite30ArrangePara[] paras, IBillType srctype) {
    TimeLog.logStart();
    Map<String, Rewrite30ArrangePara> index = this.prepareParams(paras);
    // 此处设置session变量，以避免程序到处传递
    BSContext.getInstance().setSession(Rewrite30ArrangePara.class.getName(),
        index);
    TimeLog.info("并处理参数"); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleOrderViewVO[] views = this.query(index);
    TimeLog.info("查询销售订单表体"); /*-=notranslate=-*/

    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(
            this.getServicePlugInPoint(paras[0]));
    this.addRule(processer, srctype);

    TimeLog.logStart();
    processer.before(views);
    TimeLog.info("写数据库前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();

    ViewUpdate<SaleOrderViewVO> bo = new ViewUpdate<SaleOrderViewVO>();
    views = bo.update(views, SaleOrderBVO.class, this.getUpdateNames(paras[0]));
    TimeLog.info("更新数据库"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("写数据库后执行业务规则"); /*-=notranslate=-*/

    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(Rewrite30ArrangePara.class.getName());
  }

  @Override
  public void rewrite30ArrangeNumFor20(Rewrite30ArrangePara[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras, POBillType.PrayBill);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public void rewrite30ArrangeNumFor21(Rewrite30ArrangePara[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras, POBillType.Order);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public void rewrite30ArrangeNumFor55A2(Rewrite30ArrangePara[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras, MMBillType.ProduceOrder);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public void rewrite30ArrangeNumFor5A(Rewrite30ArrangePara[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras, TOBillType.TransIn);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public void rewrite30ArrangeNumFor5X(Rewrite30ArrangePara[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras, TOBillType.TransOrder);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public void rewrite30ArrangeNumFor61(Rewrite30ArrangePara[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras, SCBillType.Order);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public void rewrite30npolnumFor55B4(Rewrite30ArrangePara[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras, MMBillType.PlanOrder);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

}
