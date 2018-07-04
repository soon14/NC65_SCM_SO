package nc.impl.so.m4331.action.quality;

import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

import nc.bs.so.m4331.quality.DeleteDeliverycheckBP;

public class DeliverycheckDeleteAction {

  public void delete(DeliveryCheckVO[] bills, boolean isCheck) {
    // AroundProcesser<DeliveryVO> processer =
    // new AroundProcesser<DeliveryVO>(
    // Action4331PlugInPoint.DeleteDeliverycheck);
    TimeLog.logStart();
    // processer.before(bills);
    TimeLog.info("调用删除BP前执行业务规则"); /*-=notranslate=-*/
    TimeLog.logStart();
    DeleteDeliverycheckBP action = new DeleteDeliverycheckBP();
    action.delete(bills, isCheck);
    TimeLog.info("调用删除BP，进行删除"); /*-=notranslate=-*/
    TimeLog.logStart();
    // processer.after(allbills);
    TimeLog.info("调用删除BP后执行业务规则"); /*-=notranslate=-*/
  }
}
