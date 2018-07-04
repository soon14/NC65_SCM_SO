package nc.ui.so.m30.billui.view.sideform;

import java.awt.event.ActionEvent;

import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.IExceptionHandler;
import nc.ui.uif2.NCAction;
import nc.vo.scmpub.res.SCMActionCode;

public class ShowAllAction extends NCAction {

  private static final long serialVersionUID = -5164763470366700119L;

  private SaleOrderSideFormMediator mediator;

  private IExceptionHandler exceptionHandler;

  public ShowAllAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SHOWALL);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    int selectedRow =
        this.mediator.getAtpSideForm().getBillForm().getBillCardPanel()
            .getBillTable().getSelectedRow();
    if (selectedRow < 0) {
      String errorMsg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
              "04006011-0041")/*@res"请选一个订单行！"*/;
      this.exceptionHandler.handlerExeption(new Exception(errorMsg));
    }
    this.mediator.getAtpSideForm().display();
    this.mediator.getPaySideForm().display();
    this.mediator.getCreditSideForm().display();
  }

  public SaleOrderSideFormMediator getMediator() {
    return this.mediator;
  }

  public void setMediator(SaleOrderSideFormMediator mediator) {
    this.mediator = mediator;
  }

  public IExceptionHandler getExceptionHandler() {
    return this.exceptionHandler;
  }

  public void setExceptionHandler(IExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
  }

}
