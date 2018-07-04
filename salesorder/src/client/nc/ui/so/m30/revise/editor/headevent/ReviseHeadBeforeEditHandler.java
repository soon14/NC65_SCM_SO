package nc.ui.so.m30.revise.editor.headevent;

import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.m30.revise.rule.IsEditableCheckRule;

/**
 * 订单表头编辑前事件派发类
 * 
 * @since 6.0
 * @version 2011-6-8 上午11:19:58
 * @author fengjb
 */
public class ReviseHeadBeforeEditHandler extends
    nc.ui.so.m30.billui.editor.headevent.HeadBeforeEditHandler {

  @Override
  public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
    // 1.修订逻辑
    // --是否可以编辑
    boolean isEditable =
        new IsEditableCheckRule().check(e.getBillCardPanel(), -1, e.getKey());
    if (!isEditable) {
      e.setReturnValue(Boolean.valueOf(isEditable));
      return;
    }

    // 2.父类销售订单逻辑
    super.handleAppEvent(e);
  }

}
