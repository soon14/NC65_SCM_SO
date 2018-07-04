package nc.ui.so.m30.billui.view.sideform;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.ml.NCLangRes;
import nc.ui.uif2.IExceptionHandler;
import nc.ui.uif2.NCAction;

public class PaySideFormAction extends NCAction {

  private static final long serialVersionUID = -2878003392171198994L;

  PaySideForm sideForm;

  public PaySideFormAction(PaySideForm sideForm,
      IExceptionHandler exceptionHandler) {
    this.sideForm = sideForm;
    this.putValue(Action.NAME,
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0274")/*查看预收款余额*/);
    this.putValue(Action.SHORT_DESCRIPTION,
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0275")/* 查看当前单据的预收款余额*/);
    super.exceptionHandler = exceptionHandler;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.sideForm.display();
  }

}
