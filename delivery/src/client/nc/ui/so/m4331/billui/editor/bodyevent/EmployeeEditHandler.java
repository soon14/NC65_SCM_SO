package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;


/**
 * 人员编辑事件
 * 
 * @since 6.36
 * @version 2015-2-6 下午3:04:08
 * @author 刘景
 */
public class EmployeeEditHandler {
  
  /**
   * 
   * 业务员编辑前
   * 
   * @param e
   */
  public void beforeEmployeeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getBodyStringValue(e.getRow(),DeliveryBVO.CSALEORGID);
    BillItem item = cardPanel.getBodyItem(DeliveryBVO.CEMPLOYEEID);
    UIRefPane deptrefpanel = (UIRefPane) item.getComponent();
    FilterPsndocRefUtils filter = FilterPsndocRefUtils
    .createFilterPsndocRefUtilsOfSO(deptrefpanel);
    filter.filterItemRefByOrg(pk_org);

    // 光标自动定位到部门
    String pk_dept = keyValue.getHeadStringValue(DeliveryBVO.CDEPTVID);
    filter.fixFocusByPK(pk_dept);
    e.setReturnValue(Boolean.TRUE);
  }

}
