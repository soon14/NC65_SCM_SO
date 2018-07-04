package nc.ui.so.salequotation.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;

/**
 * 报价单编辑态保存提交按钮
 * 
 * @since 6.1
 * @version 2012-03-05 上午10:49:52
 * @author 王天文
 * 
 */
public class SalequoSaveCommitAction extends SaveAndCommitScriptAction {

  public SalequoSaveCommitAction(SaveScriptAction pSaveAction,
      CommitScriptAction pCommitAction) {
    super(pSaveAction, pCommitAction);
    // TODO Auto-generated constructor stub
  }

  private static final long serialVersionUID = 5246191191902679124L;

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
