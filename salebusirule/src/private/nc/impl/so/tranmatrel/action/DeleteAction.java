package nc.impl.so.tranmatrel.action;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.so.tranmatrel.maintain.DeleteBP;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.tranmatrel.entity.TranMatRelVO;

/**
 * 删除动作
 * 
 * @author gdsjw
 */
public class DeleteAction {
  public TranMatRelVO delete(TranMatRelVO bill) {
    TranMatRelVO[] bills = new TranMatRelVO[] {
      bill
    };

    // 补全前台VO、加锁、检查时间戳
    TimeLog.logStart();
    BillTransferTool<TranMatRelVO> transferTool =
        new BillTransferTool<TranMatRelVO>(bills);
    TranMatRelVO[] delbills = transferTool.getOriginBills();

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
    DeleteBP action = new DeleteBP();
    action.delete(delbills);

    TimeLog.info("调用删除BP，进行删除"); /*-=notranslate=-*/

    // TimeLog.getInstance().logStart();
    // processer.after(bills);
    /*-=notranslate=-*/

    return delbills[0];
  }

  // private void addAfterRule(AroundProcesser<SoBalanceVO> processer) {
  // //
  // }

  // private void addBeforeRule(AroundProcesser<SoBalanceVO> processer) {
  // //
  // }
}
