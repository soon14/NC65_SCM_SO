/**
 *
 */
package nc.ui.so.m30.sobalance.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.RefreshAction;
import nc.vo.pub.BusinessException;

/**
 * @author gdsjw
 *
 */
@SuppressWarnings("serial")
public class SobalanceRefreshAction extends RefreshAction {

  @Override
  public void doAction(ActionEvent e) throws Exception {
    throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0000")/*@res "未实现的操作。"*/);
  }
}