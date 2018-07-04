package nc.vo.so.m32.entity;

import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;

public class SaleInvoiceViewMeta extends DataViewMeta {
  public SaleInvoiceViewMeta() {
    super();
    this.add(SaleInvoiceBVO.class);
    this.add(SaleInvoiceHVO.class);
    this.addRelation(SaleInvoiceBVO.class, SaleInvoiceBVO.CSALEINVOICEID,
        SaleInvoiceHVO.class, SaleInvoiceHVO.CSALEINVOICEID);
  }
}
