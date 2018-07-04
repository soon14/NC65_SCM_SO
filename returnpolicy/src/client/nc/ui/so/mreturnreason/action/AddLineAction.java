package nc.ui.so.mreturnreason.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.batch.BatchAddLineAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.so.mreturnreason.entity.ReturnReasonVO;
import nc.vo.uif2.LoginContext;

public class AddLineAction extends BatchAddLineAction {
  private static final long serialVersionUID = -8390081985820962407L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0000")/*@res "ÐÂÔö²Ù×÷"*/, this.getModel().getContext());
  }

  @Override
  protected void setDefaultData(Object obj) {
    super.setDefaultData(obj);

    LoginContext context = this.getModel().getContext();
    ((ReturnReasonVO) obj).setPk_group(context.getPk_group());
    ((ReturnReasonVO) obj).setPk_org(context.getPk_org());
  }
}