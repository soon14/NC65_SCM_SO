package nc.ui.so.m4331.arrange.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.billref.push.PushRefreshAction;

/**
 * 发货安排刷新
 * 
 * @since 6.1
 * @version 2013-05-03 09:58:49
 * @author yixl
 */
public class DeliveryRefreshAction extends PushRefreshAction {

  private static final long serialVersionUID = -3044639532408549234L;

  @Override
  public boolean isTPAMonitor() {
    return false;
  }

  @Override
  public boolean beforeStartDoAction(ActionEvent actionEvent) throws Exception {
    return true;
  }
}
