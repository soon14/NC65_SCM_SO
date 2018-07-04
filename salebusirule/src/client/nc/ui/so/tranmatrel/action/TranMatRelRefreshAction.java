/**
 * 
 */
package nc.ui.so.tranmatrel.action;

import java.awt.event.ActionEvent;

import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.actions.RefreshAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.BatchBillTableModel;
import nc.ui.uif2.model.BillManageModel;

/**
 * @author gdsjw
 * 
 */
@SuppressWarnings("serial")
public class TranMatRelRefreshAction extends RefreshAction {

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
  }

  @Override
  protected void showQueryInfo() {
    int size = 0;
    if (this.getModel() instanceof BillManageModel) {
      size = ((BillManageModel) this.getModel()).getData().size();

    }
    else if (this.getModel() instanceof BatchBillTableModel) {
      size = ((BatchBillTableModel) this.getModel()).getRows().size();
    }
    if (size > 0) {
      ShowStatusBarMsgUtil.showStatusBarMsg(NCLangRes.getInstance().getStrByID("4006007_0", "04006007-0010", null, new String[]{Integer.toString(size)})/*刷新完成，共刷新{0}张单据。*/, this
          .getModel().getContext());
    }
    else {
      ShowStatusBarMsgUtil.showStatusBarMsg(NCLangRes.getInstance().getStrByID("4006007_0", "04006007-0010", null, new String[]{Integer.toString(size)})/*刷新完成，共刷新{0}张单据。*/, this
          .getModel().getContext());
    }
  }
}
