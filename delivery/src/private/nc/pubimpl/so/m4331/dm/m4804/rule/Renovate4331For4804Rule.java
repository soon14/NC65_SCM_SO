package nc.pubimpl.so.m4331.dm.m4804.rule;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * 运输单关闭打开回写发货单
 *
 * @since 6.0
 * @version 2011-3-21 上午11:08:21
 * @author 祝会征
 */
public class Renovate4331For4804Rule {

  public void renovateState(String[] bids, UFBoolean state)
      throws BusinessException {
    try {
      this.renovate(bids, state);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private void lockBills(String[] bids) {
    LockOperator locker = new LockOperator();
    String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0076")/*@res "运输单手动关闭、打开时回写发货单，给发货单加锁失败。"*/;
    locker.lock(bids, message);
  }

  private DeliveryViewVO[] query(String[] bids, UFBoolean state) {
    this.lockBills(bids);
    ViewQuery<DeliveryViewVO> bo =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    bo.setSharedHead(true);
    DeliveryViewVO[] views = bo.query(bids);
    if (views.length != bids.length) {
      String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0061")/*@res "出现并发，请重新查询发货单"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    for (DeliveryViewVO view : views) {
      view.getItem().setBtransendflag(state);
    }
    return views;
  }

  private void renovate(String[] bids, UFBoolean state) {

    TimeLog.logStart();
    DeliveryViewVO[] views = this.query(bids, state);
    TimeLog.info("查询发货单信息"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      DeliveryBVO.BTRANSENDFLAG
    };
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    bo.update(views, DeliveryBVO.class, names);
    TimeLog.info("更新数据库"); /*-=notranslate=-*/
  }
}