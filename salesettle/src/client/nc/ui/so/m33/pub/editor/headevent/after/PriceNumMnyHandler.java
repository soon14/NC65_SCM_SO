package nc.ui.so.m33.pub.editor.headevent.after;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.event.list.ListBodyAfterEditEvent;
import nc.ui.so.m33.pub.SquareModelCalculator;

public class PriceNumMnyHandler {

  public void afterEdit(ListBodyAfterEditEvent e) {

    BillListPanel listPanel = e.getBillListPanel();
    int[] eidtrows = new int[] {
      e.getRow()
    };
    SquareModelCalculator calculator = new SquareModelCalculator(listPanel);
    calculator.calculate(eidtrows, e.getKey());
  }
}
