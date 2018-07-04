package nc.pubimpl.so.m4331.so.m33.bp;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m4331.maintain.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m4331.plugin.ServicePlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m4331.so.m33.bp.rule.RewriteEstArnumRule;
import nc.pubitf.so.m4331.so.m33.RewriteEstarnumPara;
import nc.vo.credit.engrossmaintain.pub.action.M4331EngrossAction;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * 回写发货单暂估应收数量接口
 *
 * @author 祝会征
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public class RewriteEstArnumFor33 {

  public void rewrite(RewriteEstarnumPara[] paras) {
    this.rewriteEstarnum(paras);
  }

  private void addRule(AroundProcesser<DeliveryViewVO> processer) {
    // 回写发货单暂估应收数量前信用占用检查
    processer.addBeforeRule(new RenovateARByBidsBeginRule(
        M4331EngrossAction.M4331ArReWrite));
    // 回写确认应收数量
    processer.addBeforeRule(new RewriteEstArnumRule());
    // 回写发货单暂估应收数量后信用占用检查
    processer.addAfterRule(new RenovateARByBidsEndRule(
        M4331EngrossAction.M4331ArReWrite));
  }

  private String[] lockBills(Map<String, RewriteEstarnumPara> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0095")/*@res "回写发货单暂估应收数量时锁表失败！"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, RewriteEstarnumPara> prepareParams(
      RewriteEstarnumPara[] paras) {
    Map<String, RewriteEstarnumPara> index =
        new HashMap<String, RewriteEstarnumPara>();
    for (RewriteEstarnumPara para : paras) {
      index.put(para.getCdeliverybid(), para);
    }
    return index;
  }

  private DeliveryViewVO[] query(Map<String, RewriteEstarnumPara> index) {
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

  private void rewriteEstarnum(RewriteEstarnumPara[] paras) {
    Map<String, RewriteEstarnumPara> index = this.prepareParams(paras);
    // 此处设置session变量，以避免程序到处传递
    BSContext.getInstance().setSession(RewriteEstarnumPara.class.getName(),
        index);
    TimeLog.info("并处理参数"); /*-=notranslate=-*/
    TimeLog.logStart();
    DeliveryViewVO[] views = this.query(index);
    TimeLog.info("查询发货单表体"); /*-=notranslate=-*/
    AroundProcesser<DeliveryViewVO> processer =
        new AroundProcesser<DeliveryViewVO>(
            ServicePlugInPoint.RewriteEstArnumFor33);
    this.addRule(processer);
    TimeLog.logStart();
    processer.before(views);
    TimeLog.info("写数据库前执行业务规则"); /*-=notranslate=-*/
    TimeLog.logStart();
    String[] names = new String[] {
      DeliveryBVO.NTOTALARNUM
    };
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    views = bo.update(views, DeliveryBVO.class, names);
    TimeLog.info("更新数据库"); /*-=notranslate=-*/
    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("写数据库后执行业务规则"); /*-=notranslate=-*/

    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(RewriteEstarnumPara.class.getName());
  }
}