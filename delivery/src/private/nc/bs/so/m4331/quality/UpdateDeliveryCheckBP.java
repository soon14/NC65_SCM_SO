package nc.bs.so.m4331.quality;

import nc.bs.so.m4331.plugin.BP4331PlugInPoint;
import nc.bs.so.m4331.quality.rule.update.CheckUpdateNullRule;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

public class UpdateDeliveryCheckBP {

  public DeliveryCheckVO[] update(DeliveryCheckVO[] bills) {
    CompareAroundProcesser<DeliveryCheckVO> processer =
        new CompareAroundProcesser<DeliveryCheckVO>(
            BP4331PlugInPoint.updateDeliverycheck);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    TimeLog.logStart();
    VOUpdate<DeliveryCheckVO> bo = new VOUpdate<DeliveryCheckVO>();
    DeliveryCheckVO[] vos = bo.update(bills);

    TimeLog.info("修改保存单据到数据库");/* -=notranslate=- */

    return vos;
  }

  private void addBeforeRule(CompareAroundProcesser<DeliveryCheckVO> processer) {
    // 修改非空校验
    IRule<DeliveryCheckVO> rule = new CheckUpdateNullRule();
    processer.addBeforeRule(rule);
  }

}
