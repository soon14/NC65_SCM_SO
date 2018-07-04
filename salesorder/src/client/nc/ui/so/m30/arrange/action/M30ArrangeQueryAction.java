package nc.ui.so.m30.arrange.action;

import nc.ui.pubapp.billref.push.PushQueryAction;

/**
 * 补货\直运安排查询
 * 
 * @since 6.1
 * @version 2013-05-11 09:04:05
 * @author yixl
 */
public class M30ArrangeQueryAction extends PushQueryAction {

  private static final long serialVersionUID = -4260505016977783889L;

  @Override
  public boolean isTPAMonitor() {
    return false;
  }
}
