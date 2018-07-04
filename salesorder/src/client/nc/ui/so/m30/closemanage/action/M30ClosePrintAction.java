package nc.ui.so.m30.closemanage.action;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.uif2.NCAction;

public class M30ClosePrintAction extends NCAction {

  /**
   * 
   */
  private static final long serialVersionUID = 6371264242998675416L;

  protected BatchBillTableModel model;

  private BatchBillTable billTable;

  public M30ClosePrintAction() {
    this.setBtnName(NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0289")/*打印*/);
    this.setCode("print");
    this.putValue(Action.SHORT_DESCRIPTION, NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0289")/*打印*/);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // TODO 自动生成方法存根

  }

  public BatchBillTable getBillTable() {
    return this.billTable;
  }

  public BatchBillTableModel getModel() {
    return this.model;
  }

  public void setBillTable(BatchBillTable billTable) {
    this.billTable = billTable;
  }

  public void setModel(BatchBillTableModel model) {
    this.model = model;
  }

}
