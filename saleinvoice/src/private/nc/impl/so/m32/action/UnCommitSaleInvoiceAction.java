package nc.impl.so.m32.action;

import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;

/**
 * 销售发票收回功能后台实现
 * 
 * @since 6.0
 * @version 2011-2-22 上午10:46:25
 * @author 么贵敬
 */
public class UnCommitSaleInvoiceAction {

  /**
   * 销售发票收回 后台处理逻辑
   * 
   * @param clientBills 销售发票VO
   * @param originBills 销售发票VO
   * @return 销售发票VO
   */
  public SaleInvoiceVO[] unSend(SaleInvoiceVO[] clientBills,
      SaleInvoiceVO[] originBills) {
    // 把VO持久化到数据库中
    BillUpdate<SaleInvoiceVO> update = new BillUpdate<SaleInvoiceVO>();
    SaleInvoiceVO[] returnVos = update.update(clientBills, originBills);
    return returnVos;
  }

}
