package nc.ui.so.pub.para;

import nc.ui.trade.component.ListToListPanel;

public class CombinDefPanel extends ListToListPanel {

  private static final long serialVersionUID = 1L;

  public CombinDefPanel(InvoiceCombinDlg.ParaListItemInfo[] leftfields,
      InvoiceCombinDlg.ParaListItemInfo[] selectedfields) {
    InvoiceCombinDlg.ParaListItemInfo[] rights = selectedfields;
    if (null == selectedfields) {
      rights = new InvoiceCombinDlg.ParaListItemInfo[0];
    }

    this.initData(leftfields, rights);
  }

  public void initData(InvoiceCombinDlg.ParaListItemInfo[] leftfields,
      InvoiceCombinDlg.ParaListItemInfo[] selectedfields) {
    this.setLeftAndRightData(leftfields, selectedfields);
  }

}
