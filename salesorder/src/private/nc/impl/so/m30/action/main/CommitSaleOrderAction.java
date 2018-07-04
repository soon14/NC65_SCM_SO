package nc.impl.so.m30.action.main;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.rule.SOPfStatusChgRule;

/**
 * 送审动作
 * 
 * @author gdsjw
 */
public class CommitSaleOrderAction {

  public SaleOrderVO[] sendApprove(SaleOrderVO[] clientBills,
      SaleOrderVO[] originBills) {
    // 审批流状态转换为单据状态
    for (SaleOrderVO newvo : clientBills) {
      SOPfStatusChgRule statuschgrule = new SOPfStatusChgRule();
      statuschgrule.changePfToBillStatus(newvo);

    }
    // 把VO持久化到数据库中
    BillUpdate<SaleOrderVO> update = new BillUpdate<SaleOrderVO>();
    SaleOrderVO[] returnVos = update.update(clientBills, originBills);
    return returnVos;
  }

}
