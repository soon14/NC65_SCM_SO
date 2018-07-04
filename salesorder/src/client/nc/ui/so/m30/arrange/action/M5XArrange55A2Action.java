package nc.ui.so.m30.arrange.action;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.uif2.NCAction;

/**
 * 临时按钮：跟需求确认，此功能遗留，补货安排节点的转生产订单按钮置灰。
 * 以后需要生产订单实现按钮功能,如m30arrangeA2Action
 * 
 * @since 6.0
 * @version 2011-9-16 上午10:56:06
 * @author 刘志伟
 */
public class M5XArrange55A2Action extends NCAction {

  private static final long serialVersionUID = -5639805229892958698L;

  public M5XArrange55A2Action() {
    super();
    this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0428")/*@res "生产订单"*/);
    this.setCode("m5XArrange55A2");
    this.putValue(Action.SHORT_DESCRIPTION, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0428"));
    this.enabled = false;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    //
  }

  @Override
  protected boolean isActionEnable() {
    return false;// 这个流程遗留，所以菜单项置灰
  }
}
