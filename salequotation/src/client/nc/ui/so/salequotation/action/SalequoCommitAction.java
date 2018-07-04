package nc.ui.so.salequotation.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;

public class SalequoCommitAction extends CommitScriptAction {

  /**
   *
   */
  private static final long serialVersionUID = 5246191191902679124L;

  @Override
  protected void beforeCheck(Object vo) {
    super.beforeCheck(vo);
  }

  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getAppUiState() == AppUiState.EDIT
        || this.getModel().getAppUiState() == AppUiState.ADD
        || this.getModel().getAppUiState() == AppUiState.COPY_ADD) {
      return true;
    }
    boolean isEnable =
        this.getModel().getAppUiState() == AppUiState.NOT_EDIT
            && null != this.getModel().getSelectedData();

    if (isEnable) {
      Object[] selectedRows = this.getModel().getSelectedOperaDatas();
      AggSalequotationHVO selectedData =
          (AggSalequotationHVO) this.getModel().getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();

      isEnable =
          null != selectedRows && selectedRows.length > 1
              || BillStatusEnum.C_FREE == billstatus.intValue();
    }
    return isEnable;
  }
}
