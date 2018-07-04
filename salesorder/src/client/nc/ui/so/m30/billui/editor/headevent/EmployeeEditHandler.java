package nc.ui.so.m30.billui.editor.headevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 人员编辑事件
 * 
 * @author liujingn
 *
 */
public class EmployeeEditHandler {

  public void beforeEdit(CardHeadTailBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);

    BillItem item = cardPanel.getHeadItem(SaleOrderHVO.CEMPLOYEEID);
    UIRefPane refPane = (UIRefPane) item.getComponent();
    FilterPsndocRefUtils filter = FilterPsndocRefUtils
    .createFilterPsndocRefUtilsOfSO(refPane);
    filter.filterItemRefByOrg(pk_org);

    // 光标自动定位到部门
    String pk_dept = keyValue.getHeadStringValue(SaleOrderHVO.CDEPTID);
    filter.fixFocusByPK(pk_dept);
    e.setReturnValue(Boolean.TRUE);
  }

}
