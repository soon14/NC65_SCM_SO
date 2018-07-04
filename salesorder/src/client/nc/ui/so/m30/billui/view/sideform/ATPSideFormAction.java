package nc.ui.so.m30.billui.view.sideform;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.ml.NCLangRes;
import nc.ui.uif2.IExceptionHandler;
import nc.ui.uif2.NCAction;

public class ATPSideFormAction extends NCAction {

  private static final long serialVersionUID = -2878003392171198994L;

  ATPSideForm sideForm;

  public ATPSideFormAction(ATPSideForm sideForm,
      IExceptionHandler exceptionHandler) {
    this.sideForm = sideForm;
    this.putValue(Action.NAME,
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0263")/*查看当前存量*/);
    this.putValue(Action.SHORT_DESCRIPTION,
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0264")/* 查看当前选中的行的现存量*/);
    super.exceptionHandler = exceptionHandler;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.sideForm.display();
  }

}
