package nc.ui.so.m38.billui.action.print;

import java.awt.Event;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.funcnode.ui.action.INCAction;
import nc.funcnode.ui.action.MenuAction;
import nc.ui.ml.NCLangRes;

public class PreOrderPrintMenuAction extends MenuAction {

  private static final long serialVersionUID = 4984040649715948701L;

  public PreOrderPrintMenuAction() {
    super();
    this.putValue(INCAction.CODE, "printGroup");
    this.putValue(Action.NAME, NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0039")/*¥Ú”°*/);
    this.putValue(Action.ACCELERATOR_KEY,
        KeyStroke.getKeyStroke('P', Event.CTRL_MASK + Event.ALT_MASK));
    this.putValue(Action.SHORT_DESCRIPTION, NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0040")/*¥Ú”°(Ctrl+Alt+P)*/);
  }
}
