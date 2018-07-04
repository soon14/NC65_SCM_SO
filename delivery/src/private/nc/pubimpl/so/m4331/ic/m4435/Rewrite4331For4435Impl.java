package nc.pubimpl.so.m4331.ic.m4435;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m4331.plugin.ServicePlugInPoint;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.so.m4331.ic.m4453.IRewrite4331For4453;
import nc.pubitf.so.m4331.ic.m4453.RewritePara4331For4453;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * 途损单回写发货单累计途损数量
 *
 * @since 6.0
 * @version 2010-12-20 下午04:21:36
 * @author 祝会征
 */
public class Rewrite4331For4435Impl implements IRewrite4331For4453 {
  private void addRule(DeliveryViewVO[] views,
      Map<String, RewritePara4331For4453> index) {
    for (DeliveryViewVO view : views) {
      String cdeliverybid = view.getItem().getCdeliverybid();
      UFDouble lossNum = index.get(cdeliverybid).getLossnum();
      UFDouble totalLoss = view.getItem().getNtranslossnum();
      totalLoss = MathTool.add(lossNum, totalLoss);
      view.getItem().setNtranslossnum(totalLoss);
    }
  }

  private String[] lockBills(Map<String, RewritePara4331For4453> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0079")/*@res "途损单回写发货单出现并发!"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, RewritePara4331For4453> prepareParams(
      RewritePara4331For4453[] paras) {
    Map<String, RewritePara4331For4453> index =
        new HashMap<String, RewritePara4331For4453>();
    for (RewritePara4331For4453 para : paras) {
      index.put(para.getCdeliverybid(), para);
    }
    return index;
  }

  private DeliveryViewVO[] query(Map<String, RewritePara4331For4453> index) {
    String[] ids = this.lockBills(index);
    ViewQuery<DeliveryViewVO> bo =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    bo.setSharedHead(true);

    DeliveryViewVO[] views = bo.query(ids);
    if (views.length != index.size()) {
      String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0061")/*@res "出现并发，请重新查询发货单"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }

  @Override
  public void rewrite(RewritePara4331For4453[] paras) throws BusinessException {
    this.rewrite4331(paras);
  }

  private void rewrite4331(RewritePara4331For4453[] paras) {
    Map<String, RewritePara4331For4453> index = this.prepareParams(paras);
    // 此处设置session变量，以避免程序到处传递
    TimeLog.info("并处理参数"); /*-=notranslate=-*/

    TimeLog.logStart();
    DeliveryViewVO[] views = this.query(index);
    TimeLog.info("查询发货单表体"); /*-=notranslate=-*/

    AroundProcesser<DeliveryViewVO> processer =
        new AroundProcesser<DeliveryViewVO>(
            ServicePlugInPoint.rewrite4331For4435);
    this.addRule(views, index);

    TimeLog.logStart();
    processer.before(views);

    TimeLog.info("写数据库前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      DeliveryBVO.NTRANSLOSSNUM
    };
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    views = bo.update(views, DeliveryBVO.class, names);
    TimeLog.info("更新数据库"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("写数据库后执行业务规则"); /*-=notranslate=-*/
  }
}