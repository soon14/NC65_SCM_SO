package nc.vo.so.m32.entity;

import nc.vo.annotation.AggVoInfo;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@AggVoInfo(parentVO = "nc.vo.so.m32.entity.SaleInvoiceHVO")
public class SaleInvoiceVO extends AbstractBill {

  /**
     * 
     */
  private static final long serialVersionUID = 266232438347908890L;

  @Override
  public SaleInvoiceBVO[] getChildrenVO() {
    return (SaleInvoiceBVO[]) super.getChildrenVO();
  }

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta("so.saleinvoice");
    return billMeta;
  }

  @Override
  public SaleInvoiceHVO getParentVO() {
    return (SaleInvoiceHVO) super.getParentVO();
  }

}
