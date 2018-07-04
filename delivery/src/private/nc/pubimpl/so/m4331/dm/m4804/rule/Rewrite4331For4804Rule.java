package nc.pubimpl.so.m4331.dm.m4804.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.pubitf.so.m4331.dm.m4804.RewritePara4331For4804;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * 运输单回写发货单累计运输数量
 *
 * @since 6.0
 * @version 2011-3-21 上午11:42:57
 * @author 祝会征
 */
public class Rewrite4331For4804Rule {

  public void rewriteTransnum(RewritePara4331For4804[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private String[] lockBills(Map<String, RewritePara4331For4804> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0077")/*@res "运输单回写发货单，给发货单加锁失败。"*/;
    locker.lock(bids, message);
    return bids;
  }

  private Map<String, RewritePara4331For4804> prepareParams(
      RewritePara4331For4804[] paras) {
    Map<String, RewritePara4331For4804> index =
        new HashMap<String, RewritePara4331For4804>();
    for (RewritePara4331For4804 para : paras) {
      index.put(para.getCdeliverybid(), para);
    }
    return index;
  }

  private DeliveryViewVO[] query(Map<String, RewritePara4331For4804> index) {
    String[] ids = this.lockBills(index);
    ViewQuery<DeliveryViewVO> bo =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    bo.setSharedHead(true);

    DeliveryViewVO[] views = bo.query(ids);
    if (views.length != index.size()) {
      String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0078")/*@res "运输单回写发货单时，出现并发，请查询。"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    this.renovateViews(index, views);
    return views;
  }

  private void renovateViews(Map<String, RewritePara4331For4804> index,
      DeliveryViewVO[] views) {
    for (DeliveryViewVO view : views) {
      String bid = view.getItem().getCdeliverybid();
      UFDouble renum = index.get(bid).getTransnum();
      UFDouble num = view.getItem().getNnum();
      UFDouble totaltranNum = view.getItem().getNtotaltransnum();
      totaltranNum = MathTool.add(totaltranNum, renum);
      view.getItem().setNtotaltransnum(totaltranNum);
      if (MathTool.compareTo(totaltranNum, num) < 0) {
        view.getItem().setBtransendflag(UFBoolean.FALSE);
        continue;
      }
      view.getItem().setBtransendflag(UFBoolean.TRUE);
    }
  }

  private void rewrite(RewritePara4331For4804[] paras) {
    Map<String, RewritePara4331For4804> index = this.prepareParams(paras);
    // 此处设置session变量，以避免程序到处传递
    TimeLog.info("并处理参数"); /*-=notranslate=-*/
    TimeLog.logStart();
    DeliveryViewVO[] views = this.query(index);
    TimeLog.info("查询发货单信息。"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      DeliveryBVO.NTOTALTRANSNUM, DeliveryBVO.BTRANSENDFLAG
    };
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    bo.update(views, DeliveryBVO.class, names);
    TimeLog.info("更新数据库"); /*-=notranslate=-*/
  }
}