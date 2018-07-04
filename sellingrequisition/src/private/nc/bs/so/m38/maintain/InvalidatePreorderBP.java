package nc.bs.so.m38.maintain;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pub.VOStatus;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 预订单失效BP
 * 
 * @since 6.0
 * @version 2011-5-7 下午02:06:05
 * @author 祝会征
 */
public class InvalidatePreorderBP {
  public PreOrderVO[] invalidatePreorder(PreOrderVO[] vos) {
    if (vos == null) {
      return null;
    }
    BillUpdate<PreOrderVO> updateAction = new BillUpdate<PreOrderVO>();
    BillTransferTool<PreOrderVO> transferTool =
        new BillTransferTool<PreOrderVO>(vos);
    PreOrderVO[] originBills = transferTool.getOriginBills();
    PreOrderVO[] fullBills = transferTool.getClientFullInfoBill();

    for (PreOrderVO bill : fullBills) {
      PreOrderHVO hvo = bill.getParentVO();
      // 只有自由状态才可以变成失效状态
      Integer status = hvo.getFstatusflag();
      if (BillStatus.FREE.equalsValue(status)) {
        hvo.setFstatusflag(BillStatus.INVALIDATE.getIntegerValue());
        hvo.setStatus(VOStatus.UPDATED);
      }
    }
    PreOrderVO[] newbills = updateAction.update(fullBills, originBills);
    // 向前台返回只发生了改变的轻量vo
    return transferTool.getBillForToClient(newbills);
  }
}
