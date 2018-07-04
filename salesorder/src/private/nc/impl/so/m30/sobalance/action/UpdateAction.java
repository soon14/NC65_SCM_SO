package nc.impl.so.m30.sobalance.action;

import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.so.m30.sobalance.maintain.IUpdateBP;
import nc.impl.so.m30.sobalance.maintain.SobalanceBPFactoryForSoManual;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * 修改保存动作
 * 
 * @author gdsjw
 */
public class UpdateAction {

  public SoBalanceVO[] update(SoBalanceVO[] bills) {

    TimeLog.logStart();
    BillTransferTool<SoBalanceVO> transferTool =
        new BillTransferTool<SoBalanceVO>(bills);
    SoBalanceVO[] updatebills = transferTool.getClientFullInfoBill();
    SoBalanceVO[] originBills = transferTool.getOriginBills();

    TimeLog.info("补全前台VO、获得修改前VO、加锁、检查时间戳"); /*-=notranslate=-*/

    TimeLog.logStart();
    IUpdateBP action =
        SobalanceBPFactoryForSoManual.getInstance().getUpdateBP();
    SoBalanceVO[] vos = action.update(updatebills, originBills);

    TimeLog.info("调用修改保存BP，进行保存"); /*-=notranslate=-*/

    TimeLog.logStart();
    vos = transferTool.getBillForToClient(vos);

    TimeLog.info("组织返回值,返回轻量级VO"); /*-=notranslate=-*/

    return vos;
  }
}
