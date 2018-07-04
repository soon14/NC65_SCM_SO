package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.fixblob.ReQuery2FixBlob;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 保存提交按钮
 * 
 * @since 6.0
 * @version 2012-3-1 下午03:18:16
 * @author 么贵敬
 */
public class SaleOrderSaveAndSendAction extends SaveAndCommitScriptAction {

  public SaleOrderSaveAndSendAction(SaveScriptAction pSaveAction,
      CommitScriptAction pCommitAction) {
    super(pSaveAction, pCommitAction);
  }

  private static final long serialVersionUID = -4763547987241220481L;

  // private RefreshSingleAction refreshAction;
  //
  // public RefreshSingleAction getRefreshAction() {
  // return this.refreshAction;
  // }
  //
  // public void setRefreshAction(RefreshSingleAction refreshAction) {
  // this.refreshAction = refreshAction;
  // }

  // public Set<String> getResumeExcType() {
  // return this.resumeExcType;
  // }
  //
  // public void setResumeExcType(Set<String> resumeExcType) {
  // this.resumeExcType = resumeExcType;
  // }

  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getAppUiState() == AppUiState.EDIT
        || this.getModel().getAppUiState() == AppUiState.ADD
        || this.getModel().getAppUiState() == AppUiState.COPY_ADD
        || this.getModel().getAppUiState() == AppUiState.TRANSFERBILL_ADD) {
      return true;
    }
    boolean isEnable =
        this.getModel().getAppUiState() == AppUiState.NOT_EDIT
            && null != this.getModel().getSelectedData();

    if (isEnable) {
      Object[] selectedRows = this.getModel().getSelectedOperaDatas();
      SaleOrderVO selectedData =
          (SaleOrderVO) this.getModel().getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();

      isEnable =
          null != selectedRows && selectedRows.length > 1
              || BillStatus.FREE.equalsValue(billstatus);
    }
    return isEnable;
  }
	/**
	 * @author wangzy
	 * @date:2018-05-18
	 * @Description: 做一下重新查询
	 */
	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		super.doAction(e);
		ReQuery2FixBlob.reFreshDate(model);
	}

}
