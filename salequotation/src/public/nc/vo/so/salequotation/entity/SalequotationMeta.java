package nc.vo.so.salequotation.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class SalequotationMeta extends AbstractBillMeta {
  public SalequotationMeta() {
    this.setParent(SalequotationHVO.class);
    this.addChildren(SalequotationBVO.class);
  }
}
