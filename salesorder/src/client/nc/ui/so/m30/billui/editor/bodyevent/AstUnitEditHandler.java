package nc.ui.so.m30.billui.editor.bodyevent;

import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
import nc.ui.so.m30.billui.rule.MatchLargessRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;

public class AstUnitEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    int row = e.getRow();
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyvalue = new CardKeyValue(cardPanel);
    String material =
        keyvalue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
    if (!PubAppTool.isNull(material)) {
      // 物料不为空，只能参照物料对应的计量单位
      BillItem astunititem = cardPanel.getBodyItem(SaleOrderBVO.CASTUNITID);
      FilterMeasdocRefUtils astunitFilter =
          new FilterMeasdocRefUtils(astunititem);
      astunitFilter.setMaterialPk(material);
      e.setReturnValue(Boolean.TRUE);
    }
    else {
      e.setReturnValue(Boolean.FALSE);
    }

  }

  public void afterEdit(CardBodyAfterEditEvent e, SaleOrderBillForm billform) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    
    String tranTypeCode =
            keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppUiContext.getInstance().getPkGroup();
    SaleOrderClientContext cache = billform.getM30ClientContext();
    M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);

    // 1.换算率
    SOUnitChangeRateRule raterule = new SOUnitChangeRateRule(keyValue);
    //性能优化：批量处理 add by zhangby5
    raterule.calcAstAndQtChangeRate(rows);
    // 2.数量单价金额运算
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    calculator.calculate(rows, SaleOrderBVO.VCHANGERATE);
    // 数量单价金额运算
    calculator.calculate(rows, SaleOrderBVO.VQTUNITRATE);
    // 3.询价
    SaleOrderFindPriceConfig config = new SaleOrderFindPriceConfig(cardPanel);
    FindSalePrice findprice = new FindSalePrice(cardPanel, config);
    findprice.findPriceAfterEdit(rows, SaleOrderBVO.CQTUNITID);
    // 4.匹配买赠
    MatchLargessRule matchlarrule = new MatchLargessRule(cardPanel, m30transvo);
    matchlarrule.matchLargess(rows);

  }

}
