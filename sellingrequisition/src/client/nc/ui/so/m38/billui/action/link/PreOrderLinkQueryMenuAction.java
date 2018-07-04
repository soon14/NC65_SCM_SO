package nc.ui.so.m38.billui.action.link;

import java.awt.Event;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.funcnode.ui.action.INCAction;
import nc.funcnode.ui.action.MenuAction;
import nc.ui.ml.NCLangRes;

public class PreOrderLinkQueryMenuAction extends MenuAction {

  private static final long serialVersionUID = 3034760443520710342L;

  public PreOrderLinkQueryMenuAction() {
    super();
    this.putValue(INCAction.CODE, "linkQueryGroup");
    this.putValue(Action.NAME, NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0037")/*联查*/);
    this.putValue(Action.ACCELERATOR_KEY,
        KeyStroke.getKeyStroke('Q', Event.CTRL_MASK + Event.ALT_MASK));
    this.putValue(Action.SHORT_DESCRIPTION, NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0038")/*联查(Ctrl+Alt+Q)*/);
  }
}
