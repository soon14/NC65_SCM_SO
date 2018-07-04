package nc.impl.so.m30.sobalance.action;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.so.m30.sobalance.maintain.IDeleteBP;
import nc.impl.so.m30.sobalance.maintain.SobalanceBPFactoryForSoManual;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * 删除动作
 * 
 * @author gdsjw
 */
public class DeleteAction {
  public SoBalanceVO[] delete(SoBalanceVO[] bills) {

    // 补全前台VO、加锁、检查时间戳
    TimeLog.logStart();
    BillTransferTool<SoBalanceVO> transferTool =
        new BillTransferTool<SoBalanceVO>(bills);
    SoBalanceVO[] delbills = transferTool.getOriginBills();

    TimeLog.info("补全前台VO、加锁、检查时间戳"); /*-=notranslate=-*/

    // AroundProcesser<SoBalanceVO> processer = new
    // AroundProcesser<SoBalanceVO>(
    // ActionPlugInPoint.DeleteAction);
    // // 增加业务规则
    // this.addBeforeRule(processer);
    // this.addAfterRule(processer);
    //
    // TimeLog.getInstance().logStart();
    // processer.before(bills);
    /*-=notranslate=-*/

    TimeLog.logStart();
    IDeleteBP action =
        SobalanceBPFactoryForSoManual.getInstance().getDeleteBP();
    action.delete(delbills);

    TimeLog.info("调用删除BP，进行删除"); /*-=notranslate=-*/

    // TimeLog.getInstance().logStart();
    // processer.after(bills);
    /*-=notranslate=-*/

    return delbills;

  }

  // private void addAfterRule(AroundProcesser<SoBalanceVO> processer) {
  // //
  // }

  // private void addBeforeRule(AroundProcesser<SoBalanceVO> processer) {
  // //
  // }
}
