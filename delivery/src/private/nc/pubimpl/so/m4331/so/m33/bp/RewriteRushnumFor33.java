package nc.pubimpl.so.m4331.so.m33.bp;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m4331.plugin.ServicePlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.so.m4331.so.m33.RewriteRushNumPara;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * 回写发货单对冲数量
 * 
 * @author 祝会征
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public class RewriteRushnumFor33 {

  public void rewrite(RewriteRushNumPara[] paras) {
    this.rewriteRushnum(paras);
  }

  private void addRule(DeliveryViewVO[] views) {
    Map<String, RewriteRushNumPara> index =
        (Map<String, RewriteRushNumPara>) BSContext.getInstance().getSession(
            RewriteRushNumPara.class.getName());
    for (DeliveryViewVO view : views) {
      String cdeliverybid = view.getItem().getCdeliverybid();
      UFDouble arnum = index.get(cdeliverybid).getRushnum();
      UFDouble totalRushnum = view.getItem().getNtotalrushnum();

      totalRushnum = MathTool.add(arnum, totalRushnum);
      view.getItem().setNtotalrushnum(totalRushnum);
    }
  }

  private String[] lockBills(Map<String, RewriteRushNumPara> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0",
            "04006002-0094")/* @res "回写发货单确认应收数量时锁表失败！" */;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, RewriteRushNumPara> prepareParams(
      RewriteRushNumPara[] paras) {
    Map<String, RewriteRushNumPara> index =
        new HashMap<String, RewriteRushNumPara>();
    for (RewriteRushNumPara para : paras) {
      index.put(para.getCdeliverybid(), para);
    }
    return index;
  }

  private DeliveryViewVO[] query(Map<String, RewriteRushNumPara> index) {
    String[] ids = this.lockBills(index);
    ViewQuery<DeliveryViewVO> bo =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    bo.setSharedHead(true);
    DeliveryViewVO[] views = bo.query(ids);
    if (views.length != index.size()) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0",
              "04006002-0061")/* @res "出现并发，请重新查询发货单" */;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }

  private void rewriteRushnum(RewriteRushNumPara[] paras) {
    Map<String, RewriteRushNumPara> index = this.prepareParams(paras);
    // 此处设置session变量，以避免程序到处传递
    BSContext.getInstance().setSession(RewriteRushNumPara.class.getName(),
        index);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006002_0", "04006002-0096")/* @res "并处理参数" */);

    TimeLog.logStart();
    DeliveryViewVO[] views = this.query(index);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006002_0", "04006002-0097")/* @res "查询发货单表体" */);
    AroundProcesser<DeliveryViewVO> processer =
        new AroundProcesser<DeliveryViewVO>(
            ServicePlugInPoint.rewriteRushnumFor33);
    this.addRule(views);

    TimeLog.logStart();
    processer.before(views);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006002_0", "04006002-0098")/* @res "写数据库前执行业务规则" */);

    TimeLog.logStart();
    String[] names = new String[] {
        DeliveryBVO.NTOTALRUSHNUM
    };
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    views = bo.update(views, DeliveryBVO.class, names);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006002_0", "04006002-0099")/* @res "更新数据库" */);

    TimeLog.logStart();
    processer.after(views);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006002_0", "04006002-0100")/* @res "写数据库后执行业务规则" */);
    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(RewriteRushNumPara.class.getName());
  }
}
