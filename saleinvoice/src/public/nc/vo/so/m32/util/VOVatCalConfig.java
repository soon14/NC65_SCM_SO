package nc.vo.so.m32.util;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.itf.scmpub.vattax.ICalculateVat;

public class VOVatCalConfig implements ICalculateVat<SaleInvoiceBVO> {

  private SaleInvoiceVO invoicevo;

  /**
   * ¹¹Ôì×Ó
   * 
   * @param invoicevo
   */
  public VOVatCalConfig(SaleInvoiceVO invoicevo) {
    this.invoicevo = invoicevo;
  }

  @Override
  public void processApportValue(int index, String changeKey, boolean isTaxFirst)
      throws BusinessException {
    SaleInvoiceVOCalculator calcultor =
        new SaleInvoiceVOCalculator(this.invoicevo);
    calcultor.setForceTaxPrior(UFBoolean.valueOf(isTaxFirst));
    calcultor.calculate(index, changeKey);
  }

  @Override
  public void processMergeVO(SaleInvoiceBVO newbvo, String changeKey,
      boolean isTaxFirst) throws BusinessException {
    SaleInvoiceHVO head = this.invoicevo.getParentVO();
    SaleInvoiceVO newvo = new SaleInvoiceVO();
    newvo.setParentVO(head);
    SaleInvoiceBVO[] bodys = new SaleInvoiceBVO[] {
      newbvo
    };
    newvo.setChildrenVO(bodys);

    SaleInvoiceVOCalculator calcultor = new SaleInvoiceVOCalculator(newvo);
    calcultor.setForceTaxPrior(UFBoolean.valueOf(isTaxFirst));
    calcultor.calculate(0, changeKey);
  }
}
