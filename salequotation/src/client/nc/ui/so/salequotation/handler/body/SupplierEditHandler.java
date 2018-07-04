package nc.ui.so.salequotation.handler.body;

import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 销售报价单供应商字段编辑事件
 * 
 * @since 6.3
 * @version 2012-12-20 14:40:27
 * @author liangjm
 */
public class SupplierEditHandler {

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
    // 编辑供应商后，设置客户物料码(V63新加)
    SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
    socustmar.set4310CustMaterial(editrow);
  }
}
