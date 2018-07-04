package nc.ui.so.m30.arrange.action;

import java.awt.event.ActionEvent;

import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.res.SCMActionCode;

public class M30ArrangeLinkQueryAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = 8909753250691811348L;

  public M30ArrangeLinkQueryAction() {
    SCMActionInitializer
        .initializeAction(this, SCMActionCode.SCM_BILLLINKQUERY);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0000")/*@res "未实现的操作。"*/);
  }

}