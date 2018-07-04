/**
 * 
 */
package nc.ui.so.m30.sobalance.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.CancelAction;
import nc.ui.so.m30.sobalance.view.SobalanceBillForm;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * @author gdsjw
 * 
 */
@SuppressWarnings("serial")
public class SobalanceCancelAction extends CancelAction {
  private SobalanceBillForm view;

  public SobalanceBillForm getView() {
    return this.view;
  }

  public void setView(SobalanceBillForm view) {
    this.view = view;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);

    // 没有表体则全部清空
    SoBalanceVO value = (SoBalanceVO) this.getView().getValue();
    SoBalanceBVO[] bodys = value.getChildrenVO();
    if (bodys == null || bodys.length == 0) {
      this.getModel().initModel(null);
    }
  }

}
