package nc.bs.so.m4331.quality;

import java.util.HashSet;
import java.util.Set;

import nc.bs.so.m4331.quality.rule.insert.CheckNewNullRule;
import nc.bs.so.m4331.quality.rule.insert.FillNewDefaultRule;
import nc.bs.so.m4331.quality.rule.insert.Rewrite4331ForCheckRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

public class InsertDeliverycheckBP {

  public DeliveryCheckVO[] insert(DeliveryCheckVO[] bills) {
    TimeLog.logStart();
    DeliveryViewVO[] views = this.query(bills);
    TimeLog.info("获得发货单view");/* -=notranslate=- */
    // 增加执行前业务规则
    this.addBeforeRule(bills, views);
    TimeLog.logStart();
    VOInsert<DeliveryCheckVO> bo = new VOInsert<DeliveryCheckVO>();
    DeliveryCheckVO[] vos = bo.insert(bills);
    TimeLog.info("保存单据到数据库");/* -=notranslate=- */
    return vos;
  }

  private void addBeforeRule(DeliveryCheckVO[] bills, DeliveryViewVO[] views) {
    CheckNewNullRule checknull = new CheckNewNullRule();
    checknull.process(bills);
    // 回写发货单表体累计合格数量、不合格数量是否质检结束
    Rewrite4331ForCheckRule rewrite = new Rewrite4331ForCheckRule();
    rewrite.rewrite4331(views, bills);
    // 填充默认值
    FillNewDefaultRule fill = new FillNewDefaultRule();
    fill.setData(bills, views);
  }

  /* 给发货单表体添加动态锁 */
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
            "04006002-0060")/* @res "质检单审核回写发货单，锁发货单表体失败" */;
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
              "04006002-0061")/* @res "出现并发，请重新查询发货单" */;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }
}
