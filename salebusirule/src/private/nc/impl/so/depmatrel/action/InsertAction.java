/**
 * 
 */
package nc.impl.so.depmatrel.action;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.so.depmatrel.maintain.InsertBP;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.depmatrel.entity.DepMatRelVO;

/**
 * 新增保存动作
 * 
 * @author gdsjw
 * 
 */
public class InsertAction {
  public DepMatRelVO insert(DepMatRelVO bill) {
    DepMatRelVO[] bills = new DepMatRelVO[] {
      bill
    };

    TimeLog.logStart();
    BillTransferTool<DepMatRelVO> transferTool =
        new BillTransferTool<DepMatRelVO>(bills);

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
    /*-=notranslate=-*/

    TimeLog.logStart();
    InsertBP action = new InsertBP();
    DepMatRelVO[] vos = action.insert(bills);

    TimeLog.info("调用新增保存BP，进行保存"); /*-=notranslate=-*/

    // TimeLog.getInstance().logStart();
    // processer.after(vos);
    /*-=notranslate=-*/

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
