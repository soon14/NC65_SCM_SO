/**
 * 
 */
package nc.impl.so.custmatrel.action;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.so.custmatrel.maintain.InsertBP;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * 新增保存动作
 * 
 * @author gdsjw
 * 
 */
public class InsertAction {
  public CustMatRelVO insert(CustMatRelVO bill) {
    CustMatRelVO[] bills = new CustMatRelVO[] {
      bill
    };
    TimeLog.logStart();
    BillTransferTool<CustMatRelVO> transferTool =
        new BillTransferTool<CustMatRelVO>(bills);
    // CustMatRelVO[] insertbills = transferTool.getClientFullInfoBill();
    TimeLog.info("得到前台VO"); /*-=notranslate=-*/

    // AroundProcesser<SoBalanceVO> processer = new
    // AroundProcesser<SoBalanceVO>(
    // ActionPlugInPoint.InsertAction);
    // // 增加业务规则
    // this.addBeforeRule(processer);
    // this.addAfterRule(processer);

    // TimeLog.getInstance().logStart();
    // processer.before(bills);
    // TimeLog.getInstance().info("调用新增保存前操作插入点"); /* -=notranslate=- */

    TimeLog.logStart();
    InsertBP action = new InsertBP();
    CustMatRelVO[] vos = action.insert(bills);
    TimeLog.info("调用新增保存BP，进行保存"); /*-=notranslate=-*/

    // TimeLog.getInstance().logStart();
    // processer.after(vos);
    // TimeLog.getInstance().info("调用新增保存后操作插入点"); /* -=notranslate=- */

    TimeLog.logStart();
    vos = transferTool.getBillForToClient(vos);
    TimeLog.info("组织返回值,返回轻量级VO"); /*-=notranslate=-*/

    return vos[0];

  }

  // private void addAfterRule(AroundProcesser<SoBalanceVO> processer) {
  // //
  // }

  // private void addBeforeRule(AroundProcesser<SoBalanceVO> processer) {
  //
  // }

}
