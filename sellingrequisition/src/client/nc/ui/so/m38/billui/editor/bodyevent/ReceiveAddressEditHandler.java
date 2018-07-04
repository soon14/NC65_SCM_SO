package nc.ui.so.m38.billui.editor.bodyevent;

import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m38.entity.PreOrderBVO;
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
 * 预订单收货地址编辑事件
 * 
 * @since 6.0
 * @version 2012-2-7 上午10:59:21
 * @author 刘景
 */
public class ReceiveAddressEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {
    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    // 1.设置收货国家/地区
    SOCountryInfoRule conutryinforule = new SOCountryInfoRule(keyValue);
    conutryinforule.setReceiveCountry(rows);
    // 2.设置购销类型和三角贸易
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(rows);
    int[] buysellrows = buyflgrule.getBuysellChgRow();
    calculator.calculate(buysellrows, SOCalConditionRule.getCalPriceKey());
    // 3.询税
    SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
    taxInfo.setTaxInfoByBodyPos(rows);
    int[] taxchgrows = taxInfo.getTaxChangeRows();
    // 4.以税率触发数量单价金额运算
    calculator.calculate(taxchgrows, SaleOrderBVO.NTAXRATE);
  }

  /**
   * 
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    BillItem item = cardPanel.getBodyItem(PreOrderBVO.CRECEIVEADDRID);
    UIRefPane uirefpane = (UIRefPane) item.getComponent();
    CustAddressDefaultRefModel model =
        (CustAddressDefaultRefModel) uirefpane.getRefModel();

    // 按照客户和组织过滤
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int editrow = e.getRow();
    String pk_org = keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String customer =
        keyValue.getBodyStringValue(editrow, PreOrderBVO.CRECEIVECUSTID);
    model.setPk_org(pk_org);
    model.setPk_customer(customer);
  }
}
