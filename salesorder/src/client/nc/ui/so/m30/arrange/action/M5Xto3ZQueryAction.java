package nc.ui.so.m30.arrange.action;

import nc.ui.pubapp.billref.push.PushQueryAction;

/**
 * 补货\直运安排查询（调拨订单）
 * 
 * @since 6.1
 * @version 2013-05-11 09:39:22
 * @author yixl
 */
public class M5Xto3ZQueryAction extends PushQueryAction {

  private static final long serialVersionUID = -7969520206659262756L;

  @Override
  public boolean isTPAMonitor() {
    return false;
  }
}
