package nc.ui.so.m4331.billui.editor.headevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m4331.entity.DeliveryHVO;

/**
 * 运输路线编辑事件
 * 
 * @since 6.0
 * @version 2011-3-24 下午02:29:34
 * @author 祝会征
 */
public class TranSportEditHandler {
  /**
   * 运输路线编辑前处理事件
   */
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    CardKeyValue keyValue = new CardKeyValue(e.getBillCardPanel());
    String pk_org = keyValue.getHeadStringValue(DeliveryHVO.PK_ORG);
    BillItem item =
        e.getBillCardPanel().getHeadItem(DeliveryHVO.CTRANSPORTROUTEID);
    UIRefPane pane = (UIRefPane) item.getComponent();
    pane.setPk_org(pk_org);
  }

}
