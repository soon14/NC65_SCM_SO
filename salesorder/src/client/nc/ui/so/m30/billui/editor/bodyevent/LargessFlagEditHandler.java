package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderLarPriceConfig;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.rule.LargessPirceRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 销售订单赠品编辑事件
 * 
 * @since 6.0
 * @version 2011-6-14 下午02:30:00
 * @author fengjb
 */
public class LargessFlagEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();

    UFBoolean largessflag =
        keyValue.getBodyUFBooleanValue(row, SaleOrderBVO.BLARGESSFLAG);
    // 1.编辑成赠品时，根据赠品取价规则设置单价
    if (null != largessflag && largessflag.booleanValue()) {
      SaleOrderLarPriceConfig config = new SaleOrderLarPriceConfig(cardPanel);
      LargessPirceRule larpricerule = new LargessPirceRule(cardPanel, config);
      int[] rows = new int[] {
        row
      };
      larpricerule.setLargessPrice(rows);
    }
    // 2. 计算表头合计
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

  }

  public void beforeEdit(CardBodyBeforeEditEvent e, SaleOrderBillForm billform) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();
    String cmaterialvid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
    if (PubAppTool.isNull(cmaterialvid)) {
      e.setReturnValue(false);
    }
    String srclarid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CLARGESSSRCID);
    if (!PubAppTool.isNull(srclarid)) {
      e.setReturnValue(false);
    }
    String carsubtypeid =
        keyValue.getHeadStringValue(SaleOrderHVO.CARSUBTYPEID);
    BillItem bodyitem = cardPanel.getBodyItem(SaleOrderBVO.BLARGESSFLAG);
    if (PubAppTool.isNull(carsubtypeid)) {
      bodyitem.setEdit(true);
    }
    else {
      bodyitem.setEdit(false);
    }

    SaleOrderClientContext cache = billform.getM30ClientContext();
    String pk_group = AppUiContext.getInstance().getPkGroup();
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    if (tranTypeCode == null) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

    M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
    boolean isBlrgcashflag = m30transvo.getBlrgcashflag().booleanValue();
    if (isBlrgcashflag) {
      e.setReturnValue(Boolean.FALSE);
    }

  }

}
