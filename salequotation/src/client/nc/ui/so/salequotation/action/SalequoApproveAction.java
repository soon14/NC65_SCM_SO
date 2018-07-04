package nc.ui.so.salequotation.action;

import nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction;
import nc.ui.uif2.UIState;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoApproveAction extends ApproveScriptAction {

  /**
   * 
   */
  private static final long serialVersionUID = -7192346113696665194L;

  @Override
  protected boolean isActionEnable() {
    // 多选时按钮都可用
    if (this.getModel().getSelectedOperaRows() != null
        && this.getModel().getSelectedOperaRows().length > 1) {
      return true;
    }
    Integer billStatus = null;
    AggSalequotationHVO aggVO =
        (AggSalequotationHVO) this.getModel().getSelectedData();
    if (aggVO != null) {
      SalequotationHVO hvo = aggVO.getParentVO();
      if (hvo != null) {
        billStatus = hvo.getFstatusflag();
      }
    }
    boolean uiStatusFlag =
        this.getModel().getUiState() == UIState.INIT
            || this.getModel().getUiState() == UIState.NOT_EDIT;
    boolean billStatusFlag =
        billStatus != null
            && (billStatus.intValue() == BillStatusEnum.C_FREE || billStatus
                .intValue() == BillStatusEnum.C_AUDITING);
    return uiStatusFlag && billStatusFlag;
  }
}
