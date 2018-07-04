package nc.ui.so.mreturnassign.action;

import nc.ui.pubapp.uif2app.actions.batch.BatchAddLineAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.so.mreturnassign.entity.ReturnAssignVO;
import nc.vo.uif2.LoginContext;

public class ReturnAssignAddLineAction extends BatchAddLineAction {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Override
  protected void setDefaultData(Object obj) {
    super.setDefaultData(obj);

    LoginContext context = this.getModel().getContext();
    ((ReturnAssignVO) obj).setPk_group(context.getPk_group());
    ((ReturnAssignVO) obj).setPk_saleorg(context.getPk_org());
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0000")/*@res "ÐÂÔö²Ù×÷"*/, this.getModel().getContext());
  }
}