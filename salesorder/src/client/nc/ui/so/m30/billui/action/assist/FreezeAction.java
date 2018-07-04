package nc.ui.so.m30.billui.action.assist;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.IActionCode;
import nc.itf.so.m30.closemanage.ISaleOrderCloseManageMaintain;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.actions.ActionInitializer;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 销售订单冻结
 * 
 * @since 6.0
 * @version 2012-5-2 下午03:52:05
 * @author 么贵敬
 */
public class FreezeAction extends NCAction {
  private static final long serialVersionUID = -2665746434561419509L;

  private AbstractAppModel model;

  public FreezeAction() {
    ActionInitializer.initializeAction(this, IActionCode.FREEZE);
  }

  @Override
  protected boolean isActionEnable() {
    // 没有选中单据，置灰
    boolean isedit = true;
    if (this.model.getSelectedData() == null) {
      isedit = false;
    }
    else {
      SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
      // 非审批状态的单据，按钮不可用
      Integer fstatusflag = vo.getParentVO().getFstatusflag();
      if (!BillStatus.AUDIT.equalsValue(fstatusflag)) {
        isedit = false;
      }
    }
    return isedit;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object obj = this.model.getSelectedData();
    SaleOrderVO[] bills = new SaleOrderVO[] {
      (SaleOrderVO) obj
    };
    SaleOrderVO[] ret = null;
    ISaleOrderCloseManageMaintain service =
        NCLocator.getInstance().lookup(ISaleOrderCloseManageMaintain.class);
    try {
      ret = service.freezeSaleOrder(bills);
      // 后台变化VO与前台合并
      ClientBillCombinServer<SaleOrderVO> util =
          new ClientBillCombinServer<SaleOrderVO>();
      util.combine(bills, ret);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    this.model.directlyUpdate(bills);
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }
}
