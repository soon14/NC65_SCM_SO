package nc.ui.so.m32.billui.action.line;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.BodyPasteLineAction;
import nc.ui.so.m32.billui.pub.CardVATCalculator;

public class InvoiceBodyPasteLineAction extends BodyPasteLineAction {

  @Override
  public void doAction() {
    BillCardPanel cardpanel = this.getCardPanel();
    int selectedRow = cardpanel.getBodyPanel().getTable().getSelectedRow();
    int pasteLineNumer = cardpanel.getBillModel().getPasteLineNumer();
    int[] rows = null;
    if (pasteLineNumer > 0) {
      rows = new int[pasteLineNumer];
      for (int i = 0; i < pasteLineNumer; i++) {
        rows[i] = selectedRow + i;
      }
    }
    super.doAction();
    if (null != rows) {
      CardVATCalculator vatcal = new CardVATCalculator(cardpanel);
      for (int row : rows) {
        vatcal.calculateVatWhenAddLine(row);
      }
    }
  }
}
