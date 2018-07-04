package nc.ui.so.salequotation.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.pflow.ScriptPFlowAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.UIState;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * 打开按钮
 * 
 * @author chenyyb
 */
public class SalequoOpenAction extends ScriptPFlowAction {

  /**
   * 
   */
  private static final long serialVersionUID = 6450347234481734278L;

  public SalequoOpenAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_BILLOPEN);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
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
        && billStatus.intValue() == BillStatusEnum.C_CLOSE;
  }
}
