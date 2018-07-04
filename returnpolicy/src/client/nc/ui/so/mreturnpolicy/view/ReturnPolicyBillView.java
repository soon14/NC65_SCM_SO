package nc.ui.so.mreturnpolicy.view;

import java.awt.Color;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.so.mreturnpolicy.ref.FomulaDialog;
import nc.ui.so.mreturnpolicy.ref.FomulaModel;

public class ReturnPolicyBillView extends BatchBillTable {
  private static final long serialVersionUID = 1L;

  private FomulaDialog ivjDlgFormulaEdit;

  @Override
  public void initUI() {
    super.initUI();
    // 置入触发公式界面的参照
    UIRefPane refPane = new UIRefPane() {
      private static final long serialVersionUID = 1L;

      @Override
      public void onButtonClicked() {
        ReturnPolicyBillView.this.getDlgFormulaEdit()
            .setName("FormulaSetParUI");
        if (this.getText() != null) {
          Object o =
              ReturnPolicyBillView.this
                  .getBillCardPanel()
                  .getBillModel()
                  .getValueAt(
                      ReturnPolicyBillView.this.getBillCardPanel()
                          .getBillTable().getSelectedRow(), "vexpressname");
          Object ocode =
              ReturnPolicyBillView.this
                  .getBillCardPanel()
                  .getBillModel()
                  .getValueAt(
                      ReturnPolicyBillView.this.getBillCardPanel()
                          .getBillTable().getSelectedRow(), "vexpresscode");
          if (o == null) {
            o = "";
          }
          if (ocode == null) {
            ocode = "";
          }
          ReturnPolicyBillView.this.getDlgFormulaEdit().setCurFormulaShow(
              new String[] {
                ocode.toString(), o.toString()
              });
        }
        int iResult = ReturnPolicyBillView.this.getDlgFormulaEdit().showModal();
        ReturnPolicyBillView.this.getDlgFormulaEdit();
        if (iResult == UIDialog.ID_OK) {
          this.setValue(ReturnPolicyBillView.this.getDlgFormulaEdit()
              .getFormulaDesc());
          int irow =
              ReturnPolicyBillView.this.getBillCardPanel().getBillTable()
                  .getSelectedRow();
          ReturnPolicyBillView.this
              .getBillCardPanel()
              .getBillModel()
              .setValueAt(
                  ReturnPolicyBillView.this.getDlgFormulaEdit()
                      .getFormulaDesc(),
                  ReturnPolicyBillView.this.getBillCardPanel().getBillTable()
                      .getSelectedRow(), "vexpressname");
          ReturnPolicyBillView.this.getBillCardPanel().setBodyValueAt(
              ReturnPolicyBillView.this.getDlgFormulaEdit().getFormulaShow(),
              irow, "vexpresscode");
        }
      }
    };
    refPane.getUITextField().setEditable(false);
    refPane.getUITextField().setBackground(Color.white);
    this.getBillCardPanel().getBodyItem("vexpressname").setComponent(refPane);
    // 置入触发公式界面的参照
    this.getBillCardPanel().getBodyItem("pk_org").setEdit(false);
  }

  FomulaDialog getDlgFormulaEdit() {
    if (this.ivjDlgFormulaEdit == null) {
      this.ivjDlgFormulaEdit = new FomulaDialog(this);
      this.ivjDlgFormulaEdit.setName("DlgFormulaEdit");
      this.ivjDlgFormulaEdit.setModel(new FomulaModel(this.getModel()
          .getContext()));
    }
    return this.ivjDlgFormulaEdit;
  }
}
