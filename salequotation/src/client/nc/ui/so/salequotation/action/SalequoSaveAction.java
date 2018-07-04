package nc.ui.so.salequotation.action;

import nc.bs.uif2.validation.IValidationService;
import nc.bs.uif2.validation.ValidationException;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class SalequoSaveAction extends SaveScriptAction {

  /**
   * 
   */
  private static final long serialVersionUID = -2283816717245269445L;

  private IValidationService validationService;

  public IValidationService getValidationService() {
    return this.validationService;
  }

  public void setValidationService(IValidationService validationService) {
    this.validationService = validationService;
  }

  @Override
  protected void beforeCheck(Object vo) {
    super.beforeCheck(vo);
    try {
      this.getValidationService().validate(vo);
    }
    catch (ValidationException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
