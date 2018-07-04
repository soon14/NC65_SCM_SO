package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;


/**
 * 部门编辑事件
 * 
 * @since 6.36
 * @version 2015-2-9 下午1:57:38
 * @author 刘景
 */
public class DeptEditHandler {
  
  /**
   * 
   * 部门编辑前
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    String pk_org = keyValue.getBodyStringValue(e.getRow(),DeliveryBVO.CSALEORGID);
    BillItem item = cardPanel.getBodyItem(DeliveryBVO.CDEPTVID);
    UIRefPane panel = (UIRefPane) item.getComponent();
    FilterDeptRefUtils filter = FilterDeptRefUtils
    .createFilterDeptRefUtilsOfSO(panel);
    filter.filterItemRefByOrg(pk_org);
  }
}
