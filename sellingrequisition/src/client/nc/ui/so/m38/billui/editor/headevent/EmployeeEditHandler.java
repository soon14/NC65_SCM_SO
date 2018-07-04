package nc.ui.so.m38.billui.editor.headevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class EmployeeEditHandler {

  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    String pk_org = keyValue.getHeadStringValue(PreOrderHVO.PK_ORG);

    BillItem item = cardPanel.getHeadItem(PreOrderHVO.CEMPLOYEEID);
    FilterPsndocRefUtils utils =
        new FilterPsndocRefUtils((UIRefPane) item.getComponent());
    utils.filterItemRefByOrg(pk_org);

    // 光标自动定位到部门
    String pk_dept = keyValue.getHeadStringValue(PreOrderHVO.CDEPTID);
    utils.fixFocusByPK(pk_dept);

    e.setReturnValue(Boolean.TRUE);

  }
}
