package nc.ui.so.mbuylargess.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;

public class BodyBeforeEditHandler implements
    IAppEventHandler<CardBodyBeforeEditEvent> {

  @Override
  public void handleAppEvent(CardBodyBeforeEditEvent e) {
    e.setReturnValue(Boolean.TRUE);
    String editkey = e.getKey();
    if (BuyLargessBVO.PK_MEASDOC.equals(editkey)) {
      AstUnitEditHandler handler = new AstUnitEditHandler();
      handler.beforeEdit(e);
    }
  }

}
