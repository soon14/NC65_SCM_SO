package nc.ui.so.mbuylargess.editor.headevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;

/**
 * 买赠设置表头编辑前事件
 * 
 * @since 6.0
 * @version 2013-8-27 下午04:24:01
 * @author 董礼
 */
@SuppressWarnings("restriction")
public class HeadBeforeEditHandler implements
    IAppEventHandler<CardHeadTailBeforeEditEvent> {

  @Override
  public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
    e.setReturnValue(Boolean.TRUE);

    String editkey = e.getKey();

    // 单位
    if (BuyLargessHVO.CBUYUNITID.equals(editkey)) {
      AstUnitEditHandler handler = new AstUnitEditHandler();
      handler.beforeEdit(e);
    }
  }

}
