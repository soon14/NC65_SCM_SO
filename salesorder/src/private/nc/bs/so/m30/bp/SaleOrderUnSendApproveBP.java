package nc.bs.so.m30.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.rule.SOPfStatusChgRule;

/**
 * 
 * @since 6.0
 * @version 2011-3-10 下午02:28:06
 * @author 么贵敬
 */
public class SaleOrderUnSendApproveBP {

  public SaleOrderVO[] unSend(SaleOrderVO[] clientBills,
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
