package nc.ui.so.m4331.billui.editor.headevent;

import nc.vo.so.m4331.entity.DeliveryHVO;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 人员编辑事件处理
 * 
 * @since 6.0
 * @version 2011-3-24 上午11:39:45
 * @author 祝会征
 */
public class EmployerEditHandler {

  /**
   * 
   * 业务员编辑前
   * 
   * @param e
   */
  public void beforeEmployeeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getHeadStringValue(DeliveryHVO.PK_ORG);
    BillItem item = cardPanel.getHeadItem(DeliveryHVO.CSENDEMPLOYEEID);
    UIRefPane deptrefpanel = (UIRefPane) item.getComponent();
    FilterPsndocRefUtils filter = FilterPsndocRefUtils
    .createFilterPsndocRefUtilsOfTR(deptrefpanel);
    filter.filterItemRefByOrg(pk_org);

    // 光标自动定位到部门
    String pk_dept = keyValue.getHeadStringValue(DeliveryHVO.CSENDDEPTID);
    filter.fixFocusByPK(pk_dept);
    e.setReturnValue(Boolean.TRUE);
  }
}
