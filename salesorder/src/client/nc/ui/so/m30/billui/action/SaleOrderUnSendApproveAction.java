package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.fixblob.ReQuery2FixBlob;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 收回按钮类
 * 
 * @since 6.0
 * @version 2011-2-22 上午10:37:57
 * @author 么贵敬
 */
public class SaleOrderUnSendApproveAction extends UnCommitScriptAction {

  /** Version */
  private static final long serialVersionUID = 4549098173080532064L;

  @Override
  protected boolean isActionEnable() {
    boolean isEnable =
        this.getModel().getAppUiState() == AppUiState.NOT_EDIT
            && null != this.getModel().getSelectedData();

    if (isEnable) {
      Object[] selectedRows = this.getModel().getSelectedOperaDatas();
      SaleOrderVO selectedData =
          (SaleOrderVO) this.getModel().getSelectedData();
      Integer billstatus = selectedData.getParentVO().getFstatusflag();
      String approver = selectedData.getParentVO().getApprover();

      isEnable =
          (null != selectedRows && selectedRows.length > 1) || null == approver
              && BillStatus.AUDITING.equalsValue(billstatus);
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
