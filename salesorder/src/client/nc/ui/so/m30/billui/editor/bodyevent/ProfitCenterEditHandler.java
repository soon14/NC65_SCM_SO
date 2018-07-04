package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;

public class ProfitCenterEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    // 经刘达和吴婷确认，结算利润中心的编辑性不受交易类型控制 add by zhangby5 2015.8.11
  }

  public void afterEdit(CardBodyAfterEditEvent e) {

  }

}
