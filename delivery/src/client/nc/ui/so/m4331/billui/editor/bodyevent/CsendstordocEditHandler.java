package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;

public class CsendstordocEditHandler {

  public void afterEdit(CardBodyAfterEditEvent e) {
   RefMoreSelectedUtils utils = new RefMoreSelectedUtils(e.getBillCardPanel());
   int[] rows = utils.refMoreSelected(e.getRow(), e.getKey(), true);

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    
    String cprofitcenterid =
        keyValue.getBodyStringValue(e.getRow(), DeliveryBVO.CSPROFITCENTERID);
    String cstordocid =
        keyValue.getBodyStringValue(e.getRow(), DeliveryBVO.CSENDSTORDOCID);
    if (PubAppTool.isNull(cprofitcenterid)
        && !PubAppTool.isNull(cstordocid)) {
    // add by zhangby5 for 利润中心取值规则
    SOProfitCenterValueRule profitRule = new SOProfitCenterValueRule(keyValue);
    profitRule.setProfitCenterValue(DeliveryBVO.CSPROFITCENTERVID,
        DeliveryBVO.CSPROFITCENTERID, rows);
    }

  }
}
