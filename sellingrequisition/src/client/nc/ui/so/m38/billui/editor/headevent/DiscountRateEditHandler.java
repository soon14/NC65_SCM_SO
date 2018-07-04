package nc.ui.so.m38.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m38.billui.pub.PreOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyUpdateByHead;

public class DiscountRateEditHandler {

  public void afterEdit(CardHeadTailAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 1.更新表体整单折扣
    BodyUpdateByHead updaterule = new BodyUpdateByHead(keyValue);
    updaterule.updateAllBodyValueByHead(PreOrderBVO.NDISCOUNTRATE,
        PreOrderHVO.NDISCOUNTRATE);
    // 2.数量单价金额运算
    PreOrderCalculator calculator = new PreOrderCalculator(cardPanel);
    int bodycount = keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      calculator.calculate(i, PreOrderHVO.NDISCOUNTRATE);
    }
  }

}
