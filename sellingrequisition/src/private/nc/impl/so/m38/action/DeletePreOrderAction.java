package nc.impl.so.m38.action;

import nc.bs.so.m38.maintain.DeletePreOrderBP;
import nc.bs.so.m38.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * 预订单删除动作
 * 
 * @author 刘志伟
 */
public class DeletePreOrderAction {
  public PreOrderVO[] delete(PreOrderVO[] bills) {
    TimeLog.logStart();
    BillTransferTool<PreOrderVO> transferTool =
        new BillTransferTool<PreOrderVO>(bills);
    PreOrderVO[] newBills = transferTool.getClientFullInfoBill();
    TimeLog.info("补全前台VO"); /*-=notranslate=-*/

    AroundProcesser<PreOrderVO> processer =
        new AroundProcesser<PreOrderVO>(ActionPlugInPoint.DeleteAction);

    TimeLog.logStart();
    processer.before(newBills);
    TimeLog.info("调用删除前操作插入点"); /*-=notranslate=-*/

    TimeLog.logStart();
    DeletePreOrderBP action = new DeletePreOrderBP();
    action.delete(newBills);
    TimeLog.info("调用删除BP，进行删除"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(newBills);
    TimeLog.info("调用删除后操作插入点"); /*-=notranslate=-*/

    TimeLog.logStart();
    // TODO
    TimeLog.info("业务日志"); /*-=notranslate=-*/

    return newBills;
  }
}
