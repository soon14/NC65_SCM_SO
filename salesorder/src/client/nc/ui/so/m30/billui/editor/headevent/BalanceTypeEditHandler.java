package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;

/**
 * 销售订单结算方式编辑事件
 * 
 * @since 6.0
 * @version 2011-6-14 上午10:47:57
 * @author fengjb
 */
public class BalanceTypeEditHandler {

  private SaleOrderBillForm billform;

  public void setBillform(SaleOrderBillForm billform) {
    this.billform = billform;
  }

  public SaleOrderBillForm getBillform() {
    return this.billform;
  }

  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    BodyValueRowRule rowrule = new BodyValueRowRule(keyValue);
    int[] rows = rowrule.getMarNotNullRows();

    String trantypecode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppUiContext.getInstance().getPkGroup();
    SaleOrderClientContext clientcontex = this.billform.getM30ClientContext();
    M30TranTypeVO trantypevo =
        clientcontex.getTransType(trantypecode, pk_group);
    SaleOrderFindPriceConfig config =
        new SaleOrderFindPriceConfig(cardPanel, trantypevo);
    FindSalePrice findprice = new FindSalePrice(cardPanel, config);
    findprice.findPriceAfterEdit(rows, SaleOrderHVO.CBALANCETYPEID);

  }

}
