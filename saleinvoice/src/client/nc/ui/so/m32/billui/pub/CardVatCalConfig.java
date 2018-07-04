package nc.ui.so.m32.billui.pub;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.SaleInvoiceVOCalculator;

import nc.itf.scmpub.vattax.ICalculateVat;

import nc.ui.pub.bill.BillCardPanel;

/**
 * 销售发票VAT
 * 
 * @since 6.1
 * @version 2012-11-20 18:45:09
 * @author 冯加彬
 */
public class CardVatCalConfig implements ICalculateVat<SaleInvoiceBVO> {

  private BillCardPanel cardPanel;

  /**
   * 构造子
   * 
   * @param cardPanel
   */
  public CardVatCalConfig(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
  }

  @Override
  public void processApportValue(int index, String changeKey, boolean isTaxFirst)
      throws BusinessException {
    CardPanelCalculator calculator = new CardPanelCalculator(this.cardPanel);
    calculator.setForceTaxPrior(UFBoolean.valueOf(isTaxFirst));
    calculator.calculate(index, changeKey);
  }

  @Override
  public void processMergeVO(SaleInvoiceBVO newbvo, String changeKey,
      boolean isTaxFirst) throws BusinessException {
    SaleInvoiceHVO head =
        (SaleInvoiceHVO) this.cardPanel.getBillData().getHeaderValueVO(
            SaleInvoiceHVO.class.getName());
    SaleInvoiceVO billvo = new SaleInvoiceVO();
    billvo.setParent(head);
    SaleInvoiceBVO[] bodys = new SaleInvoiceBVO[] {
      newbvo
    };
    billvo.setChildrenVO(bodys);

    SaleInvoiceVOCalculator calculator = new SaleInvoiceVOCalculator(billvo);
    calculator.setForcefixunitrate(UFBoolean.valueOf(isTaxFirst));
    calculator.calculate(0, changeKey);
  }
}
