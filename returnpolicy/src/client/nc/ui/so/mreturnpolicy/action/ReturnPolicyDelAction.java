package nc.ui.so.mreturnpolicy.action;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.util.ManageModeUtil;
import nc.vo.pub.BusinessException;
import nc.vo.so.mreturnpolicy.entity.ReturnPolicyVO;
import nc.vo.uif2.LoginContext;

public class ReturnPolicyDelAction extends
    nc.ui.uif2.actions.batch.BatchDelLineAction {
  private static final long serialVersionUID = -3847886613233116019L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object[] vo = this.getModel().getSelectedOperaDatas();
    for (int i = 0; i < vo.length; i++) {
      ReturnPolicyVO rtnVO = (ReturnPolicyVO) vo[i];
      LoginContext context = this.getModel().getContext();
      boolean isManageable = ManageModeUtil.manageable(rtnVO, context);
      if (!isManageable) {
        String errMsg =
            ManageModeUtil.getDisManageableMsg(context.getNodeType());
        throw new BusinessException(errMsg);
      }
    }
    super.doAction(e);
  }
}
