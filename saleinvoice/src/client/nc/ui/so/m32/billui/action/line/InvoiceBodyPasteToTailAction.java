package nc.ui.so.m32.billui.action.line;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m32.util.HeadTotalCalcUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class InvoiceBodyPasteToTailAction extends BodyPasteToTailAction {

  @Override
  public void doAction() {
    BillCardPanel cardpanel = this.getCardPanel();
    int rowCount = cardpanel.getBillModel().getRowCount();
    int pasteLineNumer = cardpanel.getBillModel().getPasteLineNumer();
    int[] rows = null;
    if (pasteLineNumer > 0) {
      rows = new int[pasteLineNumer];
      for (int i = 0; i < pasteLineNumer; i++) {
        rows[i] = rowCount - pasteLineNumer + i;
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
