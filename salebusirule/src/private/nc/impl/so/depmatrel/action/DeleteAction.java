package nc.impl.so.depmatrel.action;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.so.depmatrel.maintain.DeleteBP;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.depmatrel.entity.DepMatRelVO;

/**
 * 删除动作
 * 
 * @author gdsjw
 */
public class DeleteAction {
  public DepMatRelVO delete(DepMatRelVO bill) {
    DepMatRelVO[] bills = new DepMatRelVO[] {
      bill
    };

    // 补全前台VO、加锁、检查时间戳
    TimeLog.logStart();
    BillTransferTool<DepMatRelVO> transferTool =
        new BillTransferTool<DepMatRelVO>(bills);
    DepMatRelVO[] delbills = transferTool.getOriginBills();

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
