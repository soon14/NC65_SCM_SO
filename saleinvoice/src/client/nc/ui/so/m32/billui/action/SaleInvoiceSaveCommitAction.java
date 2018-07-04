package nc.ui.so.m32.billui.action;

import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;

/**
 * 保存提交按钮
 * 
 * @since 6.0
 * @version 2012-3-2 上午10:29:49
 * @author 么贵敬
 */
public class SaleInvoiceSaveCommitAction extends SaveAndCommitScriptAction {

  /**
   * 构造方法
   * 
   * @param pSaveAction
   * @param pCommitAction
   */
  public SaleInvoiceSaveCommitAction(SaveScriptAction pSaveAction,
      CommitScriptAction pCommitAction) {
    super(pSaveAction, pCommitAction);
    // TODO Auto-generated constructor stub
  }

  private static final long serialVersionUID = -7753657951996431809L;

  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getAppUiState() == AppUiState.EDIT
        || this.getModel().getAppUiState() == AppUiState.ADD
        || this.getModel().getAppUiState() == AppUiState.TRANSFERBILL_ADD) {
      return true;
    }
    boolean isEnable =
        this.getModel().getAppUiState() == AppUiState.NOT_EDIT
            && null != this.getModel().getSelectedData();

    if (isEnable) {
      Object[] selectedRows = this.getModel().getSelectedOperaDatas();
      SaleInvoiceVO selectedData =
          (SaleInvoiceVO) this.getModel().getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();
      if (null == selectedRows) {
        isEnable = BillStatus.FREE.equalsValue(billstatus);
      }
      else {
        isEnable =
            selectedRows.length > 1 || BillStatus.FREE.equalsValue(billstatus);
      }

    }
    return isEnable;
  }

}
