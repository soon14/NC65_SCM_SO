package nc.ui.so.m38.billui.editor.headevent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class TranTypeEditHandler {

  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 设置交易类型约束条件
    String pk_org = keyValue.getHeadStringValue(PreOrderHVO.PK_ORG);
    BillItem trantypeItem = cardPanel.getHeadItem(PreOrderHVO.CTRANTYPEID);
    FilterTransTypeRefUtils filter =
        new FilterTransTypeRefUtils(trantypeItem, pk_org);
    filter.filterTranType(new String[] {
      SOBillType.PreOrder.getCode()
    });

  }

}
