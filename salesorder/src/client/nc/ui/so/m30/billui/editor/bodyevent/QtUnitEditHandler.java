package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

public class QtUnitEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyvalue = new CardKeyValue(cardPanel);
    String material =
        keyvalue.getBodyStringValue(e.getRow(), SaleOrderBVO.CMATERIALVID);
    if (!PubAppTool.isNull(material)) {
      // 物料不为空，只能参照物料对应的计量单位
      BillItem qtunititem = cardPanel.getBodyItem(SaleOrderBVO.CQTUNITID);
      FilterMeasdocRefUtils qtunitFilter =
          new FilterMeasdocRefUtils(qtunititem);
      qtunitFilter.setMaterialPk(material);
      e.setReturnValue(true);
    }
    else {
      e.setReturnValue(false);
    }

  }

  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // 1.换算率
    SOUnitChangeRateRule raterule = new SOUnitChangeRateRule(keyValue);
    for (int row : rows) {
      raterule.calcQtChangeRate(row);
    }
    // 2.数量单价金额运算
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    calculator.calculate(rows, SaleOrderBVO.VQTUNITRATE);
    // 3.询价
    SaleOrderFindPriceConfig config = new SaleOrderFindPriceConfig(cardPanel);
    FindSalePrice findprice = new FindSalePrice(cardPanel, config);
    findprice.findPriceAfterEdit(rows, SaleOrderBVO.CQTUNITID);

  }

}
