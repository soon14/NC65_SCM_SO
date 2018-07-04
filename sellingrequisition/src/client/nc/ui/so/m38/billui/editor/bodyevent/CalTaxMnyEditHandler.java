package nc.ui.so.m38.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;

/**
 * 计税金额编辑前事件
 * 
 * @since 6.1
 * @version 2012-2-20 上午10:03:21
 */
public class CalTaxMnyEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    SOBuysellTriaRule sbtr = new SOBuysellTriaRule(keyValue);
    // 国内销售 -不允许编辑;跨国业务-允许编辑
    e.setReturnValue(sbtr.isBuysellFlagOut(e.getRow()));
  }
}
