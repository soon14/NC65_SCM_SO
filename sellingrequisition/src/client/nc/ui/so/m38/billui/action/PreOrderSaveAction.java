package nc.ui.so.m38.billui.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.uif2.UIState;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;

public class PreOrderSaveAction extends SaveScriptAction {

  /**
   *
   */
  private static final long serialVersionUID = -5327346858774689832L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (this.getModel().getUiState() == UIState.EDIT) {
      Object obj = this.editor.getValue();
      int index = this.getModel().findBusinessData(obj);
      if (index == -1) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0010")/*@res "修改保存时，获取前台差异VO出错。"*/);
      }
    }
    super.doAction(e);
  }

  @Override
  protected Object[] processBefore(Object[] vos) {
    for (Object vo : vos) {
      PreOrderVO preordervo = (PreOrderVO) vo;
      PreOrderBVO[] bvos = preordervo.getChildrenVO();
      if (null == bvos || bvos.length == 0) {

        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0011")/*@res "表体数据为空，不允许保存。"*/);
      }
    }
    return vos;
  }
}