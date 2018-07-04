package nc.ui.so.m38.billui.action.assit;

import java.awt.Event;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.funcnode.ui.action.INCAction;
import nc.funcnode.ui.action.MenuAction;
import nc.ui.ml.NCLangRes;

public class PreOrderAssitMenuAction extends MenuAction {

  private static final long serialVersionUID = 7960964535550743364L;

  public PreOrderAssitMenuAction() {
    super();
    this.putValue(INCAction.CODE, "assitFunc");
    this.putValue(Action.NAME, NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0035")/*辅助功能*/);
    this.putValue(Action.ACCELERATOR_KEY,
        KeyStroke.getKeyStroke('A', Event.CTRL_MASK));
    this.putValue(Action.SHORT_DESCRIPTION, NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0036")/*辅助功能(Ctrl+A)*/);
  }
}
