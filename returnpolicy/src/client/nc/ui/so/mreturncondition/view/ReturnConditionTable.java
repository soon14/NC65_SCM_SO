package nc.ui.so.mreturncondition.view;

import java.awt.Color;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.so.mreturncondition.ref.FomulaDlg;
import nc.ui.so.mreturncondition.ref.FomulaModel;

/**
 * 退货条件设置的UI
 * 
 * @author jianghp
 */
public class ReturnConditionTable extends BatchBillTable {

  private static final long serialVersionUID = 1L;

  private FomulaDlg ivjDlgFormulaEdit;

  @Override
  public void initUI() {
    super.initUI();
    // 置入触发公式界面的参照
    UIRefPane refPane = new UIRefPane() {
      private static final long serialVersionUID = 1L;

      @Override
      public void onButtonClicked() {
        ReturnConditionTable.this.getDlgFormulaEdit()
            .setName("FormulaSetParUI");
        if (this.getText() != null) {
          Object o =
              ReturnConditionTable.this
                  .getBillCardPanel()
                  .getBillModel()
                  .getValueAt(
                      ReturnConditionTable.this.getBillCardPanel()
                          .getBillTable().getSelectedRow(), "vexpressname");
          Object ocode =
              ReturnConditionTable.this
                  .getBillCardPanel()
                  .getBillModel()
                  .getValueAt(
                      ReturnConditionTable.this.getBillCardPanel()
                          .getBillTable().getSelectedRow(), "vexpresscode");
          if (o == null) {
            o = "";
          }
          if (ocode == null) {
            ocode = "";
          }
          ReturnConditionTable.this.getDlgFormulaEdit().setCurFormulaShow(
              new String[] {
                ocode.toString(), o.toString()
              });
        }
        int iResult = ReturnConditionTable.this.getDlgFormulaEdit().showModal();
        ReturnConditionTable.this.getDlgFormulaEdit();
        if (iResult == UIDialog.ID_OK) {
          this.setValue(ReturnConditionTable.this.getDlgFormulaEdit()
              .getFormulaDesc());
          int irow =
              ReturnConditionTable.this.getBillCardPanel().getBillTable()
                  .getSelectedRow();
          ReturnConditionTable.this.getBillCardPanel().setBodyValueAt(
              ReturnConditionTable.this.getDlgFormulaEdit().getFormulaShow(),
              irow, "vexpresscode");
          ReturnConditionTable.this.getBillCardPanel().setBodyValueAt(
              ReturnConditionTable.this.getDlgFormulaEdit().getFormulaDesc(),
              irow, "vexpressname");
        }
      }
    };

    refPane.getUITextField().setEditable(false);
    refPane.getUITextField().setBackground(Color.white);
    this.getBillCardPanel().getBodyItem("vexpressname").setComponent(refPane);
    this.getBillCardPanel().getBodyItem("pk_org").setEdit(false);
  }

  FomulaDlg getDlgFormulaEdit() {
    if (this.ivjDlgFormulaEdit == null) {
      this.ivjDlgFormulaEdit = new FomulaDlg(this);
      this.ivjDlgFormulaEdit.setName("DlgFormulaEdit");
      this.ivjDlgFormulaEdit.setModel(new FomulaModel(this.getModel()
          .getContext()));
    }
    return this.ivjDlgFormulaEdit;
  }

}
