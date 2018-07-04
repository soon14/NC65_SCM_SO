package nc.impl.so.m4331.action.maintain;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m4331.maintain.DeleteDeliveryBP;
import nc.bs.so.m4331.plugin.Action4331PlugInPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryVO;

public class DeliveryDeleteAction {

  public void delete(DeliveryVO[] bills) {

    AroundProcesser<DeliveryVO> processer =
        new AroundProcesser<DeliveryVO>(Action4331PlugInPoint.DeleteAction);
    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info(NCLangResOnserver.getInstance().getStrByID("4006002_0",
        "04006002-0140")/*调用删除BP前执行业务规则*/); /*-=notranslate=-*/
    TimeLog.logStart();
    DeleteDeliveryBP action = new DeleteDeliveryBP();
    action.delete(bills);
    TimeLog.info(NCLangResOnserver.getInstance().getStrByID("4006002_0",
        "04006002-0141")/*调用删除BP，进行删除*/); /*-=notranslate=-*/
    TimeLog.logStart();
    processer.after(bills);
    TimeLog.info(NCLangResOnserver.getInstance().getStrByID("4006002_0",
        "04006002-0142")/*调用删除BP后执行业务规则*/); /*-=notranslate=-*/

  }

}
