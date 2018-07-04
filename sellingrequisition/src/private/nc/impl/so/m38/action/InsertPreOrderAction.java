package nc.impl.so.m38.action;

import nc.bs.so.m38.maintain.InsertPreOrderBP;
import nc.bs.so.m38.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * 订单新增保存动作
 * 
 * @author 刘志伟
 * 
 */
public class InsertPreOrderAction {
  public PreOrderVO[] insert(PreOrderVO[] bills) {
    TimeLog.logStart();
    BillTransferTool<PreOrderVO> transferTool =
        new BillTransferTool<PreOrderVO>(bills);
    TimeLog.info("保存前台VO，组织返回值时使用"); /*-=notranslate=-*/

    AroundProcesser<PreOrderVO> processer =
        new AroundProcesser<PreOrderVO>(ActionPlugInPoint.InsertAction);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("调用新增保存前操作插入点"); /*-=notranslate=-*/

    TimeLog.logStart();
    InsertPreOrderBP action = new InsertPreOrderBP();
    PreOrderVO[] vos = action.insert(bills);
    TimeLog.info("调用新增保存BP，进行保存"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("调用新增保存后操作插入点"); /*-=notranslate=-*/

    TimeLog.logStart();
    vos = transferTool.getBillForToClient(vos);
    TimeLog.info("组织返回值,返回轻量级VO"); /*-=notranslate=-*/

    return vos;
  }
}
