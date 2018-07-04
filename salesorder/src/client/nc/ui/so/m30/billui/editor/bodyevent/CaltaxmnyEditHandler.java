package nc.ui.so.m30.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;

/**
 * 计算金额编辑事件
 * 
 * @since 6.0
 * @version 2012-2-17 下午04:02:28
 * @author 么贵敬
 */
public class CaltaxmnyEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    SOBuysellTriaRule rule = new SOBuysellTriaRule(keyValue);
    e.setReturnValue(rule.isBuysellFlagOut(e.getRow()));
  }

  public void afterEdit(CardBodyAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);
    SaleOrderCalculator calculator = new SaleOrderCalculator(cardPanel);
    calculator.calculate(rows, SaleOrderBVO.NCALTAXMNY);

  }

}
