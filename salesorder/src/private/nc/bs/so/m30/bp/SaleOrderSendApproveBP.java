package nc.bs.so.m30.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 
 * @since 6.0
 * @version 2011-3-10 下午02:28:06
 * @author 么贵敬
 */
public class SaleOrderSendApproveBP {

  public SaleOrderVO[] sendApprove(SaleOrderVO[] clientBills,
      SaleOrderVO[] originBills) {
    // 把VO持久化到数据库中
    BillUpdate<SaleOrderVO> update = new BillUpdate<SaleOrderVO>();
    SaleOrderVO[] returnVos = update.update(clientBills, originBills);
    return returnVos;
  }
}
