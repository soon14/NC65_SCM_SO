package nc.bs.so.m32.maintain;

import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;

/**
 * 送审动作
 * 
 * @author gdsjw
 */
public class CommitSaleInvoiceBP {

  /**
   * 送审
   * 
   * @param clientBills 前台传过来的vo
   * @param originBills 数据库原始vo
   * @return 操作后的vo
   */
  public SaleInvoiceVO[] sendApprove(SaleInvoiceVO[] clientBills,
      SaleInvoiceVO[] originBills) {
    // 把VO持久化到数据库中
    BillUpdate<SaleInvoiceVO> update = new BillUpdate<SaleInvoiceVO>();
    SaleInvoiceVO[] returnVos = update.update(clientBills, originBills);
    return returnVos;
  }

}
