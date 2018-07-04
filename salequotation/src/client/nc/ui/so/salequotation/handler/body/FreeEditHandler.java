package nc.ui.so.salequotation.handler.body;

import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;

/**
 * 销售报价单自由辅助属性编辑事件
 * 
 * @since 6.3
 * @version 2012-12-20 15:07:11
 * @author liangjm
 */
public class FreeEditHandler {

  /**
   * 
   * 
   * @param e
   */
  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 编辑自由辅助属性后，设置客户物料码(V63新加)
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.set4310CustMaterial(rows);
  }

}
