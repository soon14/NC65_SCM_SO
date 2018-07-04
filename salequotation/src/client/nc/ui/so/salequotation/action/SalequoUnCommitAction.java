package nc.ui.so.salequotation.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoUnCommitAction extends UnCommitScriptAction {

  /**
   *
   */
  private static final long serialVersionUID = -5375185829559231433L;

  @Override
  protected boolean isActionEnable() {
    // 多选时按钮都可用
    if (this.getModel().getSelectedOperaRows() != null
        && this.getModel().getSelectedOperaRows().length > 1) {
      return true;
    }
    boolean uiStatusFlag =
        this.getModel().getAppUiState() == AppUiState.NOT_EDIT
            || this.getModel().getAppUiState() == AppUiState.INIT;
    Integer billStatus = null;
    AggSalequotationHVO aggVO =
        (AggSalequotationHVO) this.getModel().getSelectedData();
    if (aggVO != null) {
      SalequotationHVO hvo = aggVO.getParentVO();
      if (hvo != null) {
        billStatus = hvo.getFstatusflag();
      }
    }
    return uiStatusFlag && billStatus != null
        && billStatus.intValue() == BillStatusEnum.C_AUDITING;
  }
}
