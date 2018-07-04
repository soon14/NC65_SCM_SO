package nc.ui.so.mreturncondition.action;

import nc.ui.pubapp.uif2app.actions.batch.BatchAddLineAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.so.mreturncondition.entity.ReturnConditionVO;
import nc.vo.uif2.LoginContext;

public class AddLineAction extends BatchAddLineAction {
  private static final long serialVersionUID = -8390081985820962407L;

  @Override
  protected void setDefaultData(Object obj) {
    super.setDefaultData(obj);

    LoginContext context = this.getModel().getContext();
    ((ReturnConditionVO) obj).setPk_group(context.getPk_group());
    ((ReturnConditionVO) obj).setPk_org(context.getPk_org());
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0000")/*@res "ÐÂÔö²Ù×÷"*/, this.getModel().getContext());
  }
}