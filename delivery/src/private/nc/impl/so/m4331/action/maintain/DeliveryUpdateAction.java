package nc.impl.so.m4331.action.maintain;

import nc.bs.so.m4331.maintain.UpdateDeliveryBP;
import nc.bs.so.m4331.plugin.Action4331PlugInPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryVO;

public class DeliveryUpdateAction {
  public DeliveryVO[] update(DeliveryVO[] bills, DeliveryVO[] originBills) {
    TimeLog.logStart();
    // BillTransferTool<DeliveryVO> transferTool =
    // new BillTransferTool<DeliveryVO>(bills);
    // DeliveryVO[] allbills = transferTool.getClientFullInfoBill();
    // DeliveryVO[] originBills = transferTool.getOriginBills();
    TimeLog.info("补全前台VO、获得修改前VO、加锁、检查时间戳"); /*-=notranslate=-*/
    CompareAroundProcesser<DeliveryVO> compareProcesser =
        new CompareAroundProcesser<DeliveryVO>(
            Action4331PlugInPoint.UpdateAction);
    TimeLog.logStart();
    compareProcesser.before(bills, originBills);
    TimeLog.info("调用更新保存BP前执行业务规则"); /*-=notranslate=-*/
    UpdateDeliveryBP action = new UpdateDeliveryBP();
    DeliveryVO[] ret = action.update(bills, originBills);
    TimeLog.logStart();
    compareProcesser.after(ret, originBills);
    TimeLog.info("调用更新保存BP后执行业务规则"); /*-=notranslate=-*/
    TimeLog.logStart();
    // ret = transferTool.getBillForToClient(ret);
    TimeLog.info("组织返回值,返回轻量级VO"); /*-=notranslate=-*/
    TimeLog.logStart();
    return ret;
  }
}
