package nc.ui.so.m38.billui.editor.bodyevent;

import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 预订单生产厂商字段编辑事件
 * 
 * @since 6.3
 * @version 2012-12-24 09:56:25
 * @author liangjm
 */
public class ProductorEditHandler {

  /**
   * 
   * 
   * @param e
   * 
   */
  public void afterEdit(CardBodyAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int editrow = e.getRow();
    // 编辑生产厂商后，设置客户物料码(V63新加)
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.setCustMaterial(editrow);
  }
}
