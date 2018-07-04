package nc.ui.so.m30.billui.editor.bodyevent;

import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;

import nc.ui.bd.ref.model.CustAddressDefaultRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;

/**
 * 收货地址编辑事件处理
 * 编辑后要影响收货国家
 * 
 * @since 6.0
 * @version 2012-2-7 下午03:52:49
 * @author 么贵敬
 */
public class ReceiveaddrEditHandler {

  /**
   * 
   * 
   * @param e
   */
  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // 2.设置国家
    SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
    countryrule.setReceiveCountry(rows);

    // 3.设置购销类型和三角贸易
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    int[] buychgrows = buyflgrule.getBuysellChgRow();
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    calculator.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
    // 4.询税
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);
    int[] taxchgrows = taxInfo.getTaxChangeRows();

    calculator.calculate(taxchgrows, SaleOrderBVO.NTAXRATE);

  }

  /**
   * 
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    BillItem item = cardPanel.getBodyItem(SOItemKey.CRECEIVEADDRID);
    UIRefPane uirefpane = (UIRefPane) item.getComponent();
    CustAddressDefaultRefModel model =
        (CustAddressDefaultRefModel) uirefpane.getRefModel();

    // 按照客户和组织过滤
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int editrow = e.getRow();
    String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String customer =
        keyValue.getBodyStringValue(editrow, SaleOrderBVO.CRECEIVECUSTID);
    model.setPk_org(pk_org);
    model.setPk_customer(customer);

  }
}
