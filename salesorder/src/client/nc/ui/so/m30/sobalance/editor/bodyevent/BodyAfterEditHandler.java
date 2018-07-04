package nc.ui.so.m30.sobalance.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;

/**
 * 订单核销编辑后时间派发类
 * 
 * @since 6.0
 * @version 2011-7-10 上午11:57:02
 * @author 刘志伟
 */
public class BodyAfterEditHandler implements
    IAppEventHandler<CardBodyAfterEditEvent> {

  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {

    String editKey = e.getKey();
    // 收款单单据号
    if (SoBalanceBVO.VARBILLCODE.equals(editKey)) {
      GatheringEditHandler handler = new GatheringEditHandler();
      handler.afterEdit(e);
    }
  }
}
