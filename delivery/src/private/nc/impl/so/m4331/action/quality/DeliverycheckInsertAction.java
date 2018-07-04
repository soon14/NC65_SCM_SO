package nc.impl.so.m4331.action.quality;

import nc.bs.so.m4331.plugin.Action4331PlugInPoint;
import nc.bs.so.m4331.quality.InsertDeliverycheckBP;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

public class DeliverycheckInsertAction {
  public DeliveryCheckVO[] insert(DeliveryCheckVO[] newvos) {
    AroundProcesser<DeliveryCheckVO> processer =
        new AroundProcesser<DeliveryCheckVO>(
            Action4331PlugInPoint.InsertDeliverycheck);
    TimeLog.logStart();
    processer.before(newvos);
    TimeLog.info("调用新增保存BP前执行业务规则"); /*-=notranslate=-*/
    TimeLog.logStart();
    InsertDeliverycheckBP action = new InsertDeliverycheckBP();
    DeliveryCheckVO[] vos = action.insert(newvos);
    TimeLog.info("调用新增保存BP，进行保存"); /*-=notranslate=-*/
    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("调用新增保存BP后执行业务规则"); /*-=notranslate=-*/
    return vos;
  }
}
