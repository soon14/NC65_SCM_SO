package nc.ui.so.m30.billui.action.assist;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.IActionCode;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.itf.so.m30.closemanage.ISaleOrderCloseManageMaintain;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.actions.ActionInitializer;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.scmpub.exp.AtpNotEnoughException;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 销售订单解冻
 * 
 * @since 6.0
 * @version 2012-5-2 下午03:54:35
 * @author 么贵敬
 */
public class UnFreezeAction extends NCAction {
  private static final long serialVersionUID = 5369349075521974594L;

  private AbstractAppModel model;

  private Map<String, Boolean> businessCheckMap;

  public UnFreezeAction() {
    ActionInitializer.initializeAction(this, IActionCode.UNFREEZE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    boolean isContinue = true;
    this.businessCheckMap = new HashMap<String, Boolean>();

    while (isContinue) {
      try {
        Object obj = this.model.getSelectedData();
        SaleOrderVO[] bills = new SaleOrderVO[] {
          (SaleOrderVO) obj
        };
        SaleOrderVO[] ret = null;

        ret = this.getServcie().unFreezeSaleOrder(bills, this.businessCheckMap);
        // 后台变化VO与前台合并
        ClientBillCombinServer<SaleOrderVO> util =
            new ClientBillCombinServer<SaleOrderVO>();
        util.combine(bills, ret);

        isContinue = false;
        this.model.directlyUpdate(bills);
      }
      catch (Exception exc) {
        Throwable ex = ExceptionUtils.unmarsh(exc);
        if (ex instanceof IResumeException) {
          if (this.isResume((IResumeException) ex)) {
            isContinue = true;
          }
          else {
            isContinue = false;
          }
        }
        else {
          ExceptionUtils.wrappException(exc);
        }
      }
    }
  }

  private ISaleOrderCloseManageMaintain getServcie() {
    return NCLocator.getInstance().lookup(ISaleOrderCloseManageMaintain.class);
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    // 没有选中单据，置灰
    if (this.model.getSelectedData() == null) {
      return false;
    }
    SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
    // 非冻结状态的单据，按钮不可用
    Integer fstatusflag = vo.getParentVO().getFstatusflag();
    if (!BillStatus.FREEZE.equalsValue(fstatusflag)) {
      return false;
    }

    return true;
  }

  private boolean isResume(IResumeException resumeInfo) {
    boolean isResume = true;
    int back = 0;
    if (BusinessCheck.ATPCheck.getCheckCode().equals(
        resumeInfo.getBusiExceptionType())) {
      back =
          MessageDialog
              .showYesNoDlg(
                  WorkbenchEnvironment.getInstance().getWorkbench().getParent(),
                  NCLangRes.getInstance().getStrByID("4006011_0",
                      "04006011-0244")/* 销售订单可用量检查 */,
                  ((AtpNotEnoughException) resumeInfo).getMessage());

      // 可用量不足继续
      if (UIDialog.ID_YES == back) {
        isResume = true;
        this.businessCheckMap.put(BusinessCheck.ATPCheck.getCheckCode(),
            Boolean.valueOf(!isResume));
      }
      else {
        isResume = false;
      }
    }
    return isResume;
  }

}
