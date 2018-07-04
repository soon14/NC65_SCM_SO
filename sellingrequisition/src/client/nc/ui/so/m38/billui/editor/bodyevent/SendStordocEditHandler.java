package nc.ui.so.m38.billui.editor.bodyevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.BodyEditEventUtil;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBatcheEditRule;

public class SendStordocEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    String csendstockorgid =
        keyValue.getBodyStringValue(e.getRow(), PreOrderBVO.CSENDSTOCKORGID);
    if (PubAppTool.isNull(csendstockorgid)) {
      e.setReturnValue(Boolean.FALSE);
    }
    else {
      // 参照发货库存组织设置相应的发货仓库参照
      BillItem item = cardPanel.getBodyItem(PreOrderBVO.CSENDSTORDOCID);
      FilterWareHouseRefUtils utils =
          new FilterWareHouseRefUtils((UIRefPane) item.getComponent());
      utils.filterItemRefByOrg(csendstockorgid);
      // 集团
      String pk_group = AppContext.getInstance().getPkGroup();
      utils.filterItemRefByGroup(pk_group);
      utils.filterDirectStorc();
      utils.filterWasteStorc();
      utils.filterUnableState();
      e.setReturnValue(Boolean.TRUE);
    }
  }

  public void afterEdit(CardBodyAfterEditEvent e) {

    int[] rows = BodyEditEventUtil.getInstance().getAfterEditRow(e);

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    SOBatcheEditRule batchedit = new SOBatcheEditRule(keyValue, new String[] {
      SOItemKey.CSENDSTOCKORGID
    });
    batchedit.clearRows(rows, SOItemKey.CSENDSTORDOCID);

  }
}
