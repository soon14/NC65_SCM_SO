package nc.ui.so.salequotation.findprice;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;

public class BodySelectedRowsGetter {

  private BillCardPanel billCardPanel;

  public BodySelectedRowsGetter(
      BillCardPanel billCardPanel) {
    super();
    this.billCardPanel = billCardPanel;
  }

  public int[] getSelectedRows() {
    int[] selectedRows = this.billCardPanel.getBillTable().getSelectedRows();
    if (selectedRows.length == 0) {
      selectedRows = this.getAllRows();
    }
    return selectedRows;
  }

  public int[] getAllRows() {
    int[] selectedRows = new int[this.billCardPanel.getRowCount()];
    for (int i = 0; i < selectedRows.length; i++) {
      selectedRows[i] = i;
    }
    return selectedRows;
  }

  public int[] getSelectedRows(BillListPanel listPanel) {
    int[] selectedRows;
    if (this.billCardPanel.isShowing()) {
      // 取得选中行
      selectedRows = this.billCardPanel.getBillTable().getSelectedRows();
      if (selectedRows.length == 0) {
        selectedRows = new int[this.billCardPanel.getRowCount()];
        for (int i = 0; i < this.billCardPanel.getRowCount(); i++) {
          selectedRows[i] = i;
        }
      }
    }
    else {
      selectedRows = listPanel.getBodyTable().getSelectedRows();
      if (selectedRows.length == 0) {
        selectedRows = new int[listPanel.getBodyTable().getRowCount()];
        for (int i = 0; i < listPanel.getBodyTable().getRowCount(); i++) {
          selectedRows[i] = i;
        }
      }
    }
    return selectedRows;
  }
}
