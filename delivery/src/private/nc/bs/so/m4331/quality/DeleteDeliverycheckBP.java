package nc.bs.so.m4331.quality;

import java.util.HashSet;
import java.util.Set;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

import nc.bs.so.m4331.quality.rule.delete.CheckEnableDeleteRule;
import nc.bs.so.m4331.quality.rule.delete.Rewrite4331OnDeleteRule;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.pub.LockOperator;

public class DeleteDeliverycheckBP {

  private void addRule(DeliveryViewVO[] views, boolean isCheck) {
    // 检查是否能够删除
    CheckEnableDeleteRule delete = new CheckEnableDeleteRule();
    delete.checkDelete(views, isCheck);
    // 清空对应发货单表体的累计合格和累计不合格数量
    Rewrite4331OnDeleteRule rewrite = new Rewrite4331OnDeleteRule();
    rewrite.rewrite4331(views);
  }

  public void delete(DeliveryCheckVO[] bills, boolean isCheck) {

    TimeLog.logStart();
    DeliveryViewVO[] views = this.query(bills);
    this.addRule(views, isCheck);
    TimeLog.info("删除前执行业务规则"); /*-=notranslate=-*/
    TimeLog.logStart();
    VODelete<DeliveryCheckVO> bo = new VODelete<DeliveryCheckVO>();
    bo.delete(bills);
    TimeLog.info("写数据库，删除单据"); /*-=notranslate=-*/
    TimeLog.logStart();
    TimeLog.info("删除后执行业务规则"); /*-=notranslate=-*/
  }

  /*
   * 给发货单表体添加动态锁
   */
  private String[] getBids(DeliveryCheckVO[] bills) {
    Set<String> set = new HashSet<String>();
    for (DeliveryCheckVO vo : bills) {
      set.add(vo.getCdeliverybid());
    }
    String[] bids = new String[set.size()];
    bids = set.toArray(bids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0",
            "04006002-0060")/*@res "质检单审核回写发货单，锁发货单表体失败"*/;
    locker.lock(bids, message);
    return bids;
  }

  private DeliveryViewVO[] query(DeliveryCheckVO[] bills) {
    String[] ids = this.getBids(bills);
    ViewQuery<DeliveryViewVO> bo =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    bo.setSharedHead(true);
    DeliveryViewVO[] views = bo.query(ids);
    if (views.length != ids.length) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0",
              "04006002-0061")/*@res "出现并发，请重新查询发货单"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }
}
