package nc.pubimpl.so.m4331.so.m30.bp;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.so.m4331.maintain.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m4331.plugin.ServicePlugInPoint;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m4331.so.m30.bp.rule.Renovate4331PriceRule;
import nc.pubitf.so.m4331.so.m30.IDeliveryPriceParaFor30;
import nc.vo.credit.engrossmaintain.pub.action.M4331EngrossAction;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 销售订单价格修订后更新发货单价格
 *
 * @since 6.0
 * @version 2011-3-24 下午08:19:07
 * @author 祝会征
 */
public class Renovate4331PriceBP {

  private Map<String, IDeliveryPriceParaFor30> paraMap;

  /**
   * 销售订单价格修订后更新发货单价格
   *
   * @param dataMap
   * @throws BusinessException
   */
  public void renovatePrice(Map<String, IDeliveryPriceParaFor30> paraMap1)
      throws BusinessException {
    try {
      if ((null == paraMap1) || (paraMap1.size() == 0)) {
        return;
      }
      this.paraMap = paraMap1;
      this.renovate();
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private void addRule(AroundProcesser<DeliveryViewVO> processer,
      DeliveryViewVO[] views) {
    // 修订发货单价格前信用占用检查
    processer.addBeforeRule(new RenovateARByBidsBeginRule(
        M4331EngrossAction.M4331ReVise));
    // 更新价格
    new Renovate4331PriceRule().process(this.paraMap, views);
    // 修订发货单价格后信用占用检查
    processer.addAfterRule(new RenovateARByBidsEndRule(
        M4331EngrossAction.M4331ReVise));
  }

  private DeliveryViewVO[] getDeliveryInfos(String[] srcBids, String[] srcHids) {
    VOQuery<DeliveryBVO> query = new VOQuery<DeliveryBVO>(DeliveryBVO.class);
    SqlBuilder sqlbuilder = new SqlBuilder();
    sqlbuilder.append(" and ");
    sqlbuilder.append(DeliveryBVO.CSRCBID, srcBids);
    sqlbuilder.append(" and ");
    sqlbuilder.append(DeliveryBVO.CSRCID, srcHids);
    DeliveryBVO[] bvos = query.query(sqlbuilder.toString(), null);
    if (VOChecker.isEmpty(bvos)) {
      return null;
    }
    Set<String> bidSet = new HashSet<String>();
    for (DeliveryBVO bvo : bvos) {
      bidSet.add(bvo.getCdeliverybid());
    }
    String[] bids = new String[bidSet.size()];
    bidSet.toArray(bids);
    this.lock(bids);
    ViewQuery<DeliveryViewVO> bo =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    bo.setSharedHead(true);
    DeliveryViewVO[] views = bo.query(bids);
    return views;
  }

  /**
   * 加锁
   *
   * @param bvos
   */
  private void lock(String[] bids) {
    LockOperator locker = new LockOperator();
    String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0091")/*@res "销售订单修订价格，回写发货单时，给发货单加锁失败"*/;
    locker.lock(bids, message);
  }

  private DeliveryViewVO[] query() {
    String[] srcBids = new String[this.paraMap.size()];
    this.paraMap.keySet().toArray(srcBids);
    IDeliveryPriceParaFor30[] paras =
        new IDeliveryPriceParaFor30[this.paraMap.size()];
    this.paraMap.values().toArray(paras);
    Set<String> hidSet = new HashSet<String>();
    for (IDeliveryPriceParaFor30 para : paras) {
      hidSet.add(para.getHid());
    }
    if (hidSet.size() == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0092")/*@res "销售订单表头id不能为空。"*/);
    }
    String[] srcHids = new String[hidSet.size()];
    hidSet.toArray(srcHids);
    DeliveryViewVO[] views = this.getDeliveryInfos(srcBids, srcHids);
    return views;
  }

  private void renovate() {

    TimeLog.logStart();
    DeliveryViewVO[] views = this.query();
    if ((null == views) || (views.length == 0)) {
      return;
    }
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0093")/*@res "获得发货单信息"*/);
    AroundProcesser<DeliveryViewVO> processer =
        new AroundProcesser<DeliveryViewVO>(
            ServicePlugInPoint.Renovate4331PriceBP);
    this.addRule(processer, views);
    TimeLog.logStart();
    processer.before(views);
    TimeLog.info("写数据库前执行业务规则"); /*-=notranslate=-*/
    TimeLog.logStart();
    this.updateDB(views);
    TimeLog.info("更新数据库"); /*-=notranslate=-*/
    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("写数据库后执行业务规则"); /*-=notranslate=-*/
  }

  /**
   * 更新数据库
   *
   * @param bvos
   */
  private void updateDB(DeliveryViewVO[] views) {
    Set<DeliveryBVO> bvoSet = new HashSet<DeliveryBVO>();
    for (DeliveryViewVO view : views) {
      bvoSet.add(view.getItem());
    }
    DeliveryBVO[] bvos = new DeliveryBVO[bvoSet.size()];
    bvoSet.toArray(bvos);
    VOUpdate<DeliveryBVO> update = new VOUpdate<DeliveryBVO>();
    update.update(bvos);
  }
}