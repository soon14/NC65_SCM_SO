package nc.ui.so.m38.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m38.billui.pub.PreOrderLarPriceConfig;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.rule.LargessPirceRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 预订单赠品标志编辑事件
 * 
 * @since 6.0
 * @version 2011-6-14 下午04:55:22
 * @author fengjb
 */
public class LargessFlagEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();
    String cmaterialvid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CMATERIALVID);
    if (PubAppTool.isNull(cmaterialvid)) {
      e.setReturnValue(false);
    }

  }

  public void afterEdit(CardBodyAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();

    UFBoolean largessflag =
        keyValue.getBodyUFBooleanValue(row, PreOrderBVO.BLARGESSFLAG);
    // 1.编辑成赠品时，根据赠品取价规则设置单价
    if (null != largessflag && largessflag.booleanValue()) {
      PreOrderLarPriceConfig config = new PreOrderLarPriceConfig(cardPanel);
      LargessPirceRule larpricerule = new LargessPirceRule(cardPanel, config);
      int[] rows = new int[] {
        row
      };
      larpricerule.setLargessPrice(rows);
    }

  }

}
