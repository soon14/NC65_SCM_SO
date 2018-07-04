package nc.ui.so.m30.billui.cash.actions;

import java.awt.event.ActionEvent;

import nc.bs.uif2.IActionCode;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.IFuncletWindow;
import nc.ui.so.m30.billui.cash.model.CashSaleArsubModel;
import nc.ui.so.m30.billui.cash.model.CashSaleSobalanceModel;
import nc.ui.so.m30.billui.cash.view.CashSaleArsubView;
import nc.ui.so.m30.billui.cash.view.CashSaleSobalanceView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.actions.ActionInitializer;

public class CancelAction extends NCAction {

  private static final long serialVersionUID = 6518328837156298097L;

  private CashSaleArsubView arsublistView;

  private CashSaleArsubModel arsubmodel;

  private CashSaleSobalanceView sobalanceListView;

  private CashSaleSobalanceModel sobalancemodel;

  public CancelAction() {
    super();
    ActionInitializer.initializeAction(this, IActionCode.CANCEL);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    IFuncletWindow window =
        WorkbenchEnvironment.getInstance().findOpenedFuncletWindow("40060399");
    window.closeWindow();
  }

  public CashSaleArsubView getArsublistView() {
    return this.arsublistView;
  }

  public CashSaleArsubModel getArsubmodel() {
    return this.arsubmodel;
  }

  public CashSaleSobalanceView getSobalanceListView() {
    return this.sobalanceListView;
  }

  public CashSaleSobalanceModel getSobalancemodel() {
    return this.sobalancemodel;
  }

  public void setArsublistView(CashSaleArsubView arsublistView) {
    this.arsublistView = arsublistView;
  }

  public void setArsubmodel(CashSaleArsubModel arsubmodel) {
    this.arsubmodel = arsubmodel;
  }

  public void setSobalanceListView(CashSaleSobalanceView sobalanceListView) {
    this.sobalanceListView = sobalanceListView;
  }

  public void setSobalancemodel(CashSaleSobalanceModel sobalancemodel) {
    this.sobalancemodel = sobalancemodel;
  }

  @Override
  protected boolean isActionEnable() {
    return super.isActionEnable();
  }

}
