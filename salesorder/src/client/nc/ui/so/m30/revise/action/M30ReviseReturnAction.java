package nc.ui.so.m30.revise.action;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JDialog;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.ml.NCLangRes;
import nc.ui.uif2.NCAction;

/**
 * 修订历史节点返回按钮
 * 
 * @since 6.0
 * @version 2011-6-10 下午01:55:10
 * @author 刘志伟
 */
public class M30ReviseReturnAction extends NCAction {

  private static final long serialVersionUID = -5738936479886531979L;

  public M30ReviseReturnAction() {
    this.setBtnName(NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0387")/*返回*/);
    this.setCode("ReviseHisReturn");
    this.putValue(Action.SHORT_DESCRIPTION, NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0387")/*返回*/);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    JDialog dialog =
        (JDialog) WorkbenchEnvironment.getInstance().findOpenedFuncletWindow(
            M30ReviseHistoryAction.HISTORY_FUNCODE);
    dialog.setVisible(false);
    dialog.dispose();
  }
}
