package nc.ui.so.mreturnpolicy.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.uif2app.actions.batch.BatchAddLineAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.util.TimeUtils;
import nc.vo.so.mreturnpolicy.entity.ReturnPolicyVO;
import nc.vo.uif2.LoginContext;

public class ReturnPolicyAddLineAction extends BatchAddLineAction {

  private static final long serialVersionUID = 1L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
  }

  @Override
  protected void setDefaultData(Object obj) {
    super.setDefaultData(obj);

    ReturnPolicyVO rpVO = (ReturnPolicyVO) obj;
    LoginContext context = this.getModel().getContext();
    UFDate date = ClientContext.getInstance().getBusiDate();
    rpVO.setPk_group(context.getPk_group());
    rpVO.setPk_org(context.getPk_org());
    rpVO.setDenddate(TimeUtils.getEndDate(date));
    rpVO.setDstartdate(TimeUtils.getStartDate(date));
    context.getInitData();
    ShowStatusBarMsgUtil.showStatusBarMsg(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0",
            "04006006-0000")/*@res "ÐÂÔö²Ù×÷"*/, this.getModel().getContext());
  }
}
