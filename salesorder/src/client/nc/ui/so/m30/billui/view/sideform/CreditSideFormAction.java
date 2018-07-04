package nc.ui.so.m30.billui.view.sideform;

import java.awt.event.ActionEvent;

import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.IExceptionHandler;
import nc.ui.uif2.NCAction;
import nc.vo.scmpub.res.SCMActionCode;

public class CreditSideFormAction extends NCAction {

  private static final long serialVersionUID = -2878003392171198994L;

  CreditSideForm sideForm;

  public CreditSideFormAction(CreditSideForm sideForm,
      IExceptionHandler exceptionHandler) {
    SCMActionInitializer.initializeAction(this, SCMActionCode.QUERYCUSTCREDIT);
    this.sideForm = sideForm;
    super.exceptionHandler = exceptionHandler;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.sideForm.display();
  }
}
