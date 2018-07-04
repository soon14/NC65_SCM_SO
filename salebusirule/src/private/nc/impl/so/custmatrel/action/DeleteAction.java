package nc.impl.so.custmatrel.action;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.so.custmatrel.maintain.DeleteBP;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * 删除动作
 * 
 * @author gdsjw
 */
public class DeleteAction {
  public CustMatRelVO delete(CustMatRelVO bill) {
    CustMatRelVO[] bills = new CustMatRelVO[] {
      bill
    };
    // 补全前台VO、加锁、检查时间戳
    TimeLog.logStart();
    BillTransferTool<CustMatRelVO> transferTool =
        new BillTransferTool<CustMatRelVO>(bills);
    CustMatRelVO[] delbills = transferTool.getOriginBills();
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
    // TimeLog.getInstance().info("调用删除前操作插入点"); /* -=notranslate=- */

    TimeLog.logStart();
    DeleteBP action = new DeleteBP();
    action.delete(delbills);
    TimeLog.info("调用删除BP，进行删除"); /*-=notranslate=-*/

    // TimeLog.getInstance().logStart();
    // processer.after(bills);
    // TimeLog.getInstance().info("调用删除后操作插入点"); /* -=notranslate=- */

    return delbills[0];
  }

  // private void addAfterRule(AroundProcesser<SoBalanceVO> processer) {
  // //
  // }

  // private void addBeforeRule(AroundProcesser<SoBalanceVO> processer) {
  // //
  // }
}
