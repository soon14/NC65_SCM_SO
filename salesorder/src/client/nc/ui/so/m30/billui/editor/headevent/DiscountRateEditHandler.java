package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.BodyUpdateByHead;

public class DiscountRateEditHandler {

  public void afterEdit(CardHeadTailAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 1.更新表体整单折扣
    BodyUpdateByHead updaterule = new BodyUpdateByHead(keyValue);
    updaterule.updateAllBodyValueByHead(SaleOrderBVO.NDISCOUNTRATE,
        SaleOrderHVO.NDISCOUNTRATE);
    // 2.数量单价金额运算
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    BodyValueRowRule couutil = new BodyValueRowRule(keyValue);
    int[] rows = couutil.getMarNotNullRows();
    calculator.calculate(rows, SaleOrderHVO.NDISCOUNTRATE);

    // 3.合计
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

  }

}
