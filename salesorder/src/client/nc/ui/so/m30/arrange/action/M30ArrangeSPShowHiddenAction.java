package nc.ui.so.m30.arrange.action;

import java.awt.event.ActionEvent;

import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.res.SCMActionCode;

public class M30ArrangeSPShowHiddenAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = -4523528432453130250L;

  public M30ArrangeSPShowHiddenAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_ONHANDINFO);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0000")/*@res "未实现的操作。"*/);

  }
}