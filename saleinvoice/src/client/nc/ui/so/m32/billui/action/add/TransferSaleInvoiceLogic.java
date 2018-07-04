package nc.ui.so.m32.billui.action.add;

import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;

public class TransferSaleInvoiceLogic extends TransferViewProcessor {

  @Override
  public void processBillTransfer(Object[] pbillVOs) {
    super.processBillTransfer(pbillVOs);
    SaleInvoiceEditor editor = (SaleInvoiceEditor) this.getBillForm();
    editor.setCardEditEnable();
    // new SOCardEditableSetter().setHeadEditForRef(editor.getBillCardPanel());
  }

  // @Override
  // public void doTransferAddLogic(Object selectedData) {
  // super.doTransferAddLogic(selectedData);
  // SaleInvoiceEditor editor = (SaleInvoiceEditor) this.getBillForm();
  // editor.setCardEditEnable();
  // new CardEditableSetter().setHeadEditForRef(editor.getBillCardPanel());
  // }

}
