/**
 * 
 */
package nc.impl.so.m30.sobalance.action;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.so.m30.sobalance.maintain.IInsertBP;
import nc.impl.so.m30.sobalance.maintain.SobalanceBPFactoryForSoManual;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * 新增保存动作
 * 
 * @author gdsjw
 * 
 */
public class InsertAction {
  public SoBalanceVO[] insert(SoBalanceVO[] bills) {

    TimeLog.logStart();
    BillTransferTool<SoBalanceVO> transferTool =
        new BillTransferTool<SoBalanceVO>(bills);
    SoBalanceVO[] insertbills = transferTool.getClientFullInfoBill();

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
    IInsertBP action =
        SobalanceBPFactoryForSoManual.getInstance().getInsertBP();
    SoBalanceVO[] vos = action.insert(insertbills);

    TimeLog.info("调用新增保存BP，进行保存"); /*-=notranslate=-*/

    // TimeLog.getInstance().logStart();
    // processer.after(vos);
    /*-=notranslate=-*/

    TimeLog.logStart();
    vos = transferTool.getBillForToClient(vos);

    TimeLog.info("组织返回值,返回轻量级VO"); /*-=notranslate=-*/

    return vos;
  }

  // private void addAfterRule(AroundProcesser<SoBalanceVO> processer) {
  // //
  // }

  // private void addBeforeRule(AroundProcesser<SoBalanceVO> processer) {
  //
  // }

}
