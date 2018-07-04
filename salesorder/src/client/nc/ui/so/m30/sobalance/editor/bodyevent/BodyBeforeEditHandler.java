package nc.ui.so.m30.sobalance.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;

/**
 * 订单核销表体编辑前时间派发类
 * 
 * @since 6.0
 * @version 2011-7-10 上午11:56:22
 * @author 刘志伟
 */
public class BodyBeforeEditHandler implements
    IAppEventHandler<CardBodyBeforeEditEvent> {

  @Override
  public void handleAppEvent(CardBodyBeforeEditEvent e) {
    // 默认为可编辑
    e.setReturnValue(Boolean.TRUE);
    String editKey = e.getKey();

    // 收款单单据号
    if (SoBalanceBVO.VARBILLCODE.equals(editKey)) {
      GatheringEditHandler handler = new GatheringEditHandler();
      handler.beforeEdit(e);
    }
    // 本次收款金额
    else if (SoBalanceBVO.NORIGTHISBALMNY.equals(editKey)) {
      ThisBalEditHandler handler = new ThisBalEditHandler();
      handler.beforeEdit(e);
    }
  }

}
