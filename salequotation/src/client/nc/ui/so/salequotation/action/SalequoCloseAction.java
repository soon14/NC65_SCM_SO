package nc.ui.so.salequotation.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.pflow.ScriptPFlowAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.UIState;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoCloseAction extends ScriptPFlowAction {

  /**
   * 
   */
  private static final long serialVersionUID = -1317060790149858543L;

  public SalequoCloseAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_BILLCLOSE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
  }

  @Override
  protected void batchBeforeCheck() {
    super.batchBeforeCheck();
  }

  @Override
  protected boolean isActionEnable() {
    // 多选时按钮都可用
    if (this.getModel().getSelectedOperaRows() != null
        && this.getModel().getSelectedOperaRows().length > 1) {
      return true;
    }
    Integer billStatus = null;
    AggSalequotationHVO aggVO =
        (AggSalequotationHVO) this.model.getSelectedData();
    if (aggVO != null) {
      SalequotationHVO hvo = aggVO.getParentVO();
      if (hvo != null) {
        billStatus = hvo.getFstatusflag();
      }
    }
    return this.model.getUiState() == UIState.NOT_EDIT && billStatus != null
        && billStatus.intValue() == BillStatusEnum.C_AUDIT;
  }
}
