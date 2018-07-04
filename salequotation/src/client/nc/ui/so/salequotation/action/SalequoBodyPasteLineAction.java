package nc.ui.so.salequotation.action;

import nc.bs.framework.common.NCLocator;
import nc.itf.price.priceform.IPriceFormService;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.actions.BodyPasteLineAction;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.salequotation.entity.SalequotationBVO;

public class SalequoBodyPasteLineAction extends BodyPasteLineAction {

  /**
   * 
   */
  private static final long serialVersionUID = 9053075868209050379L;

  private IPriceFormService priceFormService;

  @Override
  public void doAction() {
    super.doAction();
    BillScrollPane bsp = this.getCardPanel().getBodyPanel();
    int rowlength = bsp.getTableModel().getPasteLineNumer();
    String[] priceForms = new String[rowlength];
    for (int l = 0; l < rowlength; l++) {
      priceForms[l] =
          (String) this
              .getCardPanel()
              .getBillModel()
              .getValueAt(this.lastPastedRow() - l,
                  SalequotationBVO.VPRICEDETAIL);
    }
    try {
      this.getPriceFormService().copyPriceForm(priceForms);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private IPriceFormService getPriceFormService() {
    if (this.priceFormService == null) {
      this.priceFormService =
          NCLocator.getInstance().lookup(IPriceFormService.class);
    }
    return this.priceFormService;
  }

}
